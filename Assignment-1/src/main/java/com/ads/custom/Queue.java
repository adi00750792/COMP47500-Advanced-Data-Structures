package com.ads.custom;

public interface Queue<T> {
    // Adds an item to the back of the queue.
    void enqueue(T item);

    // Removes and returns the item from the front of the queue.
    T dequeue();

    // Returns the front item of the queue without removing it.
    T peek();

    // Checks if the queue is empty.
    boolean isEmpty();

    // Returns the number of items in the queue.
    int size();
}