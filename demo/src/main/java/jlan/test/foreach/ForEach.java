package jlan.test.foreach;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

public class ForEach {
	public static void main(String[] args) {
		Thread[] ts = new Thread[5];
		for(int i=0;i<3;i++) {
			ts[i] = new Thread(()->System.out.println(Thread.currentThread().getName()));
		}
		int count=0;
		for(Thread t:ts) {
			System.out.println(++count);
			if(t != null) {
				t.start();
			}
		}
		
		List<Integer> list = new LinkedList<>();
		Random rand = new Random();
		for(int i=0;i<17;i++) {
			list.add(rand.nextInt(50));
		}
		
		System.out.println(new Gson().toJson(list.subList(10, list.size())));
		
		
		System.out.println("-------------------");
		
		int total = 6652;
		
		for(int i=0;i<total;i+=2500) {
			for(int j=0,k=i;j<5&&k<total;j++,k+=500) {
				int end;
				if((k+500)>=total) {
					end = total;
				}else {
					end = k+500;
				}
				System.out.println("Thread "+j+" \tinterval: "+k+"\t"+end);
			}
		}
		
	}
}
