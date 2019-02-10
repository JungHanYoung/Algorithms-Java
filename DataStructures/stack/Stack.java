package stack;

import section1.Node;
import section1.MySingleLinkedList;

public class Stack<T> {
	
	private final int max = 10;
	private MySingleLinkedList<T> list = new MySingleLinkedList<>();
	int nTop = -1;
	
	public void push(T item) {
		if (is_full()) {
			return;
		}
		list.addFirst(item);
		nTop++;
	}
	
	public T pop() {
		if(isEmpty()) {
			return null;
		}
		T item = list.get(0);
		list.removeFirst();
		return item;
	}
	
	private boolean is_full() {
		if(max > nTop)
			return false;
		else 
			return true;
	}
	
	private boolean isEmpty() {
		if(nTop < 0)
			return true;
		else 
			return false;
	}
	
	public void printStack() {
		Node<T> temp = list.head;
		for(int i = 0 ; i < list.size; i++) {
			System.out.println(temp.data.toString());
			temp = temp.next;
		}
			
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<String> stack = new Stack<>();
		stack.push("Hello");
		stack.push("World");
		stack.push("My name is ");
		stack.push("Jeong Han Young");
		System.out.println(stack.pop());
		
		stack.printStack();
	}

}
