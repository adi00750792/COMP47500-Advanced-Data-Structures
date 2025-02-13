package com.ads.custom;

import java.util.LinkedList;

//Space Complexity is O(n)
public class Stack <T>{
	
	// Uses LinkedList to implement the stack
	private LinkedList<T> list;
	
	public Stack() {
		list = new LinkedList<T>(); 
	}
	
	public void push(T item) { //O(1)
		list.addFirst(item); 
	}
	
	public T  pop() { //O(1)
		if(list.size() == 0)
			throw new RuntimeException("Stack is Empty");
		return list.removeFirst(); 
	}
	
	public T peek() { //O(1)
		if(list.size() == 0)
			throw new RuntimeException("Stack is Empty");
		return list.getFirst();
	}
	
	public int size() {  //O(1)
		return list.size();
		
	}
	
	public boolean isEmpty() { //O(1)
		if(list.size() == 0)
			return true;
		return false;
	}
	
}

