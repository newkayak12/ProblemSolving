package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


//retry*2
public class TriangleSnail {
    //https://school.programmers.co.kr/learn/courses/30/lessons/68645



    /**
     * <pre>
     * 정수 n이 매개변수로 주어집니다.
     * 다음 그림과 같이 밑변의 길이와 높이가 n인 삼각형에서 맨 위
     * 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후,
     * 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을
     * return 하도록 solution 함수를 완성해주세요.
     *
     *
     *          1
     *        2   9
     *     3   10   8
     *   4   5   6    7
     *
     *       n = 4
     *
     *             1
     *           2  12
     *        3   13   11
     *     4   14   15   10
     *  5    6    7    8    9
     *
     *       n = 5
     *              1
     *           2   18
     *         3    19  17
     *       4   20  27  16
     *     5  21  28  26   15
     *    6  22  23  24  25  14
     *  7  8   9   10  11  12  13
     *      n =7
     *
     *      7 -> 6 -> 5 -> 4 -> 3 -> 2 -> 1
     *
     *      7 + 6 + 5 -> 18
     *      18+ 4 + 3 + 2 -> 27
     *
     * </pre>
     * <p>
     * <p>
     * 맨 왼쪽 n 까지 + 1
     * 맨 아래 n ~ n + n - 1
     */
    @Nested
    public class TestCases {
        @Test
        public void case1() {
            int n = 4;
            int[] result = {1, 2, 9, 3, 10, 8, 4, 5, 6, 7};
            /**
             * <pre>
             *     1
             *     2 9
             *     3 10  8
             *     4  5  6  7
             * </pre>
             */
            Assertions.assertArrayEquals(result, solution(n));
//            Assertions.assertArrayEquals(result, solutionSecond(n));
        }

        @Test
        public void case2() {
            int n = 5;
            int[] result = {1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9};

            /**
             * <pre>
             *     1
             *     2 12
             *     3 13 11
             *     4 14 15 10
             *     5  6  7  8  9
             * </pre>
             */
            Assertions.assertArrayEquals(result, solution(n));
//            Assertions.assertArrayEquals(result, solutionSecond(n));
        }

        @Test
        public void case3() {
            int n = 6;
            int[] result = {1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11};

            /**
             * <pre>
             *     1
             *     2 15
             *     3 16 14
             *     4 17 21 13
             *     5 18 19 20 12
             *     6  7  8  9 10 11
             * </pre>
             */
            Assertions.assertArrayEquals(result, solution(n));
//            Assertions.assertArrayEquals(result, solutionSecond(n));
        }
    }

    public int[] solution(int n) {
        /**
         * 1 (0, 0)
         * 2 (1, 0) 9  (1, 1)
         * 3 (2, 0) 10 (2, 1)  8 (2, 2)
         * 4 (3, 0) 5  (3, 1)  6 (3, 2) 7 (3, 3)
         *
         * 1
         * 2 12
         * 3 13 11
         * 4 14 15 10
         * 5  6  7  8  9
         *
         * 1
         * 2 15
         * 3 16 14
         * 4 17 21 13
         * 5 17 19 20 12
         * 6  7  8  9 10 11
         */


        //정 못찾겠으면 그냥 해봐야

        int[][] triangle = new int[n][n];
        int y = -1;
        int x = 0;
        int number = 1;

        for( int i = n; i > 0; i -= 3) {
            for( int j = 0; j < i; j ++)  triangle[++y][x] = number++;
            for( int j = 0; j < i - 1; j ++ ) triangle[y][++x] = number++;
            for( int j = 0; j < i - 2; j ++ ) triangle[--y][--x] = number++;
        }

        return  Arrays.stream(triangle).flatMap(i -> Arrays.stream(i).boxed()).filter(i -> i!=0).mapToInt(i -> i).toArray();
    }
    class Success {
        public int[] solution(int n) {
            int[][] snail = new int[n][n];

            int total = 0;
            for (int i = n; i > 0; i--) total += i;

            int number = 1;
            int row = -1;
            int col = 0;

            for (int turn = n; turn >= 0; turn -= 3) {
                for (int down = 0; down < turn; down++) {//4
                    snail[++row][col] = number++;
                    //3, 0
                }
                print(snail);
                for (int right = 0; right < turn - 1; right++) { //3
                    snail[row][++col] = number++;
                    //3, 3
                }
                print(snail);
                for (int leftUp = 0; leftUp < turn - 2; leftUp++) {
                    snail[--row][--col] = number++;
                }
                print(snail);
            }


            int[] answer = Arrays.stream(snail).flatMap(i -> Arrays.stream(i).boxed().filter(j -> j != 0)).mapToInt(i -> i).toArray();
            return answer;
        }

        void print(int[][] arr) {
            System.out.println("STORKE========");
            for (int[] a : arr) System.out.println(Arrays.toString(a));
            System.out.println("==============");
        }
        public int[] solutionSecond(int n) {
            int[][] table = new int[n][n];

            int row = -1;
            int col = 0;
            int number = 1;

            for ( int i = n; i > 0; i -= 3){

                for(int j = 0; j < i; j++) table[++row][col] = number ++;
                for(int j = 0; j < i - 1; j ++ ) table[row][++col] = number ++;
                for(int j = 0; j < i - 2; j ++ ) table[--row][--col] = number ++;
            }


            return Arrays.stream(table).flatMap(ints -> Arrays.stream(ints).boxed()).filter(i -> i > 0).mapToInt( i -> i).toArray();
        }
    }



    @Nested
    class  SquareSnail {
        @Test
        public void case1 () {
            /**
             * 1 4
             * 2 3
             */
            int n = 2;
            int[] result = {1,4,2,3};

            Assertions.assertArrayEquals(result, solution(n));
        }
        @Test
        public void case2 () {
            /**
             * 1 8 7
             * 2 9 6
             * 3 4 5
             */
            int n = 3;
            int[] result = {1,8,7, 2,9,6, 3,4,5};


            Assertions.assertArrayEquals(result, solution(n));
        }

        @Test
        public void case3 () {
            /**
             * 1  12  11  10
             * 2  13  16   9
             * 3  14  15   8
             * 4   5   6   7
             */
            int n = 4;
            int[] result = {1,12, 11, 10, 2, 13, 16, 9, 3, 14, 15, 8, 4, 5, 6, 7};


            Assertions.assertArrayEquals(result, solution(n));
        }

        @Test
        public void case4 () {
            /**
             * 1  20 19 18 17  16
             * 2  21 32 31 30  15
             * 3  22 33 36 29  14
             * 4  23 34 35 28  13
             * 5  24 25 26 27  12
             * 6  7  8  9  10  11
             */
            int n = 6;
            int[] result = {
                    1, 20 ,19 ,18 ,17 ,16,
                    2, 21 ,32 ,31 ,30 ,15,
                    3, 22 ,33 ,36 ,29 ,14,
                    4, 23 ,34 ,35 ,28 ,13,
                    5, 24 ,25 ,26 ,27 ,12,
                    6, 7  ,8  ,9  ,10 ,11
            };


            Assertions.assertArrayEquals(result, solution(n));
        }


        public int[] solution( int n  ) {

            int[][] square = new int[n][n];
            int number = 1;
            int x = 0;
            int y = -1;

            for( int side = n; side > 0; side -= 2 ) {
                for( int i = 0; i < side; i ++ )  square[++y][x] = number++;
                for( int i = 0; i < side - 1; i ++ ) square[y][++x] = number++;
                for( int i = 0; i < side - 1; i ++ ) square[--y][x] = number++;
                for( int i = 0; i < side - 2; i ++ ) square[y][--x] = number++;
            }


            return Arrays.stream(square).flatMapToInt(ints -> Arrays.stream(ints)).toArray();
        }
    }

}


















