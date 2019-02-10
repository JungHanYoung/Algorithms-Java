### 스택/큐 응용 - 프린터 우선순위

[프로그래머스 링크](https://programmers.co.kr/learn/courses/30/lessons/42587)

#### 문제설명

1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
3. 그렇지 않으면 J를 인쇄합니다.

예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.

내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

<br>

#### 입출력 예

|priorities|location|return|
|----------|--------|------|
|[2, 1, 3, 2]|2|1|
|[1, 1, 9, 1, 1, 1]|0|5|

<br>

#### 내 풀이

**문제 이해**

- 맨 앞의 데이터가 제일 높은 우선순위이면 프린트
- 제일 높지 않으면 대기목록에서 가장 뒤로

**문제 해결 접근**

- 일단 맨 앞의 데이터와 전체 데이터를 비교하는 로직이 필요
  - 맨 앞의 데이터가 제일 높은 우선순위이면 프린트
    - **실제 인쇄 순서를 저장할 변수(List) 필요**
  - 제일 높지 않으면 대기목록에서 가장 뒤로 
    - 앞의 데이터를 꺼내 비교하고, 뒤로 다시 데이터를 넣는 방식 필요
      - **입력된 대기목록을 Queue로 이용!**
- 입력값을 보면 대기목록으로 배열, 인쇄 요청한 문서의 위치는 index로 주어진다.
  - Queue를 통해 데이터를 비교하며 실제 인쇄 순서로 다시 배열을 만든다면 **실제 인쇄순서를 다 만들었을 때 이전 대기 목록의 location(index)을 가지는 값을 찾을 수 없다.**
  - location값까지 저장할 객체 정의가 필요
    - location(이전 인쇄 대기 목록에서의 위치)
    - priority(인쇄 우선순위 값)

|조건|결과|
|:-:|:-:|
|앞에 있는 인쇄의 우선순위 >= 제외한 각각의 우선순위|실제 인쇄목록에 추가|
|앞에 있는 인쇄의 우선순위 < 제외한 각각의 우선순위|대기목록(Queue) 뒤로 삽입|

<br>

#### 소스코드

~~~java
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;     // 구현 클래스는 아무거나 사용함.

public class Print_precedure_algorithm {
	
	private static int solution(int[] priorities, int location) {
		int answer = 0;
		
		List<PrintQueue> arrange_list = new ArrayList<>();  // 실제 인쇄 순서
		Queue<PrintQueue> queue = new ArrayBlockingQueue<>(priorities.length);  // 대기목록 큐
		
		for(int i = 0 ; i < priorities.length ; i++) {
			queue.add(new PrintQueue(i, priorities[i]));    // 배열을 맵핑하여 큐에 저장
		}
		
		while(!queue.isEmpty()) {   // Queue에 데이터가 없을 때까지
			PrintQueue temp = queue.remove();
			
			if(queue.stream().anyMatch(pq -> pq.priority > temp.priority)) {    // Queue순회했을 때 더 높은 우선순위가 나오면
				queue.add(temp);
			} else {
				arrange_list.add(temp); //
			}
		}
		
		for(PrintQueue pq : arrange_list) {
			if(pq.location == location) {
				answer = arrange_list.indexOf(pq);
                break;
			}
		}
		
		return answer + 1;  // 순위는 1부터 시작
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		int[] pr1 = {2,1,3,2};
		int[] pr2 = {1, 1, 9, 1, 1, 1};
		int result1 = solution(pr1, 2);
		int result2 = solution(pr2, 0);
		
		System.out.println(result1);
		System.out.println(result2);
	}

}

class PrintQueue {      // 네이밍을 잘못한 것 같지만 걍 ㄱ
	public int location;
	public int priority;
	public PrintQueue(int location, int priority) {
		this.location = location;
		this.priority = priority;
	}
}

->
1
5
~~~