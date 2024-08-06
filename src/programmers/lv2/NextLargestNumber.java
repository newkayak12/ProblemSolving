package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NextLargestNumber {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12911
    /**
     * <pre>
     *     문제 설명
     *
     * 자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.
     *      -  조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
     *      -  조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
     *      -  조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
     * 예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.
     *
     * 자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.
     *
     * 제한 사항
     *      - n은 1,000,000 이하의 자연수 입니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int n = 78;      //1001110  64 + 0 +  0 + 8 + 4 + 2 + 0
            int result = 83; //1010011  64 + 0 + 16 + 0 + 0 + 2 + 1

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case2 () {
            int n = 15;       //1111   0 + 8 + 4 + 2 + 1
            int result = 23; //10111  16 + 0 + 4 + 2 + 1
            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case3 () {
            int n = 8;       // 1000
            int result = 16; //10000
            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case4 () {
            int n = 10;      // 1010
            int result = 12; // 1100
            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case5 () {
            int n = 12;      //  1100
            int result = 17; // 10001
            Assertions.assertEquals(result, solution(n));
        }
        /**
         * 1이 한 개면 무조건  원본 * 2
         * 1이 두 개고 1을 옮길 수 있으면 그 다음 것을 하나 올려야 한다. 원본 + 2^(length - 1) - 2^(length - 2)
         * 1이 두 개고 1을 옮길 수 없으면 2^n+1 + 1
         */

        @Test
        public void test () {

        }
    }


    private int popcount(int x) {
        int m1  = 0x55555555;
        int m2  = 0x33333333;
        int m4  = 0x0f0f0f0f;
        int m8  = 0x00ff00ff;
        int m16 = 0x0000ffff;
        System.out.println(Integer.toBinaryString(x) + " "+x);
        x = (x & m1 ) + ((x >>  1) & m1 );
        System.out.println(Integer.toBinaryString(x) + " "+x);
        x = (x & m2 ) + ((x >>  2) & m2 );
        System.out.println(Integer.toBinaryString(x) + " "+x);
        x = (x & m4 ) + ((x >>  4) & m4 );
        System.out.println(Integer.toBinaryString(x) + " "+x);
        x = (x & m8 ) + ((x >>  8) & m8 );
        System.out.println(Integer.toBinaryString(x) + " "+x);
        x = (x & m16) + ((x >> 16) & m16);
        System.out.println(Integer.toBinaryString(x) + " "+x);
        return x;
    }

    @Test
    public void test () {
        System.out.println(Integer.bitCount(70));
        System.out.println(popcount(70));
        // HD, Figure 5-2
//        i = i - ((i >>> 1) & 0x55555555);
//        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
//        i = (i + (i >>> 4)) & 0x0f0f0f0f;
//        i = i + (i >>> 8);
//        i = i + (i >>> 16);
//        return i & 0x3f;

    }

    public int solution ( int n ) {
        int number = n ;
        int count = Integer.bitCount(n);
        while (true) {
            number += 1;
            if( count == Integer.bitCount(number)){
                break;
            }
        }

        return number;
    }

    class Success {
        public int solution ( int n ) {
            int bitCount = Integer.bitCount(n);
            int answer = n;
            loop: while( true ) {
                answer += 1;
                if( Integer.bitCount(answer) == bitCount ) {
                    break loop;
                }
            }
            return answer;
        }
    }

}
