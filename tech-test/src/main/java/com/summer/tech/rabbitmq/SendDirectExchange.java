package com.summer.tech.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SendDirectExchange {

	private final static String EXCHANGE_NAME = "test_exchange_direct";
	private final static String QUEUE_NAME = "test_queue_direct";
	private final static String ROUTE_KEY = "route_direct";

	public static void main(String[] argv) throws Exception {
		// 获取到连接以及mq通道
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		// 声明exchange
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 绑定队列到交换机
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTE_KEY);

		// 消息内容
		String message = "Hello World!";
		channel.basicPublish(EXCHANGE_NAME, ROUTE_KEY, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
}
