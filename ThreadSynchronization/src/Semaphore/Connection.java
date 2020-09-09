package Semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();
	private int connections = 0;
	Semaphore sem = new Semaphore(10);
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void doConnect() throws InterruptedException {
		try {
			connect();
		}
		finally {
			sem.release();
		}
	}
	
	public void connect() throws InterruptedException {
		sem.acquire();
		synchronized(this) {
			connections++;
			System.out.println("Connections: "+ connections);
		}
		
		Thread.sleep(2000);
		
		synchronized(this) {
			connections--;
		}
	}
}
