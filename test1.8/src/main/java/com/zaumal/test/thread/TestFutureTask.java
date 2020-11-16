package com.zaumal.test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Future 缺点：
 * Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，我们无法得知Future什么时候完成。
 * 要么使用阻塞，在future.get()的地方等待future返回的结果，这时又变成同步操作。
 * 要么使用isDone()轮询地判断Future是否完成，这样会耗费CPU的资源。
 */
public class TestFutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		testRunnable();
		
		testExecutors();
		
//		testCallable();// �� TestCallable
	}
	
	public static void testExecutors() throws InterruptedException, ExecutionException {
		ExecutorService exe = Executors.newCachedThreadPool();
		Future<String> future = exe.submit(() -> {
			Thread.sleep(1000*2);
			return "1";
		});
		
		System.out.println("2");
		
		System.out.println(future.get());
		
		System.out.println("3");
	}
	
	public static void testRunnable() throws InterruptedException, ExecutionException {
		class Result{
			int a = 0;
		}
		
		Result result = new Result();
		
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				result.a = 1;
			}
		};
		
		FutureTask<Result> task = new FutureTask<Result>(run, result);
		
		Thread t = new Thread(task);
		
		t.start();
		
		System.out.println(2);
		
		System.out.println(task.get().a);
		
		System.out.println(3);
	}
}
