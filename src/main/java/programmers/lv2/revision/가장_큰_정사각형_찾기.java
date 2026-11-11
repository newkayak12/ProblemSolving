package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 가장_큰_정사각형_찾기 {


    @Test
    void test1() {
        int[][] board = new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}};
        int answer = 9;

        assertEquals(answer, solution(board));
    }

    @Test
    void test2() {
        int[][] board = new int[][]{{0, 0, 1, 1}, {1, 1, 1, 1}};
        int answer = 4;

        assertEquals(answer, solution(board));
    }

    @Test
    void test3() {
        int[][] board = new int[][]{{1, 1}, {1, 0}};
        int answer = 1;

        assertEquals(answer, solution(board));
    }

    @Test
    void test4() {
        int[][] board = new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        int answer = 9;

        assertEquals(answer, solution(board));
    }


    @Test
    void test5() {
        int[][] board = new int[][]{{1}};
        int answer = 1;

        assertEquals(answer, solution(board));
    }

    @Test
    void test6() {
        int[][] board = new int[][]{{0}};
        int answer = 0;

        assertEquals(answer, solution(board));
    }


    @Test
    void test7() {
        int[][] board = new int[][]{{1}, {1}, {1}};
        int answer = 1;

        assertEquals(answer, solution(board));
    }


    public int solution(int[][] board) {

        /**
         * table에 직접 더하자
         * 인덱스 넘어가면 무시
         * 현재 || 왼쪽 || 아래 || 대각선 == 0 이면 패스
         *   max = 현재 값
         *
         * 대각선 = 현재, 왼쪽, 아래 중 제일 작은 것 + 1
         *   만약 max > 대각선
         *       max = 대각선
         *
         *
         *
         *
         * 0	1	1	1
         * 1	1	1	1
         * 1	1	1	1
         * 0	0	1	0
         *
         * 0	1	1	1
         * 1	1	2	2
         * 1	2	2	3
         * 0	0	1	0
         *
         * 제일 큰 수 제곱
         */


        int max = 0;


        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (row + 1 >= board.length || col + 1 >= board[row].length) {
                    max = Math.max(max, board[row][col]);
                    continue;
                }


                int now = board[row][col];
                int right = board[row][col + 1];
                int bottom = board[row + 1][col];
                int diagonal = board[row + 1][col + 1];

                if (now == 0 || right == 0 || bottom == 0 || diagonal == 0) {
                    continue;
                }

                int sum = Math.min(Math.min(bottom, right), now) + 1;

                board[row + 1][col + 1] = sum;
                if (max < sum) {
                    max = sum;
                }
            }
        }

        return max * max;
    }
}
