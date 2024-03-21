package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addTest(){
        ArrayDeque ad1 = new ArrayDeque();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addFirst(4);
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addFirst(10);
        ad1.addLast(8);
        ad1.addLast(9);
        ad1.addLast(10);
        ad1.addLast(11);
        ad1.addLast(0);

        ad1.printDeque();
    }
}
