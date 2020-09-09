package PrintNumbersUsingEvenOddThread;

import java.util.Scanner;

public class App {

	private volatile static boolean isEven = true;
	private static Object lock1 = new Object();

	public static Thread printEven(int n) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				int i = 0;
				while (i <= n) {
					synchronized (lock1) {
						while (!isEven) {
							try {
								lock1.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.print(i + " ");
						i += 2;
						isEven = false;
						lock1.notify();
					}
				}
			}

		});
		return t1;
	}

	public static Thread printOdd(int n) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				while (i <= n) {
					synchronized (lock1) {
						while (isEven) {
							try {
								lock1.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						System.out.print(i + " ");
						i += 2;
						isEven = true;
						lock1.notify();
					}
				}
			}

		});
		return t1;
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Give n to be printed seuquentially: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Thread t2 = printOdd(n);
		Thread t1 = printEven(n);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("\nCompleted..");

	}

}
