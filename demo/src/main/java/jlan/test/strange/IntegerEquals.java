package jlan.test.strange;

public class IntegerEquals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer a = new Integer(100);
//		Integer b = new Integer(100);
		int b = 100;
		System.out.println(a==b); //true,if b is Integer, a==b is false
		
		Integer c = new Integer(200);
		Integer d = 200;
		System.out.println(c==d); //false
		
	}

}
