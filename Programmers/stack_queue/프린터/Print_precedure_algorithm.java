package stack_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Print_precedure_algorithm {
	
	private static int solution(int[] priorities, int location) {
		int answer = 0;
		
		List<PrintQueue> arrange_list = new ArrayList<>();
		Queue<PrintQueue> queue = new ArrayBlockingQueue<>(priorities.length);
		
		for(int i = 0 ; i < priorities.length ; i++) {
			queue.add(new PrintQueue(i, priorities[i]));
		}
		
		while(!queue.isEmpty()) {
			PrintQueue temp = queue.remove();
			//boolean flag = true;
			
			if(queue.stream().anyMatch(pq -> pq.priority > temp.priority)) {
				queue.add(temp);
			} else {
				arrange_list.add(temp);
			}
		}
		
		for(PrintQueue pq : arrange_list) {
			if(pq.location == location) {
				answer = arrange_list.indexOf(pq);
			}
		}
		
		return answer + 1;
		
//		queue.forEach(new Consumer<Integer>() {
//
//			@Override
//			public void accept(Integer t) {
//				// TODO Auto-generated method stub
//				int n = queue.remove();
//				
//			}
//			
//		});
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

class PrintQueue {
	public int location;
	public int priority;
	public PrintQueue(int location, int priority) {
		this.location = location;
		this.priority = priority;
	}
}
