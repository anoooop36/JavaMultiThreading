package ProducerConsumerLowLevel;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	private Random random = new Random();
	
	public void produce() throws InterruptedException {
		int value = 0;
		while(true) {
			Thread.sleep(random.nextInt(1000));
			synchronized(lock) {
				while(list.size() == LIMIT) {
					lock.wait();
				}
				System.out.println("producer: "+Thread.currentThread().getName());
				list.add(value++);
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized(lock) {
				System.out.print("List size is: "+ list.size());
				while(list.size() == 0)
					lock.wait();
				int value = list.removeFirst();
				System.out.println(" value is: "+ value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}
 