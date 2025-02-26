package com.ads.custom;
import java.util.LinkedList;

public class CustomQueue<T> implements Queue<T> {
	//Used linkedlist to implement the stack
	private LinkedList<T> list;
   
	//constructor to initialize the
	public CustomQueue() {
//		this.sizeCapacity = sizeCapacity;
		list = new LinkedList<T>(); 
	}

	@Override
    public void enqueue (T item) {
    	// add the item to the end of the queue.
    	this.list.add(item);
    }

    @Override
    public T dequeue () {
    	// check is the queue is empty if empty throw the exception
        if(list.isEmpty()==true){
            throw new RuntimeException("Queue is empty");
        }
        // removes the first element from the queue.
        return list.removeFirst();
    }

    @Override
    public T peek () {
    	// check is the queue is empty if empty throw the exception.
        if(isEmpty()==true){
            throw new RuntimeException("Queue is empty");
        }else{
        	// returns the top element from the queue.
            return list.getFirst();
        }
    }

    @Override
    public boolean isEmpty () {
    	//check if stack is empty or not.
        if(list.isEmpty()==true){
            return true;
        }
        return false;
    }

    @Override
    public int size () {
    	//return the size of the stack.
        return list.size();
    }
}
