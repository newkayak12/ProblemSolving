package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MinimumValue {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12941
    /**
     * <pre>
     * 길이가 같은 배열 A, B 두개가 있습니다. 각 배열은 자연수로 이루어져 있습니다.
     *
     * 배열 A, B에서 각각 한 개의 숫자를 뽑아 두 수를 곱합니다.
     * 이러한 과정을 배열의 길이만큼 반복하며, 두 수를 곱한 값을 누적하여 더합니다.
     *
     * 이때 최종적으로 누적된 값이 최소가 되도록 만드는 것이 목표입니다.
     * (단, 각 배열에서 k번째 숫자를 뽑았다면 다음에 k번째 숫자는 다시 뽑을 수 없습니다.)
     *
     * 예를 들어 A = [1, 4, 2] , B = [5, 4, 4] 라면
     *
     *   -   A에서 첫번째 숫자인 1, B에서 첫번째 숫자인 5를 뽑아 곱하여 더합니다.
     *      (누적된 값 : 0 + 5(1x5) = 5)
     *
     *   -   A에서 두번째 숫자인 4, B에서 세번째 숫자인 4를 뽑아 곱하여 더합니다.
     *      (누적된 값 : 5 + 16(4x4) = 21)
     *
     *   -   A에서 세번째 숫자인 2, B에서 두번째 숫자인 4를 뽑아 곱하여 더합니다.
     *      (누적된 값 : 21 + 8(2x4) = 29)
     *
     * 즉, 이 경우가 최소가 되므로 29를 return 합니다.
     *
     * 배열 A, B가 주어질 때 최종적으로 누적된
     * 최솟값을 return 하는 solution 함수를 완성해 주세요.
     *
     * 제한사항
     *   -  배열 A, B의 크기 : 1,000 이하의 자연수
     *   -  배열 A, B의 원소의 크기 : 1,000 이하의 자연수
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] A = {1, 4, 2};
            int[] B = {5, 4, 4};
            int answer = 29;

            Assertions.assertEquals(answer, solution(A, B));
        }

        @Test
        public void case2 () {
            int[] A = {1, 2};
            int[] B = {3, 4};
            int answer = 10;

            Assertions.assertEquals(answer, solution(A, B));
        }
    }

    public int solution( int[] A, int[] B ) {
        Arrays.sort(A);
        Arrays.sort(B);

        return Math.min(getMin(A, B), getMin(B, A));
    }

    private int getMin( int[] outer, int[] inner ) {
        int result = 0;
        for ( int i = 0; i < outer.length; i ++ ) {
            result += outer[i] * inner[outer.length - 1  - i];
        }

        return result;
    }
}
