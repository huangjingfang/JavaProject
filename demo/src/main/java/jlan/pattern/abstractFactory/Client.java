package jlan.pattern.abstractFactory;

/**
 * 提供一个接口，用于创建 相关的对象家族 。
 * 
 * @author kolgan
 *
 */
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractFactory factory = new Factory1();
		AbstractPdtA a = factory.createPdtA();
		AbstractPdtB b = factory.createPdtB();
		
		System.out.println(a.getClass().getName());
		System.out.println(b.getClass().getName());
	}

}
