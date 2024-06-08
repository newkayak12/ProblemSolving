package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Repaint {
    //retry
    //https://school.programmers.co.kr/learn/courses/30/lessons/161989
    /**
     * TODO
     * <pre>
     *      어느 학교에 페인트가 칠해진 길이가 n미터인 벽이 있습니다.
     *      벽에 동아리 · 학회 홍보나 회사 채용 공고 포스터 등을 게시하기
     *      위해 테이프로 붙였다가 철거할 때 떼는 일이 많고
     *      그 과정에서 페인트가 벗겨지곤 합니다.
     *
     *      페인트가 벗겨진 벽이 보기 흉해져 학교는 벽에 페인트를 덧칠하기로 했습니다.
     *      넓은 벽 전체에 페인트를 새로 칠하는 대신,
     *      구역을 나누어 일부만 페인트를 새로 칠 함으로써 예산을 아끼려 합니다.
     *      이를 위해 벽을 1미터 길이의 구역 n개로 나누고,
     *      각 구역에 왼쪽부터 순서대로 1번부터 n번까지 번호를 붙였습니다.
     *      그리고 페인트를 다시 칠해야 할 구역들을 정했습니다.
     *
     *      벽에 페인트를 칠하는 롤러의 길이는 m미터이고,
     *        - 롤러로 벽에 페인트를 한 번 칠하는 규칙은 다음과 같습니다.
     *        - 롤러가 벽에서 벗어나면 안 됩니다.
     *      구역의 일부분만 포함되도록 칠하면 안 됩니다.
     *
     *      즉, 롤러의 좌우측 끝을 구역의 경계선 혹은 벽의 좌우측 끝부분에 맞춘 후 롤러를
     *      위아래로 움직이면서 벽을 칠합니다.
     *
     *      현재 페인트를 칠하는 구역들을 완전히 칠한 후 벽에서 롤러를 떼며,
     *      이를 벽을 한 번 칠했다고 정의합니다.
     *
     *      한 구역에 페인트를 여러 번 칠해도 되고 다시 칠해야 할
     *      구역이 아닌 곳에 페인트를 칠해도 되지만 다시 칠하기로 정한 구역은
     *      적어도 한 번 페인트칠을 해야 합니다.
     *
     *      예산을 아끼기 위해 다시 칠할 구역을 정했듯 마찬가지로
     *      롤러로 페인트칠을 하는 횟수를 최소화하려고 합니다.
     *
     *      정수 n, m과 다시 페인트를 칠하기로 정한 구역들의 번호가
     *      담긴 정수 배열 section이 매개변수로 주어질 때 롤러로 페인트칠해야
     *      하는 최소 횟수를 return 하는 solution 함수를 작성해 주세요.
     *
     * </pre>
     *
     */
    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int n = 8; //totalLength
            int m = 4; //maxPaintLength
            int[] section = {2,3,6}; //repaintTargets
            int expected = 2;


            Assertions.assertEquals(expected, solution(n, m, section));
        }
        @Test
        public void case2 () {
            int n = 5;
            int m = 4;
            int[] section = {1,3};
            int expected = 1;
            Assertions.assertEquals(expected, solution(n, m, section));
        }
        @Test
        public void case3 () {
            int n = 4;
            int m = 1;
            int[] section = {1,2,3,4};
            int expected = 4;
            Assertions.assertEquals(expected, solution(n, m, section));
        }
    }

    public int solution( int n , int m, int[] section ) {
        int start = section[0];
        int end = section[0] + m - 1;
        int count = 1;

       for ( int sec : section ) {
           if( sec >= start && sec <= end) continue;

           start = sec;
           end = start + m - 1;
           count += 1;
       }

       return count;
    }

   class Success {
       public int solution( int n , int m, int[] section ) {
           int start = section[0];
           int end = start + (m - 1);
           int ans = 1;

           for ( int num : section ) {
               if ( num >= start && num <= end ) continue;
               else {
                   start = num;
                   end = start + (m - 1);
                   ans ++;
               }
           }

           return ans;
       }
   }
}
