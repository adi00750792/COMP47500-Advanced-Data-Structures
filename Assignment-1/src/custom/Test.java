package custom;

public class Test {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println("Size = " + stack.size());

		System.out.println(stack.pop());
		System.out.println("Size = " + stack.size());

		System.out.println(stack.pop());
		System.out.println("Size = " + stack.size());

		System.out.println(stack.pop());
		System.out.println("Size = " + stack.size());

		
	}
	
	
}
