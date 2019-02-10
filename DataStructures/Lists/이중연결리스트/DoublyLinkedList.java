package lists;

public class DoublyLinkedList<T> {

	public Node<T> head;
	public Node<T> tail;
	public int size;
	
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	Node<T> getNode(int index) {
		if(index < size / 2) {
			Node<T> x = head;
			for(int i = 0 ; i < index ; i++)
				x = x.next;
			return x;
		} else {
			Node<T> x = tail;
			for(int i = size - 1 ; i > index ; i--)
				x = x.prev;
			return x;
		}
	}
	
	// 노드 삽입, 삭제, 검색
	
	// 삽입	*****************************************
	public void insertHead(T item) {
		/*
		 * 1. 삽입노드의 오른쪽 링크를 연결
		 * 2. 삽입노드의 왼쪽 링크를 연결
		 * 3. 왼쪽에 연결된 노드의 오른쪽 링크 연결
		 * 4. 오른쪽 노드의 왼쪽링크에 삽입노드 연결*/
		Node<T> temp = new Node<T>(item);	// null <-- newNode --> null
		temp.next = head;		// null <-- newNode --> currentHead
		if(head != null) {
			head.prev = temp;	// null <-- newNode <--> currentHead
		}
		head = temp;			// newNode를 head로 지정
		if(tail == null)
			tail = temp;
		size++;
		
	}
	
	public void insertTail(T item) {
		Node<T> temp = new Node<T>(item);	// null <-- newNode --> null
		tail.next = temp;		// currentTail --> newNode --> null
		temp.prev = tail;		// currentTail <--> newNode --> null
		tail = temp;			// newNode를 tail로 지정
		size++;

	}
	
	public void insert(int index, T item) {
		Node<T> newNode = new Node<T>(item);
		Node<T> current = getNode(index);
		if(current == tail)
			insertTail(item);
		else if(current == null)
			insertHead(item);
		else {
			// prevNode(current) -- 들어갈 좌표 -- nextNode
			newNode.prev = current;
			current.next.prev = newNode;
			newNode.next = current.next;
			current.next = newNode;
			
//			newNode.prev = current.prev;		// prevNode <-- newNode --> null
//			current.prev.next = newNode;		// prevNode <--> newNode --> null
//			newNode.next = current;				// prevNode <--> newNode --> nextNode(current)
//			current.prev = newNode;				// prevNode <--> newNode <--> nextNode(current)
		}
		size++;
	}
	
	
	// 삭제   *********************************************
	public void deleteHead() {
		head = head.next;		// head --> nextHead
		head.prev = null;
		if(head == null)	// 리스트 원소가 1개였어서 삭제할때 꼬리링크도 삭제
			tail = null;
		size--;
	}
	
	public void deleteTail() {
		tail = tail.prev;
		tail.next = null;
		if(tail == null)
			head = null;
		size--;
	}
	
	public void delete(int index) {
		Node<T> current = getNode(index);
		if(current == head)
			deleteHead();
		else if(current == tail)
			deleteTail();
		else {
			current.prev.next = current.next;
			current.next.prev = current.prev;
		}
		size--;
	}
	
	public void printList() {
		Node<T> current = head;
		for(int i = 0 ; i < size ; i++) {
			System.out.println(current.data.toString());
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<String> list = new DoublyLinkedList<>();
		
		list.insertHead("Hello");
		list.insertHead("Wow~ ");
		list.insert(2, "World");
		list.insertTail("My name");
		list.deleteHead();
		list.deleteTail();
		list.insert(2, "sos");
		
		list.printList();
	}
	
}
