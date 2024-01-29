package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

public class OddDecimalSystem {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12899
    /**
     *<pre>
     * 124 나라가 있습니다. 124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.
     *
     *    - 124 나라에는 자연수만 존재합니다.
     *    - 124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
     *
     * 예를 들어서 124 나라에서 사용하는 숫자는 다음과 같이 변환됩니다.
     *</pre>
     *
     * <table>
     *     <tr>
     *         <th>10진법</th>
     *         <th>124나라</th>
     *         <th>10진법</th>
     *         <th>124나라</th>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>1</td>
     *         <td>6</td>
     *         <td>14</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>2</td>
     *         <td>7</td>
     *         <td>21</td>
     *     </tr>
     *     <tr>
     *         <td>3</td>
     *         <td>4</td>
     *         <td>8</td>
     *         <td>22</td>
     *     </tr>
     *     <tr>
     *         <td>4</td>
     *         <td>11</td>
     *         <td>9</td>
     *         <td>24</td>
     *     </tr>
     *     <tr>
     *         <td>5</td>
     *         <td>12</td>
     *         <td>10</td>
     *         <td>41</td>
     *     </tr>
     * </table>
     *
     *<pre>
     * 자연수 n이 매개변수로 주어질 때, n을 124 나라에서 사용하는 숫자로 바꾼 값을
     * return 하도록 solution 함수를 완성해 주세요.
     *
     * 제한사항
     *    - n은 50,000,000이하의 자연수 입니다.
     *</pre>
     */

    @Nested
    public class NumberTest {
        private String divTest ( long n ){
            if ( n >= 3 ) return (n / 3) +""+ (n % 3);
            else return n+"";
        }
        private String divThree ( long n ) {
/**
 * 1 = 1
 * 2 = 2
 * 3 = ( ( 3 / 3 ) -1 ) * 3 + 3 ==> 3
 * 4 = ( 4 / 3 ) * 3 + 1 => 11
 * 5 = ( 5 / 3 ) * 3 + 2 ==> 12
 * 6 = ( ( 6 / 3 )  - 1) * 3 + 3 ==> 13
 * 7 = ( 7 / 3 ) * 3 + 1 ==> 21
 * 8 = ( 8 / 3 ) * 3 + 2 ==> 22
 * 9 = ( ( 9 / 3 ) - 1 ) * 3 + 3 ==> 24
 * 10 = ( 10 / 3 ) * 3 + 1 ==> 31
 * 11 = ( 11 / 3 ) * 3 + 2  ==> 32
 * 12 = ( ( 12 / 3 ) - 1 ) * 3 + 3  ==> 33
 * 13 = ( ( 13 / 3) ==> 4 ==> (4 / 3) * 3 + 1 ==> 11) + 1 ==> 111
 * 14 = ( ( 14 / 3) ==> 4 ==> (4 / 3) * 3 + 1 ==> 11) + 2 ==> 112
 * 15 = ( ( 15 / 3) ==> 5 ==> (4 / 3) * 3 + 1 ==> 11) + 2 ==> 112
 */

            if (n <= 3) return n+"";
            else {
                if (n % 3 == 0) {
                    if( (n / 3.0) > 4.0 ) return divThree( (n / 3) - 1)+""+3;
                    return ((n / 3) - 1) + "" + 3;
                }
                else {
                    if( (n / 3.0) > 4.0 ) return divThree( (n / 3))+"" + (n % 3);
                    return (n / 3) + "" + (n % 3);
                }
            }


        }

        private String afterEffect ( String s ) {
            return s.replaceAll("3", "4");
        }


        @ParameterizedTest()
        @ValueSource(ints = {
                1,2,3,4,5,6,7,8,9,10,
                11,12,13,14,15,16,17,18,19,20,
                21,22,23,24,25,26,27,28,29,30
        })
        public void case0 (int n ) {
            System.out.println(n+"/"+ afterEffect(divThree(n)));
        }

    }


    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int n = 1;
            String result = "1";

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case2 () {
            int n = 2;
            String result = "2";

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case3 () {
            int n = 3;
            String result = "4";

            Assertions.assertEquals(result, solution(n));
        }

        @Test
        public void case4 () {
            int n = 4;
            String result = "11";

            Assertions.assertEquals(result, solution(n));
        }
    }


    private String divThree (int n) {

        if (n <= 3) return n+"";
        else {
            if (n % 3 == 0) {
                if( (n / 3.0) > 4.0 ) return divThree( (n / 3) - 1)+""+3;
                return ((n / 3) - 1) + "" + 3;
            }
            else {
                if( (n / 3.0) > 4.0 ) return divThree( (n / 3))+"" + (n % 3);
                return (n / 3) + "" + (n % 3);
            }
        }
    }
    private String afterEffect ( String s ) {
        return s.replaceAll("3", "4");
    }
    public String solution(int n) {
        return afterEffect(divThree(n));
    }

    /**
     * 효율성 테스트 시간 초과로 개선 필요
     * while로 개선하면 될 것으로 보임
     */


    @Nested
    class TestCases2 {
        @Test
        public void case1 () {
            int n = 1;
            String result = "1";

            Assertions.assertEquals(result, solution2(n));
        }

        @Test
        public void case2 () {
            int n = 2;
            String result = "2";

            Assertions.assertEquals(result, solution2(n));
        }

        @Test
        public void case3 () {
            int n = 3;
            String result = "4";

            Assertions.assertEquals(result, solution2(n));
        }

        @Test
        public void case4 () {
            int n = 4;
            String result = "11";

            Assertions.assertEquals(result, solution2(n));
        }
    }
    private String divThreeEfficiency (int n) {

        int number = n;
        StringBuilder answer = new StringBuilder();
        if (n <= 3)  answer.append(n);
        else {
            while (number > 3) {
                if (number % 3 == 0) {

                    answer.append( 3 );

                    if ((number / 3.0) > 4.0)  number = (number / 3) - 1;
                    else {
                        answer.append((number / 3) - 1);
                        number = 0;
                    }

                } else {

                    answer.append((number % 3));

                    if ((number / 3.0) > 4.0)  number = number / 3;
                    else {
                        answer.append((number / 3));
                        number = 0;
                    }

                }
            }
        }

        return answer.reverse().toString();
    }
    private String afterEffect2 ( String s ) {
        return s.replaceAll("3", "4");
    }
    public String solution2(int n) {
        return afterEffect2(divThreeEfficiency(n));
    }


}
