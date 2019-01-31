package jlan.concurrent.lock.object;

/**
 * wait()的可重入设计
 * @author h15039
 *
 */
public class ObjectLockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LockHolder holder = new LockHolder();
		new Thread(new Task1(holder)).start();
		new Thread(new Task2(holder)).start();
	}

	static class Task1 implements Runnable{
		LockHolder holder;
		
		public Task1(LockHolder holder) {
			// TODO Auto-generated constructor stub
			this.holder = holder;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("***ready to get lock1");
			synchronized (holder.lock1) {
				new Thread(new Task3(holder)).start();
				System.out.println("***get lock ->"+Thread.currentThread().getName());
				try {
					System.out.println("***Lock1 ready to wait");
					holder.lock1.wait();
					System.out.println("***Lock1 get,ready to Exit");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	static class Task2 implements Runnable{
		LockHolder holder;
		
		public Task2(LockHolder holder) {
			// TODO Auto-generated constructor stub
			this.holder = holder;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("---ready to get lock1");
			synchronized (holder.lock1) {
				System.out.println("---get lock ->"+Thread.currentThread().getName());
				System.out.println("---Lock1 ready to notify");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				holder.lock1.notify();
				System.out.println("---Lock1 notified,ready to Exit,Lock1 release");
			}
		}
	}
	
	
	static class Task3 implements Runnable{
		LockHolder holder;
		
		public Task3(LockHolder holder) {
			// TODO Auto-generated constructor stub
			this.holder = holder;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (holder.lock1) {
				System.out.println("+++Task3 get lock ->"+Thread.currentThread().getName());
				System.out.println("+++Lock1 ready to notify");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				holder.lock1.notify();
				System.out.println("+++Lock1 notified,ready to Exit,Lock1 release");
			}
		}
	}
}
