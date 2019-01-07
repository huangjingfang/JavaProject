package jlan.pattern.adapter;

public class Main {
	public static void main(String[] args) {
		Phone iphonex = new IPhone();
		Telephone adapter = new PhoneAdapter(iphonex);
		adapter.dial();
	}
}
