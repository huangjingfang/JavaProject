package jlan.pattern.singlton;

import java.util.Random;

/**
 *  饿汉式 
 * 缺点：
 * 不存在并发风险，单是可能浪费资源。可以通过反射破坏单例模式
 * @author kolgan
 *
 */
public class SingletonHungry {
	private static SingletonHungry singlton = new SingletonHungry();
	public int value;
	
	public SingletonHungry() {
		// TODO Auto-generated constructor stub
		value = new Random().nextInt(100);
	}
	
	public static SingletonHungry getSinglton() {
		return singlton;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		for(int i=0;i<5;i++) {
			new Thread(()-> System.out.println(SingletonHungry.getSinglton().value)).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Now the Singleton value is:"+SingletonHungry.getSinglton().value);
		Class<SingletonHungry> clazz = SingletonHungry.class;
		SingletonHungry hungry = clazz.newInstance();
		System.out.println("Lazy's value:"+hungry.value);
	}
}
