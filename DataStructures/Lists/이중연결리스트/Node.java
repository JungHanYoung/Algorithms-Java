package lists;

public class Node<T> {

	public T data;
	public Node<T> prev;
	public Node<T> next;
	
	public Node(T item) {
		this.data = item;
		this.prev = null;
		this.next = null;
	}
	
}
