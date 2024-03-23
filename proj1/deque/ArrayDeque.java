package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{

    private T[] items;
    private int size;
    private int nextFirst = 1; // nonzero
    private int nextLast = 2;
    private int defaultLength = 6;
    private int zipSpace = 5; // spaces remained at the beginning and ending after zip the array

    public ArrayDeque(){
        items = (T []) new Object[defaultLength];
        size = 0;
    }

    @Override
    public void addFirst(T item){
        if (nextFirst == 0){ // at this time; nextLast - nextFirst = items.length - 1
            resize((int) (items.length * 2));
//            zip(zipSpace);
        }

        items[nextFirst] = item;
        nextFirst += -1;
        size += 1;
    }

    @Override
    public void addLast(T item){
        if (nextLast == items.length){
            resize((int) (items.length * 2));
//            zip(zipSpace);
        }
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }


    // from [x x 0] or [0 x x] to [0 0 x x 0]
    private void resize(int capacity){
        T[] tmpItems = (T []) new Object[capacity];
        int space = (int) (capacity - items.length) / 2;
        System.arraycopy(items, 0, tmpItems, space, items.length);
        items = tmpItems;
        nextFirst += space;
        nextLast += space;
    }

    //
    private void zip(int space){
        if (size / items.length < 0.25){ // if usage factor is lower than 25% in the array items
            T[] tmpItems = (T []) new Object[size + space * 2];
            System.arraycopy(items, nextFirst + 1, tmpItems, space, size);
            items = tmpItems;
            nextFirst = space - 1;
            nextLast = space + size;
        }
    }

    @Override
    public int size(){
       return size;
    }

    @Override
    public void printDeque(){
        int startIndex = nextFirst + 1;
        while (startIndex < nextLast){
            System.out.print(items[startIndex] + " ");
            startIndex += 1;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        if (size > 0){
            zip(zipSpace);
            nextFirst += 1;
            size += -1;
            return items[nextFirst];
        }else {
            return null;
        }

    }

    @Override
    public T removeLast(){
        if (size > 0){
            zip(zipSpace);
            nextLast -= 1;
            size += -1;
            return  items[nextLast];
        }else {
            return null;
        }

    }
    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return items[index + nextFirst + 1];
        }else {
            return null;
        }
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;

        public ArrayDequeIterator(){
            wizPos = 0;
        }

        public boolean hasNext(){
            return wizPos < size;
        }

        public T next(){
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        // class check
        if (!(other instanceof ArrayDeque)){
            return false;
        }
        // size check
        ArrayDeque<T> o = (ArrayDeque<T>) other;
        if (this.size() != o.size()){
            return false;
        }
        // items check
        for (int index=0; index < size; index++){
            if (this.get(index) != o.get(index)){
                return false;
            }
        }
        return true;
    }

}
