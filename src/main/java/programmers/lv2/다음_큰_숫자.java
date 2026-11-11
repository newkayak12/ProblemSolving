package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 다음_큰_숫자 {

    @Test
    public void solution1() {
        int n = 78;
        int expected = 83;

        assertEquals(expected, calculate(n));
    }

    @Test
    public void solution2() {
        int n = 15;
        int expected = 23;

        assertEquals(expected, calculate(n));
    }

    private int calculate(int n) {
        int answer = n;
        int count = Integer.bitCount(n);
        while(true) {
            answer ++;
            if(Integer.bitCount(answer) == count) {
                break;
            }
        }

        return answer;
    }

}
