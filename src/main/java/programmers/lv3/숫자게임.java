package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 숫자게임 {

    /**
     * 최대 -> Greedy? -> 8,6,2,2 가 아니라 패턴상 아닐것으로 보임 -> DFS?
     */

    private int recursive(int[] A, int[] B, boolean[] visit, int index, int beforeScore) {
        if (index >= B.length) {
            return beforeScore;
        }

        int score = beforeScore;
        for (int i = index + 1; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (visit[j]) {
                    continue;
                }
                visit[j] = true;
                int delta = A[i] < B[j] ? 1 : 0;
                score = Math.max(recursive(A, B, visit, index + 1, beforeScore + delta), score);
                visit[j] = false;
            }
        }

        return score;
    }

    public int solutionBack(int[] A, int[] B) {
        int score = recursive(A, B, new boolean[B.length], 0, 0);
        return score;
    }

    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int totalScore = 0;
        int bIndex = 0;

        for (int i = 0; i < A.length; i++) {

            while (bIndex < B.length) {

                if (A[i] < B[bIndex]) {
                    totalScore++;
                    bIndex++;
                    break;
                }

                bIndex++;
            }
        }

        return totalScore;
    }


    @Test
    void test1() {
        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};
        int result = 3;

        assertEquals(result, solution(A, B));
    }

    @Test
    void test2() {
        int[] A = {2, 2, 2, 2};
        int[] B = {1, 1, 1, 1};
        int result = 0;

        assertEquals(result, solution(A, B));
    }
}
