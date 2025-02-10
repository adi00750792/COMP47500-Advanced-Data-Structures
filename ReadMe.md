### Methods of `CustomQueue`

1. **`enqueue(T item)`**
    - **Description**: Adds the specified item to the rear of the queue.
    - **Parameters**:
        - `item`: The element to be added to the queue.
    - **Returns**:
        - Nothing. The item is added to the queue.

2. **`dequeue()`**
    - **Description**: Removes and returns the element from the front of the queue.
    - **Returns**:
        - The element at the front of the queue.
    - **Throws**:
        - `RuntimeException`: If the queue is empty, a `RuntimeException` will be thrown.

3. **`peek()`**
    - **Description**: Retrieves, but does not remove, the element from the front of the queue.
    - **Returns**:
        - The element at the front of the queue.
    - **Throws**:
        - `RuntimeException`: If the queue is empty, a `RuntimeException` will be thrown.

4. **`isEmpty()`**
    - **Description**: Checks whether the queue is empty.
    - **Returns**:
        - `true` if the queue is empty, otherwise `false`.

5. **`size()`**
    - **Description**: Returns the number of elements in the queue.
    - **Returns**:
        - The size (count of elements) in the queue.
