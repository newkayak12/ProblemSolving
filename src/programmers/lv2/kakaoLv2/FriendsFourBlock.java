package programmers.lv2.kakaoLv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

public class FriendsFourBlock {
    //https://school.programmers.co.kr/learn/courses/30/lessons/17679



    /**
     * <pre>
     * 프렌즈4블록
     * 블라인드 공채를 통과한 신입 사원 라이언은 신규 게임 개발 업무를 맡게 되었다.
     * 이번에 출시할 게임 제목은 "프렌즈4블록".
     *
     * 같은 모양의 카카오프렌즈 블록이 2×2 형태로 4개가 붙어있을 경우
     * 사라지면서 점수를 얻는 게임이다.
     *
     * 초기 배치를 문자로 표시하면 아래와 같다.
     *
     * TTTANT           A          A
     * RRFACC           A          A
     * RRRFCC        T TFNT       TFNT
     * TRRRAA        TTFRAA       FRAA
     * TTMMMF        TTMMMF     T MMMF
     * TMMTTJ        TMMTTJ     TMMTTJ
     *
     *  초기     ->     1차시   -> 2차
     *
     *
     * 각 문자는 라이언(R), 무지(M), 어피치(A), 프로도(F), 네오(N), 튜브(T), 제이지(J), 콘(C)을 의미한다
     * 입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.
     *
     * 입력 형식
     * - 입력으로 판의 높이 m, 폭 n과 판의 배치 정보 board가 들어온다.
     * - 2 ≦ n, m ≦ 30
     * - board는 길이 n인 문자열 m개의 배열로 주어진다. 블록을 나타내는 문자는 대문자 A에서 Z가 사용된다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1() {
            int m = 4;
            int n = 5;
            String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
            int answer = 14;

            Assertions.assertEquals(answer, solution(m, n, board));


        }

        @Test
        public void case2() {
            int m = 6;
            int n = 6;
            String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
            int answer = 15;

            Assertions.assertEquals(answer, solution(m, n, board));
        }
    }


    public int solution(int m, int n, String[] board) {
        int answer = 0;
        int[][] table = createTable(m, n, board);


        while (true) {
            Set<String> collect = checkTable(table);
            table = dropTable(table, collect.stream().collect(Collectors.toList()));

            answer += collect.size();

            if( collect.isEmpty() ) break;
        }

        return answer;
    }

    private int[][] createTable(int m, int n, String[] board) {
        int height = m;
        int width = n;
        int[][] table = new int[height][width];

        for (int h = 0; h < m; h ++) {
            for (int w = 0; w < n; w++) table[h][w] = board[h].charAt(w);
        }

        return table;
    }
    private Set<String> checkTable(int[][] table) {
        Set<String> set = new LinkedHashSet<>();
        for( int h = 0; h < table.length - 1; h ++) {
            for (int w = 0; w < table[h].length - 1; w++) {
                int ref = table[h][w];
                int horizontal = table[h + 1][w];
                int vertical = table[h][w + 1];
                int diagonal = table[h + 1][w + 1];

                if(ref == 0 || horizontal == 0 || vertical == 0 ||diagonal == 0) continue;

                if(
                        ref == horizontal &&
                                horizontal == vertical &&
                                vertical == diagonal
                ) {
                    set.add(String.format("%s %s",  w, h));
                    set.add(String.format("%s %s",  w, h + 1));
                    set.add(String.format("%s %s",  w + 1, h));
                    set.add(String.format("%s %s",  w + 1, h + 1));
                }
            }
        }


        return set;
    }
    /**
     0 : [ _, _, _, A, _, _, ],
     1 : [ _, _, _, A, _, _, ],
     2 : [ T, _, T, F, N, T, ],
     3 : [ T, T, F, R, A, A, ],
     4 : [ T, T, M, M, M, F, ],
     5 : [ T, M, M, T, T, J, ],
     */
    private int[][] dropTable(int[][] table, List<String> collect) {
        for( String list : collect) {
            String[] split = list.split(" ");
            Integer width = Integer.parseInt(split[0]);
            Integer height = Integer.parseInt(split[1]);
            table[height][width] = 0;
        }
        print(table);
        for( int height = table.length - 1; height >= 0; height -- ) {
            for( int width = 0; width < table[0].length; width ++ ) {
                if( table[height][width] != 0) continue;
                //element가 0이 아니면
                for ( int k = height - 1; k >= 0; k --) {
                    //다음 element부터 확인
                    if( table[k][width] != 0) { //0이 아닌 경우를 만나면 1:1 교환
                        table[height][width] = table[k][width];
                        table[k][width] = 0;
                        break;
                    }
                }
            }
        }
        print(table);
        return table;
    }

    private void print(int[][] table) {
        System.out.println("[");
        for (int i = 0; i < table.length; i++) {
            int[] row = table[i];
            System.out.print("\t"+ i+" : [ ");
            for (int col : row) {
                System.out.print((char) col + ", ");
            }
            System.out.println("], ");
        }
        System.out.println("]\n");
    }

    private void print(boolean[][] table) {
        System.out.println("[");
        for (boolean[] row : table) {
            System.out.println("\t" + Arrays.toString(row) + ", ");
        }
        System.out.println("]\n");
    }


    class FirstChallenge {
        public int solution(int m, int n, String[] board) {
            boolean[][] check = new boolean[m][n];
            int[][] table = new int[m][n];
            for (int i = 0; i < m; i++) {
                char[] row = board[i].toCharArray();
                for (int j = 0; j < n; j++) {
                    table[i][j] = row[j];
                }
            }

            return bubbling(table, check, 0);
        }

        private int bubbling(int[][] table, boolean[][] check, int count) {
            List<String> result = checkTable(table, check);

            print(table);
            print(check);
            if (result.size() == 0) return count;
            else return bubbling(table, check, count + result.size());
        }

        private List<String> checkTable(int[][] table, boolean[][] check) {
            List<String> result = new LinkedList<>();
            for (int i = 0; i < table.length - 1; i++) {
                for (int j = 0; j < table[i].length - 1; j++) {
                    int startPoint = table[i][j];
                    int horizontal = table[i][j + 1];
                    int verticalInset = 1;
                    int diagonalInset = 1;

                    while ((i + verticalInset) < table.length && check[i + verticalInset][j]) {
                        verticalInset += 1;
                    }
                    while ((i + diagonalInset) < table.length && check[i + diagonalInset][j + 1]) {
                        diagonalInset += 1;
                    }

//                for(; i+ verticalInset < table.length; verticalInset++) {
//                    if( check[i + verticalInset][j]) verticalInset += 1;
//                    else break;
//                }
//
//                for(; i+ diagonalInset < table.length; diagonalInset++) {
//                    if( check[i + diagonalInset][j + 1]) diagonalInset += 1;
//                    else break;
//                }

                    int vertical = table[i + verticalInset][j];
                    int diagonal = table[i + diagonalInset][j + 1];


                    if (
                            startPoint == horizontal &&
                                    startPoint == vertical &&
                                    startPoint == diagonal
                    ) {

                        result.add(String.format("%s %s", i, j));
                        result.add(String.format("%s %s", i, j + 1));
                        result.add(String.format("%s %s", i + verticalInset, j));
                        result.add(String.format("%s %s", i + diagonalInset, j + 1));
                    }
                }
            }

            for (String point : result) {
                String[] split = point.split(" ");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[0]);

                check[x][y] = true;

            }

            return result.stream().distinct().collect(Collectors.toList());
        }


    }

    class SecondChallenge {
        public int solution(int m, int n, String[] board) {
            int answer = 0;
            int height = m;
            int width = n;

            int[][] table = new int[height][width];
            boolean[][] checker = new boolean[height][width];


            for (int h = 0; h < m; h++) {
                for (int w = 0; w < n; w++) table[h][w] = board[h].charAt(w);
            }

            Set<String> all = new HashSet<>();
            while (true) {
                Set<String> set = new HashSet<>();

                for (int h = 0; h < m - 1; h++) {
                    for (int w = 0; w < n - 1; w++) {
                        if (checker[h][w] || checker[h][w + 1]) continue;
                        int verticalHeight = h + 1;
                        int diagonalHeight = h + 1;
                        int horizontalHeight = h;

                        while (checker[verticalHeight][w]) verticalHeight += 1;
                        while (checker[diagonalHeight][w + 1]) diagonalHeight += 1;
                        while (checker[horizontalHeight][w] && diagonalHeight > horizontalHeight) horizontalHeight += 1;


                        int ref = table[h][w];


                        int horizontal = table[horizontalHeight][w + 1];
                        int vertical = table[verticalHeight][w];
                        int diagonal = table[diagonalHeight][w + 1];


//                    0 0, 0 1,
//                    1 0, 1 1, 1 2
//                    2 0, 2 1, 2 2
//                    3 0, 3 1



                        if(h == 0 && w == 2 && ref == 'B') {
                            System.out.println("????????");
                            System.out.println(horizontalHeight);
                            System.out.println(verticalHeight);
                            System.out.println(diagonalHeight);
                            System.out.println((char) vertical);
                            System.out.println((char) diagonal);
                            System.out.println((char) horizontal);
                        }

                        if (
                                ref == horizontal &&
                                        horizontal == vertical &&
                                        vertical == diagonal
                        ) {

                            set.addAll(
                                    Arrays.asList(
                                            String.format("%s %s" , h, w),
                                            String.format("%s %s" , h, w+1),
                                            String.format("%s %s" , verticalHeight, w),
                                            String.format("%s %s" , diagonalHeight, w+1)
                                    )
                            );
                        }
                    }
                }

                System.out.println("AFT" + set);

                if (set.isEmpty()) break;
                else {
                    for( String point : set) {
                        String[] sp = point.split(" ");
                        int hgt = Integer.parseInt(sp[0]);
                        int wth = Integer.parseInt(sp[1]);

                        checker[hgt][wth] = true;
                    }

                    all.addAll(set);
                }
            }

            System.out.println(all);
            print(table);


            return answer;
        }
    }
}
