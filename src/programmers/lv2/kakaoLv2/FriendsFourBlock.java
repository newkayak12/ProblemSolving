package programmers.lv2.kakaoLv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
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
        public void case1 () {
            int m = 4;
            int n = 5;
            String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
            int answer = 14;

            Assertions.assertEquals(answer, solution(m, n, board));

        }

        @Test
        public void case2 () {
            int m = 6;
            int n = 6;
            String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
            int answer = 15;

            Assertions.assertEquals(answer, solution(m, n, board));
        }
    }

    public int solution(int m, int n, String[] board) {
        boolean[][] check = new boolean[m][n];
        int[][] table = new int[m][n];
        for (int i = 0; i <  m; i ++ ) {
            char[] row = board[i].toCharArray();
            for (int j = 0; j < n; j++ ) {
                table[i][j] = row[j];
            }
        }

        return bubbling(table, check, 0);
    }
    private int bubbling(int[][] table,  boolean[][] check, int count) {
        List<String> result = checkTable(table, check);

        print(table);
        print(check);
        if( result.size() == 0 ) return count;
        else return bubbling(table, check, count + result.size());
    }

    private List<String> checkTable( int[][] table, boolean[][] check ) {
        List<String> result = new LinkedList<>();
        for( int i = 0; i < table.length - 1; i ++ ) {
            for( int j = 0; j < table[i].length - 1; j ++ ) {
                int startPoint = table[i][j];
                int horizontal = table[i][j + 1];
                int verticalInset = 1;
                int diagonalInset = 1;

                while( (i + verticalInset) < table.length && check[i + verticalInset][j] ) {
                    verticalInset += 1;
                }
                while( (i + diagonalInset) < table.length && check[i + diagonalInset][j+1]) {
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



                if(
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

        for( String point : result ) {
            String[] split = point.split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[0]);

            check[x][y] = true;

        }





        return result.stream().distinct().collect(Collectors.toList());
    }




    private void print(int[][] table) {
        System.out.println("[");
        for (int[] row : table){
            System.out.print("\t[ ");
            for( int col : row) {
                System.out.print((char) col +", ");
            }
            System.out.println("], ");
        }
        System.out.println("]\n");
    }
    private void print(boolean[][] table) {
        System.out.println("[");
        for (boolean[] row : table){
            System.out.println("\t"+Arrays.toString(row)+", ");
        }
        System.out.println("]\n");
    }
}
