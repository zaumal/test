package com.zaumal.test.lock;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestConditon {
	public static void main(String[] args) {
		Queue<String> queue = new ArrayBlockingQueue<>(10);
		ReentrantLock lock = new ReentrantLock();
		Condition produceCondition = lock.newCondition();
		Condition consumeCondition = lock.newCondition();
		
		new Producer(lock,queue,produceCondition,consumeCondition).start();
		new Producer(lock,queue,produceCondition,consumeCondition).start();
		new Producer(lock,queue,produceCondition,consumeCondition).start();
		new Producer(lock,queue,produceCondition,consumeCondition).start();
		
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
		new Consumer(lock,queue,produceCondition,consumeCondition).start();
	}
	
	public static void await(Lock lock,Condition condition) {
		lock.lock();
		try {
			condition.await();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public static void signalAll(Lock lock,Condition condition) {
		lock.lock();
		try {
			condition.signalAll();
		}finally {
			lock.unlock();
		}
	}
}


class Producer extends Thread{
	private static volatile int i = 0;
	private Lock lock;
	private Queue<String> queue;
	private Condition produceCondition;
	private Condition consumeCondition;
	
	public Producer(Lock lock,Queue<String> queue,Condition produceCondition,Condition consumeCondition) {
		this.lock = lock;
		this.queue = queue;
		this.produceCondition = produceCondition;
		this.consumeCondition = consumeCondition;
	}
	
	@Override
	public void run() {
		while(true) {
			if(queue.size() == 10) {
				System.out.println(Thread.currentThread().getName() + " 队列已满");
				TestConditon.signalAll(lock, consumeCondition);
				TestConditon.await(lock, produceCondition);
			}
			try {
				Thread.sleep(new Random().nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this.getClass()) {
				queue.offer(String.valueOf(i++));
				System.out.println(Thread.currentThread().getName() + " 生产： " + i);
			}
			TestConditon.signalAll(lock, consumeCondition);
		}
	}
}

class Consumer extends Thread{
	private Lock lock;
	private Queue<String> queue;
	private Condition produceCondition;
	private Condition consumeCondition;
	
	public Consumer(Lock lock,Queue<String> queue,Condition produceCondition,Condition consumeCondition) {
		this.lock = lock;
		this.queue = queue;
		this.produceCondition = produceCondition;
		this.consumeCondition = consumeCondition;
	}
	
	@Override
	public void run() {
		while(true) {
			if(queue.isEmpty()) {
				System.out.println(Thread.currentThread().getName() + " 队列已空");
				TestConditon.signalAll(lock, produceCondition);
				TestConditon.await(lock, consumeCondition);
			}
			try {
				Thread.sleep(new Random().nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 消费： " + queue.poll());
			TestConditon.signalAll(lock, produceCondition);
		}
	}
}
