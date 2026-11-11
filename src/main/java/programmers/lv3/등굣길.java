package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 등굣길 {

    /**
     * DP 사용하면 될 것 같고 최단 거리로 갱신하면서 내려가면 될 것 같음 근데 문제는 개수란 말이지? 어떻게 개수를 셀 것인가? 각 셀에 숫자는 해당 칸까지 최소로 도달하는 가짓수일텐데
     */

    public int solution(int m, int n, int[][] puddles) {
        int x = m;
        int y = n;
        long[][] map = new long[y][x];

        map[0][0] = 1;
        for (int[] puddle : puddles) {
            int puddleX = puddle[0];
            int puddleY = puddle[1];
            map[puddleY - 1][puddleX - 1] = Long.MAX_VALUE;
        }

        int[][] directions = {{1, 0}, {0, 1},};

        //{-1, 0},{0, -1}

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                if (map[i][j] == Long.MAX_VALUE) {
                    continue;
                }
                for (int[] direction : directions) {
                    int deltaY = direction[0];
                    int deltaX = direction[1];
                    if (i + deltaY < y && i + deltaY > -1 &&
                        j + deltaX < x && j + deltaX > -1 &&
                        map[i + deltaY][j + deltaX] != Long.MAX_VALUE
                    ) {
                        map[i + deltaY][j + deltaX] = (map[i + deltaY][j + deltaX] + map[i][j]) % 1_000_000_007;
                    }
                }
            }
        }


        return (int) (map[n - 1][m - 1]);
    }


    @Test
    public void test1() {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int result = 4;

        assertEquals(result, solution(m, n, puddles));
    }
}
