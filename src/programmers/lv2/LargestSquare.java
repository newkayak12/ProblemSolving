package programmers.lv2;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

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
        }

        @Test
        public void case12 () {
            int[][] table = {
                    {0, 0, 1, 1},
                    {1, 1, 1, 1}
            };
            int result = 9;
        }
    }

    public int solution( int[][] board ) {
        int arraySize = board.length;


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
    private
}
