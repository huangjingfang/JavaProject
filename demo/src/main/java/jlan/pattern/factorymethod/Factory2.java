package jlan.pattern.factorymethod;

import jlan.pattern.simplefactory.Product;
import jlan.pattern.simplefactory.Product2;

public class Factory2 extends Factory{

	@Override
	public Product createProduct() {
		// TODO Auto-generated method stub
		return new Product2();
	}

}
