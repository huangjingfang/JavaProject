package tools.elasticjob.struct;

import java.util.List;

public class VectorGraph<T> {
	transient int size = 0;
	
	/*入边为0的节点*/
	private Node<T>[] first;
	transient int firstSize;
	
	/*出边为0的节点*/
	private Node<T>[] last;
	transient int lastSize;
	
	private static final int DEFAULT_ARRAY_SIZE = 4;
	
	@SuppressWarnings("unchecked")
	public VectorGraph() {
		// TODO Auto-generated constructor stub
		first = new Node[DEFAULT_ARRAY_SIZE];
		last = new Node[DEFAULT_ARRAY_SIZE];
		firstSize = 0;
		lastSize = 0;
	}
	
	public VectorGraph(Node<T> node) {
		// TODO Auto-generated constructor stub
		this();
		first[0] = node;
		last[0] = node;
		firstSize++;
		lastSize++;
	}
	
	public synchronized void addNode(Node<T> father,Node<T> newNode) throws Exception{
		if(contains(father)) {
			father.addChild(newNode);
			if(newNode.nextSize==0) {
				addLast(newNode);
			}
		}else {
			throw new Exception("father节点不在当前图中");
		}
		
	}
	
	public synchronized void addNode(Node<T> node) {
		addFirst(node);
		addLast(node);
	}
	
	/**
	 * 获取node的所有前置节点
	 * @param node
	 * @return
	 */
	public synchronized List<Node<T>> getDependNodes(Node<T> node){
		
		return null;
	}
	
	/**
	 * 删除该节点
	 * @param node
	 */
	public void remove(Node<T> node) {
		
	}
	
	/**
	 * 对图中的father和child建立father->child的关系
	 * @param father
	 * @param child
	 */
	public void link(Node<T> father,Node<T> child) {
		
	}
	
	/**
	 * 获取该图的一个线性序列，所有的父序列都必须在子序列之前
	 * @return
	 * @throws Exception 如果图中有有向环，或者有若干个孤立的点，则抛出相关异常
	 */
	public List<Node<T>> getLinearSeries() throws Exception{
		return null;
	}
	
	public boolean contains(Node<T> node) {
		return false;
	}
	private void addLast(Node<T> node) {
		ensureLast();
		last[lastSize] = node;
		lastSize++;
	}
	
	private void addFirst(Node<T> node) {
		ensureFirst();
		first[firstSize] = node;
		firstSize++;
	}
	
	private void ensureLast() {
		if(lastSize == last.length) {
			int newSize = last.length*2;
			@SuppressWarnings("unchecked")
			Node<T>[] newLast = new Node[newSize];
			System.arraycopy(last, 0, newLast, 0, last .length);
			last = newLast;
		}
	}
	
	private void ensureFirst() {
		if(firstSize == first.length) {
			int newSize = first.length*2;
			@SuppressWarnings("unchecked")
			Node<T>[] newFirst = new Node[newSize];
			System.arraycopy(first, 0, newFirst, 0, first.length);
			first = newFirst;
		}
	}
	
	static class Node<T>{
		T value;
		
		/*节点上级*/
		Node<T>[] next;
		transient int nextSize;
		
		/*节点下级*/
		Node<T>[] previous;
		transient int previousSize;
		
		private static final int DEFAULT_ARRAY_SIZE = 4;
		
		@SuppressWarnings("unchecked")
		public Node(T value) {
			// TODO Auto-generated constructor stub
			this.value = value;
			
			this.next = (Node<T>[])new Node[DEFAULT_ARRAY_SIZE];
			this.previous = (Node<T>[])new Node[DEFAULT_ARRAY_SIZE];
			nextSize = 0;
			previousSize = 0;
		}
		
		public Node(T value,Node<T> father) {
			// TODO Auto-generated constructor stub
			this(value);
			previous[0] = father;
			previousSize++;
			adaptFather(father);
		}
		
		/**
		 * 对该节点增加一个父节点
		 * @param father
		 */
		public synchronized void addFather(Node<T> father) {
			ensureFatherSize();
			previous[previousSize] = father;
			previousSize++;
			adaptFather(father);
		}
		
		/**
		 * 对该节点增加一个子节点
		 * @param child
		 */
		public synchronized void addChild(Node<T> child) {
			ensureChildSize();
			next[nextSize] = child;
			nextSize++;
			adaptChild(child);
		}
		
		/**
		 * 获取父节点的个数
		 * @return
		 */
		public synchronized int getPreviousCount() {
			return previousSize;
		}
		
		/**
		 * 获取子节点的个数
		 * @return
		 */
		public synchronized int getNextCount() {
			return nextSize;
		}
		
		private void adaptFather(Node<T> father) {
			father.ensureChildSize();
			father.next[father.nextSize]=this;
			father.nextSize++;
 		}
		
		private void adaptChild(Node<T> child) {
			child.ensureFatherSize();
			child.previous[child.previousSize] = this;
			child.previousSize++;
		}
		
		private void ensureFatherSize() {
			if(previousSize==previous.length) {
				int newSize = previous.length*2;
				@SuppressWarnings("unchecked")
				Node<T>[] newProvious = new Node[newSize];
				System.arraycopy(previous, 0, newProvious, 0, previous.length);
				previous = newProvious;
			}
		}
		private void ensureChildSize() {
			if(nextSize == next.length) {
				int newSize = next.length*2;
				@SuppressWarnings("unchecked")
				Node<T>[] newNext = new Node[newSize];
				System.arraycopy(next, 0, newNext, 0, next.length);
				next = newNext;
			}
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			StringBuilder builder = new StringBuilder("parent:");
			for(int i=0;i<previousSize;i++) {
				Node<T> node = previous[i];
				builder.append(node.value).append(",");
			}
			builder.append("\n").append("children:");
			for(int i=0;i<nextSize;i++) {
				Node<T> node = next[i];
				builder.append(node.value).append(",");
			}
			return builder.substring(0, builder.length()-1);
		}
	}
	public static void main(String[] args) {
		Node<String> node0 = new Node<String>("node0");
		Node<String> node1 = new Node<String>("node1",node0);
		Node<String> node2 = new Node<String>("node2",node0);
		Node<String> node3 = new Node<String>("node3",node0);
		Node<String> node4 = new Node<String>("node4",node0);
		Node<String> node5 = new Node<String>("node5",node0);
		Node<String> node6 = new Node<String>("node6",node2);
		node6.addFather(node3);
		Node<String> node7 = new Node<String>("node7",node5);
		node7.addFather(node6);	
		System.out.println(node7.toString());
	}
}
