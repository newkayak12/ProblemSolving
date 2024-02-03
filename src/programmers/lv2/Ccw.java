package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Ccw {
    //https://www.acmicpc.net/problem/11758
    /**
     * <pre>
     * 2차원 좌표 평면 위에 있는 점 3개 P1, P2, P3가 주어진다.
     * P1, P2, P3를 순서대로 이은 선분이 어떤 방향을 이루고 있는지 구하는 프로그램을 작성하시오.
     *
     * P1, P2, P3를 순서대로 이은 선분이 반시계 방향을 나타내면 1, 시계 방향이면 -1, 일직선이면 0을 출력한다.
     * </pre>
     */

    @Nested
    class TestCase {
        @Test
        public void case1 () {
            String[] points = {"1,1", "3,3", "7,3"};
            int result = -1;
            Assertions.assertEquals(result, solution(points));
        }

        @Test
        public void case2 () {
            String[] points = {"1,1", "3,3", "5,5"};
            int result = 0;
            Assertions.assertEquals(result, solution(points));
        }

        @Test
        public void case3 () {
            String[] points = {"1,1", "7,3", "5,5"};
            int result = 1;
            Assertions.assertEquals(result, solution(points));
        }
    }

    public int solution( String[] points ) {
        int[][] coordinate = new int[points.length][2];

        for ( int i = 0; i < points.length; i ++ ) {
            String point[] = points[i].split(",");

            coordinate[i] = Arrays.stream(point)
                                  .mapToInt(Integer::parseInt)
                                  .toArray();
        }

        int result = (
                (coordinate[1][0] - coordinate[0][0]) *
                (coordinate[2][1] - coordinate[0][1])
                        -
                (coordinate[2][0] - coordinate[0][0]) *
                (coordinate[1][1] - coordinate[0][1])
        );

        if (result > 0) return 1;
        else if (result == 0) return 0;
        else return -1;
    }


    /**
     * 필요한 개념
     * 두 좌표로 기울기를 구하는 공식
     * - ( y2 -  y1 ) / ( x2 - x1 )
     *
     * 외적
     * - (x2-x1) (y3-y1) - (x3-x1) (y2-y1)
     *
     * > 벡터
     *
     * 1. 스칼라
     * - 스칼라(Scalar)는 행렬/벡터를 구성하는 요소인 각 숫자들을 의미한다.
     * 스칼라는 행렬/벡터의 구성 요소 중 최소 단위를 의미하며, 물리학에서는 크기를 가지는 요소라고 생각된다.
     *
     * 2. 벡터
     * - 스칼라들의 집합으로 표현할 수 있으며, 크기와 방향을 모두 가지는 요소이다.
     * 즉, 벡터는 숫자를 원소로 가지는 배열로써 d차원 공간에서의 원점으로부터의 상대적으로 위치한 한 점을 의미한다.
     *
     * 벡터는 행벡터, 열벡터로 이뤄져 있다.
     *
     * 3. 연산
     * - 벡터의 덧셈/ 뺼셈
     * 두 벡터의 덧셈/ 뺼셈의 연산은 차원이 동일한 벡터끼리 연산이 가능하다.
     * 두 벡터의 덧셈은 다른 벡터를 원점으로 두고 상대적으로 위치하는 것으로 원점을 다른 벡터로 이동하여
     * 상대적 위치를 구하는 연산이 된다.
     *
     * - 벡터의 곱셈
     * 스칼라 곱은 벡터의 모든 요소에 ɑ만큼의 스칼라를 곱해주는 연산으로 벡터의 길이,
     * 원점으로부터의 상대적 길이를 늘려주는 연산이다. 스칼라가 음수라면 벡터의 방향이 바뀐다.
     *
     * 두 벡터의 성분곱은 각각에 대응하는 두 벡터의 원소들을 서로 곱하는 연산으로
     * 덧셈/뺄셈과 마찬가지로 두 벡터의 차원이 동일해야 연산이 가능하다.
     *
     * - 벡터의 내적 (벡터의 특정 방향, 성분, 투영(사영)의 크기, 일의 크기, 전류 밀도에 대한 전류의 크기 등을 구할 떄 필요)
     * 행렬의 기본 연산 중의 하나로, 다른 연산들은 input, output이 같지만 내적의 경우 벡터의 벡터의
     * 연산 결과 값이 스칼라로 도출된다.
     * > 교환 법칙이 성립한다. ( A X B = B X A )
     *
     *
     * - 벡터의 외적( 면 벡터의 표면, 토크, 각속도 등을 구할 때 필요하다.)
     * 외적은 벡터 곱(VectorProduct) 또는 CrossProduct라고 말하며, 두 베겉의 크기와 두 벡터 사이의 각이 사인 값
     * 그리고 수직인 벡터의 곱으로 정의한다.
     * > 교환 법칙이 성립하지 않는다. 대신 방향이 반대로 된다. ( A X B = -B X A )
     *
     *
     */
}
