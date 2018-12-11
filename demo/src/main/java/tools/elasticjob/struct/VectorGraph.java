package tools.elasticjob.struct;

public class VectorGraph<T> {
	transient int size = 0;
	
	public VectorGraph() {
		// TODO Auto-generated constructor stub
	}
	
	static class Node<T>{
		T value;
		
		Node<T>[] next;
		transient int nextSize;
		
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
		
		public synchronized void addFather(Node<T> father) {
			ensureFatherSize();
			previous[previousSize] = father;
			previousSize++;
			adaptFather(father);
		}
		
		public synchronized void addChild(Node<T> child) {
			ensureChildSize();
			next[nextSize] = child;
			nextSize++;
			adaptChild(child);
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
