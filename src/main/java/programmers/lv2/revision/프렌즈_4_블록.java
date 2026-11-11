package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class 프렌즈_4_블록 {


    @Test
    void test1() {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        int answer = 14;

        assertEquals(answer, solution(m, n, board));
    }

    @Test
    void test2() {
        int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        int answer = 15;

        assertEquals(answer, solution(m, n, board));
    }


    char[][] createTable(int m, int n, String[] board) {
        char[][] table = new char[m][n];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                table[i][j] = board[i].charAt(j);
            }
        }

        return table;
    }

    Set<String> match(char empty, int m, int n, String delimiter, char[][] table) {
        Set<String> matches = new HashSet<>();

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                char character = table[i][j];
                if (character == empty) {
                    continue;
                }

                boolean match = table[i + 1][j] == character &&
                    table[i + 1][j + 1] == character &&
                    table[i][j + 1] == character;

                if (match) {
                    matches.add(i +delimiter+ j);
                    matches.add((i + 1) +delimiter+j);
                    matches.add(i+delimiter+ (j + 1));
                    matches.add((i + 1) +delimiter+(j + 1));
                }

            }
        }

        return matches;
    }

    int clear(char empty, String delimiter, char[][] table, Set<String> matches) {

        for (String match : matches) {
            String[] split = match.split(delimiter);
            int matchY = Integer.parseInt(split[0]);
            int matchX = Integer.parseInt(split[1]);


            table[matchY][matchX] = empty;
        }

        return matches.size();
    }

    char[][] scroll(char empty, int m, int n, char[][] table) {
        boolean run;

        do {

            run = false;

            for (int i = m - 1; i > 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (table[i][j] == empty && table[i - 1][j] != empty) {
                        table[i][j] = table[i - 1][j];
                        table[i - 1][j] = empty;
                        run = true;
                    }
                }
            }

        } while(run);


        return table;
    }

    int solution(int m, int n, String[] board) {

        int score = 0;
        String delimiter = "/";
        char empty = '-';
        char[][] table = createTable(m, n, board);

        while (true) {

            //찾기
            Set<String > matches = match(empty, m, n, delimiter, table);
            if (matches.isEmpty()) {
                break;
            }

            // 정리하기
            score += clear(empty, delimiter, table, matches);

            //스크롤
            table = scroll(empty, m, n, table);

        }

        return score;
    }
}
