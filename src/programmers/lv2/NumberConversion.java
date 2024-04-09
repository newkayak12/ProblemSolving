package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NumberConversion {
    //https://school.programmers.co.kr/learn/courses/30/lessons/154538
    /***
     * 자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.
     *
     * x에 n을 더합니다
     * x에 2를 곱합니다.
     * x에 3을 곱합니다.
     *
     * 자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해
     * 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요.
     * 이때 x를 y로 만들 수 없다면 -1을 return 해주세요.
     *
     *
     * 제한사항
     * - 1 ≤ x ≤ y ≤ 1,000,000
     * - 1 ≤ n < y
     */
    @Nested
    class TestCases {
     @Test
     public void case1 () {
         int x = 10;
         int y = 40;
         int n = 5;
         int result = 2;

         Assertions.assertEquals(result, solution(x, y, n));
     }
        @Test
        public void case2 () {
            int x = 10;
            int y = 40;
            int n = 30;
            int result = 1;

            Assertions.assertEquals(result, solution(x, y, n));
        }
        @Test
        public void case3 () {
            int x = 2;
            int y = 5;
            int n = 4;
            int result = -1;

            Assertions.assertEquals(result, solution(x, y, n));
        }
    }

    public int solution(int x, int y, int n) {
        return this.calc(x, y, n, 0);
    }
    private int calc( int x, int y, int n, int count) {

    }
}
