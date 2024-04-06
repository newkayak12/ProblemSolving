package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


//retry
public class StockPrice {
    // https://school.programmers.co.kr/learn/courses/30/lessons/42584
    /**
     *
     * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때,
     * 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
     *
     * 제한사항
     *   - prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
     *   - prices의 길이는 2 이상 100,000 이하입니다.
     */

    @Nested
    public class TestCase {
        @Test
        public void case1 () {
            int[] prices = new int[] {1,2,3,2,3};
            int[] returnValue = new int[] {4,3,1,1,0};


            Assertions.assertArrayEquals(returnValue, solution(prices));
        }

        @Test
        public void case2 () {
            int[] prices = new int[]      {4,1,9,2,3,4,5,6,1};
            int[] returnValue = new int[] {1,7,1,4,3,2,2,1,0};


            Assertions.assertArrayEquals(returnValue, solution(prices));
        }
    }

    /**
     * 왜 이걸 스택/ 큐로 풀라고 한걸까?
     */
    public int[] solutionFor(int[] prices) {
        int[] answer = new int[prices.length];

        for ( int i = 0; i < prices.length - 1; i ++ ) {

            for (  int j = i + 1; j < prices.length; j ++ ) {
                answer[i] += 1;
                if( prices[i] > prices[j]) break;
            }

        }

        print(answer);
        return answer;
    }

    /**
     * 이래서?
     * 이게 인덱스별로 지속 기간을 세는거라
     * 각 인덱스랑은 비교하는거 이외 시간 초는 독립 개념임
     *
     * 문제를 풀 때 작은 단위로 문제를 쪼갰으면 좋았을거 같은데
     */
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> idx = new Stack<>();
        idx.add(0);

        for ( int i = 1; i < prices.length; i ++ ) {
            while(
                    !idx.isEmpty() &&
                    prices[i] < prices[idx.peek()]
            ) {
                int index = idx.pop();
                answer[index] = i - index;
            }

            idx.add(i);
        }


        while( !idx.isEmpty() ) {
            int index = idx.pop();
            answer[index] = prices.length - index - 1;
        }

        return answer;
    }

    public int[] solutionQueue(int[] prices) {
        int[] answer = new int[prices.length];

        Queue<Integer> queue = new LinkedList<>();
        for ( int price : prices ) queue.add(price);


        int idx = 0;
        while( !queue.isEmpty() ) {
            int compare = queue.poll();
            int count = 0;

            for (int  i = idx + 1; i < prices.length; i ++ ) {
                if( compare > prices[i] ) {
                    count += 1;
                    break;
                }
                count += 1;
            }

            answer[idx++] = count;
        }


        print(answer);
        return answer;
    }


    public int[] solutionFailure(int[] prices) {
        int[]  answer = new int[prices.length];
        Arrays.fill(answer, 1);
        answer[prices.length - 1] = 0;
        for( int i = prices.length - 2; i >= 0; i -- ) {
            if( prices[i] <= prices[ i + 1] ) {
                answer[i] = prices.length  - (i + 1);
            } else {
                answer[i] = 1;
            }
        }
//        print(answer);
        return answer;
    }

    void print (int[] arr) {
        for( int i : arr ) System.out.print(i + ", ");
        System.out.println();
    }
}
