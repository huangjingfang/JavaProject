package jlan.jvm;

public class StackOverFlow {
	public static int count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StackOverFlow().test();
	}
	
	public void test() {
		count++;
		test();
	}

}
