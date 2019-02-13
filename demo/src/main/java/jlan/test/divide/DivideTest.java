package jlan.test.divide;

import utils.Utils;

public class DivideTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start = Utils.getCurrentTime();
		for(long i=0;i<1000000000l;i++) {
			int a = 4096/3;
		}
		long stop = Utils.getCurrentTime();
		
		System.out.println("Time consumption:"+(stop-start)+"ms");
		
		
		start = Utils.getCurrentTime();
		for(long i=0;i<1000000000l;i++) {
			int a = 4096/2;
		}
		stop = Utils.getCurrentTime();
		
		System.out.println("Time consumption:"+(stop-start)+"ms");
	}
	
	

}
