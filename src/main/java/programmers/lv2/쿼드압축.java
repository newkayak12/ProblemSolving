package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class 쿼드압축 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/68936?language=java
     * <p>
     * 1. n % 2 == 0 이어야만 quad가 진행이 가능하다. 2. 그게 아니라면 단일로 카운트 해야 한다.
     * <p>
     * 분할정복?
     * <p>
     * 작은 단위로 나눠서 세고, 모았을 때 모두 같은 수이면 압축
     */


    @Test
    public void solution1() {
        int[][] arr = new int[][]{
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        };
        int[] expected = new int[]{4, 9};

        assertArrayEquals(expected, calculate(arr));
    }

    @Test
    public void solution2() {
        int[][] arr = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 1, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 1, 1, 1}
        };

        int[] expected = new int[]{10, 15};

        assertArrayEquals(expected, calculate(arr));
    }

    private int[] calculate(int[][] arr) {
        return quad(arr, 0, 0, arr.length, new int[2]);
    }

    private boolean zippable(int[][] arr, int y, int x, int size) {
        int sum = 0;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                sum += arr[i][j];
            }
        }

        return sum == 0 || sum == size * size;
    }

    private int[] quad(int[][] arr, int y, int x, int size, int answer[]) {
        boolean isZippable = zippable(arr, y, x, size);
        if (isZippable) {
            answer[arr[y][x]] += 1;
            return answer;
        }

        quad(arr, y, x, size / 2, answer); //leftTop
        quad(arr, y, x + size / 2, size / 2, answer); //rightTop
        quad(arr, y + size / 2, x, size / 2, answer); //leftBottom
        quad(arr, y + size / 2, x + size / 2, size / 2, answer); //rightBottom

        return answer;
    }


}
