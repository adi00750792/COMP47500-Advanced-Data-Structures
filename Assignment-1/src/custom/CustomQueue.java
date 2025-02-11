package custom;

import java.util.LinkedList;

public class CustomQueue<T> implements Queue<T> {
   private LinkedList<T> list = new LinkedList<T>();
    @Override
    public void enqueue (T item) {
        list.add(item);
    }

    @Override
    public T dequeue () {
        if(list.isEmpty()==true){
            throw new RuntimeException("Queue is empty");
        }
        return list.removeFirst();
    }

    @Override
    public T peek () {
        if(isEmpty()==true){
            throw new RuntimeException("Queue is empty");
        }else{
            return list.getFirst();
        }
    }

    @Override
    public boolean isEmpty () {
        if(list.isEmpty()==true){
            return true;
        }
        return false;
    }

    @Override
    public int size () {
        return list.size();
    }
}
