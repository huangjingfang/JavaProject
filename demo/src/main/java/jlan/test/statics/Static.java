package jlan.test.statics;

import java.util.PriorityQueue;
import java.util.Queue;

public class Static {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(A.a);
		System.out.println(B.a);
	}

}

class A{
//	public static final String a="world";	// world
//	public static String a="world";		//hello world
	public static final String a = new String("world"); //hello world
	static {
		System.out.println("hello ");
	}
}

class B extends A{
	static {
		System.out.println(" blabla ");
	}
}