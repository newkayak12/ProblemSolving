package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class 줄_서는_방법 {

    @Test
    void solution1() {
        int n = 3;
        int k = 5;

        int[] result = new int[]{3, 1, 2};

        assertArrayEquals(result, calculate(n, k));
    }


    private long factorial(long n) {
        if (n <= 0) return 1;
        long number = n;

        for(long i = n - 1; i > 1; i--) {
            number *= i;
        }
        return number;
    }

    private int[] calculate(int n, long k) {
        long size = k - 1;
        List<Integer> reference = new ArrayList<>();
        long[] factorials = new long[n + 1];
        factorials[0] = 1;

        for(int i = 1; i <= n; i++) {
            reference.add(i);
            factorials[i] = factorials[i - 1] * i;
        }

        int[] sequence = new int[reference.size()];

        for(int i = 1; i <= n; i++) {
            long factorialValue = factorials[n - i];
            long div = size/factorialValue;
            size = size % factorialValue;


            sequence[i - 1] = reference.remove((int) div);
        }


        return sequence;
    }
}
