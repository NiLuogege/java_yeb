package com.niluogege.server.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.niluogege.server.mapper.MailLogMapper;
import com.niluogege.server.pojo.MailConstants;
import com.niluogege.server.pojo.MailLog;
import com.niluogege.server.service.IMailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置
 */
@Configuration
public class RabbitMQConfig {


    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private IMailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);

        /**
         * 消息确认回调
         * data:消息唯一标识
         * ack：确认结果
         * cause：失败原因
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData data, boolean ack, String cause) {
                    logger.error("消息回调 ack=" + ack+" cause=$cause");
                String msgId = data.getId();
                if (ack) {
                    logger.error("消息确认-成功 msgId=" + msgId);
                    mailLogService.update(new UpdateWrapper<MailLog>().set("status", 1).eq("msgId", msgId));
                } else {
                    logger.error("消息确认-失败 msgId=" + msgId);
                }
            }
        });

        /**
         * 消息失败回调，比如router不到queue的回到
         *
         */
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message msg, int repCode, String repText, String exchange, String routingKey) {
                logger.error("消息失败回调" + msg.getBody());
            }
        });

        return rabbitTemplate;
    }

    /**
     * 创建 消息队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME,true);
    }

    /**
     * 创建交换器
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    /**
     * 队列绑定交换机
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }
}
