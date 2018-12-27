package jlan.jvm;

public class PrintGc {
	private static final int _1MB = 1024*1024;

	//b4如果在b1之前分配内存整个过程会触发一次gc，但是如果在最后一行分配则直接分配到老年代，不会发生gc
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] b1,b2,b3,b4;
		//b4 = new byte[4 * _1MB];
		b1 = new byte[2 * _1MB];
		b2 = new byte[2 * _1MB];
		b3 = new byte[2 * _1MB];
		//byte[] b5 = new byte[3 * _1MB];
		
		b4 = new byte[4 * _1MB];
	}

}
