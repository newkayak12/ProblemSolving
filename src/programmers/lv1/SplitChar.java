package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SplitChar {
    //https://school.programmers.co.kr/learn/courses/30/lessons/140108

    /**
     * 문자열 s가 입력되었을 때 다음 규칙을 따라서 이 문자열을 여러 문자열로 분해하려고 합니다.
     *
     * 먼저 첫 글자를 읽습니다. 이 글자를 x라고 합시다.
     *
     *  이제 이 문자열을 왼쪽에서 오른쪽으로 읽어나가면서,
     * x와 x가 아닌 다른 글자들이 나온 횟수를 각각 셉니다.
     *
     * 처음으로 두 횟수가 같아지는 순간 멈추고, 지금까지 읽은 문자열을 분리합니다.
     *
     * s에서 분리한 문자열을 빼고 남은 부분에 대해서 이 과정을 반복합니다.
     * 남은 부분이 없다면 종료합니다.
     *
     * 만약 두 횟수가 다른 상태에서 더 이상 읽을 글자가 없다면,
     * 역시 지금까지 읽은 문자열을 분리하고, 종료합니다.
     *
     * 문자열 s가 매개변수로 주어질 때,
     * 위 과정과 같이 문자열들로 분해하고,
     * 분해한 문자열의 개수를 return 하는 함수 solution을 완성하세요.
     */


    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String s = "banana";
            int expect = 3;
            Assertions.assertEquals(expect, solution(s));
        }

        @Test
        public void case2 () {
            String s = "abracadabra";
            int expect = 6;
            Assertions.assertEquals(expect, solution(s));
        }
        @Test
        public void case3 () {
            String s = "aaabbaccccabba";
            int expect = 3;
            Assertions.assertEquals(expect, solution(s));
        }

        @Test
        public void case4 () {
            String s = "aaba";
            int expect = 0;
            Assertions.assertEquals(expect, solution(s));
        }
        @Test
        public void case5 () {
            String s = "baaa";
            int expect = 2;
            Assertions.assertEquals(expect, solution(s));
        }

        @Test
        public void case6 () {
            String s = "aaaaaa";
            int expect = 1;
            Assertions.assertEquals(expect, solution(s));
        }

        @Test
        public void case7 () {
            String s = "a";
            int expect = 1;
            Assertions.assertEquals(expect, solution(s));
        }

        @Test
        public void case8 () {
            String s = "abaabc";
            int expect = 4;
            Assertions.assertEquals(expect, solution(s));
        }
    }

    public static int solution (String s) {
        return tasker(s, 0);
    }

    private static int tasker (String s, int count) {
        if( s.length() == 0 ) return count;

        System.out.println(s);

        char selected = s.charAt(0);
        char[] charArr = s.toCharArray();

        int same = 0;
        int diff = 0;




        loop : for ( char charac : charArr) {
            if( charac == selected ) same += 1;
            else diff += 1;

            if ( same == diff ) {
                count += 1;
                break loop;
            }
        }


      if (diff != same) count +=1 ;

        return tasker(s.substring(same+diff), count);
    }
}
