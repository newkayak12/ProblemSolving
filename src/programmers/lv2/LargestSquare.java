package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LargestSquare {
    //retry
    //12
    //https://school.programmers.co.kr/learn/courses/30/lessons/12905
    /**
     * <pre>
     * 1와 0로 채워진 표(board)가 있습니다.
     * 표 1칸은 1 x 1 의 정사각형으로 이루어져 있습니다.
     * 표에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return 하는 solution 함수를 완성해 주세요.
     * (단, 정사각형이란 축에 평행한 정사각형을 말합니다.)
     * </pre>
     *
     * <table>
     *     <tr>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">0</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *     </tr>
     *     <tr>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *     </tr>
     *     <tr>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *     </tr>
     *     <tr>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">0</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">0</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">1</td>
     *         <td style="width:10px; height:10px; border:1px black solid; background-color:white; text-align:center;">0</td>
     *     </tr>
     * </table>
     * <pre>
     *  가장 큰 정사각형은 9가 된다.
     *
     *  제한사항
     *      - 표(board)는 2차원 배열로 주어집니다.
     *      - 표(board)의 행(row)의 크기 : 1,000 이하의 자연수
     *      - 표(board)의 열(column)의 크기 : 1,000 이하의 자연수
     *      - 표(board)의 값은 1또는 0으로만 이루어져 있습니다.
     * </pre>
     */

    @Nested
    public class TestCase {
        @Test
        public void case1 () {
            int[][] table = {
                    {0, 1, 1, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1},
                    {0, 0, 1, 0}
            };
            int result = 9;

            Assertions.assertEquals(result, solution(table));
        }

        @Test
        public void case2 () {
            int[][] table = {
                    {0, 0, 1, 1},
                    {1, 1, 1, 1}
            };
            int result = 4;

            Assertions.assertEquals(result, solution(table));
        }

        @Test
        public void case3 () {
            int[][] table = {
                    {0, 0, 1, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1}
            };
            int result = 9;

            Assertions.assertEquals(result, solution(table));
        }

        @Test
        public void case4 () {
            int[][] table = {
                    {0, 0, 0, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1}
            };
            int result = 9;

            Assertions.assertEquals(result, solution(table));
        }

        @Test
        public void case5 () {
            int[][] table = {
                    {1, 1, 1, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1},
                    {1, 1, 1, 1}
            };
            int result = 16;

            Assertions.assertEquals(result, solution(table));
        }

        @Test
        public void case6 () {
            int[][] table = {{0, 1, 0}};
            int result = 1;

            Assertions.assertEquals(result, solution(table));
        }

        @Test
        public void case7 () {
            int[][] table = {{0,0,0,0}};
            int result = 0;

            Assertions.assertEquals(result, solution(table));
        }
    }


    public int solution(int[][] board) {
//
        int[][] map = new int[board.length][board[0].length];

        int max = 0;
        for( int i = 1; i < map.length; i ++ ) {
            for( int j = 1; j < board[0].length; j++ ) {
                if(board[i][j]!=0) {
                    board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1]) + 1;

                    max = Math.max(max, board[i][j]);
                }
            }
        }

        if( max == 0 ) {
            int result = 0;
            for(int i : board[0]) {
                if( i == 1) {
                    result = 1;
                    break;
                }
            }
            return result;
        }
        else return (int) Math.pow(max, 2);
    }

    private void print(int[][] board) {
        for( int[] element : board ) {
            System.out.println(Arrays.toString(element));
        }
    }

    class Success {
        /**
         * DynamicProgramming
         *  <pre>
         *      자료구조의 동적할당(Dynamic Allocation)에서 '동적'은
         *      '프로그램이 실행되는 도중에 실행에 필요한 메모리를 할당하는 기법'을 의미한다.
         *      그러나, 알고리즘의 동적 계획법(Dynamic Programming)에서 '동적'은 별 뜻 없다
         *      그냥 '기억하기'라고 생각하면 편하다.
         *
         *      '프로그래밍'은 컴퓨터 프로그래밍이 아니라 테이블을 만든다는 뜻이다.
         *  </pre>
         *
         *  DP를 사용할 조건
         *  <pre>
         *  최적 부분 구조(Optimal Substructure)
         *      - 상위 문제를 하위 문제로 나눌 수 있으며
         *        하위 문제의 답을 모아서 상위 문제를 해결할 수 있다.
         *
         *  중복되는 부분 문제(Overlapping Subproblem)
         *      - 동일한 작은 문제를 반복적으로 해결해야 한다.
         *  </pre>
         *
         *  DP?
         *  <pre>
         *   DP 알고리즘 기법은 이미 계산된 결과(하위 문제)는 별도의 메모리 영역에 저장하여
         *   다시 계산하지 않도록 설계함으로써 메모리를 적절히 사용하여 수행 시간 효율성을 비약적으로 향상시키는 방법이다.
         *
         *   DP 구현 방법은 일반적으로 두 가지 방식,
         *   Top-down(하향식)과 Bottom-up(상향식)으로 구성된다.
         *  </pre>
         *
         *
         *  Top-down
         *  <pre>
         *   상위 문제를 해결하기 위해서 하위 문제를 재귀적으로 호출하여
         *   하위 문제를 해결함으로써 상위 문제를 해결하는 방식이다.
         *   이 때 해결해놓은 하위 문제를 저장해 놓기 위해 Memoization이 사용된다.
         *
         * int fibo(int x) {
         * 		if( x ==1 || x==2) return 1;
         * 		if(dp[x] != 0) return dp[x];
         * 		dp[x] = fibo(x-1) + fibo(x-2);
         *
         *     	return dp[x];
         *  }
         *  </pre>
         *  Bottom-up
         *  <pre>
         *    하위에서부터 문제를 해결해나가면서 먼저 계산했던 문제들의 값을 활용해서
         *    상위 문제를 해결해나가는 방식으로 DP의 전형적인 형태는 Bottom-up이다.
         *    여기서 사용되는 문제 결과 값 저장용 리스트는 DP 테이블이라고 부른다.
         *
         *  int fibo(int x) {
         * 	    dp[1] =1;
         * 	    dp[2] =1;
         * 	    for(int i=3; i<x+1; i++) {
         * 	    	dp[i] = dp[i-1] + dp[i-2];
         *      }
         * 	  return dp[x];*
         *  }
         *  </pre>
         *
         *  메모이제이션
         *  <pre>
         *    메모이제이션은 DP구현 방법 중 하나로,
         *    한 번 계산한 결과를 메모리 공간에 메모하는 기법이다.
         *    이를 사용하면 모든 부분 문제가 한 번씩만 계산된다고 보장할 수 있기 때문에
         *    함수 호출 횟수가 엄청나게 감소함을 예상할 수 있다.
         *
         * // 일반 재귀 함수
         * // 중복된 계산을 반복해서 하게 된다. 시간복잡도 O(2^n)으로 x의 수가 커질수록 복잡도가 엄청나게 커짐
         * static int fibo(int x) {
         *    if( x ==1 || x==2) return 1;
         *    return fibo(x-1) + fibo(x-2);
         * }
         *
         *
         * // Memoization
         * // 하위 문제의 결과 값을 dp[]배열에 저장해놓고 필요할 때마다 계산된 값을 호출
         * static int fibo(int x) {
         *    if( x ==1 || x==2) return 1;
         *    if(dp[x] != 0) return dp[x];
         *    dp[x] = fibo(x-1) + fibo(x-2);
         *    return dp[x];
         * }
         *
         * 메모제이션 특징
         *
         * - 같은 문제를 다시 호출 하면 메모했던 결과를 그대로 가져온다
         * - 값을 기록해 놓는다는 점에서 캐싱(Cachig)이라고 한다
         * - DP에만 국한된 개념이 아니다. 한 번 계산된 결과를 담아 놓기만 하고 DP가 아닌 다른 방식으로도 사용될 수 있다. (캐싱,메모이제이션)
         * - 피보나치 함수를 예로 들었을 때, 이미 계산된 결과를 저장하면 아래의 색칠된 노드만 계산이 처리되어 프로그램의 작업 처리속도를 크게 향상시킬 수 있다.
         *  </pre>
         */
        public int solution(int[][] board) {
            int[][] map = new int[board.length + 1][board[0].length + 1];
            /**
             *   {0, 0, 1, 1},
             *   {1, 1, 1, 1},
             *   {1, 2, 1, 1},
             *   {1, 1, 1, 1}
             */

            int maxLen = 0;
            for (int i = 1; i <= board.length; i++) {
                for (int j = 1; j <= board[0].length; j++) {
                    if(board[i-1][j-1] != 0) {
                        int min = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]);
                        // 0,1이니까 0이 포함되면 0이 된다.
                        // 좌, 우 1인지 체크 = min() 둘 다 1이면 1
                        // 추가로 애초에 0이 있으면 더 원복
                        map[i][j] = min + 1;
    //
                        maxLen = Math.max(maxLen, min + 1);
                    }
                }
            }

            return maxLen * maxLen;
        }



        public int solutionReference(int[][] board) {
            int[][] map = new int[board.length + 1][board[0].length + 1];

            int maxLen = 0;
            for (int i = 1; i <= board.length; i++) {
                for (int j = 1; j <= board[0].length; j++) {
                    if(board[i-1][j-1] != 0) {
                        int min = Math.min(Math.min(map[i - 1][j], map[i][j - 1]), map[i - 1][j - 1]);
                        map[i][j] = min + 1;

                        maxLen = Math.max(maxLen, min + 1);
                    }
                }
            }

            return maxLen * maxLen;
        }



        private void print ( int [][] board, Map<String, Integer> start, Map<String, Integer> end ) {
            System.out.println("-------------"+ start+" / "+ end);
            for ( int [] nums : board ) {
                System.out.println(
                        "{"+Arrays.stream(nums).boxed().map(String::valueOf).collect(Collectors.joining(","))+"}"
                );
            }
            System.out.println("-------------");
        }
        class FailureCase1 {
            public int solution( int[][] board ) {
                return resolve(board);
            }

            private Map<String, Integer> findStartPoint (int[][] board ) {
                int startY = 0;
                int startX = 0;

                outer: for (; startY < board.length; startY ++ ) {
                    for (; startX < board[0].length; startX ++ ) {
                        if( board[startY][startX] == 1) break outer;
                    }
                }

                return Map.of("y", startY, "x", startX);
            }
            private Map<String, Integer> findEndPoint ( int[][] board, Map<String, Integer> startPoint) {
                int maxHorizontalIndex = board.length - 1;
                int maxVerticalIndex = board[0].length - 1;
                int endX = startPoint.get("x");
                int endY = startPoint.get("y");

                int size = Math.min(maxVerticalIndex - startPoint.get("x"), maxHorizontalIndex - startPoint.get("y"));
                check: for ( int i = size; i > 0; i -- ) {
                    if( board[endY + i][endX + i] == 1 ) {
                        endX = endX + i;
                        endY = endY + i;
                        break check;
                    }
                }


                return Map.of("x", endX, "y", endY);
            }
            public int resolve ( int[][] board ) {
                Map<String, Integer> startPoint = findStartPoint(board);
                Map<String, Integer> endPoint = findEndPoint(board, startPoint);
                Boolean isCompleted = Boolean.TRUE;

                if(
                        endPoint.get("x").equals(startPoint.get("x")) &&
                                endPoint.get("y").equals(startPoint.get("y"))
                ) {
                    board[startPoint.get("y")][startPoint.get("x")] = 0;
                    return resolve(board);
                }

                yLoop:for (int y = startPoint.get("y"); y <= endPoint.get("y"); y++) {
                    xLoop:
                    for (int x = startPoint.get("x"); x <= endPoint.get("x"); x++) {
                        if (board[y][x] != 1) {
                            isCompleted = Boolean.FALSE;
                            break yLoop;
                        }
                    }
                }


                System.out.println((endPoint.get("y") - startPoint.get("y") + 1)+":"+ (endPoint.get("y") - startPoint.get("y") + 1));
                if (isCompleted) return (endPoint.get("y") - startPoint.get("y") + 1) * (endPoint.get("y") - startPoint.get("y") + 1);
                else {
                    board[endPoint.get("y")][endPoint.get("x")] = 0;
                    return resolve(board);
                }
            }
        }
        class FailureCase2 {
        public int solution(int[][] board) {
            List<String> result = new ArrayList<>();
            int answer = 0;

            for ( String p : findStartPoint(board)) {
                List<String> end = findEndPoint(board, p);
                result.addAll(end);
            }
            result.sort((p, n) -> {
                String[] prevSplit = p.split("/");
                String[] nextSplit = n.split("/");
                int prevStartX = Integer.valueOf(prevSplit[0].split(",")[0]);
                int prevEndX = Integer.valueOf(prevSplit[1].split(",")[0]);

                int nextStartX = Integer.valueOf(nextSplit[0].split(",")[0]);
                int nextEndX = Integer.valueOf(nextSplit[1].split(",")[0]);
                return   (nextEndX - nextStartX) - (prevEndX - prevStartX);
            });

            loop: for ( String point : result ) {
                answer  = resolve(board, point);
                if ( answer != -1 ) break loop;
            }
            return answer;
        }

        private List<String> findStartPoint (int[][] board ) {
            List<String> result = new ArrayList<>();
            outer: for (int startY = 0; startY < board.length - 1; startY ++ ) {
                inner: for (int startX = 0; startX < board[0].length - 1; startX ++ ) {
                    if( board[startY][startX] == 1) {
                        board[startY][startX] = -1;
                        result.add(String.format("%s,%s", startX,  startY));
                        break inner;
                    }
                }
            }

            return result;
        }
        private List<String> findEndPoint ( int[][] board, String startPoint) {
            int maxHorizontalIndex = board.length -1;
            int maxVerticalIndex = board[0].length -1;

            String[] startSplit = startPoint.split(",");
            int startX = Integer.parseInt(startSplit[0]);
            int startY = Integer.parseInt(startSplit[1]);
            int endX = Integer.parseInt(startSplit[0]);
            int endY = Integer.parseInt(startSplit[1]);

            List<String> result = new ArrayList<>();


            int size = Math.min(
                    maxVerticalIndex - startX,
                    maxHorizontalIndex - startY
            );

            check: for ( int i = size; i > 0; i -- ) {
                if( board[endY + i][endX + i] == 1 ) {
                    endX = endX + i;
                    endY = endY + i;

                    result.add(String.format("%s,%s/%s,%s", startX, startY, endX, endY));
                    break check;
                }
            }

            return result;
        }

        private int resolve ( int[][] board, String coordinates ) {
            String[] split = coordinates.split("/");
            int startX = Integer.valueOf(split[0].split(",")[0]);
            int startY = Integer.valueOf(split[0].split(",")[1]);

            int endX = Integer.valueOf(split[1].split(",")[0]);
            int endY = Integer.valueOf(split[1].split(",")[1]);

            int area = (endX - startX + 1) * (endY - startY + 1);
            int count = 0;

            for(int y = startY; y <= endY; y ++ ) {
                for (int x = startX; x <= endX; x ++) {
                    if( Math.abs(board[y][x]) == 1) count += 1;
                }
            }


            if( area != count ) return -1;
            else return area;
        }
    }
    }
}
