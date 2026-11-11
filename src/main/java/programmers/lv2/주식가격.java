package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 주식가격 {

    @Test
    public void solution1(){
        int[] prices = new int[]{1, 2, 3, 2, 3};
        int[] expected = new int[]{4, 3, 1, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution2(){
        int[] prices = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{4, 3, 2, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution3(){
        int[] prices = new int[]{5, 4, 3, 2, 1};
        int[] expected = new int[]{1, 1, 1, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution4(){
        int[] prices = new int[]{1, 2, 1, 2, 3};
        int[] expected = new int[]{4, 1, 2, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution5(){
        int[] prices = new int[]{1, 1, 1, 1, 1};
        int[] expected = new int[]{4, 3, 2, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution6(){
        int[] prices = new int[]{3,2,1};
        int[] expected = new int[]{1, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution7(){
        int[] prices = new int[]{3,5,2};
        int[] expected = new int[]{2, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution8(){
        int[] prices = new int[]{1, 2, 3, 2, 3};
        int[] expected = new int[]{4, 3, 1, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    @Test
    public void solution9(){
        int[] prices = new int[]{2, 3, 1, 2};
        int[] expected = new int[]{2, 1, 1, 0};

        assertArrayEquals(expected, calculate(prices));
    }

    private int[] calculate(int[] prices) {
        int[] result = new int[prices.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < prices.length - 1; i ++) {
            int next = i + 1;
            stack.addLast(i);
            while( !stack.isEmpty() && prices[stack.peekLast()] > prices[next] ) {
                int idx = stack.removeLast();
                result[idx] = (i + 1) - idx;
            }

        }



        while(!stack.isEmpty()) {
            int index = stack.removeLast();
            int size = prices.length;

            result[index] = size - 1 - index;
        }

        System.out.println(Arrays.toString(result));
        return result;
    }
}
