package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.print.attribute.SetOfIntegerSyntax;
import java.util.*;

public class NumberConversion {
    //https://school.programmers.co.kr/learn/courses/30/lessons/154538
    /***
     * 자연수 x를 y로 변환하려고 합니다. 사용할 수 있는 연산은 다음과 같습니다.
     *
     * x에 n을 더합니다
     * x에 2를 곱합니다.
     * x에 3을 곱합니다.
     *
     * 자연수 x, y, n이 매개변수로 주어질 때, x를 y로 변환하기 위해
     * 필요한 최소 연산 횟수를 return하도록 solution 함수를 완성해주세요.
     * 이때 x를 y로 만들 수 없다면 -1을 return 해주세요.
     *
     *
     * 제한사항
     * - 1 ≤ x ≤ y ≤ 1,000,000
     * - 1 ≤ n < y
     */
    @Nested
    class TestCases {
     @Test
     public void case1 () {
         int x = 10;
         int y = 40;
         int n = 5;
         int result = 2;

         Assertions.assertEquals(result, solution(x, y, n));
     }
        @Test
        public void case2 () {
            int x = 10;
            int y = 40;
            int n = 30;
            int result = 1;

            Assertions.assertEquals(result, solution(x, y, n));
        }
        @Test
        public void case3 () {
            int x = 2;
            int y = 5;
            int n = 4;
            int result = -1;

            Assertions.assertEquals(result, solution(x, y, n));
        }
    }


    public int solution(int x, int y, int n){
       Queue<Integer[]> queue = new LinkedList<>();
       Set<Integer> visit = new HashSet<>();
       queue.add(new Integer[]{x, 0});

       while( !queue.isEmpty() ) {
           Integer[] tuple = queue.poll();
           if( tuple[0] == y ) return tuple[1];

           if( !visit.contains(tuple[0] + n) && tuple[0] < y ) {
               int calculated = tuple[0] + n;
               queue.add(new Integer[]{calculated, tuple[1] + 1});
               visit.add(calculated);
           }
           if( !visit.contains(tuple[0] * 2) && tuple[0] < y ) {
               int calculated = tuple[0] * 2;
               queue.add(new Integer[]{calculated, tuple[1] + 1});
               visit.add(calculated);
           }
           if( !visit.contains(tuple[0] * 3) && tuple[0] < y ) {
               int calculated = tuple[0] * 3;
               queue.add(new Integer[]{calculated, tuple[1] + 1});
               visit.add(calculated);
           }

       }

       return -1;
    }

    class Success {

        public int solution(int x, int y, int n) {
            return this.calc(x, y, n);
        }

        //BFS
        private int calc( int x, int y, int n) {
            int count = 0;
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            queue.add(x);

            while( !queue.isEmpty() ) {

                int nowSize = queue.size();
                for (int i = 0; i < nowSize; i ++ ) {
                    int prevNum = queue.poll();
                    if( prevNum == y) return count;
                    if( !visited.contains(prevNum + n) && prevNum + n <= y ) {
                        queue.add(prevNum + n);
                        visited.add(prevNum + n);
                    }
                    if( !visited.contains(prevNum * 2) && prevNum * 2 <= y ) {
                        queue.add(prevNum * 2);
                        visited.add(prevNum * 2);
                    }
                    if( !visited.contains(prevNum * 3) && prevNum * 3 <= y ) {
                        queue.add(prevNum * 3);
                        visited.add(prevNum * 3);
                    }
                }
                count += 1 ;
            }

            return  -1;
        }
        //    BFS
        private int calcQueue( int x, int y, int n, int count) {
            Queue<List<int[]>> stack = new LinkedList<>();
            List<Integer> answerList = new ArrayList<>();
            stack.add(Arrays.asList(new int[] {x, count}));


            while ( !stack.isEmpty() ) {
                List<int[]> pop = stack.poll();
                for(int i = 0; i < pop.size(); i ++  ) {
                    int[] state = pop.get(i);

                    int prevNum = state[0];
                    int prevCount = state[1];

                    if (prevCount  == -1) continue;
                    if (prevNum == y) {
                        answerList.add(prevCount);
                        break;
                    }

                    List<int[]> tmp = new ArrayList<>();

                    tmp.add(new int[]{prevNum * 3, prevNum * 3 > y ? -1 : prevCount + 1});
                    tmp.add(new int[]{prevNum * 2, prevNum * 2 > y ? -1 : prevCount + 1});
                    tmp.add(new int[]{prevNum + n, prevNum + n > y ? -1 : prevCount + 1});
                    stack.add(tmp);
                }
            }

            if (answerList.isEmpty()) return  - 1;
            else return Collections.min(answerList);

        }
        //DFS
        //timeout
        private int calcStack( int x, int y, int n, int count) {
            List<Integer> answerList = new ArrayList<>();
            Stack<List<int[]>> stack = new Stack<>();
            stack.add(Arrays.asList(new int[] {x, count}));


            while ( !stack.isEmpty() ) {
                List<int[]> pop = stack.pop();
                for(int i = 0; i < pop.size(); i ++  ) {
                    int[] state = pop.get(i);

                    int prevNum = state[0];
                    int prevCount = state[1];

                    if (prevCount  == -1) continue;
                    if (prevNum == y) {
                        answerList.add(prevCount);
                        break;
                    }

                    List<int[]> tmp = new ArrayList<>();

                    tmp.add(new int[]{prevNum * 3, prevNum * 3 > y ? -1 : prevCount + 1});
                    tmp.add(new int[]{prevNum * 2, prevNum * 2 > y ? -1 : prevCount + 1});
                    tmp.add(new int[]{prevNum + n, prevNum + n > y ? -1 : prevCount + 1});
                    stack.add(tmp);
                }
            }

            if (answerList.isEmpty()) return  - 1;
            else return Collections.min(answerList);
        }
        //timeout
        private int calcRecursive( int x, int y, int n, int count) {

            if ( x == y ) return count - 1;
            else if ( x < y ) {

                int pl= this.calcRecursive(x + n, y, n, count + 1);
                int multTwo = this.calcRecursive(x * 2, y, n, count + 1);
                int multThree = this.calcRecursive(x * 3, y, n, count + 1);

                count = Math.min(Math.min(pl, multTwo), multThree);
                return count;
            } else return Integer.MAX_VALUE;

        }
    }
}
