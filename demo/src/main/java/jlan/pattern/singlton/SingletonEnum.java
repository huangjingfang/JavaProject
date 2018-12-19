package jlan.pattern.singlton;

import java.util.Random;

/**
 * 枚举
 * 线程安全，反射无法破坏
 * @author kolgan
 *
 */
public enum SingletonEnum {
	INTSANCE;
	
	public int value;
	
	private SingletonEnum() {
		// TODO Auto-generated constructor stub
		value = new Random().nextInt(100);
	}
	
	public static SingletonEnum getSingleton() {
		return INTSANCE;
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		for(int i=0;i<5;i++) {
			new Thread(()-> System.out.println(SingletonEnum.getSingleton().value)).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Now the Singleton value is:"+SingletonEnum.getSingleton().value);
		Class<SingletonEnum> clazz = SingletonEnum.class;
		SingletonEnum[] en = clazz.getEnumConstants();
		System.out.println("Lazy's value:"+en.length);
	}
}
