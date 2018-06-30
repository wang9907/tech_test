package com.summer.tech.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
	private static int id = 0;
	private static AtomicInteger atomicId = new AtomicInteger();
	private static CountDownLatch latch = null;

	public static synchronized int getNextId() {
		return ++id;
	}

	public static int getNextIdWithAtomic() {
		return atomicId.incrementAndGet();
	}

	public static void main(String[] args) throws InterruptedException {
		latch = new CountDownLatch(50);
		long begin = System.nanoTime();
		for (int i = 0; i < 50; i++) {
			new Thread(new Task(false)).start();
		}
		latch.await();
		System.out.println("synchrouzy style consume time "
				+ (System.nanoTime() - begin));

		begin = System.nanoTime();
		for (int i = 0; i < 50; i++) {
			new Thread(new Task(true)).start();
		}
		latch.await();
		System.out.println("CAS style consume time "
				+ (System.nanoTime() - begin));

	}

	static class Task implements Runnable {
		private boolean isAtomic;

		public Task(boolean isAtomic) {
			this.isAtomic = isAtomic;
		}

		public void run() {
			for (int i = 0; i < 1000; i++) {
				if (isAtomic) {
					getNextIdWithAtomic();
				} else {
					getNextId();
				}
			}
			latch.countDown();
		}

	}
}
