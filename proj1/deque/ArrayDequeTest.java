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

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque ad2 = new ArrayDeque();

        assertTrue("A newly initialized LLDeque should be empty", ad2.isEmpty());
        ad2.addFirst(-1);

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad2.size());
        assertFalse("lld1 should now contain 1 item", ad2.isEmpty());

        ad2.addLast(0);
        assertEquals(2, ad2.size());

        ad2.addLast(1);
        assertEquals(3, ad2.size());

        System.out.println("Printing out deque: ");
        ad2.printDeque();
    }
    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque ad3 = new ArrayDeque();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", ad3.isEmpty());

        ad3.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", ad3.isEmpty());

        ad3.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", ad3.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque ad4 = new ArrayDeque();
        ad4.addFirst(3);

        ad4.removeLast();
        ad4.removeFirst();
        ad4.removeLast();
        ad4.removeFirst();

        int size = ad4.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Tests get the item at the given index */
    public void getTest(){
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque ad5 = new ArrayDeque();

        assertEquals("sss", null, ad5.get(0));

        ad5.addFirst(3);
        int res;
        res= ad5.get(0);
        assertEquals("sss", 3, res);

        ad5.addFirst(10);
        res = ad5.get(0);
        assertEquals("sss", 10, res);

        res = ad5.get(1);
        assertEquals("sss", 3, res);

        ad5.addLast(5);
        res = ad5.get(0);
        assertEquals("sss", 10, res);

        res = ad5.get(1);
        assertEquals("sss", 3, res);

        res = ad5.get(2);
        assertEquals("sss", 5, res);

        assertEquals("sss", null, ad5.get(10));
    }
}
