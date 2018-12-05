package jlan.lab.concurrent;

public class ObjectLock implements Runnable{
	Integer A = 0;

	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++) {
			if(i%2==0) {
				System.out.println(Thread.currentThread().getId()+":waitTest()\t"+waitTest());
			}else {
				System.out.println(Thread.currentThread().getId()+":invokeTest()\t"+invokeTest());
			}
		}
	}
	
	public static void main(String[] args) {
		ObjectLock lock = new ObjectLock();
		for(int i=0;i<2;i++) {
			new Thread(lock).start();
		}
	}

	public synchronized int waitTest() {
		while(A++ == 10) {
			try {
				A.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return A;
	}
	
	public synchronized int invokeTest() {
		while(A++ == 15) {
			A.notifyAll();
		}
		return A;
	}
}
