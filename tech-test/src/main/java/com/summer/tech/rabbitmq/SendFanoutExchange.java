package com.summer.tech.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SendFanoutExchange {

	private final static String EXCHANGE_NAME = "test_exchange_fanout";
	private final static String QUEUE_NAME = "test_queue_fanout";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明exchange,fanout(扇形类型)不需要指定关联路由键
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 绑定队列到交换机
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

		// 消息内容
		String message = "Hello World!";
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
}
