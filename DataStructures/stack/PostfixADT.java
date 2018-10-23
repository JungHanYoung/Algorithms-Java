package postfix;

import java.util.Stack;

/*
 * 1. 여는 괄호<(>는 무조건 스택에 push한다. 이때 스택 내의 어떤 연산자도 pop하지 않는다.
 * 2. 어떤 연산자를 스택에 push할 때 스택의 top에 여는 괄호가 있으면 아무도 pop하지않고 그냥 push한다.
 * 3. 입력에 닫는 괄호가 나오면 스택에서 여는 괄호가 나올 때 까지 pop하여 출력한다. 닫는 괄호는 스택에 push하지 않는다.*/

// 피연산자는 1자리 숫자만 가능... 쩝

public class PostfixADT{
	
	// 연산자의 우선순위 출력(값이 클수록 우선), -1리턴시 피연산자
	private static int prec(char exp) {
		switch(exp) {
			case '+': case '-': return 1;
			case '*': case '/': return 2;
		}
		return -1;
	}
	
	// exp 파라미터에서의 infix는 피연산자가 한 자리 숫자만 허용(2자리일 경우 연산자 피연산자 구분에 대한 알고리즘 더 필요)
	private static String infixToPostfix(String exp) {
		
		Stack<Character> opStack = new Stack<>();		// 연산자 스택
		StringBuilder postfix = new StringBuilder();
		// infix에서 피연산자는 한 자리 숫자만을 이용
		char[] exp_ch = exp.toCharArray();
		
		
		for(int i = 0 ; i < exp_ch.length ; i++) {
			int currentPrec = prec(exp_ch[i]);
			
			if(currentPrec < 0) {		// 피연산자일 경우
				
				postfix.append(exp_ch[i]);
				
			} else {					// 연산자일 경우
				
				if(opStack.isEmpty()) {		// 스택이 비어있을 경우
					opStack.push(exp_ch[i]);
				} else {					
					char top_op = opStack.peek();
					if(prec(exp_ch[i]) <= prec(top_op)) { // top의 연산자 우선순위 >= 다음 연산자, 우선순위가 같아도
						postfix.append(opStack.pop());
						opStack.push(exp_ch[i]);
					} else {
						opStack.push(exp_ch[i]);
					}
				}
			}
			
		}
		while(!opStack.isEmpty()) {			// 연산자 스택에 남아있는 것을 모두 pop
			postfix.append(opStack.pop());
		}
		
		return postfix.toString();
	}
	
//	private static int postfixCalc(String postfix) {
//		Stack<Character> stack = new Stack<>();
//		char[] exp_postfix = postfix.toCharArray();
//		for(char ch : exp_postfix) {
//			if(isOperand(ch)) {
//				stack.push(ch);
//			} else {
//				int operand1 = (int)(stack.pop()-48);
//				int operand2 = (int)(stack.pop()-48);
//			}
//		}
//		return -1;
//	}
//	
//	private static boolean isOperand(char ch) {
//		switch(ch) {
//			case '+': case '-': case '*': case '/': return false;
//			default: return true;
//		}
//	}

	public static void main(String[] args) {
		System.out.println(infixToPostfix("2*2*2+2"));
		System.out.println(infixToPostfix("4+6*3"));
	}
	
}
