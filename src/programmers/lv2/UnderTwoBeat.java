package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UnderTwoBeat {
    //https://school.programmers.co.kr/learn/courses/30/lessons/77885
    /**
     * <pre>
     * 양의 정수 x에 대한 함수 f(x)를 다음과 같이 정의합니다.
     * x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수
     *
     * 예를 들어,
     *
     * f(2) = 3 입니다. 다음 표와 같이 2보다 큰 수들 중에서 비트가
     * 다른 지점이 2개 이하이면서 제일 작은 수가 3이기 때문입니다.
     *
     * 수	비트	다른      비트의 개수
     * 2	000...0010
     * 3	000...0011	    1
     *
     * f(7) = 11 입니다.
     * 다음 표와 같이 7보다 큰 수들 중에서 비트가 다른 지점이 2개 이하이면서
     * 제일 작은 수가 11이기 때문입니다.
     *
     * 수	비트	다른    비트의 개수
     * 7	000...0111
     * 8	000...1000	  4
     * 9	000...1001	  3
     * 10	000...1010	  3
     * 11	000...1011	  2
     *
     * 정수들이 담긴 배열 numbers가 매개변수로 주어집니다.
     * numbers의 모든 수들에 대하여 각 수의 f 값을 배열에 차례대로 담아
     * return 하도록 solution 함수를 완성해주세요.
     *
     *
     * 제한사항
     * - 1 ≤ numbers의 길이 ≤ 100,000
     * - 0 ≤ numbers의 모든 수 ≤ 1015
     * </pre>
     */
    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            long[] numbers = new long[]{2, 7};
            long[] result  = new long[]{3, 11};

            Assertions.assertArrayEquals(result, solution(numbers));
        }

        @Test
        public void case2 () {
            // 1     1
            // 2    10
            // 3    11
            // 4   100
            // 5   101
            // 6   110
            // 7   111
            // 8  1000
            // 9  1001
            // 10 1010
            // 11 1001
            // 12 1100
            // 13 1011
            // 14 1110
            // 15 1111


//            f(1) = 2
//            f(2) = 3
//            f(3) = 5
//            f(4) = 5
//            f(7) = 11
//            f(8) = 9
//            f(9) = 10
//            f(10) = 11
//            f(11) = 12
//            f(12) = 13
//            f(13) = 14

            /**
             * f(3) = 5
             * f(7) = 11
             * f(9) = 10
             * f(11) = 14
             * f(13) + 14
             */

            long[] numbers = new long[]{3, 6};
            long[] result  = new long[]{5, 11};

            Assertions.assertArrayEquals(result, solution(numbers));
        }

        @Test
        @Disabled
        public void bit () {
            for ( int i = 2; i < 10; i ++) {
                System.out.println(Integer.toBinaryString(i));
            }
        }
        @Test
        @Disabled
        public void pow () {
            System.out.println(Math.pow(2, 0));
        }

    }


    public long[] solution(long[] numbers) {
        //짝수면  최상단 비트를 1개 이동
        //홀수면  + 1
        long[] answer = new long[numbers.length];

        for ( int i = 0; i < numbers.length; i ++ ) {
            long number = numbers[i];
            if( number % 2 == 0) answer[i] = number + 1;
            else {
              long ref = number + 1;
              while (true) {
                  System.out.println(ref +" // "+ number + " // " +Long.bitCount((ref ^ number)));
                  if( Long.bitCount((ref ^ number)) <= 2) {
                      numbers[i] = ref;
                      System.out.println(ref);
                      break;
                  }
                  ref += 1;
              }
            }
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }
    class Success {
        private long bitLength( long number ) {
            long count = 0;
            while( number > 0) {
                count += number & 1;
                number >>= 1;
            }
            return count;
        }
        public long[] solution(long[] numbers) {
            long[] answer = new long[numbers.length];

            for( int i = 0; i < numbers.length; i ++ ) {
                long number = numbers[i];
                long upper = 0;
                while (true){
                    long result = number ^ (number + (long) Math.pow(2, upper*1.0));
                    long count = this.bitLength(result);

                    if( count == 1||count==2 ) break;
                    else upper += 1;
                }

                answer[i] = number + (long) Math.pow(2, upper*1.0);
            }

            return answer;
        }
    }

}
