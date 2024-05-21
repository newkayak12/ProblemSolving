package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MagicalElevator {
    //https://school.programmers.co.kr/learn/courses/30/lessons/148653
    /**
     * <pre>
     * 마법의 세계에 사는 민수는 아주 높은 탑에 살고 있습니다.
     * 탑이 너무 높아서 걸어 다니기 힘든 민수는 마법의 엘리베이터를 만들었습니다.
     * 마법의 엘리베이터의 버튼은 특별합니다.
     *
     * 마법의 엘리베이터에는 -1, +1, -10, +10, -100, +100 등과
     * 같이 절댓값이 10c (c ≥ 0 인 정수) 형태인 정수들이 적힌 버튼이 있습니다.
     * 마법의 엘리베이터의 버튼을 누르면 현재 층 수에 버튼에 적혀 있는 값을 더한 층으로 이동하게 됩니다.
     * 단, 엘리베이터가 위치해 있는 층과 버튼의 값을 더한 결과가 0보다 작으면
     * 엘리베이터는 움직이지 않습니다. 민수의 세계에서는 0층이 가장 아래층이며 엘리베이터는
     * 현재 민수가 있는 층에 있습니다.
     *
     * 마법의 엘리베이터를 움직이기 위해서 버튼 한 번당 마법의 돌 한 개를 사용하게 됩니다.
     * 예를 들어, 16층에 있는 민수가
     * 0층으로 가려면 -1이 적힌 버튼을 6번,
     * -10이 적힌 버튼을 1번 눌러 마법의 돌 7개를 소모하여 0층으로 갈 수 있습니다.
     *
     * 하지만, +1이 적힌 버튼을 4번,
     * -10이 적힌 버튼 2번을 누르면 마법의 돌 6개를 소모하여 0층으로 갈 수 있습니다.
     *
     * 마법의 돌을 아끼기 위해 민수는 항상 최소한의 버튼을 눌러서 이동하려고 합니다.
     * 민수가 어떤 층에서 엘리베이터를 타고 0층으로 내려가는데 필요한 마법의 돌의 최소 개수를 알고 싶습니다.
     * 민수와 마법의 엘리베이터가 있는 층을 나타내는 정수 storey 가 주어졌을 때,
     * 0층으로 가기 위해 필요한 마법의 돌의 최소값을 return 하도록 solution 함수를 완성하세요.
     *
     * 제한사항
     * 1 ≤ storey ≤ 100,000,000
     * </pre>
     */

    @Nested
    public class TestCases {

        @Test
        public void case01 () {
            int storey = 16;
            int result = 6;

            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case02 () {
            int storey = 2554;
            int result = 16;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case03 () {
            int storey = 7;
            int result = 4;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case04 () {
            int storey = 17;
            int result = 5;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case05 () {
            int storey = 34;
            int result = 7;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case06 () {
            int storey = 35;
            int result = 8;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case07 () {
            int storey = 36;
            int result = 8;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case08 () {
            int storey = 55;
            int result = 10;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case09 () {
            int storey = 75;
            int result = 8;
            Assertions.assertEquals(result, solution(storey));
        }

        @Test
        public void case10 () {
            int storey = 555;
            int result = 14;
            Assertions.assertEquals(result, solution(storey));
        }
    }

    public int solution(int storey) {
        //정리 -> 마지막이 6이상 -> 더하기
        //    -> 5미만 -> 빼기
        //    -> 5 -> 다음 자릿수가 5보다 크거나 같으면 더하고
        //    -> 5 -> 다음 자릴수가 5보다 작다면 빼고
        // 특이 케이스
        // 75의 경우
        // 70 -(-100)-> -25 -(+ 10 * 2)-> -5 -(1 * 5)-> 0
        // 총 8

        // 10 * 2 + 1 * 5 - 100 * 1
        // 10
        return calculation(storey);
    }

    private  int calculation (int number) {

        int answer = 0;
        int TEN = 10;
        if (number == 0) return 0;
        else {
            int diff = number - ((number / TEN) * TEN);

            if (diff > 5) {
                number += (10 - diff);
                number /= TEN;
                answer += ((10 - diff) + calculation(number));
            } else if (diff < 5) {
                number /= TEN;
                answer += (diff + calculation(number));
            } else {
                int nextNumber = number / TEN;
                int nextDiff = nextNumber - (nextNumber/TEN) * TEN;
                if( nextDiff >= 5 ) answer += (5 + calculation(number / TEN + 1));
                else  answer += (5 + calculation(number / TEN));
            }


            return answer;
        }
    }
    class MinusCaseIsNotConsidered {


        public int solution(int storey) {
            //정리 -> 마지막이 6이상 -> 더하기
            //    -> 5미만 -> 빼기
            //    -> 5 -> 다음 자릿수가 5보다 크거나 같으면 더하고
            //    -> 5 -> 다음 자릴수가 5보다 작다면 빼고
            return calculation(storey);
        }

        private  int calculation (int number) {

            System.out.println(number);
            int answer = 0;
            int TEN = 10;
            if (number == 0) return 0;
            else {
                int diff = number - ((number / TEN) * TEN);

                System.out.println("DIFF :" +diff);
                if (diff > 5) {
                    number += (10 - diff);
                    number /= TEN;
                    answer += ((10 - diff) + calculation(number));
                } else if (diff < 5) {
                    number /= TEN;
                    answer += (diff + calculation(number));
                } else {
                    answer += (5 + calculation(number / TEN));
                }


                return answer;
            }
        }
    }
}
