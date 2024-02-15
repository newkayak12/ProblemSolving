package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LongJump {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12914
    /**
     * <pre>
     * 효진이는 멀리 뛰기를 연습하고 있습니다.
     * 효진이는 한번에 1칸, 또는 2칸을 뛸 수 있습니다.
     * 칸이 총 4개 있을 때, 효진이는
     *
     * (1칸, 1칸, 1칸, 1칸)
     * (1칸, 2칸, 1칸)
     * (1칸, 1칸, 2칸)
     * (2칸, 1칸, 1칸)
     * (2칸, 2칸)
     *
     * 의 5가지 방법으로 맨 끝 칸에 도달할 수 있습니다.
     * 멀리뛰기에 사용될 칸의 수 n이 주어질 때,
     * 효진이가 끝에 도달하는 방법이 몇 가지인지 알아내,
     * 여기에 1234567를 나눈 나머지를 리턴하는 함수, solution을 완성하세요.
     *
     * 예를 들어 4가 입력된다면, 5를 return하면 됩니다.
     *
     *   제한 사항
     * n은 1 이상, 2000 이하인 정수입니다.
     * </pre>
     */

    @Nested
    class TestCases{
        @Test
        public void case1 () {
            /**
             * (1칸, 1칸, 1칸)
             * (1칸, 2칸)
             * (2칸, 1칸)
             */
            int n = 3;
            int result = 3;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case2 () {
            /**
             * (1칸, 1칸, 1칸, 1칸)
             * (1칸, 2칸, 1칸)
             * (1칸, 1칸, 2칸)
             * (2칸, 1칸, 1칸)
             * (2칸, 2칸)
             */
            int n = 4;
            int result = 5;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case3 () {
            /**
             * (1칸, 1칸, 1칸, 1칸, 1칸)
             * (2칸, 1칸, 1칸, 1칸)
             * (1칸, 2칸, 1칸, 1칸)
             * (1칸, 1칸, 2칸, 1칸)
             * (1칸, 1칸, 1칸, 2칸)
             * (2칸, 2칸, 1칸)
             * (2칸, 1칸, 2칸)
             * (1칸, 2칸, 2칸)
             */
            int n = 5;
            int result = 8;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case4 () {
            /**
             * (1칸, 1칸, 1칸, 1칸, 1칸, 1칸)
             * (2칸, 1칸, 1칸, 1칸, 1칸)
             * (1칸, 2칸, 1칸, 1칸, 1칸)
             * (1칸, 1칸, 2칸, 1칸, 1칸)
             * (1칸, 1칸, 1칸, 2칸, 1칸)
             * (1칸, 1칸, 1칸, 1칸, 2칸)
             *
             * (2칸, 2칸, 1칸, 1칸)
             * (1칸, 1칸, 2칸, 2칸)
             * (2칸, 1칸, 2칸, 1칸)
             * (1칸, 2칸, 1칸, 2칸)
             * (2칸, 1칸, 1칸, 2칸)
             * (1칸, 2칸, 2칸, 1칸)
             *
             * (2칸, 2칸, 2칸)
             */
            int n = 6;
            int result = 13;

            Assertions.assertEquals(result, solution(n));
        }


    }

    /**
     *  n = 3 -> 3
     *  n = 4 -> 5
     *  n = 5 -> 8
     *  n = 6 -> 13
     *
     *  DP?
     */


    public long solution ( int n ) {
        return this.calculation(n,  0,0L, 1L);
    }

    private long calculation (int totalTile, int countNow,  long p1, long p2 ) {
        long div = 1_234_567;
        if ( countNow >= totalTile ) return (p2 % div);
        else return calculation(totalTile, countNow + 1, p2 % div, (p1 + p2) % div);
    }
}
