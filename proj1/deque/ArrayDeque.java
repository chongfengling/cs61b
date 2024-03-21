package deque;

public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst; // nonzero
    private int nextLast;

    public ArrayDeque(){
        items = (T []) new Object[6];
        size = 0;
        // circular ArrayDeque
        nextFirst = 1;
        nextLast = 2;
    }

    public void addFirst(T item){
        if (nextFirst == 0){ // at this time; nextLast - nextFirst = items.length - 1
            resize(items.length + 10);
        }

        items[nextFirst] = item;
        nextFirst += -1;
        size += 1;
    }

    public void addLast(T item){
        if (nextLast == items.length){
            resize(items.length + 10);
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

    public void add(int item){

    }

    public void remove(int index){

    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
       return size;
    }

    public void printDeque(){
        int startIndex = nextFirst + 1;
        while (startIndex < nextLast){
            System.out.print(items[startIndex] + " ");
            startIndex += 1;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (size > 0){
            nextFirst += 1;
            size += -1;
            return items[nextFirst -1];
        }else {
            return null;
        }

    }

    public T removeLast(){
        if (size > 0){
            nextLast -= 1;
            size += -1;
            return  items[nextLast + 1];
        }else {
            return null;
        }

    }
    public T get(int index) {
        if (index >= 0 && index < size) {
            return items[index + nextFirst + 1];
        }else {
            return null;
        }
    }

}
