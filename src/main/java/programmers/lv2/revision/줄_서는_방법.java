package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class 줄_서는_방법 {

    @Test
    void test1() {
        int n = 3;
        int k = 5;
        int[] result = {3, 1, 2};

        assertArrayEquals(result, solution(n, k));
    }


    int[] solution(int n, int k) {

        long size = k - 1;//0-index

        List<Integer> numbers = new ArrayList<>();
        long[] factorialTable = new long[n + 1];
        factorialTable[0] = 1;

        for(int i = 1; i <= n; i++) {
            numbers.add(i);
            factorialTable[i] = factorialTable[i - 1] * i;
        }



        int[] sequence = new int[numbers.size()];
        for(int i = 1; i <= n; i++) {
            long factorialValue = factorialTable[n - i];
            long div = size / factorialValue;
            size %= factorialValue;

            sequence[i - 1] = numbers.remove((int) div);
        }

        return sequence;
    }


    private long[] getFactorialTable(int n) {
        long[] factorialTable = new long[n + 1];
        factorialTable[0] = 1;

        for(int i = 1; i <= n; i++) {
            factorialTable[i] = factorialTable[i - 1] * i;
        }

        return factorialTable;
    }
}
