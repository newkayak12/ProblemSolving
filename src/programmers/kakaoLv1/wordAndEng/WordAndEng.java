package programmers.kakaoLv1.wordAndEng;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class WordAndEng {
    /**
     * 다음은 숫자의 일부 자릿수를 영단어로 바꾸는 예시입니다.
     * 1478 → "one4seveneight"
     * 234567 → "23four5six7"
     * 10203 → "1zerotwozero3"
     *
     *
     * 숫자	영단어
     * 0	zero
     * 1	one
     * 2	two
     * 3	three
     * 4	four
     * 5	five
     * 6	six
     * 7	seven
     * 8	eight
     * 9	nine
     */


    private static final Map<String, Integer> mapping = Map.of(
            	"zero",0,
            	"one",1,
            	"two",2,
            	"three",3,
            	"four",4,
            	"five",5,
            	"six",6,
            	"seven",7,
            	"eight",8,
            	"nine",9
    );

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String input = "one4seveneight";
            int expected = 1478;
            Assertions.assertEquals(solution(input), expected);


        }
        @Test
        public void case2 () {
            String input = "23four5six7";
            int expected = 234567;
            Assertions.assertEquals(solution(input), expected);
        }
        @Test
        public void case3 () {
            String input = "2three45sixseven";
            int expected = 234567;
            Assertions.assertEquals(solution(input), expected);
        }
        @Test
        public void case4 () {
            String input = "123";
            int expected = 123;
            Assertions.assertEquals(solution(input), expected);
        }
    }

    public static int solution( String s ) {
        String answer = new String(s);
        for ( String key : mapping.keySet() ) {
            answer = answer.replaceAll(key, String.valueOf(mapping.get(key)));
        };
        return Integer.parseInt(answer);
    }
}
