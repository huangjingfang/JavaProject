package jlan.pattern.singlton;

import java.util.Random;
/**
 * 静态内部类
 * 线程安全，不浪费资源，可以通过反射破坏
 * @author h15039
 *
 */
public class SingletonStatic {
	public int value;
	public SingletonStatic() {
		// TODO Auto-generated constructor stub
		value = new Random().nextInt(100);
	}
	private static class SingletonHolder{
		private static final SingletonStatic instance = new SingletonStatic();
	}
	
	public static SingletonStatic getSingleton() {
		return SingletonHolder.instance;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		for(int i=0;i<5;i++) {
			new Thread(()-> System.out.println(SingletonStatic.getSingleton().value)).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Now the Singleton value is:"+SingletonStatic.getSingleton().value);
		Class<SingletonStatic> clazz = SingletonStatic.class;
		SingletonStatic hungry = clazz.newInstance();
		System.out.println("Lazy's value:"+hungry.value);
	}
}
