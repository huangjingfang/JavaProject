package jlan.pattern.abstractFactory;

public class Factory1 extends AbstractFactory{

	@Override
	public AbstractPdtA createPdtA() {
		// TODO Auto-generated method stub
		return new PdtA1();
	}

	@Override
	public AbstractPdtB createPdtB() {
		// TODO Auto-generated method stub
		return new PdtB1();
	}

}
