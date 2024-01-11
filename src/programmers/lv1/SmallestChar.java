package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SmallestChar {
//https://school.programmers.co.kr/learn/courses/30/lessons/147355

    /**
     *
     * <pre>
     *   숫자로 이루어진 문자열 t와 p가 주어질 때,
     *   t에서 p와 길이가 같은 부분문자열 중에서,
     *   이 부분문자열이 나타내는 수가 p가 나타내는 수보다 작거나 같은 것이
     *   나오는 횟수를 return하는 함수 solution을 완성하세요.
     *
     *
     *   예를 들어, t="3141592"이고 p="271" 인 경우,
     *   t의 길이가 3인 부분 문자열은 314, 141, 415, 159, 592입니다.
     *
     *   이 문자열이 나타내는 수 중 271보다 작거나 같은 수는 141, 159 2개 입니다.
     *
     *    - 1 ≤ p의 길이 ≤ 18
     *    - p의 길이 ≤ t의 길이 ≤ 10,000
     *    - t와 p는 숫자로만 이루어진 문자열이며, 0으로 시작하지 않습니다.
     * </pre>
     *
     *
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String t = "3141592";
            String p = "271";
            int result = 2;
            Assertions.assertEquals(result, solution(t, p));
        }

        @Test
        public void case2 () {
            String t = "500220839878";
            String p = "7";
            int result = 8;
            Assertions.assertEquals(result, solution(t, p));
        }

        @Test
        public void case3 () {
            String t = "10203";
            String p = "15";
            int result = 3;
            Assertions.assertEquals(result, solution(t, p));
        }
    }

    public static int solution (String t, String p) {
        long length = p.length();
        long numberP = Long.parseLong(p);
        int count = 0;

        for ( int i = 0; i < t.length() - length + 1; i ++ ) {
            long number = Long.parseLong(t.substring(i, i + (int)length));
            if ( number <= numberP ) count += 1 ;
        }


        return count;
    }
}
