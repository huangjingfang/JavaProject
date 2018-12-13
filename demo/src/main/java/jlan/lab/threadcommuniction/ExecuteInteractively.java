package jlan.lab.threadcommuniction;

/**
 * 两个线程交互执行
 * @author kolgan
 *
 */
public class ExecuteInteractively implements Runnable {
	static Object lock = new Object();
	
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				synchronized (lock) {
					for(int i=0;i<5;i++) {
						System.out.println(Thread.currentThread().getName()+":\t"+i);
						try {
							lock.notify();
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					lock.notify();
				}
			}
		}).start();
		
		
		new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				synchronized (lock) {
					for(int i=0;i<5;i++) {
						System.out.println(Thread.currentThread().getName()+":\t"+i);
						lock.notify();
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					lock.notify();
				}
			}
		}).start();
	}
	
	public void run() {
		// TODO Auto-generated method stub
	}

}
