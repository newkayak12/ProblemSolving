package programmers.lv2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.DoubleStream;

public class TwoOnNTile {

    /**
     * <pre>
     * 가로 길이가 2이고 세로의 길이가 1인 직사각형모양의 타일이 있습니다. 이 직사각형 타일을 이용하여 세로의 길이가 2이고 가로의 길이가 n인 바닥을 가득 채우려고 합니다. 타일을 채울 때는 다음과 같이 2가지 방법이 있습니다.
     * 타일을 가로로 배치 하는 경우
     * 타일을 세로로 배치 하는 경우
     * 예를들어서 n이 7인 직사각형은 다음과 같이 채울 수 있습니다.
     * </pre>
     *
     * <table>
     *     <tr>
     *         <td style="border: 1px green solid; width:10px;" rowspan="2"> </td>
     *         <td style="border: 1px green solid; width:20px;"></td>
     *         <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *         <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *         <td style="border: 1px green solid; width:20px;"></td>
     *     </tr>
     *      <tr>
     *          <td style="border: 1px green solid; width:20px;"></td>
     *          <td style="border: 1px green solid; width:20px;"></td>
     *      </tr>
     * </table>
     *
     * <pre>
     * 직사각형의 가로의 길이 n이 매개변수로 주어질 때, 이 직사각형을 채우는 방법의 수를 return 하는 solution 함수를 완성해주세요.
     *
     * 제한사항
     * 가로의 길이 n은 60,000이하의 자연수 입니다.
     * 경우의 수가 많아 질 수 있으므로, 경우의 수를 1,000,000,007으로 나눈 나머지를 return해주세요.
     * </pre>
     */
    @Nested
    public class TestCases{
        @Test
        public void case0 () {
            /**
             * ||
             *
             * ㅡ
             */
            int n = 2;
            int result = 2;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case1 () {
            /**
             * |||
             * |_
             * _|
             */
            int n = 3;
            int result = 3;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case2 () {
            /**
             * _ _
             * ||_
             * |_|
             * _||
             * ||||
             */
            int n = 4;
            int result = 5;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case3() {
            /**
             * _ _|
             * _ | _
             * |_ _
             *
             * _ |||
             * |_||
             * ||_|
             * |||_
             *
             * |||||
             *
             *
             */
            int n = 5;
            int result = 8;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case4() {
            /**
             * _ _ _
             *
             * _ _ ||
             * _ | _ |
             * | _ _ |
             * _ || _
             * | _ | _
             * ||_ _
             *
             * _ ||||
             * | _ |||
             * || _ ||
             * ||| _ |
             * |||| _
             *
             * ||||||
             */
            int n = 6;
            int result = 13;

            Assertions.assertEquals(result, solution(n));
        }
    }

    /**
     * n = 1 -> 1
     * n = 2 -> 2
     * n = 3 -> 3
     * n = 4 -> 5
     * n = 5 -> 8
     * n = 6 -> 13
     */

    public int solution ( int n ) {
        return this.calculation(n,  0,0, 1);
    }

    private int calculation ( int totalTile, int countNow,  int p1, int p2 ) {
        int div = 1_000_000_007;
        if ( countNow >= totalTile ) return (p2 % div);
        else return calculation(totalTile, countNow + 1, p2 % div, (p1 + p2) % div);
    }
}
