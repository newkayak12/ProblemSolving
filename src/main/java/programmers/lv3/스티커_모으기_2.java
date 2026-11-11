package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 스티커_모으기_2 {
    /**
     * 환형
     * 스티커를 고르는 것 자체가 문제
     * X 선택 X 순이니 하나를 선택하면 양 옆은 사용하지 못하도록 변경된다.
     * 가장 많은 스티커를 선택할 수 있는 방법이므로 X 선택 X 스킵 선택 X 이런 케이스는 없을 수도 있을 듯
     *
     * 1 ~ 100000을 매 번 순회하면서 더하면 문제가 있을거다
     *
     *
     * 14, 6, 5, 11, 3, 9, 2, 10 -> 6, 11, 0, 10이 된 이유를 찾아야 한다.
     * DP
     *
     */


    public int solution(int sticker[]) {

        /**
         * 14, 6, 5, 11, 3, 9, 2, 10
         *
         * dp[i] = max( dp[i-1], dp[i-2] + a[i] )
         */

        if(sticker.length == 1) return sticker[0];
        if(sticker.length == 2) return Math.max(sticker[0], sticker[1]);

        int[] copyFirst = Arrays.copyOfRange(sticker, 0, sticker.length - 1);
        int[] copySecond = Arrays.copyOfRange(sticker, 1, sticker.length);

        copyFirst[1] = Math.max(copyFirst[0], copyFirst[1]);
        copySecond[1] = Math.max(copySecond[0], copySecond[1]);

        for(int i = 2; i < copyFirst.length; i++) {
            int beforeOneStep = i - 1;
            int beforeTwoStep = i - 2;

            int pickedCurrent = copyFirst[i] + copyFirst[beforeTwoStep];
            int notPickedCurrent = copyFirst[beforeOneStep];

            copyFirst[i] = Math.max(pickedCurrent, notPickedCurrent);
        }

        for(int i = 2; i < copySecond.length; i++) {
            int beforeOneStep = i - 1;
            int beforeTwoStep = i - 2;

            int pickedCurrent = copySecond[i] + copySecond[beforeTwoStep];
            int notPickedCurrent = copySecond[beforeOneStep];

            copySecond[i] = Math.max(pickedCurrent, notPickedCurrent);
        }

        int firstValue =  Arrays.stream(copyFirst).max().orElse(0);
        int secondValue =  Arrays.stream(copySecond).max().orElse(0);


        return Math.max(firstValue, secondValue);
    }




    @Test
    void test1() {
        int[] sticker = new int[]{14, 6, 5, 11, 3, 9, 2, 10};
        int answer = 36;
        assertEquals(answer, solution(sticker));
    }

    @Test
    void test2() {
        int[] sticker = new int[]{1, 3, 2, 5, 4};
        int answer = 8;
        assertEquals(answer, solution(sticker));
    }
}
