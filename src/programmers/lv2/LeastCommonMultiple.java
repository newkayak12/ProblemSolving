package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LeastCommonMultiple {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12953
    /**
     * 두 수의 최소공배수(Least Common Multiple)란 입력된 두 수의 배수 중
     * 공통이 되는 가장 작은 숫자를 의미합니다.
     *
     * 예를 들어 2와 7의 최소공배수는 14가 됩니다.
     * 정의를 확장해서, n개의 수의 최소공배수는 n 개의 수들의 배수 중
     * 공통이 되는 가장 작은 숫자가 됩니다. n개의 숫자를 담은 배열 arr이 입력되었을 때
     * 이 수들의 최소공배수를 반환하는 함수, solution을 완성해 주세요.
     *
     *    제한 사항
     *  - arr은 길이 1이상, 15이하인 배열입니다.
     *  - arr의 원소는 100 이하인 자연수입니다.
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] arr = {2,6,8,14};
            int result = 168;

            Assertions.assertEquals(result, solution(arr));
        }

        @Test
        public void case2 () {
            int[] arr = {1,2,3};
            int result = 6;

            Assertions.assertEquals(result, solution(arr));
        }
    }

    public int solution( int[] arr ) {
        Arrays.sort(arr);
        int common = lcm(arr[arr.length - 1], arr[arr.length - 2]);
        for (int i  = arr.length - 3; i > 0; i -- ) {
            common = lcm(common, arr[i]);
        }
        return common;
    }
    private int gcd( int a, int b ) {
        if( a % b == 0 ) return b;
        else return gcd ( b, a % b );
    }
    public int lcm ( int a, int b ) {
        return (a * b) / gcd(a, b);
    }
}
