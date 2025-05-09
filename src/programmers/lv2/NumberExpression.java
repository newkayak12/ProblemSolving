package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NumberExpression {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12924
    /**
     * <pre>
     * Finn은 요즘 수학공부에 빠져 있습니다. 수학 공부를 하던 Finn은 자연수 n을 연속한 자연수들로 표현 하는 방법이 여러개라는 사실을 알게 되었습니다. 예를들어 15는 다음과 같이 4가지로 표현 할 수 있습니다.
     *    - 1 + 2 + 3 + 4 + 5 = 15
     *    - 4 + 5 + 6 = 15
     *    - 7 + 8 = 15
     *    - 15 = 15
     * 자연수 n이 매개변수로 주어질 때, 연속된 자연수들로 n을 표현하는 방법의 수를 return하는 solution를 완성해주세요.
     *
     * > 제한사항
     * n은 10,000 이하의 자연수 입니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int n = 15;
            int result = 4;

            Assertions.assertEquals(result, solution(n));
        }
    }

    public int solution ( int n ) {
        int count = 0;
        for( int i = n; i > 0; i --) {
            int number = n;

            for( int j = i; j > 0; j --) {
                number -= j;
                if(number == 0) {
                    count += 1;
                    break;
                }
                else if ( number < 0) break;
            }
        }

        return count;
    }


    class Success {
        public int solution ( int n ) {
        int count = 1;
        for ( int i = n - 1; i > 0; i-- ) {
            int now = i;
          inner:  for ( int j = i - 1; j > 0; j--) {
                now += j;
                if( now == n ) count += 1;
                else if ( now > n) break inner;
            }
        }

        return count;
    }
    }
}
