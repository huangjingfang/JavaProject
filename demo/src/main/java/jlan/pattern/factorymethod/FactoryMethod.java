package jlan.pattern.factorymethod;

/**
 * 定义了一个创建对象的接口，但由子类决定要实例化哪个类。工厂方法把实例化操作推迟到子类。
 * 与简单工厂模式不同的是，工厂方法模式中创建对象的方法来自子类而不是工厂类
 * @author h15039
 *
 */
public class FactoryMethod {
	public static void main(String[] args) {
		Factory factory = new Factory1();
		factory.printProduct();
	}
}
