package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class n_진수_게임 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/17687?language=javascript
     *
     * n 진법
     * t 미리 구할 숫자의 갯수
     * m 게임 참기 인원
     * p 튜브 순서
     */


    @Test
    public void solution1() {
        int n = 2;
        int t = 4;
        int m = 2;
        int p = 1;
        String expected = "0111";

        assertEquals(expected, calculate(n, t, m, p));
    }
    @Test
    public void solution2() {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 1;
        String expected =  "02468ACE11111111";

        assertEquals(expected, calculate(n, t, m, p));
    }
    @Test
    public void solution3() {
        int n = 16;
        int t = 16;
        int m = 2;
        int p = 2;
        String expected =  "13579BDF01234567";

        assertEquals(expected, calculate(n, t, m, p));
    }

    private String calculate(int n, int t, int m, int p) {
        StringBuilder builder = new StringBuilder();
        int number = 0;
        int index = 1;
        int sequence = p;

        while(builder.length() < t) {
            String radix =  Integer.toString(number++, n);

            for(Character ch : radix.toCharArray()){
                if(builder.length() == t) break;

                if(index - sequence == 0) {
                    builder.append(ch);
                    sequence += m;
                }

                index ++;
            }
        }

        return builder.toString().toUpperCase();
    }
}
