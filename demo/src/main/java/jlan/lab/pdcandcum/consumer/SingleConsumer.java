package jlan.lab.pdcandcum.consumer;

import java.util.concurrent.ConcurrentLinkedDeque;

public class SingleConsumer extends Consumer implements Runnable{
	private ConcurrentLinkedDeque<Integer> deque;
	public volatile boolean stop = false;
	
	public SingleConsumer(ConcurrentLinkedDeque<Integer> deque) {
		// TODO Auto-generated constructor stub
		this.deque = deque;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while((!stop)||(!deque.isEmpty())) {
			synchronized (deque) {
				Integer next = deque.poll();
				if(next==null) {
					try {
						Thread.sleep(5);
						continue;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+"\t"+deque.size()+"\t"+next);
			}
		}
		System.out.println("Consumer "+ Thread.currentThread().getName()+" finished");
	}

}
