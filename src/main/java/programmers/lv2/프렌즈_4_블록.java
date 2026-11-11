package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 프렌즈_4_블록 {


    @Test
    public void solution1() {
        int m = 4;
        int n = 5;
        String[] board = new String[]{
            "CCBDE", "AAADE", "AAABF", "CCBBF"
        };
        int expected = 14;
        assertEquals(expected, calculate(m, n, board));
    }

    @Test
    public void solution2() {
        int m = 6;
        int n = 6;
        String[] board = new String[]{
            "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"
        };
        int expected = 15;

        assertEquals(expected, calculate(m, n, board));
    }

    @Test
    public void solution3() {
        int m = 3;
        int n = 6;
        String[] board = new String[]{
            "TTTTTT", "TTTTTT", "RRRFCC",
        };
        int expected = 12;

        assertEquals(expected, calculate(m, n, board));
    }

    @Test
    public void solution4() {
        int m = 2;
        int n = 6;
        String[] board = new String[]{
            "TTTTTT", "TTTTTT",
        };
        int expected = 12;

        assertEquals(expected, calculate(m, n, board));
    }

    @Test
    public void solution5() {
        int m = 2;
        int n = 6;
        String[] board = new String[]{
            "TTTTTT", "TTTTTT",
        };
        int expected = 12;

        assertEquals(expected, calculate(m, n, board));
    }


    private int calculate(int m, int n, String[] board) {
        /**
         * 찾는거
         *
         * 정리하는거
         */

        char[][] boards = Arrays.stream(board).map(String::toCharArray).toArray(char[][]::new);
        boolean hasNext = true;

        while (hasNext) {
            hasNext = false;
            boolean[][] breaks = new boolean[m][n];

            for (int h = 0; h < m - 1; h++) {
                for (int w = 0; w < n - 1; w++) {
                    char point = boards[h][w];
                    char right = boards[h][w + 1];
                    char down = boards[h + 1][w];
                    char diagonal = boards[h + 1][w + 1];

                    if (point == '_' || right == '_' || down == '_' || diagonal == '_') {
                        continue;
                    }
                    if (point == right && right == down && down == diagonal) {
                        breaks[h][w] = true;
                        breaks[h][w + 1] = true;
                        breaks[h + 1][w] = true;
                        breaks[h + 1][w + 1] = true;

                        hasNext = true;
                    }
                }
            }

            for (int h = 0; h < m; h++) {
                for (int w = 0; w < n; w++) {
                    if (breaks[h][w]) {
                        boards[h][w] = '_';
                    }
                }
            }

            for (int w = 0; w < n; w++) {
                for (int h = m - 1; h >= 0; h--) {
                    if (boards[h][w] == '_') {
                        for (int k = h - 1; k >= 0; k--) {
                            if (boards[k][w] != '_') {
                                boards[h][w] = boards[k][w];
                                boards[k][w] = '_';
                                break;
                            }
                        }
                    }
                }
            }
        }

        int result = (int) Arrays.stream(boards)
            .map(it -> new String(it))
            .flatMap(it -> Arrays.stream(it.split("")))
            .filter(it -> it.equals("_"))
            .count();
        return result;
    }
}
