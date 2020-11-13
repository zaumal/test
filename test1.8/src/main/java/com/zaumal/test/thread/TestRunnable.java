package t;

public class TestRunnable {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(1);
			}
		});
		
		t.start();
		
		System.out.println(2);
		
		t.join();
		
		System.out.println(3);
	}
}
