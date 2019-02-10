package section1;

class MySingleLinkedList<T> {
	
	
	// 첫번째 노드의 주소
	public Node<T> head;
	public int size;	// 리스트 크기
	
	public MySingleLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	public void addFirst(T item) {
		
		Node<T> newNode = new Node<T>(item);	// 1) 새로운 노드를 만들고, 추가할 데이터를 저장한다.
		newNode.next = head;					// 2) 새로운 노드의 next필드가 현재의 head노드를 가리킨다.
		this.head = newNode;					// 3) 새로운 노드를 새로운 head로 한다.
		this.size++;							// 노드가 추가되었으므로, 리스트 사이즈 증가.
		
	}
	
	public void addAfter(Node<T> before, T item) {

		Node<T> temp = new Node<T>(item);	// 1) 새로운 노드를 만들고 데이터를 저장한다.
		temp.next = before.next;			// 2) 새로운 노드의 next필드가 before의 다음 노드를 가리킨다.
		before.next = temp;					// 3) 새로운 노드를 before의 다음 노드로 만든다.
		this.size++;
		
	}
	
	public T removeFirst() {
		/*
		 * head가 null이 아니라면 head가 현재 노드의 다음 노드를 가리킨다.
		 * */
		if(head == null) 
			return null;
		T temp = head.data;
		head = head.next;
		this.size--;
		return temp;
	}
	
	public T removeAfter(Node<T> before) {
		Node<T> temp = before.next;
		if(temp == null)
			return null;
		before.next = temp.next;
		this.size--;
		return temp.data;
	}
	
	
	public void add(int index, T item) {	// 삽입
		
	}
	
	
	public void remove(int index) {	// 삭제
		
	}
	
	
	public int indexOf(T item) {	// 검색 Traversing a Linked List
		Node<T> p = this.head;
		int index = 0;
		while(p != null) {
			if(p.data.equals(item))
				return index;
			p = p.next;
			index++;
		}
		return -1;
	}
	
	public T get(int index) {
		if(index < 0 || index >= size) {
			return null;
		}
		Node<T> p = head;
		for(int i = 0 ; i < index; i++)
			p = p.next;
		return p.data;
	}
	
	public static void main(String[] args) {
		MySingleLinkedList<String> list = new MySingleLinkedList<String>();
		
		list.addFirst("Hello List~");
		
		System.out.println(list.toString());
		/*
		list.add(0, "Saturday");
		list.add(1, "Friday");	// S, F
		list.add(0, "Monday");	// M, S, F
		list.add(2, "Tuesday");	// M, S, T, F
		
		String str = list.get(2);	// str = "Tuesday";
		list.remove(2);			// M, S, F
		int pos = list.indexOf("Friday"); // pos = 2
		*/
	}
}
