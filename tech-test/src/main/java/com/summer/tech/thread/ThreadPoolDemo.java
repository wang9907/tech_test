package com.summer.tech.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {
	final BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
	//final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
	final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 60, 30,
			TimeUnit.SECONDS, queue);
	final AtomicInteger completeTask = new AtomicInteger();
	final AtomicInteger rejectedTask = new AtomicInteger();
	static long beginTime;
	final int count = 1000;

	public static void main(String[] args) {
		beginTime = System.currentTimeMillis();
		ThreadPoolDemo demo = new ThreadPoolDemo();
		demo.start();

	}

	public void start() {
		CountDownLatch latch = new CountDownLatch(count);
		CyclicBarrier barrier = new CyclicBarrier(count);

		for (int i = 0; i < count; i++) {
			new Thread(new TestThread(latch, barrier)).start();
		}
		try {
			latch.await();
			executor.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	class TestThread implements Runnable {
		private CountDownLatch latch;
		private CyclicBarrier barrier;

		public TestThread(CountDownLatch latch, CyclicBarrier barrier) {
			super();
			this.latch = latch;
			this.barrier = barrier;
		}

		public void run() {
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			try {
				executor.execute(new Task(latch));
			} catch (RejectedExecutionException exception) {
				latch.countDown();
				System.out.println("被拒绝的任务数：" + rejectedTask.incrementAndGet());
			}

		}

	}

	class Task implements Runnable {
		private CountDownLatch latch;

		public Task(CountDownLatch latch) {
			super();
			this.latch = latch;
		}

		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("执行的任务数为：" + completeTask.incrementAndGet());
			System.out.println("任务耗时："
					+ (System.currentTimeMillis() - beginTime));
			latch.countDown();
		}

	}
}
