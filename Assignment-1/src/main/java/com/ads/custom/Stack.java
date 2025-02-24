package com.ads.custom;

import java.util.LinkedList;

//Space Complexity is O(n)
public interface Stack<T>{
	
	
	 void push(T item);
	
	 T  pop() ;
	
	 T peek() ;
	
	 int size() ;
	
	 boolean isEmpty() ;

	
}

