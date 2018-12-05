package jlan.lab.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest implements Runnable {
	int count = 0;
	AtomicInteger atomicCount = new AtomicInteger(0);
	Set<Integer> set = new HashSet<Integer>(50000);
	
	public static void main(String[] args) {
		AtomicTest test = new AtomicTest();
		for(int i=0;i<5;i++){
			new Thread(test).start();
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10000;i++) {
//			int value = add();
			int value = addAtomic();
			if(set.contains(value)) {
				System.out.println(value+" duplicated");
			}else {
				set.add(value);
			}
//			System.out.println(value);
		}
	}

	public int add() {
		count = count+1;
		return count;
//		return count++;
	}
	
	public int addAtomic() {
		int value = atomicCount.incrementAndGet();
		return value;
	}
}
