package jlan.lab.Api.threadlocal;

/**
 * 该例子主要为了说明ThreadLocal的作用
 * @author kolgan
 *
 */
public class ThreadLocalUseCase implements Runnable {
	/*每个线程在ThreadLocal中都保留一个共享资源的副本*/
	static ThreadLocal<String> threadNote = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		ThreadLocalUseCase useCase = new ThreadLocalUseCase();
		threadNote.set(Thread.currentThread().getName());
		for(int i =0;i<10;i++) {
			new Thread(useCase).start();
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		set();
		System.out.println(threadNote.get());
	}
	
	private void set() {
		// TODO Auto-generated method stub
		threadNote.set(Thread.currentThread().getName());
	}

}
