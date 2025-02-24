package com.ads.custom;

import java.util.LinkedList;

//Space Complexity is O(n)
public class CustomStack <T> implements Stack<T>{
	
	private LinkedList<T> list;
	
	public CustomStack() {
		list = new LinkedList<T>(); 
	}

	@Override
	public void push(T item) {
		list.addFirst(item); 

		
	}

	@Override
	public T pop() {
		if(list.size() == 0)
			throw new RuntimeException("Stack is Empty");
		return list.removeFirst(); 
	}

	@Override
	public T peek() {
		if(list.size() == 0)
			throw new RuntimeException("Stack is Empty");
		return list.getFirst();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		if(list.size() == 0)
			return true;
		return false;
	}	
	
}





	
