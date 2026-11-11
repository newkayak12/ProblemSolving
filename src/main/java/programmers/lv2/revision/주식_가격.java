package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import org.junit.jupiter.api.Test;

public class 주식_가격 {

    @Test
    void solution1() {
        int[] prices = {1, 2, 3, 2, 3};
        int[] returnValue = {4, 3, 1, 1, 0};

        assertArrayEquals(returnValue, calculate(prices));
    }

    int[] calculate(int[] prices) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int[] copy = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            while (!arrayDeque.isEmpty() && prices[arrayDeque.getLast()] > prices[i]) {
                int index = arrayDeque.removeLast();
                copy[index] = i - index;
            }

            arrayDeque.addLast(i);
        }

        int max = prices.length - 1;

        while (!arrayDeque.isEmpty()) {
            int index = arrayDeque.removeLast();
            copy[index] = max - index;
        }

        return copy;
    }
}
