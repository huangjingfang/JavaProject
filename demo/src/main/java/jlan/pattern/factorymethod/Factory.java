package jlan.pattern.factorymethod;

import jlan.pattern.simplefactory.Product;

public abstract class Factory {
	public abstract Product createProduct();
	
	public void printProduct() {
		Product product = createProduct();
		System.out.println(product.getClass().getName());
	}
}
