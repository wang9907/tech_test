package com.summer.tech.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	// 生产者线程
	public static class Producer implements Runnable {
		private final BlockingQueue<Integer> blockingQueue;
		private volatile boolean flag;
		private Random random;

		public Producer(BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
			flag = false;
			random = new Random();

		}

		public void run() {
			while (!flag) {
				int info = random.nextInt(100);
				try {
					blockingQueue.put(info);// 生产对象并存入阻塞队列中
					System.out.println(info);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void shutDown() {
			flag = true;
		}
	}

	// 消费者线程
	public static class Consumer implements Runnable {
		private final BlockingQueue<Integer> blockingQueue;
		private volatile boolean flag;

		public Consumer(BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		public void run() {
			while (!flag) {
				int info;
				try {
					info = blockingQueue.take();// 从阻塞队列中消费产品
					System.out.println(info);
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void shutDown() {
			flag = true;
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(
				10);
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		// 创建5个生产者，5个消费者，并启动生产和消费
		for (int i = 0; i < 10; i++) {
			if (i < 5) {
				new Thread(producer, "producer" + i).start();
			} else {
				new Thread(consumer, "consumer" + (i - 5)).start();
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		producer.shutDown();// 手动停止生产
		consumer.shutDown();// 手动停止消费

	}
}