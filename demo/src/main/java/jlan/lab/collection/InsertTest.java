package jlan.lab.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsertTest {
	private static final int count = 1000000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> array = new ArrayList<String>();
		List<String> list = new LinkedList<>();
		
		long t1 = System.currentTimeMillis();
		for(int i = 0;i<count;i++) {
//			array.add("kolgan");
			array.add(0, "kolgan");
		}
		
		long t2 = System.currentTimeMillis();
		for(int i = 0;i<count;i++) {
//			list.add("kolgan");
			list.add(0, "kolgan");
		}
		
		long t3 = System.currentTimeMillis();
		
		System.out.println("Insert consumption ArrayList:"+(t2-t1)+"ms");
		System.out.println("Insert consumption LinkedList:"+(t3-t2)+"ms");
	}

}
