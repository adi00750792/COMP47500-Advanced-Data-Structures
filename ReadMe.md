**CUSTOM QUEUE IMPLEMENTATION DETAILS** 
Methods of CustomQueue:

**enqueue(T item)**:
Adds the specified item to the rear of the queue.
Parameters:

item: The element to be added to the queue.
Returns:
Nothing. The item is added to the queue.

**dequeue()**:
Removes and returns the element from the front of the queue.
Returns:

The element at the front of the queue.
Throws:

RuntimeException if the queue is empty.
**peek()**:
Retrieves, but does not remove, the element from the front of the queue.
Returns:

The element at the front of the queue.
Throws:

RuntimeException if the queue is empty.
**isEmpty()**:
Checks whether the queue is empty.
Returns:

true if the queue is empty, otherwise false.
**size()**:
Returns the number of elements in the queue.
Returns:

The size (count of elements) in the queue.