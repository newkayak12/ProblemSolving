package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 숫자의_표현 {


    @Test
    public void solution1() {
        int n = 15;
        int expected = 4;

        assertEquals(expected, calculate(n));
    }

    @Test
    public void solution2() {
        int n = 1;
        int expected = 1;

        assertEquals(expected, calculate(n));
    }

    public int calculate(int n) {
        int start = 0;
        int end = 1;
        int sum = start + end;
        int count = 0;

        while (start <= end && end <= n) {
            if (sum < n) {
                end++;
                sum += end;
            } else if (sum > n) {
                sum -= start;
                start++;
            } else {
                count++;
                end++;
                sum += end;
            }
        }

        return count;
    }
}
