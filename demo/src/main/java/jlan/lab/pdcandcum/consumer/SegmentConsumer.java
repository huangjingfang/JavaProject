package jlan.lab.pdcandcum.consumer;

import java.util.List;

public class SegmentConsumer implements Runnable{
	private List<Integer> segment;
	
	public SegmentConsumer(List<Integer> segment) {
		// TODO Auto-generated constructor stub
		this.segment = segment;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(Integer value:segment) {
			System.out.println(Thread.currentThread().getName()+"\tvalue:"+value);
		}
	}

}
