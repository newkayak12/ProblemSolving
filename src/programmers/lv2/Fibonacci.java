package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
    // https://school.programmers.co.kr/learn/courses/30/lessons/12945
    /**
     *
     * 피보나치 수는 F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2) 가 적용되는 수 입니다.
     *
     *   예를들어
     *   F(2) = F(0) + F(1) = 0 + 1 = 1
     *   F(3) = F(1) + F(2) = 1 + 1 = 2
     *   F(4) = F(2) + F(3) = 1 + 2 = 3
     *   F(5) = F(3) + F(4) = 2 + 3 = 5
     *
     * 와 같이 이어집니다.
     * 2 이상의 n이 입력되었을 때, n번째 피보나치 수를 1234567으로 나눈 나머지를 리턴하는 함수,
     * solution을 완성해 주세요.
     *
     * 제한 사항
     *   n은 2 이상 100,000 이하인 자연수입니다.
     */

    @Nested
    class TestCase {
        @Test
        public void case1 () {
            int n = 3;
            int result = 2;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case2 () {
            int n = 5;
            int result = 5;
            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case3 () {
            int n = 100_000;
            int result = 174725;
            Assertions.assertEquals(result, solution(n));
        }
    }

    public int solution( int n ){
        return (int)(this.noRecursive(n) % 1234567);
    }
    private int fibonacci(int n ) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        else return fibonacci((n - 1) % 1234567) + fibonacci((n - 2)  % 1234567 );
    }
    private long noRecursive ( int n ) {
        long i = 1;

        long now = 0;
        long next = 1;

        while ( i < n ) {
            long tmp = next;
            next += now;
            now = tmp;

            i++;
        }


        return next;
    }

    class Success {




        public int solution( int n ) {
            return  (int) (fib4(n) % 1234567);
        }


        /**
         * stackOverFlow를 피할수 없음
         * 그래서 modulo 연산의 특징을 이용해서 풀이
         */
        private long fib4 (  int end ){
            long start = 1;

            long prev = 0;
            long next = 1;

            while ( start < end ) {
                long tmp = next;
                next = (prev % 1234567) + (next % 1234567);
                prev = tmp;

                start++;
            }


            return next;
        }



        @Nested
        public class FibonacciTestCase3 {
            @Test
            public void fibTest0 () {
                int n = 2;
                System.out.println(fib3(n));
            }
            @Test
            public void fibTest1 () {
                int n = 3;
                System.out.println(fib3(n));
            }
            @Test
            public void fibTest2 () {
                int n = 5;
                System.out.println(fib3(n));
            }
            @Test
            public void fibTest3 () {
                int n = 9;
                System.out.println(fib3(n));
            }
            @Test
            public void fibTest4 () {
                int n = 10;
                System.out.println(fib3(n));
            }
            @Test
            public void fibTestMax () {
                int n = 100_000;
                System.out.println(fib3(n));
            }
        }
        private long fib3 (  int end ){
            long prev = 0;
            long next = 1;
            long start = 1;

            while ( start < end ) {
                long tmp = next;
                next = prev + next;
                prev = tmp;

                start++;
            }


            return next;
        }

        @Nested
        public class FibonacciTestCase2 {
            @Test
            public void fibTest0 () {
                int n = 2;
                System.out.println(fib2(0, 0, 1, n));
            }
            @Test
            public void fibTest1 () {
                int n = 3;
                System.out.println(fib2(0, 0, 1, n));
            }
            @Test
            public void fibTest2 () {
                int n = 5;
                System.out.println(fib2(0, 0, 1, n));
            }
            @Test
            public void fibTest3 () {
                int n = 9;
                System.out.println(fib2(0, 0, 1, n));
            }
            @Test
            public void fibTest4 () {
                int n = 10;
                System.out.println(fib2(0, 0, 1, n));
            }
            @Test
            public void fibTestMax () {
                int n = 100_000;
                System.out.println(fib2(0, 0, 1, n));
            }
        }
        private int fib2 ( int prev, int next, int start, int end ){

            if( start == end ) return prev + next;
            else if (start == 1) return fib2(next, prev + next + 1, start + 1, end);
            else return fib2(next, prev + next, start + 1, end);
        }

        @Nested
        public class FibonacciTestCase {

            /**
             * [0] 0
             * [1] 1
             * [2] 1 (0 + 1)
             * [3] 2 (1 + 1)
             * [4] 3 (1 + 2)
             * [5] 5 (2 + 3)
             * [6] 8 (3 + 5)
             * [7] 13 (5 + 8)
             * [8] 21 (8 + 13)
             * [9] 34 (13 + 21)
             * [10] 55 (21 + 34)
             */

            @Test
            public void fibTest0 () {
                List< Long > fibArr = new ArrayList<>();
                fibArr.add(0L);
                fibArr.add(1L);
                int n = 2;
                System.out.println(fib(n, 0, fibArr));
            }
            @Test
            public void fibTest1 () {
                List< Long > fibArr = new ArrayList<>();
                fibArr.add(0L);
                fibArr.add(1L);
                int n = 3;
                System.out.println(fib(n, 0, fibArr));
            }
            @Test
            public void fibTest2 () {
                List< Long > fibArr = new ArrayList<>();
                fibArr.add(0L);
                fibArr.add(1L);
                int n = 5;
                System.out.println(fib(n, 0, fibArr));
            }
            @Test
            public void fibTest3 () {
                List< Long > fibArr = new ArrayList<>();
                fibArr.add(0L);
                fibArr.add(1L);
                int n = 9;
                System.out.println(fib(n, 0, fibArr));
            }
            @Test
            public void fibTest4 () {
                List< Long > fibArr = new ArrayList<>();
                fibArr.add(0L);
                fibArr.add(1L);
                int n = 10;
                System.out.println(fib(n, 0, fibArr));
            }
            @Test
            public void fibTestMax () {
                List< Long > fibArr = new ArrayList<>();
                fibArr.add(0L);
                fibArr.add(1L);
                int n = 100_000;
                System.out.println(fib(n, 0, fibArr));
            }
        }
        private Long fib (int end, int idx, List<Long> arr) {

            if ( idx >= 2) {
                Long first = arr.get(idx - 2);
                Long second = arr.get(idx - 1);
                arr.add(first + second);
            }

            if ( idx == end ) return arr.get(end);
            else return fib(end, idx + 1, arr);
        }

    }
}
