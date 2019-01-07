package jlan.pattern.adapter;

public class PhoneAdapter implements Telephone {
	private Phone phone;
	
	public PhoneAdapter(Phone phone) {
		// TODO Auto-generated constructor stub
		this.phone = phone;
	}
	@Override
	public void dial() {
		// TODO Auto-generated method stub
		phone.call();
	}

}
