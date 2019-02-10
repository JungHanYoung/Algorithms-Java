package section2;

import section1.MySingleLinkedList;
import section1.Node;

public class Polynomial {
	
	public char name;
	public MySingleLinkedList<Term> terms;
	
	public Polynomial(char name) {
		this.name = name;
		this.terms = new MySingleLinkedList<Term>();
	}
	
	// 새로운 항을 추가
	/*
	 * 1. 추가하려는 항과 동일 차수의 항이 이미 있는 경우
	 *    - 기존 항의 계수만 변경
	 * 2. 그렇지 않은 경우: 새로운 항 삽입(항들은 차수의 내림차순으로 항상 정렬)*/
	/*
	 * 삽입과정
	 * - 삽입하려는 항의 차수보다 작은 차수가 나오면 해당 작은 차수 앞에 넣어준다!
	 *    - 하지만 항을 삽입하려면 이전 노드를 알아야한다!(노드에 대한 참조변수가 2개 필요(prev, next))
	 * */
	public void addTerm(int coef, int expo) {
		if(coef == 0)
			return;
		Node<Term> p = terms.head;
		Node<Term> q = null;
		while(p != null && p.data.expo > expo) {
			q = p;
			p = p.next;
		}
		if(p != null && p.data.expo == expo) {	// 상수
			p.data.coef += coef;
			if(p.data.coef == 0) {
				// 항의 계수가 0일 경우 삭제
				if(q == null)	// 첫번째 항일 경우
					terms.removeFirst();
				else
					terms.removeAfter(q);
			}
		} else {		// 새로운 항을 q뒤에 삽입
			Term t = new Term(coef, expo);
			if(q == null)	// 추가될 항이 첫번째 항일 경우
				terms.addFirst(t);
			else			
				terms.addAfter(q, t);
		}
	}
	
	public int calc(int x) {
		int result = 0;
		
		Node<Term> p = terms.head;
		while(p != null) {
			result += p.data.calc(x);
			p = p.next;
		}
		return result;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<Term> p = terms.head;
		while(p != null) {
			result
				.append("+")
				.append(p.data.coef)
				.append("x^")
				.append(p.data.expo);
			p = p.next;
		}
		return result.toString();
	}
	
}
