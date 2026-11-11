package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 타일링_2_x_n {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12900?language=javascript
     *
     * n = 1 -> 1
     * n = 2 -> 2
     * n = 3 -> 3
     * n = 4 -> 5
     *
     * fibonacci
     */


    @Test
    public void solution(){
        int n = 4;
        int expected = 5;


        assertEquals(expected, calculate(n));
    }

    private int calculate(int n) {
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return fibonacci(0, 1, 0, n);
        }
    }

    private int fibonacci(int first, int second, int count, int goal) {
       int mod = 1_000_000_007;
       if(count >= goal) return second;
       return fibonacci(second % mod, (first + second) % mod, count + 1, goal);
    }

}
