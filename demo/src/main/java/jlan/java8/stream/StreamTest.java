package jlan.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		for(int i=0;i<10000;i++) {
			list.add(String.valueOf(i));
		}
		Stream<String> stream = list.stream();
		stream.forEach((x)->System.out.println(x.substring(1)));
		
		//构建Stream
		Stream<String> stm = Stream.of("a","b","c");
		stm.forEach(System.out::println);
	}

}
