package programmers.lv1.kakaoLv1.crane;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

public class Crane {
//    https://school.programmers.co.kr/learn/courses/30/lessons/64061?language=java

// comment : 행렬 뒤집어진게 진짜 열받는 포인트
//[
//[0,0,0,0,0],
//[0,0,1,0,3],
//[0,2,5,0,1],
//[4,2,4,4,2],
//[3,5,1,3,1]
//]


    @Nested
    public  class TestCases {
        @Test
        public void case1 () {
            int[][] board = {
                              {0, 0, 0, 0, 0},
                              {0, 0, 1, 0, 3},
                              {0, 2, 5, 0, 1},
                              {4, 2, 4, 4, 2},
                              {3, 5, 1, 3, 1}
                            };
            int[] moves = {1,5,3,5,1,2,1,4};
            int result = 4;

            Assertions.assertEquals(solution(board, moves), result);

        }
    }

    public  int solution( int [][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> bucket = new Stack<>();
        for( int move : moves ) {
            for( int i = 0; i  < board.length; i ++ ) {
                int number = board[i][move - 1];
                if( number != 0 ) {
                    board[i][move - 1] = 0;

                    if(
                            !bucket.isEmpty() &&
                            number ==  bucket.peek()
                    ) {
                       bucket.pop();
                       answer += 2;
                    } else {
                        bucket.add(number);
                    }
                    break;
                }
            }
        }
        return answer;
    }


    static class  Success {
        public static void main(String[] args) {
            int[][] board = {
                    {0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 3},
                    {0, 2, 5, 0, 1},
                    {4, 2, 4, 4, 2},
                    {3, 5, 1, 3, 1}
            };
            int[] moves = {1,5,3,5,1,2,1,4};
            System.out.println(solution(board, moves));



            int[][] board2 = {
                    {1, 1, 1, 1, 0},
                    {0, 0, 1, 1, 3},
                    {0, 2, 5, 2, 1},
                    {4, 2, 4, 8, 2},
                    {3, 5, 1, 2, 1}
            };
            int[] moves2 = {1,5,4,2,2,1,2,3};


            System.out.println(solution(board2, moves2));
        }

        public static  int solution( int [][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> bucket = new Stack<>();


        for ( int move: moves){
          inner: for (int i = 0; i < board.length; i ++){
               int pick = board[i][move - 1];
               if( pick != 0 ){
                   bucket.push(pick);
                   board[i][move - 1] = 0;
                   break inner;
               }
           }
            if( bucket.size() >= 2 ) {
                int latest = bucket.pop();
                int prev = bucket.pop();
                if (latest == prev ) answer +=2;
                else {
                    bucket.push(prev);
                    bucket.push(latest);
                }
            }
        }


        return answer;
    }
    }
}
