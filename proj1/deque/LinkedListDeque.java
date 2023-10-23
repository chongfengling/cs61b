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
}
