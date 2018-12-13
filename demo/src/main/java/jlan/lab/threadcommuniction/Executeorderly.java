package jlan.lab.threadcommuniction;

import java.util.concurrent.Semaphore;

/**
 * 两个线程按照顺序执行，一个线程执行完之后另一个再执行
 * @author kolgan
 *
 */
public class Executeorderly implements Runnable{
	static Semaphore semaphore = new Semaphore(1);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Executeorderly executeorderly = new Executeorderly();
		Thread a = new Thread(executeorderly);
		Thread b = new Thread(executeorderly);
		a.start();
		b.start();
		try {
			a.join();
			b.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("************************");
		byJoin();
	}

	public void run() {
		// TODO Auto-generated method stub
//		print();
//		printSynchronized();
		printSemaphore();
	}
	
	/**
	 * 无任何操作，两线程不通信
	 */
	public void print() {
		for(int i=0;i<5;i++) {
			System.out.println(Thread.currentThread().getName()+":\t"+i);
		}
	}

	/**
	 * 针对同一个任务，可以对该任务加锁实现线程先后执行
	 */
	public synchronized void printSynchronized() {
		for(int i=0;i<5;i++) {
			System.out.println(Thread.currentThread().getName()+":\t"+i);
		}
	}
	
	/**
	 * 通过信号量可以实现不同任务之间的通信
	 * 该方式与加锁
	 */
	public void printSemaphore() {
		try {
			semaphore.acquire();
			for(int i=0;i<5;i++) {
				System.out.println(Thread.currentThread().getName()+":\t"+i);
			}
			semaphore.release();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void byJoin() {
		final Thread a = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<5;i++) {
					System.out.println(Thread.currentThread().getName()+":\t"+i);
				}
			}
		});
		
		Thread b = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					a.join();
					for(int i=0;i<5;i++) {
						System.out.println(Thread.currentThread().getName()+":\t"+i);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		a.start();
		b.start();
	}
}
