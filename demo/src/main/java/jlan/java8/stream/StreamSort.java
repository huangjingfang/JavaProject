package jlan.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamSort {

	public static void main(String[] args) {
		List<Integer> list = generateData();
		long start = System.currentTimeMillis();
		for(int i=0;i<50;i++) {
			ArraySort(new ArrayList<>(list));
//			StmSort(new ArrayList<>(list));
//			PStmSort(new ArrayList<>(list));
		}
		long stop = System.currentTimeMillis();
		System.out.println("Average Time consumption By ArraySort:"+(stop-start)/50+"ms");
		
//		ArraySort(list);
//		StmSort(list);
	}
	
	public static void ArraySort(List<Integer> list) {
		long start = System.currentTimeMillis();
		Collections.sort(list);
		long stop = System.currentTimeMillis();
		System.out.println("Time consumption By ArraySort:"+(stop-start)+"ms");
	}
	
	public static void StmSort(List<Integer> list) {
		long start = System.currentTimeMillis();
		
		list.stream().sorted().collect(Collectors.toList());
//		List<Integer> l = list.parallelStream().sorted().collect(Collectors.toList());
		
		long stop = System.currentTimeMillis();
		System.out.println("Time consumption By StreamSort:"+(stop-start)+"ms");
	}
	
	public static void PStmSort(List<Integer> list) {
		long start = System.currentTimeMillis();
		
		list.parallelStream().sorted().collect(Collectors.toList());
//		List<Integer> l = list.parallelStream().sorted().collect(Collectors.toList());
		
		long stop = System.currentTimeMillis();
		System.out.println("Time consumption By StreamSort:"+(stop-start)+"ms");
	}
	
	public static List<Integer> generateData(){
		int size = 10000000;
		List<Integer> list = new ArrayList<>(size);
		Random rand = new Random();
		for(int i=0;i<size;i++) {
			list.add(rand.nextInt(size));
		}
		return list;
	}
}
