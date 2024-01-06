package programmers.lv1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
       }

       @Test
        public void case2 () {
           String[] babbling = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
           int result = 2;
       }
    }

    public static int solution( String[] babbling ) {
        String[] language = {"aya", "ye", "woo", "ma"};
        for ( String babb : babbling ) {

        }
    }
}
