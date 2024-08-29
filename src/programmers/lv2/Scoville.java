package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class Scoville {

    //https://school.programmers.co.kr/learn/courses/30/lessons/42626
    /**
     * <pre>
     *     매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
     *     모든 음식의 스코빌 지수를 K 이상으로 만들기 위해
     *     Leo는 스코빌 지수가 가장 낮은 두 개의 음식을
     *     아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.
     *
     *      섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
     *
     *      Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
     *      Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가
     *      주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기
     *      위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.
     *
     *      제한 사항
     *      - scoville의 길이는 2 이상 1,000,000 이하입니다.
     *      - K는 0 이상 1,000,000,000 이하입니다.
     *      - scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
     *      - 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] scoville = new int[]{1, 2, 3, 9, 10, 12};
            int k = 7;
            int result = 2;

            Assertions.assertEquals(result, solution(scoville, k));
        }
    }


    public int solution(int[] scoville, int K){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for( int element : scoville ) queue.add(element);

        int count = 0;
        if (queue.peek() > K) return - 1;

        while (queue.size() >= 2 && queue.peek() < K) {
            count += 1;
            int first = queue.poll();
            int second = queue.poll();
            queue.add(first +( second * 2));
        }

        if( queue.peek() < K) return -1;
        else return count;
    }

    class Success {
        public int solution(int[] scoville, int K) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            int answer = 0;
            for ( int sc : scoville) queue.add(sc);

            if (K == 0) return -1;
            if (queue.peek() > K) return - 1;
            else {
                while( queue.size() >= 2 && queue.peek() < K ) {
                    answer += 1;
                    int smallest = queue.poll();
                    int nextOne = queue.poll();
                    queue.add(smallest + nextOne * 2);
                }

                if( queue.peek() < K ) return -1;
            }

            return answer;
        }


        public int solutionOutOfMemory(int[] scoville, int K) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for ( int sc : scoville) queue.add(sc);

            if (queue.peek() > K) return - 1;
            else return mixingBowl(queue, K, 1);
        }

        private int mixingBowl ( PriorityQueue<Integer> mix, int k, int count ) {

            int smallest = mix.poll();
            int nextOne = mix.poll();

            int result = smallest + nextOne * 2;
            mix.add(result);

            System.out.println(mix);

            if( mix.peek() > k) return count;
            else return mixingBowl(mix, k, count + 1);
        }
    }


}
