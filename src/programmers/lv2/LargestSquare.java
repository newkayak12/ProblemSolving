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
            int result = 16;

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
            int result = 16;

            Assertions.assertEquals(result, solution(table));
        }
    }


    public int solution(int[][] board) {
        List<String> result = new ArrayList<>();

        for ( String p : findStartPoint(board)) {
            List<String> end = findEndPoint(board, p);
            result.addAll(end);
        }

        System.out.println(result);



        return 0;
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
        int maxHorizontalIndex = board.length - 1;
        int maxVerticalIndex = board[0].length - 1;

        String[] startSplit = startPoint.split(",");
        int startX = Integer.parseInt(startSplit[0]);
        int startY = Integer.parseInt(startSplit[1]);
        int endX = Integer.parseInt(startSplit[0]);
        int endY = Integer.parseInt(startSplit[1]);

        List<String> result = new ArrayList<>();


        int size = Math.min(maxVerticalIndex - startX, maxHorizontalIndex - startY);
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
}
