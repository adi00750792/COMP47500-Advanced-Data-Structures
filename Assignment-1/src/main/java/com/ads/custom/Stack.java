package com.ads.custom;

//Space Complexity is O(n)
public interface Stack<T>{
	//add the item to the start of the Stack.
	void push(T item);
	//Removed element from start of the Stack.
	T  pop() ;
	//Return the top element from the stack.
	T peek() ;
	//Return the size of the stack.
	int size() ;
	//Returns if the stack is empty.
	boolean isEmpty() ;	
}

