package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HallOfFame {
    //https://school.programmers.co.kr/learn/courses/30/lessons/138477

    /**
     *<pre>
     * "명예의 전당"이라는 TV 프로그램에서는 매일 1명의 가수가 노래를 부르고,
     * 시청자들의 문자 투표수로 가수에게 점수를 부여합니다.
     *
     * 매일 출연한 가수의 점수가 지금까지 출연 가수들의 점수 중
     * 상위 k번째 이내이면 해당 가수의 점수를 명예의 전당이라는 목록에 올려 기념합니다.
     *
     * 즉 프로그램 시작 이후 초기에 k일까지는
     * 모든 출연 가수의 점수가 명예의 전당에 오르게 됩니다.
     *
     * k일 다음부터는 출연 가수의 점수가 기존의 명예의 전당 목록의
     * k번째 순위의 가수 점수보다 더 높으면,
     * 출연 가수의 점수가 명예의 전당에 오르게 되고
     *
     * 기존의 k번째 순위의 점수는 명예의 전당에서 내려오게 됩니다.
     *
     * 이 프로그램에서는 매일 "명예의 전당"의 최하위 점수를 발표합니다.
     *
     * 예를 들어, k = 3이고, 7일 동안 진행된 가수의 점수가
     * [10, 100, 20, 150, 1, 100, 200]이라면,
     * 명예의 전당에서 발표된 점수는
     * 아래의 그림과 같이 [10, 10, 10, 20, 20, 100, 100]입니다.
     *
     * <table>
     *     <tr>
     *         <th>일자</th>
     *         <td>1</td>
     *         <td>2</td>
     *         <td>3</td>
     *         <td>4</td>
     *         <td>5</td>
     *         <td>6</td>
     *         <td>7</td>
     *     </tr>
     *     <tr>
     *         <th>score</th>
     *         <td>10</td>
     *         <td>100</td>
     *         <td>20</td>
     *         <td>150</td>
     *         <td>1</td>
     *         <td>100</td>
     *         <td>200</td>
     *     </tr>
     *     <tr>
     *          <th>명예의 전당 <br/> (k = 3)</th>
     *          <td>10</td>
     *          <td>100<br/>10 </td>
     *          <td>100<br/>20<br/>10</td>
     *          <td>150<br/>100<br/>20</td>
     *          <td>150<br/>100<br/>20</td>
     *          <td>150<br/>100<br/>100</td>
     *          <td>200<br/>150<br/>100</td>
     *      </tr>
     *     <tr>
     *           <th>발표점수</th>
     *           <td>10</td>
     *           <td>10</td>
     *           <td>10</td>
     *           <td>20</td>
     *           <td>20</td>
     *           <td>100</td>
     *           <td>100</td>
     *       </tr>
     * </table>
     *
     * 명예의 전당 목록의 점수의 개수 k,
     * 1일부터 마지막 날까지 출연한 가수들의 점수인 score가 주어졌을 때,
     * 매일 발표된 명예의 전당의 최하위 점수를 return하는
     * solution 함수를 완성해주세요.
     *</pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int k = 3;
            int[] score = {10, 100, 20, 150, 1, 100, 200};
            int[] expect = {10, 10, 10, 20, 20, 100, 100};

            Assertions.assertArrayEquals(expect, solution(k, score));
        }

        @Test
        public void case2 () {
            int k = 4;
            int[] score = {0, 300, 40, 300, 20, 70, 150, 50, 500, 1000};
            int[] expect = {0, 0, 0, 0, 20, 40, 70, 70, 150, 300};

            Assertions.assertArrayEquals(expect, solution(k, score));
        }
    }

    public static int[] solution( int k, int[] score ) {
        int[] answer = new int[score.length];
        Integer[] table = new Integer[k];
        Arrays.fill(table, 2000);

        for ( int i = 0; i < score.length; i ++ ) {
            if ( i + 1 > k ) {
                if ( table[k - 1] < score[i] ) table[ k - 1] = score[i];
            }
            else table[0] = score[i];
            Arrays.sort(table, (a, b) -> b - a);
            answer[i] = table[k - 1];
        }


        return answer;
    }

    private static void print (int [] arr) {
        System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
    }
    private static void print (Integer [] arr) {
        System.out.println(Arrays.stream(arr).collect(Collectors.toList()));
    }
}
