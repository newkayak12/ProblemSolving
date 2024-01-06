package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class RemainOne {

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int n = 10;
            int expect = 3;

            Assertions.assertEquals(expect, solution(n));
        }

        @Test
        public void case2 () {
            int n = 12;
            int expect = 11;

            Assertions.assertEquals(expect, solution(n));
        }
    }

    private int solution (int n ) {
        int answer = 0;

        if( ( 3 <= n ) ||( n <= 1_000_000 ) ) {

            while ( answer == 0 || n % answer != 1 ) {
                answer += 1;
            }

        }

        return answer;
    }
}
