package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PickingTheGround {
    //https://school.programmers.co.kr/learn/courses/30/lessons/12913
    /**
     * <pre>
     * 땅따먹기 게임을 하려고 합니다. 땅따먹기 게임의 땅(land)은 총 N행 4열로 이루어져 있고,
     * 모든 칸에는 점수가 쓰여 있습니다.
     * 1행부터 땅을 밟으며 한 행씩 내려올 때,
     * 각 행의 4칸 중 한 칸만 밟으면서 내려와야 합니다.
     * 단, 땅따먹기 게임에는 한 행씩 내려올 때,
     * 같은 열을 연속해서 밟을 수 없는 특수 규칙이 있습니다.
     *
     * 예를 들면,
     * | 1 | 2 | 3 | 5 |
     * | 5 | 6 | 7 | 8 |
     * | 4 | 3 | 2 | 1 |
     *
     * 로 땅이 주어졌다면, 1행에서 네번째 칸 (5)를 밟았으면,
     * 2행의 네번째 칸 (8)은 밟을 수 없습니다.
     * 마지막 행까지 모두 내려왔을 때,
     * 얻을 수 있는 점수의 최대값을 return하는 solution 함수를 완성해 주세요.
     * 위 예의 경우,
     * 1행의 네번째 칸 (5),
     * 2행의 세번째 칸 (7),
     * 3행의 첫번째 칸 (4) 땅을 밟아 16점이 최고점이 되므로 16을 return 하면 됩니다.
     *
     * 제한사항
     * - 행의 개수 N : 100,000 이하의 자연수
     * - 열의 개수는 4개이고, 땅(land)은 2차원 배열로 주어집니다.
     * - 점수 : 100 이하의 자연수
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1() {
            int[][] land = {{1,2,3,5}, {5,6,7,8}, {4,3,2,1}};
            int answer = 16;

            Assertions.assertEquals(answer, solution(land));
        }
        @Test
        public void case2() {
            int[][] land = {{1,2,3,3}, {5,6,7,8}, {4,3,2,1}};
            int answer = 15;

            Assertions.assertEquals(answer, solution(land));
        }
    }

    //BFS
    public int solution( int[][] land) {

        for( int y = 1; y < land.length; y ++ ) {
            int max = -1;
            for ( int x = 0; x < land[0].length; x ++ ) {
                if ( x != 0 ) max = Math.max(land[y][x] + land[y - 1][0], max);
                if ( x != 1 ) max = Math.max(land[y][x] + land[y - 1][1], max);
                if ( x != 2 ) max = Math.max(land[y][x] + land[y - 1][2], max);
                if ( x != 3 ) max = Math.max(land[y][x] + land[y - 1][3], max);

                land[y][x] = max;
                max = -1;
            }
//            System.out.println(Arrays.stream(land[y]).boxed().map(String::valueOf).collect(Collectors.joining(", ")));;
        }

        int max = -1;
        for (int x = 0; x < 4; x ++) max = Math.max(land[land.length - 1 ][x], max);
        return max;
    }
    public int solution2( int[][] land) {
        // y의 개수 n개
        // x의 개수 4개
        int beforeMax = -1;
        int beforeIndex = -1;
        int accumulate = 0;

        for( int x = 0; x < land[0].length; x ++ ) {
            if( beforeMax < land[0][x] ){
                beforeIndex = x;
                beforeMax = land[0][x];
            }
        }
        accumulate += beforeMax;
        for ( int y = 1; y < land.length; y ++ ) {
            beforeMax = -1;
            int indexNow = -1;
            for ( int x = 0; x < land[0].length; x ++ ) {
                if ( x == beforeIndex ) continue;
                if ( beforeMax < land[y][x] ){
                    indexNow = x;
                    beforeMax = land[y][x];
                }
            }

            beforeIndex = indexNow;
            accumulate += beforeMax;
        }

        return accumulate;
    }
}
