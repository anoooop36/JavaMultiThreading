package Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Connection.getInstance().connect();
		ExecutorService executor = Executors.newCachedThreadPool();
		
		
		for(int i=0;i<200;i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					try {
						Connection.getInstance().doConnect();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
	}

}
