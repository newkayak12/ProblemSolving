package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MinMax {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12939
    /**
     *<pre>
     * 문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다.
     * str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.
     * 예를 들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.
     *
     * 제한 조건
     *  - s에는 둘 이상의 정수가 공백으로 구분되어 있습니다.
     *</pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String s = "1 2 3 4";
            String returnValue = "1 4";

            Assertions.assertEquals(returnValue, solution(s));
        }

        @Test
        public void case2 () {
            String s = "-1 -2 -3 -4";
            String returnValue = "-4 -1";

            Assertions.assertEquals(returnValue, solution(s));
        }

        @Test
        public void case3 () {
            String s = "-1 -1";
            String returnValue = "-1 -1";

            Assertions.assertEquals(returnValue, solution(s));
        }
    }

    public String solution ( String s ) {
        List<Integer> array = Arrays.stream(s.split(" "))
                                .map(Integer::valueOf)
                                .collect(Collectors.toList());
        Collections.sort(array);

        return String.format("%d %d", array.get(0), array.get(array.size() - 1));
    }

}
