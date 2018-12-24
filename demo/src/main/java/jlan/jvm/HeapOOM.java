package jlan.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		while(true) {
			list.add(new String("asss"));
		}
	}

}
