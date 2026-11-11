package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 땅따먹기 {

    /**
     * greedy
     * 각 선택에서 최대 값을 선택
     * 단, 지정한 조건 준수
     *
     *
     * int sum = 0
     * for row의 1부터
     *     for cell
     *          int 최대값;
     *
     *          if( cell != 1 ) 최대 값 = max( 최대 값, array[row][1] + array[row - 1][1])
     *          if( cell != 2 )  최대 값 = max( 최대 값, array[row][2] + array[row - 1][2])
     *          if( cell != 3 )  최대 값 = max( 최대 값, array[row][3] + array[row - 1][3])
     *          if( cell != 4 )  최대 값 = max( 최대 값, array[row][4] + array[row - 1][4])
     *
     *          array[row][col] = 최대 값 기록
     *
     * int max = Integer.MIN_VALUE
     * for cell
     *   max = max(max, array[array.length - 1][cell]
     */

    int solution(int[][] land) {
        for(int row = 1; row < land.length; row++) {
            for(int col = 0; col < 4; col++) {
                int max = Integer.MIN_VALUE;

                if(col != 0) max = Math.max(max, land[row][col] +  land[row - 1][0]);
                if(col != 1) max = Math.max(max, land[row][col] +  land[row - 1][1]);
                if(col != 2) max = Math.max(max, land[row][col] +  land[row - 1][2]);
                if(col != 3) max = Math.max(max, land[row][col] +  land[row - 1][3]);

                land[row][col] = max;
            }
        }


        int max = Integer.MIN_VALUE;
        for(int col = 0; col < land.length; col++) {
            max = Math.max(max, land[land.length - 1][col]);
        }

        return max;
    }

    @Test
    void test1() {
        int[][] land = new int[][]{{1,2,3,5}, {5,6,7,8},{4,3,2,1}};
        int answer = 	16;

        assertEquals(answer, solution(land));
    }
}
