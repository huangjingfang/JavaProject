package jlan.lab.pdcandcum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import jlan.lab.pdcandcum.consumer.SegmentConsumer;
import jlan.lab.pdcandcum.consumer.SingleConsumer;
import jlan.lab.pdcandcum.producer.BalanceProducer;

public class ProducerAndConsumer {
	
	public static void main(String[] args) {
		pacBySegment();
	}
	
	public static void pacByDeque() {
		// TODO Auto-generated method stub
//		PoissonRandom random = new PoissonRandom(500);
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<1000;i++) {
			list.add(random.nextInt(500));
		}
		ConcurrentLinkedDeque<Integer> deque = new ConcurrentLinkedDeque<>();
		Thread producer = new Thread(new BalanceProducer(list, deque));
		producer.start();
		SingleConsumer[] ts = new SingleConsumer[3];
		for(int i=0;i<3;i++) {
			ts[i] = new SingleConsumer(deque);
			new Thread(ts[i]).start();
		}
		try {
			producer.join();
			for(int i=0;i<3;i++) {
				ts[i].stop = true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void pacBySegment() {
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<5000;i++) {
			list.add(random.nextInt(500));
		}
		ExecutorService service = Executors.newFixedThreadPool(5);
		for(int i=0;i<list.size();i+=500) {
			service.execute(new SegmentConsumer(list.subList(i, i+500)));
		}
		service.shutdown();
		try {
			service.awaitTermination(2, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
