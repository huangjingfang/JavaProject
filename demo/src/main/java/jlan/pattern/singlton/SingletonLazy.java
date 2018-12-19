package jlan.pattern.singlton;

import java.util.Random;

/**
 * 懒汉式 
 * 缺点：
 * 存在并发风险，可以通过反射破坏单例模式
 * @author kolgan
 *
 */
public class SingletonLazy {
	private static SingletonLazy singlton;
	public int value;
	
	public SingletonLazy() {
		// TODO Auto-generated constructor stub
		value = new Random().nextInt(100);
	}
	
	//对该方法加锁可以避免线程安全
	public static SingletonLazy getSinglton() {
		if(singlton==null) {
			singlton = new SingletonLazy();
		}
		return singlton;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		for(int i=0;i<5;i++) {
			new Thread(()-> System.out.println(SingletonLazy.getSinglton().value)).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Now the Singleton value is:"+SingletonLazy.getSinglton().value);
		Class<SingletonLazy> clazz = SingletonLazy.class;
		SingletonLazy lazy = clazz.newInstance();
		System.out.println("Lazy's value:"+lazy.value);
	}
}
