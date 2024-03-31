package deque;

import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {
    public static class IntComparator implements Comparator<Integer> {
        // > 0: int1 > int2
        @Override
        public int compare(Integer int1, Integer int2) {
            return int1 - int2;
        }
    }

    public static class StrComparator implements Comparator<String> {

        // > 0: str1 is longer than str2
        // = 0: same length
        @Override
        public int compare(String str1, String str2) {
            if (str1.length() > str2.length()) {
                return 1;
            } else if (str1.length() < str2.length()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
