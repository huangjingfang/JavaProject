package jlan.java8.functionref;

import java.util.Arrays;
import java.util.List;


/**
 *  Suppiler 接口
 * @author h15039
 *
 */
public class FunctionReferenceTest {
	
	
	public static void main(String[] args) {
		FunctionReferenceTest test = new FunctionReferenceTest();
		test.function();
	}
	
	public void function() {
		List<String> list = Arrays.asList("a","b","c");
		list.forEach(System.out::println);
		list.forEach(str -> System.out.println(str));
	}
}
