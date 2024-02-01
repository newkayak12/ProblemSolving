package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ThreeOnNTile {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12902
    /**
     * <pre>
     * 가로 길이가 2이고 세로의 길이가 1인 직사각형 모양의 타일이 있습니다. 이 직사각형 타일을 이용하여 세로의 길이가 3이고 가로의 길이가 n인 바닥을 가득 채우려고 합니다. 타일을 채울 때는 다음과 같이 2가지 방법이 있습니다
     *      - 타일을 가로로 배치 하는 경우
     *      - 타일을 세로로 배치 하는 경우
     * 예를들어서 n이 8인 직사각형은 다음과 같이 채울 수 있습니다.
     * </pre>
     *
     *   <table>
     *      <tr>
     *          <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *          <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *          <td style="border: 1px green solid; width:20px;"></td>
     *          <td style="border: 1px green solid; width:20px;" colspan="2"></td>
     *          <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *          <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *      </tr>
     *       <tr>
     *           <td style="border: 1px green solid; width:20px;"></td>
     *           <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *           <td style="border: 1px green solid; width:10px;" rowspan="2"></td>
     *       </tr>
     *       <tr>
     *           <td style="border: 1px green solid; width:20px;" colspan="2"></td>
     *           <td style="border: 1px green solid; width:20px;"></td>
     *           <td style="border: 1px green solid; width:20px;" colspan="2"></td>
     *       </tr>
     *  </table>
     *
     * <pre>
     * 직사각형의 가로의 길이 n이 매개변수로 주어질 때, 이 직사각형을 채우는 방법의 수를 return 하는 solution 함수를 완성해주세요.
     *
     * 제한사항
     *  - 가로의 길이 n은 5,000이하의 자연수 입니다.
     *  - 경우의 수가 많아 질 수 있으므로, 경우의 수를 1,000,000,007으로 나눈 나머지를 return해주세요.
     * </pre>
     */

    @Nested
    class TestCases {

        @Test
        public void case1 () {
            int n = 2;
            int result = 3;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case2 () {
            int n = 4;
            int result = 11;

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case3 () {
            int n = 6;
            int result = 41;

            Assertions.assertEquals(result, solution(n));
        }
    }


    public int solution ( int n ) {

        /**
         * 2 = 3
         * 4 = 11
         * 6 = 41
         */
        return 0;
    }
}
