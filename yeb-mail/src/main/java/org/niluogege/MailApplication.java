package org.niluogege;

import com.niluogege.server.pojo.MailConstants;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 启动类
 *
 * exclude = {DataSourceAutoConfiguration.class} 表示 该项目 不需要使用数据库，
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class,args);
	}

	/**
	 * 创建 消息队列
	 * @return
	 */
	@Bean
	public Queue queue(){
		return new Queue(MailConstants.MAIL_QUEUE_NAME);
	}

}