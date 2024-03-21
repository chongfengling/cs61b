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

    public void addFirst(int item){
        if (nextFirst < 0){ // at this time; nextLast - nextFirst = items.length - 1
            resize(size + 10);
        }

        items[nextFirst] = item;
        nextFirst += -1;
        size += 1;
    }

    public void addLast(int item){
        if (nextLast >= items.length){
            resize(size + 10);
        }
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
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

    public void printDeque(){
        int startIndex = nextFirst + 1;
        while (startIndex < nextLast){
            System.out.print(items[startIndex] + " ");
            startIndex += 1;
        }
        System.out.println();

    }

}
