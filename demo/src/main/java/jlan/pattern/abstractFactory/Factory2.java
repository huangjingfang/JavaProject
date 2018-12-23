package jlan.pattern.abstractFactory;

public class Factory2 extends AbstractFactory{

	@Override
	public AbstractPdtA createPdtA() {
		// TODO Auto-generated method stub
		return new PdtA2();
	}

	@Override
	public AbstractPdtB createPdtB() {
		// TODO Auto-generated method stub
		return new PdtB2();
	}

}
