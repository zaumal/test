package t;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> call = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(2000);
				return 1;
			}
		};
		
		FutureTask<Integer> future = new FutureTask<Integer>(call);
		
		Thread t = new Thread(future);
		
		t.start();
		
		System.out.println(2);
		
		System.out.println(future.get());
		
		System.out.println(3);
	}
}
