package com.niluogege.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.niluogege.server.pojo.Employee;
import com.niluogege.server.pojo.MailConstants;
import com.niluogege.server.pojo.MailLog;
import com.niluogege.server.service.IEmployeeService;
import com.niluogege.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class MailTesk {

    @Autowired
    private IMailLogService mailLogService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 十秒执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask() {
        //获取到所有 没有被发送的邮件
        List<MailLog> needMails = mailLogService.list(
                new QueryWrapper<MailLog>()
                        .eq("status", "0")
                        .lt("tryTime", LocalDateTime.now()));//筛选重试时间，为了避免重复发送消息
        for (MailLog needMail : needMails) {
            if (3 <= needMail.getCount()) {//如果重试次数超过3次,则标记为 邮件发送失败
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 2).eq("msgId", needMail.getMsgId()));
            } else {// 没有超过三次就再次发送右键
                //更新数据
                mailLogService.update(new UpdateWrapper<MailLog>().set("count", needMail.getCount() + 1)
                        .set("updateTime", LocalDateTime.now())
                        .set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT))
                        .eq("msgId", needMail.getMsgId()));

                //找到用户数据
                Employee employee = employeeService.getEmployees(needMail.getEid()).get(0);

                //重新发送消息
                rabbitTemplate.convertAndSend(
                        MailConstants.MAIL_EXCHANGE_NAME,
                        MailConstants.MAIL_ROUTING_KEY_NAME,
                        employee,
                        new CorrelationData(needMail.getMsgId()));

            }


        }
    }

}
