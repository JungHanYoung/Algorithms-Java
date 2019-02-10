package stack_queue;

import java.util.Stack;

public class StealBar_algorithm {
	
	private static int solution(String arrangement) {
		int answer = 0;
		int add_stealbar = 0;
		Stack<Character> stack = new Stack<>();
		
		for(char input : arrangement.toCharArray()) {
			if(stack.isEmpty()) {
				stack.push(input);
				add_stealbar++;
			} else {
				char top = stack.peek();
				if(input == '(') {
					stack.push(input);
					add_stealbar++;
				} else {
					if(top == '(') {
						stack.push(input);
						add_stealbar--;
						answer += add_stealbar;
					} else {
						stack.push(input);
						add_stealbar--;
						answer++;
					}
				}
			}
		}
		
        return answer;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = solution("()(((()())(())()))(())");
		System.out.println("Answer : " + n);
	}

}
