package tools.elasticjob.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.google.gson.Gson;

/**
 * 基于邻接表的图表示
 * @author kolgan
 *
 * @param <T>
 */
public class LinkedGraph<T> {
	private HashMap<T, List<T>> table;
	
	public LinkedGraph() {
		// TODO Auto-generated constructor stub
		table = new HashMap<T, List<T>>();
	}
	
	public void add(T value) throws Exception {
		if(table.containsKey(value)) {
			throw new Exception("节点已存在！");
		}
		table.put(value, new LinkedList<T>());
	}
	
	public void connect(T node1,T node2) throws Exception {
		if(table.containsKey(node1)&&table.containsKey(node2)) {
			List<T> list = table.get(node1);
			list.add(node2);
		}else {
			throw new Exception("节点不存在");
		}
	}
	
	public List<T> getarent(T node){
		List<T> list = new ArrayList<T>();
		Iterator<T> iterator = table.keySet().iterator();
		while(iterator.hasNext()) {
			T key = iterator.next();
			List<T> value = table.get(key);
			if(value.contains(node)) {
				list.add(key);
			}
		}
		return list;
	}
	
	public List<T> getChild(T node){
		return table.get(node);
	}
	
	public Set<T> traceParent(T node){
		Set<T> ancesters = new HashSet<T>();
		Stack<T> stack = new Stack<T>();
		List<T> list = getarent(node);
		for(T t:list) {
			ancesters.add(t);
			stack.push(t);
		}
		while(!stack.isEmpty()) {
			T t = stack.pop();
			List<T> parents = getarent(t);
			if(parents.size()==0) {
				continue;
			}else {
				for(T p:parents) {
					ancesters.add(p);
					stack.push(p);
				}
			}
		}
		return ancesters;
	}
	
	
	private boolean checkHasCircle() {
		return false;
	}
	public static void main(String[] args) throws Exception {
		LinkedGraph<String> graph = new LinkedGraph<String>();
		graph.add("node0");
		graph.add("node1");
		graph.add("node2");
		graph.add("node3");
		graph.add("node4");
		graph.add("node5");
		graph.add("node6");
		graph.connect("node0", "node1");
		graph.connect("node0", "node2");
		graph.connect("node0", "node3");
		graph.connect("node0", "node4");
		graph.connect("node0", "node5");
		graph.connect("node2", "node6");
		graph.connect("node5", "node6");
		Gson gson = new Gson();
		System.out.println(new Gson().toJson(graph.getChild("node0")));
		System.out.println(gson.toJson(graph.getarent("node6")));
		System.out.println(gson.toJson(graph.traceParent("node6")));
	}
}
