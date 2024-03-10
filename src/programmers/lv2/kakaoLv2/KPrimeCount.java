package programmers.lv2.kakaoLv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class KPrimeCount {
    //https://school.programmers.co.kr/learn/courses/30/lessons/92335
    /**
     * 문제 설명
     * 양의 정수 n이 주어집니다. 이 숫자를 k진수로 바꿨을 때,
     * 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.
     *  - 0P0처럼 소수 양쪽에 0이 있는 경우
     *  - P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
     *  - 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
     *  - P처럼 소수 양쪽에 아무것도 없는 경우
     *  - 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
     *       - 예를 들어, 101은 P가 될 수 없습니다.
     *
     * 예를 들어, 437674을 3진수로 바꾸면 211020101011입니다.
     * 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며,
     * 총 3개입니다. (211, 2, 11을 k진법으로 보았을 때가 아닌,
     * 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.) 211은 P0 형태에서 찾을 수 있으며,
     * 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.
     *
     * 정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때,
     * 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록
     * solution 함수를 완성해 주세요.
     *
     * 제한사항
     * - 1 ≤ n ≤ 1,000,000
     * - 3 ≤ k ≤ 10
     */

    @Nested
    class TestCases {
        @Test
        public void case1() {
            int n = 437674;
            int k = 3;
            int result = 3;

            Assertions.assertEquals(result, solution(n, k));
        }

        @Test
        public void case2() {
            int n = 110011;
            int k = 10;
            int result = 2;

            Assertions.assertEquals(result, solution(n, k));
        }
        @Test
        public void case3() {
            int n = 1;
            int k = 10;
            int result = 0;

            Assertions.assertEquals(result, solution(n, k));
        }

        @Test
        public void case4() {
            int n = 1000000;
            int k = 3;
            int result = 2;

            Assertions.assertEquals(result, solution(n, k));
        }

        @Test
        public void primeTest1() {
            System.out.println(checkPrime(11L));
        }

        @Test
        public void primeTest2() {
            System.out.println(checkPrime(12L));
        }
    }

    public int solution(int n, int k) {
        int answer = 0;
        String converted = Long.toString(n, k);


        for ( String s : converted.replaceAll("0+", "0").split("0") ) {

            if ( Long.parseLong(s) <=  1  ) continue;
            if ( checkPrime(Long.parseLong(s)) ) answer += 1;

        }

        return answer;
    }

    private Boolean checkPrime( Long number ) {
        if( number < 2 ) return Boolean.FALSE;

        for ( int i = 2; i <= Math.sqrt(number); i ++ ){
            if( number % i == 0) return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
