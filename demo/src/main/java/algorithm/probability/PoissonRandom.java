package algorithm.probability;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * 产生泊松分布随机数
 * @author h15039
 *
 */
public class PoissonRandom {
	private int lamba;
	Random random ;
	public PoissonRandom(int lamba) {
		// TODO Auto-generated constructor stub
		this.lamba = lamba;
		random = new Random();
	}
	
	public int next() {
		int k = 0;
		double p = 0;
		
		do {
			k++;
			p = p-Math.log(random.nextDouble());
		} while (p<lamba);
		return k-1;
	}
	
	public static void main(String[] args) {
		PoissonRandom random = new PoissonRandom(100);
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<10000000;i++) {
			int next = random.next();
			if(map.keySet().contains(next)) {
				map.put(next, map.get(next)+1);
			}else {
				map.put(next, 1);
			}
		}
		
		List<Integer> list = new LinkedList<Integer>(map.keySet());
		Collections.sort(list);
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			int key = it.next();
			int value = map.get(key);
			System.out.println(key+"\t"+value);
		}
	}
}
