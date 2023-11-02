package deque;

public class LinkedListDeque<T> {
    private class node { // static or not?
        private node prev;
        private final T item;
        private node next;

        private node(node i, T x, node j) {
            prev = i;
            item = x;
            next = j;
        }

        private node(T x){
            item = x;
            prev = this;
            next = this;

        }
    }

    private int size = 0;
    private final node sentinel;

    public LinkedListDeque(){
        sentinel = new node(null);
    }

    public  void addFirst(T item){
        size += 1;
        node firstNode = new node(item);
        firstNode.prev = sentinel;
        firstNode.next = sentinel.next;
        firstNode.next.prev = firstNode;

        sentinel.next = firstNode;
        if (sentinel.prev == sentinel){
            sentinel.prev = firstNode;
        }
    }

    public void addLast(T item){
        size += 1;
        node lastNode = new node(item);
        lastNode.next = sentinel;
        lastNode.prev = sentinel.prev;
        lastNode.prev.next = lastNode;

        sentinel.prev = lastNode;
        if (sentinel.next == sentinel){
            sentinel.next = lastNode;
        }
    }

    public T removeFirst(){
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size > 0){
            size += -1;
        }
        return removedItem;
    }

    public T removeLast(){
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size > 0){
            size += -1;
        }
        return removedItem;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        node p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    // 0 is front while 1 is the next item
    public T get(int index){
        if (index >= size || index < 0){
            return  null;
        }

        int pos = 0;
        node currentNode = sentinel.next;
        while (pos != index){
            pos += 1;
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }
}
