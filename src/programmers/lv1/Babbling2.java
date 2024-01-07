package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Babbling2 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/133499
    /**
     * 머쓱이는 태어난 지 11개월 된 조카를 돌보고 있습니다.
     * 조카는 아직 "aya", "ye", "woo", "ma" 네 가지 발음과
     * 네 가지 발음을 조합해서 만들 수 있는 발음밖에 하지 못하고
     * 연속해서 같은 발음을 하는 것을 어려워합니다.
     *
     * 문자열 배열 babbling이 매개변수로 주어질 때,
     * 머쓱이의 조카가 발음할 수 있는 단어의 개수를 return 하도록 solution 함수를 완성해주세요.
     */

    @Nested
    public class TestCase {
       @Test
        public void case1 () {
           String[] babbling = {"aya", "yee", "u", "maa"};
           int result = 1;

           Assertions.assertEquals(result, solution(babbling));
       }

       @Test
        public void case2 () {
           String[] babbling = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
           int result = 2;

           Assertions.assertEquals(result, solution(babbling));
       }

        @Test
        public void case3 () {
            String[] babbling = {"zxv", "uasduu", "saf", "gas", "fd"};
            int result = 0;

            Assertions.assertEquals(result, solution(babbling));
        }

        @Test
        public void case4 () { //이 예시가 가장 어이없었음
            /**
             * ymae : ma -> ye
             * ye : ye -> ""
             *
             * 통과 이렇게 되는 부분
             */
            String[] babbling = {"ymae"};
            int result = 0;

            Assertions.assertEquals(result, solution(babbling));
        }

    }

    public static int solution( String[] babbling ) {
        String[] language = {"aya", "ye", "woo", "ma"};
        Pattern continuous = Pattern.compile(
                  "(aya){2,}|(ye){2,}|(woo){2,}|(ma){2,}",
                        Pattern.DOTALL
                        );


        int count = Arrays.stream(babbling)
              .filter(elem -> {
                  Matcher matcher = continuous.matcher(elem);
                  return !matcher.find();
              })
              .map(elem -> {
                  String result = elem;
                  for ( String lang : language ) {
                      result = result.replaceAll(lang, "!");
                      System.out.println(result);
                  }
                  return result.replaceAll("!","")
                               .length() == 0 ? 1 : 0;
              })
              .collect(Collectors.summingInt(value -> value.intValue()));


        return count;
    }
}
