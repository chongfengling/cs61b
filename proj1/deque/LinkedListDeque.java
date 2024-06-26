package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node<T> { // static or not?
        private Node<T> prev;
        private final T item;
        private Node<T> next;

        private Node(Node i, T x, Node j) {
            prev = i;
            item = x;
            next = j;
        }

        private Node(T x) {
            item = x;
            prev = this;
            next = this;
        }
    }

    private int size = 0;
    private final Node<T> sentinel;
    public LinkedListDeque() {
        sentinel = new Node(null);
    }

    @Override
    public  void addFirst(T item) {
        size += 1;
        Node<T> firstNode = new Node(item);
        firstNode.prev = sentinel;
        firstNode.next = sentinel.next;
        firstNode.next.prev = firstNode;

        sentinel.next = firstNode;
        if (sentinel.prev == sentinel) {
            sentinel.prev = firstNode;
        }
    }

    @Override
    public void addLast(T item) {
        size += 1;
        Node<T> lastNode = new Node(item);
        lastNode.next = sentinel;
        lastNode.prev = sentinel.prev;
        lastNode.prev.next = lastNode;

        sentinel.prev = lastNode;
        if (sentinel.next == sentinel) {
            sentinel.next = lastNode;
        }
    }

    @Override
    public T removeFirst() {
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        if (size > 0) {
            size += -1;
        }
        return removedItem;
    }

    @Override
    public T removeLast() {
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        if (size > 0) {
            size += -1;
        }
        return removedItem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    @Override
    // 0 is front while 1 is the next item
    public T get(int index) {
        if (index >= size || index < 0) {
            return  null;
        }

        int pos = 0;
        Node<T> currentNode = sentinel.next;
        while (pos != index) {
            pos += 1;
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return  null;
        }
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node<T> currentNode) {
        if (index == 0) {
            return currentNode.item;
        }
        if (currentNode.next == null) {
            return null;
        }
        return getRecursive(index - 1, currentNode.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> wizNode;
        LinkedListDequeIterator() {
            wizNode = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return wizNode != sentinel;
        }

        @Override
        public T next() {
            T returnItem = wizNode.item;
            wizNode = wizNode.next;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        // memory address check
        if (this == other) {
            return true;
        }
        // class check
        if (!(other instanceof Deque)) {
            return false;
        }
        // size check
        Deque<T> o = (Deque<T>) other;
        if (this.size() != o.size()) {
            return false;
        }
        // items check
        for (int index = 0; index < this.size(); index++) {
            if (!(this.get(index).equals(o.get(index)))) {
                return false;
            }
        }
        return true;
    }
}
