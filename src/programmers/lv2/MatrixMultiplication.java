package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class MatrixMultiplication {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12949
    /**
     * <pre>
     *     문제 설명
     *
     * 2차원 행렬 arr1과 arr2를 입력받아, arr1에 arr2를 곱한 결과를 반환하는 함수,
     * solution을 완성해주세요.
     *     제한 조건
     * - 행렬 arr1, arr2의 행과 열의 길이는 2 이상 100 이하입니다.
     * - 행렬 arr1, arr2의 원소는 -10 이상 20 이하인 자연수입니다.
     * - 곱할 수 있는 배열만 주어집니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int[][] arr1 = new int[][] {
                    {1,4},
                    {3,2},
                    {4,1}
            };

            int[][] arr2 = new int[][] {
                    {3,3},
                    {3,3}
            };

            int[][] result = new int[][] {
                    {15,15},
                    {15,15},
                    {15,15}
            };

            Assertions.assertArrayEquals(result, solution(arr1, arr2));
        }

        @Test
        public void case2 () {
            int[][] arr1 = new int[][] {
                    {2,3,2},
                    {4,2,4},
                    {3,1,4}
            };

            int[][] arr2 = new int[][] {
                    {5,4,3},
                    {2,4,1},
                    {3,1,1}
            };

            int[][] result = new int[][] {
                    {22,22,11},
                    {36,28,18},
                    {29,20,14}
            };

            Assertions.assertArrayEquals(result, solution(arr1, arr2));
        }
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        /**
         *  {
         *    {x0y0,  x1y0},
         *    {x0y1,  x1y1},
         *    {x0y2,  x1y2}
         *  }
         *
         *  {
         *    {a0b0,  a1b0},
         *    {a0b1,  a1b1},
         *  }
         *
         *
         *  {
         *      { x0y0 * a0b0 + x1y0 * a0b1, x0y0 * a1b0 + x1y0 * a1b1 },
         *      { x0y1 * a0b0 + x1y1 * a0b1, x0y1 * a1b0 + x1y1 * a1b1 },
         *      { x0y2 * a0b0 + x1y2 * a0b1, x0y2 * a1b0 + x1y2 * a1b1 }
         *  }
         *
         *  r[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0]
         *  r[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1]
         *
         *
         *
         *  r[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0]
         *  r[2][0] = a[2][0] * b[0][0] + a[2][1] * b[1][0]
         *
         *
         *  r[0][i] = a[0][0] * b[0][i] +
         *            a[0][1] * b[1][i]
         *  r[j][0] = a[j][0] * b[0][0] +
         *            a[j][1] * b[1][0]
         *
         *  r[j][i] = a[j][0] * b[0][i] + a[j][1] * b[1][i]
         *
         */

        /**
         * r[0][1] = a[0][0] * b[0][1] +
         *           a[0][1] * b[1][1] +
         *           a[0][2] * b[2][1]
         *
         * r[1][0] = a[1][0] * b[0][0] +
         *           a[1][1] * b[1][0] +
         *           a[1][2] * b[2][0]
         *
         * r[1][1] = a[1][0] * b[0][1] +
         *           a[1][1] * b[1][1] +
         *           a[1][2] * b[2][1]
         */


        int[][] answer = new int[arr1.length][arr2[0].length];

        for( int i = 0; i < answer.length; i ++ ) {
            for (int j = 0; j < answer[0].length; j ++ ) {
                int tmp = 0;

                for( int k = 0; k < arr2.length; k++ ) {
                    tmp += arr1[i][k] * arr2[k][j];
                }


                answer[i][j] = tmp;
            }
        }

        return answer;
    }

}
