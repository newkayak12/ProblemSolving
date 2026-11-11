package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 게임_맵_최단거리 {


    @Test
    public void solution1() {
        int[][] maps = new int[][]{
            {1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {0, 0, 0, 0, 1}
        };
        int expected = 11;

        assertEquals(expected, calculate(maps));
    }

    @Test
    public void solution2() {
        int[][] maps = new int[][]{
            {1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1}
        };
        int expected = -1;

        assertEquals(expected, calculate(maps));
    }

    private int calculate(int[][] maps) {
        //BFS
        int height = maps.length;
        int width = maps[0].length;

        boolean[][] visit = new boolean[height][width];
        ArrayDeque<Integer[]> queue = new ArrayDeque<>();
        queue.addLast(new Integer[]{0, 0, 1});
        visit[0][0] = true;

        while (!queue.isEmpty()) {
            Integer[] position = queue.removeFirst();
            int y = position[0];
            int x = position[1];
            int count = position[2];

            if (y == height - 1 && x == width - 1) {
                return count;
            }


            if (y - 1 >= 0 && !visit[y - 1][x] && maps[y - 1][x] == 1) {
                queue.addLast(new Integer[]{y - 1, x, count + 1});
                visit[y - 1][x] = true;
            }
            if (y + 1 <= height - 1 && !visit[y + 1][x] && maps[y + 1][x] == 1) {
                queue.addLast(new Integer[]{y + 1, x, count + 1});
                visit[y + 1][x] = true;
            }
            if (x - 1 >= 0 && !visit[y][x - 1] && maps[y][x - 1] == 1) {
                queue.addLast(new Integer[]{y, x - 1, count + 1});
                visit[y][x - 1] = true;
            }
            if (x + 1 <= width - 1 && !visit[y][x + 1] && maps[y][x + 1] == 1) {
                queue.addLast(new Integer[]{y, x + 1, count + 1});
                visit[y][x + 1] = true;
            }
        }

        return -1;
    }

}
