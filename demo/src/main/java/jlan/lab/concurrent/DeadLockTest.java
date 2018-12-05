package jlan.lab.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class DeadLockTest implements Runnable {

	Integer A = 0;
	AtomicInteger atomic = new AtomicInteger(0);
	
	public static void main(String[] args) {
		DeadLockTest test = new DeadLockTest();
		for(int i=0;i<3;i++) {
			new Thread(test).start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DeadLock Happened");
	}
	
	
	public void run() {
		// TODO Auto-generated method stub
		if(atomic.incrementAndGet()%2==0) {
			methodA();
		}else {
			methodB();
		}
	}
	

	public void methodA() {
		synchronized (A) {
			System.out.println(Thread.currentThread().getId()+" get Lock on A");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("try to get lock on B");	
			synchronized (AnotherResource.class) {
				System.out.println("get Lock on B");
			}
		}
	}
	
	public void methodB() {
		synchronized (AnotherResource.class) {
			System.out.println(Thread.currentThread().getId()+" get Lock on B");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("try to get lock on A");
			synchronized (A) {
				System.out.println("get Lock on A");
			}
		}
	}
}
