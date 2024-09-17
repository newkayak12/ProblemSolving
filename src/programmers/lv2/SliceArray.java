package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

public class SliceArray {

    //https://school.programmers.co.kr/learn/courses/30/lessons/87390
    /**
     * <pre>
     * 정수 n, left, right가 주어집니다. 다음 과정을 거쳐서 1차원 배열을 만들고자 합니다.
     *
     *  1. n행 n열 크기의 비어있는 2차원 배열을 만듭니다.
     *  2, i = 1, 2, 3, ..., n에 대해서, 다음 과정을 반복합니다.
     *       - 1행 1열부터 i행 i열까지의 영역 내의 모든 빈 칸을 숫자 i로 채웁니다.
     *  3. 1행, 2행, ..., n행을 잘라내어 모두 이어붙인 새로운 1차원 배열을 만듭니다.
     *  4. 새로운 1차원 배열을 arr이라 할 때,
     *  arr[left], arr[left+1], ..., arr[right]만 남기고 나머지는 지웁니다.
     *
     * 정수 n, left, right가 매개변수로 주어집니다.
     * 주어진 과정대로 만들어진 1차원 배열을 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     * - 1 ≤ n ≤ 107
     * - 0 ≤ left ≤ right < n2
     * - right - left < 105
     * </pre>
     */

    //TwoPointer

    @Nested
    class TestCases {
        @Test
        public void case1() {
            int n = 3;
            int left = 2;
            int right = 5;
            int[] result = new int[] { 3, 2, 2, 3 };
            // 1 2 3  / 0
            // 2 2 3  / 4
            // 3 3 3  / 8

            Assertions.assertArrayEquals(result, solution(n, left, right));
        }

        @Test
        public void case2() {
            int n = 4;
            int left = 7;
            int right = 14;
            int[] result = new int[] { 4, 3, 3, 3, 4, 4, 4, 4 };

            // 1 2 3 4 /  0
            // 2 2 3 4 /  5
            // 3 3 3 4 /  10
            // 4 4 4 4 /  15
            Assertions.assertArrayEquals(result, solution(n, left, right));
        }
    }

    public int[] solution(int n, long left, long right) {
        int[] result = new int[(int) (right - left) + 1];
        for (long i = 0; i < result.length ; i ++ ) {
            int x = (int) (i + left) % n;
            int y = (int) (i + left) / n;

            result[(int) i] = Math.max(x, y) + 1;
        }
        return result;
    }

    class SuccessSecond {
        public int[] solution(int n, long left, long right) {
            int[] result = new int[(int) (right - left) + 1];
            for( int i = 0; i < result.length; i ++ ) {
                int height = (int)(( i + left) / n) + 1;
                int width = (int)((i + left) % n) + 1;

                result[i] = Math.max(height, width);
                //이차원 만들던 점화식을 그냥 바로 대입
            }
            return result;
        }

        public int[] solutionTimeOut(int n, long left, long right) {
            int[][] matrix = new int[n][n];
            int[] result = new int[(int) (right - left + 1)];

            for( int i = 0; i < matrix.length; i ++ ) {
                for (int j = 0; j < matrix.length; j ++ ) {
                    matrix[i][j] = Math.max(i, j) + 1;
                }
            }
            /**
             * (0,0) (0,1) (0,2)
             * (0,1) (1,1) (2,2)
             * (0,2) (1,2) (2,2)
             */


            for( long i = left; i <= right; i ++ ) {
                int height = (int) i / n;
                int width = (int) i % n;

                result[(int)(i - left)] = matrix[height][width];
            }
            return result;
        }

    }

    class Success {


        public int[] solution(int n, long left, long right) {
            int[] arr = new int[(int) (right - left) + 1];

            for( int i = 0; i < arr.length; i++ ) {
                int row = (int)(( i + left ) / n)  + 1;
                int col = (int)(( i + left ) % n)  + 1;
                arr[i] = ((col > row) ? col : row);
            }
            return arr;
        }

        public int[] solutionOptimized(int n, long left, long right) {
            int[] arr = new int[n * n];
            for( int i = 0; i < arr.length; i++ ) {
                int row = (( i / n ) + 1);
                int col = (( i % n ) + 1);
//            System.out.println(i + "//" + ((col > row) ? col : row));

                arr[i] = ((col > row) ? col : row);
            }


            print(arr);

            return Arrays.copyOfRange(arr, (int)left, (int)right + 1);
        }


        public int[] solutionExceedMemory(int n, long left, long right) {
            int[][] arr =
                    new int[n][n];
            for (int i = 0; i < arr.length; i ++ ) {
                for( int j = 0; j < arr[0].length; j ++ ) {
                    arr[j][i] = 1;
                }
            }



            for (int i = 0; i < n; i ++ ) {
                for( int j = 0; j < n; j ++ ) {
                    if( i <= n - 2 && j <= n - 2 ) arr[i + 1][j + 1] = arr[i ][j ] + 1 ;
                    if( i <= n - 2 && arr[i + 1][j] == 1 ) arr[i + 1][j] = arr[i][j] + 1;
                    if( j <= n - 2 && arr[i][j + 1] == 1 ) arr[i][j + 1] = arr[i][j] + 1;
                }
            }

            int[] answer = new int[n * n];
            for (int i = 0; i < n; i ++ ) {
                for( int j = 0; j < n; j ++ ) {
                    answer[i * n + j] = arr[i][j];
                }
            }


            return Arrays.copyOfRange(answer, (int)left, (int)right + 1);
        }

        private void print( int [][] arr) {
            System.out.println("[");
            for( int[] inner : arr ) {

                System.out.print("\t[");
                for( int other: inner) {
                    System.out.print(" "+other+",");
                }
                System.out.println("],");
            }
            System.out.println("]");
        }
        private void print( int [] arr) {

            System.out.print("[");
            for( int other: arr) {
                System.out.print(" "+other+",");
            }
            System.out.println("]");
        }
    }
}
