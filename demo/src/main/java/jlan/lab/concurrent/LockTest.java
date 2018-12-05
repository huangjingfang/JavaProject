package jlan.lab.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable{
	ReentrantLock lock = new ReentrantLock();
	private Integer count = 0;
	private AtomicInteger atomicCount = new AtomicInteger(0);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LockTest lockTest = new LockTest();
		for(int i=0;i<5;i++) {
			new Thread(lockTest).start();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
//		add();
//		addWithLock();
//		syncAdd();
//		addAtomic();
		
		addAndLockCount();
	}
	
	
	public void add() {
		System.out.println(Thread.currentThread().getId()+":before\t"+count);
		count++;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+":after\t"+count);
	}
	
	
	public void addWithLock() {
		lock.lock();
		add();
		lock.unlock();
	}
	
	public synchronized void syncAdd() {
		System.out.println(Thread.currentThread().getId()+":before\t"+count);
		count++;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+":after\t"+count);
	}
	
	/**
	 * 乐观锁，锁住了但是在该例子中无法表现出来
	 */
	public void addAtomic() {
		System.out.println(Thread.currentThread().getId()+":before\t"+atomicCount.get());
		atomicCount.incrementAndGet();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+":after\t"+atomicCount.get());
	}
	
	public void addAndLockCount() {
		synchronized (count) {
			System.out.println(Thread.currentThread().getId()+":before\t"+count);
			count++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getId()+":after\t"+count);
		}
	}
}
