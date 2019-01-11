package jlan.lab.pdcandcum.producer;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BalanceProducer extends Producer implements Runnable{
	private List<Integer> list;
	private ConcurrentLinkedDeque<Integer> deque;
	private int index;
	
	
	public BalanceProducer(List<Integer> list,ConcurrentLinkedDeque<Integer> deque) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.deque = deque;
		index = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(index<list.size()) {
			if(deque.size()<1000) {
				deque.add(list.get(index));
				index++;
			}else {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		stop = true;
		System.out.println("Balencer finished");
	}
	
}
