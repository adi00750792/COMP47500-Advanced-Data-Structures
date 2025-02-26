package com.ads.custom;

import java.util.LinkedList;


//Space Complexity is O(n)
public class CustomStack <T> implements Stack<T>{
	
	//Used linkedlist to implement the stack
	private LinkedList<T> list;
	
	//Specify the capacity of the stack
	private int stackCapacity;
	
	//Constructor to initialize the CustomStack
	public CustomStack(int stackCapacity) {
		this.stackCapacity = stackCapacity;
		list = new LinkedList<T>(); 
	}
	
	//return the stack capacity
	public int getStackCapacity() {
		return stackCapacity;
	}

	
	@Override
	public void push(T item) {
		//Check if stack is full is stack is full it will remove the last item from the stack.
		if(this.stackCapacity <= this.list.size())
			this.list.removeLast();
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





	
