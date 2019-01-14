package jlan.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(5);
		for(int i=0;i<100;i++) {
			service.submit(()-> System.out.println(Thread.currentThread().getName()));
		}
		service.shutdown();
	}

}
