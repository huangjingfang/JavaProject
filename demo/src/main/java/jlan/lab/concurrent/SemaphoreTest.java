package jlan.lab.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {
	Object lock = new Object();
	static Semaphore startSemaphore = new Semaphore(3);
	static Semaphore stopSemaphore = new Semaphore(0);
	ThreadLocal<Semaphore> threadLocal = new ThreadLocal<Semaphore>();
	
	public void run() {
		// TODO Auto-generated method stub
		synchronized (lock) {
			for(int i=0;i<10;i++) {
				try {
					startSemaphore.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+":\t"+i);
				try {
					lock.wait();
					System.out.println("notified");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semaphore semaphore = new Semaphore(1);
		System.out.println(semaphore.availablePermits());
		semaphore.release(10);
		System.out.println(semaphore.availablePermits());
		semaphore.drainPermits();
		System.out.println(semaphore.availablePermits());
	/*	try {
			semaphore.acquire(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(semaphore.availablePermits());
		
		SemaphoreTest test = new SemaphoreTest();
		for(int i =0;i<3;i++) {
			new Thread(test).start();
		}
		
		new Thread(test.new Manager()).start();
	}

	
	class Manager implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			synchronized (lock) {
				for(int i=0;i<10;i++) {
					while(startSemaphore.availablePermits()!=0) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("Manager:\t"+i);
					startSemaphore.release(3);
					lock.notifyAll();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}
