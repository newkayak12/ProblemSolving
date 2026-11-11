package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 땅따먹기 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12913?language=javascript
     */


    @Test
    public void solution() {
        int[][] land = new int[][]{
            {1, 2, 3, 5},
            {5, 6, 7, 8},
            {4, 3, 2, 1}
        };
        int expected = 16;

        assertEquals(expected, calculate(land));
    }

    private int calculate(int[][] land) {
        //DP
        // land[i][j] += maxOf(land[i-1][0], land[i-1][1],land[i-1][2])

        for (int i = 1; i < land.length; i++) {//row
            for (int j = 0; j < 4; j++) {  //now?
                int max = Integer.MIN_VALUE;

                if(j != 0) max = Math.max(land[i][j] + land[i-1][0], max);
                if(j != 1) max = Math.max(land[i][j] + land[i-1][1], max);
                if(j != 2) max = Math.max(land[i][j] + land[i-1][2], max);
                if(j != 3) max = Math.max(land[i][j] + land[i-1][3], max);

                land[i][j] = max;
            }


        }

        int max = Integer.MIN_VALUE;

        for(int element : land[land.length - 1]){
            max = Math.max(max, element);
        }


        return max;
    }
}
