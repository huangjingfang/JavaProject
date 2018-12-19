package jlan.pattern.simplefactory;
/**
 * 简单工厂模式
 * 
 * 简单工厂把实例化的操作单独放到一个类中，这个类就成为简单工厂类，让简单工厂类来决定应该用哪个具体子类来实例化。
 * 实现客户类到子类的解耦
 * @author kolgan
 *
 */
public class SimpleFactory {
	public Product createProduct(int type) {
		switch (type) {
		case 1:
			return new Product1();
		case 2:
			return new Product2();
		default:
			return null;
		}
	}
	
	public static void main(String[] args) {
		SimpleFactory factory = new SimpleFactory();
		Product p1 = factory.createProduct(1);
		System.out.println(p1.getClass().getName());
	}
}
