package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 최솟값_만들기 {


    @Test
    public void solution1(){
        Integer[] A = new Integer[]{1, 4, 2};
        Integer[] B = new Integer[]{5, 4, 4};
        Integer expected = 29;

        assertEquals(expected, calculate(A, B));
    }


    @Test
    public void solution2(){
        Integer[] A = new Integer[]{1, 2};
        Integer[] B = new Integer[]{3, 4};
        Integer expected = 10;

        assertEquals(expected, calculate(A, B));
    }

    private Integer calculate(Integer[] A, Integer[] B){
        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0;

        for(int i = 0; i < A.length; i ++) {
            int a = A[i];
            int b = B[A.length - 1 - i];

            sum += a * b;
        }

        return sum;
    }



}
