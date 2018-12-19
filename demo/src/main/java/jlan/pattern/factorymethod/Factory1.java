package jlan.pattern.factorymethod;

import jlan.pattern.simplefactory.Product;
import jlan.pattern.simplefactory.Product1;

public class Factory1 extends Factory{

	@Override
	public Product createProduct() {
		// TODO Auto-generated method stub
		return new Product1();
	}

}
