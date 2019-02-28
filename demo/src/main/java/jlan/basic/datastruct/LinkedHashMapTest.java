package jlan.basic.datastruct;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
/**
 * LinkedHashMap 实现缓存的算法，别实现的是fifo算法和lru算法，时间复杂度均为O(1)级别
 * @author h15039
 *
 */
public class LinkedHashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 3;
		
		LinkedHashMap<String, String> fifo = new LinkedHashMap<String, String>(size,0.75f){
			private static final long serialVersionUID = 1998359480886958426L;
			
			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
				// TODO Auto-generated method stub
				return size()>size;
			}
			
		};
		fifo.put("aaa", "1");
		fifo.put("bbb", "2");
		fifo.put("aaa", "5");
		fifo.put("ccc", "3");
		fifo.put("ddd", "4");
		
		
		Iterator<Entry<String, String>> iterator = fifo.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		
		System.out.println("************************divider***************************");
		
		
		LinkedHashMap<String, String> lru = new LinkedHashMap<String, String>(size,0.75f,true){
			private static final long serialVersionUID = 1998359480886958426L;
			
			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
				// TODO Auto-generated method stub
				return size()>size;
			}
			
		};
		lru.put("aaa", "1");
		lru.put("bbb", "2");
		lru.put("aaa", "5");
		lru.put("ccc", "3");
		lru.put("ddd", "4");
		
		Iterator<Entry<String, String>> lit = lru.entrySet().iterator();
		while(lit.hasNext()) {
			Entry<String, String> entry = lit.next();
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
	}

}
