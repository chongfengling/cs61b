package deque;

public class ArrayDeque<T> implements Deque<T>{

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

    @Override
    public void addFirst(T item){
        if (nextFirst == 0){ // at this time; nextLast - nextFirst = items.length - 1
            resize((int) (items.length * 2));
            zip(10);
        }

        items[nextFirst] = item;
        nextFirst += -1;
        size += 1;
    }

    @Override
    public void addLast(T item){
        if (nextLast == items.length){
            resize((int) (items.length * 2));
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

    private void zip(int space){
        if (size / items.length < 0.25){ // if usage factor is lower than 25% in the array items
            T[] tmpItems = (T []) new Object[size + space];
            System.arraycopy(items, nextFirst, tmpItems, space, size);
            items = tmpItems;
            nextFirst = space;
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

}
