package deque;

public class ArrayDeque {

    private int[] items;
    private int size;
    private int nextFirst; // nonzero
    private int nextLast;

    public ArrayDeque(){
        items = new int[6];
        size = 0;
        // circular ArrayDeque
        nextFirst = 1;
        nextLast = 2;
    }
    // from [x x 0] or [0 x x] to [0 0 x x 0]
    private void resize(int capacity){
        int[] tmpItems = new int[capacity];
        int space = (int) (capacity - size) / 2;
        System.arraycopy(items, 0, tmpItems, space, size);
        items = tmpItems;
        nextFirst += space;
        nextLast += space;
    }

}
