package deque;

public class LinkedListDeque<T> {
    private class IntNode { // static or not?
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode i, T x, IntNode j) {
            prev = i;
            item = x;
            next = j;
        }

        public IntNode(T x){
            item = x;
            prev = this;
            next = this;

        }
    }

    private int size = 0;
    private IntNode sentinel;

    public LinkedListDeque(){
        sentinel = new IntNode(null);
    }

    public  void addFirst(T item){
        size += 1;
        IntNode firstNode = new IntNode(item);
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
        IntNode lastNode = new IntNode(item);
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
        IntNode p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

}
