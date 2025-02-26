package com.ads.custom;

import java.util.LinkedList;


//Space Complexity is O(n)
public class CustomStack <T> implements Stack<T>{
	
	//Used linkedlist to implement the stack
	private LinkedList<T> list;
	

	//Constructor to initialize the CustomStack
	public CustomStack() {
		list = new LinkedList<T>(); 
	}
	

	@Override
	public void push(T item) {
		//Add the item to the start of the stack.
		this.list.addFirst(item);	
	}

	@Override
	public T pop() {
		//Check if stack is empty if empty throw the run time exception.
		if(list.size() == 0)
			throw new RuntimeException("Stack is Empty");
		//Removes the first item from the stack.
		return list.removeFirst(); 
	}

	@Override
	public T peek() {
		//Check is stack is empty then throw the run time exception.
		if(list.size() == 0)
			throw new RuntimeException("Stack is Empty");
		//Return the top element from the stack.
		return list.getFirst();
	}

	@Override
	public int size() {
		//return the size of the stack.
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		//Check if stack is empty, if empty return true
		if(list.size() == 0)
			return true;
		//else false.
		return false;
	}	
	
	
}





	
