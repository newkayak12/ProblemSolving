package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class _124_나라의_숫자 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12899?language=javascript
     * <p>
     * 1  1 2  2 3  4 ✅ 4  11 5  12 6  14 ✅ 7  21 8  22 9  24 ✅ 10 41 11 42 12 44 ✅ 13 111
     */


    @Test
    void solution1() {

        int n = 1;
        String expected = "1";
        assertEquals(expected, calculate1(n));
        assertEquals(expected, calculate2(n));
    }

    @Test
    void solution2() {

        int n = 2;
        String expected = "2";
        assertEquals(expected, calculate1(n));
        assertEquals(expected, calculate2(n));
    }

    @Test
    void solution3() {

        int n = 3;
        String expected = "4";
        assertEquals(expected, calculate1(n));
        assertEquals(expected, calculate2(n));
    }

    @Test
    void solution4() {

        int n = 4;
        String expected = "11";
        assertEquals(expected, calculate1(n));
        assertEquals(expected, calculate2(n));
    }

    private String calculate1(int n) {
        String[] num = {"4","1","2"};
        String answer = "";

        while(n > 0){
            answer = num[n % 3] + answer;
            n = (n - 1) / 3;
        }
        return answer;
    }

    private String calculate2(int n) {
        int number = n;
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            if(number % 3 == 0) {
                result.append(4);
                number =  number / 3 - 1;
            }
            else {
                result.append(number % 3 == 1 ? "1" : "2");
                number /= 3;
            }
        }

        return result.reverse().toString();
    }
}
