package programmers.lv2.kakaoLv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

//// NOTSOLVED
public class MakeQueuesSumSame {
    //https://school.programmers.co.kr/learn/courses/30/lessons/118667
    /**
     * <pre>
     * 문제 설명
     *
     * 길이가 같은 두 개의 큐가 주어집니다.
     * 하나의 큐를 골라 원소를 추출(pop)하고,
     * 추출된 원소를 다른 큐에 집어넣는(insert) 작업을 통해 각 큐의 원소 합이 같도록 만들려고 합니다.
     * 이때 필요한 작업의 최소 횟수를 구하고자 합니다.
     * 한 번의 pop과 한 번의 insert를 합쳐서 작업을 1회 수행한 것으로 간주합니다.
     *
     * 큐는 먼저 집어넣은 원소가 먼저 나오는 구조입니다.
     * 이 문제에서는 큐를 배열로 표현하며, 원소가 배열 앞쪽에 있을수록 먼저 집어넣은 원소임을 의미합니다.
     * 즉, pop을 하면 배열의 첫 번째 원소가 추출되며,
     * insert를 하면 배열의 끝에 원소가 추가됩니다.
     *
     * 예를 들어 큐 [1, 2, 3, 4]가 주어졌을 때,
     * pop을 하면 맨 앞에 있는 원소 1이 추출되어 [2, 3, 4]가 되며,
     * 이어서 5를 insert하면 [2, 3, 4, 5]가 됩니다.
     *
     * 다음은 두 큐를 나타내는 예시입니다.
     * > queue1 = [3, 2, 7, 2]
     * > queue2 = [4, 6, 5, 1]
     *
     * 두 큐에 담긴 모든 원소의 합은 30입니다. 따라서,
     * 각 큐의 합을 15로 만들어야 합니다.
     * 예를 들어, 다음과 같이 2가지 방법이 있습니다.
     *
     *  1. queue2의 4, 6, 5를 순서대로 추출하여 queue1에 추가한 뒤,
     *     queue1의 3, 2, 7, 2를 순서대로 추출하여 queue2에 추가합니다.
     *     그 결과 queue1은 [4, 6, 5], queue2는 [1, 3, 2, 7, 2]가 되며,
     *     각 큐의 원소 합은 15로 같습니다. 이 방법은 작업을 7번 수행합니다.
     *
     *  2. queue1에서 3을 추출하여 queue2에 추가합니다.
     *     그리고 queue2에서 4를 추출하여 queue1에 추가합니다.
     *     그 결과 queue1은 [2, 7, 2, 4], queue2는 [6, 5, 1, 3]가 되며,
     *     각 큐의 원소 합은 15로 같습니다. 이 방법은 작업을 2번만 수행하며,
     *     이보다 적은 횟수로 목표를 달성할 수 없습니다.
     *
     * 따라서 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수는 2입니다.
     * 길이가 같은 두 개의 큐를 나타내는 정수 배열 queue1, queue2가 매개변수로 주어집니다.
     * 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수를 return 하도록 solution 함수를 완성해주세요.
     * 단, 어떤 방법으로도 각 큐의 원소 합을 같게 만들 수 없는 경우, -1을 return 해주세요.
     *
     * 제한사항
     *  -  1 ≤ queue1의 길이 = queue2의 길이 ≤ 300,000
     *  -  1 ≤ queue1의 원소, queue2의 원소 ≤ 109
     *  -  주의: 언어에 따라 합 계산 과정 중 산술 오버플로우 발생 가능성이 있으므로 long type 고려가 필요합니다.
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1() {
            int[] queue1 = {3, 2, 7, 2};
            int[] queue2 = {4, 6, 5, 1};
            int result = 2;

            Assertions.assertEquals(result, solution(queue1, queue2));
        }

        @Test
        public void case2() {
            int[] queue1 = {1, 2, 1, 2};
            int[] queue2 = {1, 10, 1, 2};
            int result = 7;

            Assertions.assertEquals(result, solution(queue1, queue2));
        }

        @Test
        public void case3() {
            int[] queue1 = {1, 1};
            int[] queue2 = {1, 5};
            int result = -1;

            Assertions.assertEquals(result, solution(queue1, queue2));
        }
    }


    /**
     * <pre>
     * 1.
     * totalHalf를 만들 경우의 수를 찾는 과정
     * 한 큐에서 totalHalf가 되면 반대도 totalHalf가 될거라고 가정하는 식으로
     *
     * 2.
     * Queue의 합이랑 totalSum이랑 같을 때까지
     *
     * case1
     * > 3, 2, 7, 2, ||  4, 6, 5, 1
     * > 2, 7, 2, ||  4, 6, 5, 1, 3
     * > 7, 2, ||  4, 6, 5, 1, 3, 2
     * > 2, ||  4, 6, 5, 1, 3, 2, 7
     * > ||  4, 6, 5, 1, 3, 2, 7, 2
     * > 4, ||  6, 5, 1, 3, 2, 7, 2
     * > 4, 6, ||  5, 1, 3, 2, 7, 2
     * > 4, 6, 5, ||  1, 3, 2, 7, 2
     *
     * Q1 / Q2로 두고 Q1에 Q2 peek이 totalSum이랑 같니? -Y-> FIN
     *                                             -N-> Q1 poll
     * -> Q1 empty -> Q2 poll, Q1 push
     *
     * case2
     * > 4, 6, 5, 1, || 3, 2, 7, 2
     * > 6, 5, 1, || 3, 2, 7, 2, 4
     * > 6, 5, 1, 3, || 2, 7, 2, 4
     * </pre>
     */


    public int solution(int[] queue1, int[] queue2){
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        int q1Total = 0;
        int q2Total = 0;
        int total = 0;
        for(int q : queue1) {
            q1.add(q);
            total += q;
            q1Total += q;
        }
        for(int q : queue2) {
            q2.add(q);
            total += q;
            q2Total += q;
        }

        if( total % 2 != 0 ) return -1;

        long target = total / 2;
        while ( true ) {
            if( answer > (queue1.length + queue2.length) * 2 ) return -1;

            if( q1Total == target ) break;
            else if (q1Total > target) {
                q1Total -= q1.peek();
                q2.add(q1.poll());
            }
            else {
                q1Total -= q2.peek();
                q1.add(q1.poll());
            }

            answer++;
        }

        return answer;
    }

   class TwoPointerFail {
        public int solution(int[] queue1, int[] queue2) {
            int answer = 0;

            List<Integer> queue = new ArrayList<>();
            int totalSum = 0;
            for (int i = 0; i < queue1.length; i++) {queue.add(queue1[i]); totalSum+= queue1[i];}
            for (int i = 0; i < queue2.length; i++) {queue.add(queue2[i]); totalSum+= queue2[i];}
            int totalHalf = totalSum / 2;

            int start = 0;
            int end = 0;
            int sum = queue.get(0);

            /**
             * goal  15
             *  4, 6, 5, 1, 3, 2, 7, 2
             *  ↑  ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2   -> capture
             *  ↑     ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *     ↑  ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *     ↑     ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2  -> capture
             *     ↑        ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *        ↑     ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *        ↑        ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *        ↑           ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *        ↑              ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *           ↑           ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *           ↑           ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *              ↑        ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *                 ↑     ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *                    ↑  ↑
             *
             *  4, 6, 5, 1, 3, 2, 7, 2
             *                       ↑
             *  FIN
             */

            /**
             * 15
             *
             * 3, 2, 7, 2, 4, 6, 5, 1
             * ↑
             *
             * 3, 2, 7, 2, 4, 6, 5, 1
             * ↑  ↑
             *
             * 3, 2, 7, 2, 4, 6, 5, 1
             * ↑     ↑
             *
             * 3, 2, 7, 2, 4, 6, 5, 1
             * ↑        ↑
             *
             * 3, 2, 7, 2, 4, 6, 5, 1
             * ↑           ↑
             *
             * 3, 2, 7, 2, 4, 6, 5, 1
             * ↑
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             * 3, 2, 7, 2, 4, 6, 5, 1
             */

            /**
             * 20
             * 1, 2, 1, 2, 1, 10, 1, 2
             *
             * // 호나형으로 생각해서 한 바퀴 도는게 애매하다.
             */

            // totalHalf == sum -> Capture && start + 1

            while( true ) {
                if (queue.size() - 1 == end && end == start) break;
                else {
                    if( sum >= totalHalf ) {
                        if( sum == totalHalf) answer += 1;
                        start += 1;
                        sum -= queue.get(start);
                        System.out.println(sum);
                    }
                    else if( queue.size() - 1 == end ) {
                        start += 1;
                        sum -= queue.get(start);
                    }
                    else  if( end < queue.size() - 1){
                        end += 1;
                        sum += queue.get(end);
                    }
                }
            }

            System.out.println(answer);
            if( answer == 0) return  -1;
            else return answer;
        }
    }
    class Greedy {
        public int solution(int[] queue1, int[] queue2) {
            int queue1Total = 0;
            int queue2Total = 0;

            Queue<Integer> queueA = new LinkedList<>();
            Queue<Integer> queueB = new LinkedList<>();
            for( int element : queue1 ) {
                queue1Total += element;
                queueA.add(element);
            }
            for( int element : queue2 ) {
                queue2Total += element;
                queueB.add(element);
            }

            int count = 0;
            for( int i = 0; i <= 4 * queue1.length ; i ++ ) {
                if( queue1Total == queue2Total ) return count;
                else if( queue1Total > queue2Total ) {
                    int poll = queueA.poll();
                    queue1Total -= poll;
                    queue2Total += poll;
                    queueB.add(poll);
                    count += 1;
                }
                else {
                    int poll = queueB.poll();
                    queue1Total += poll;
                    queue2Total -= poll;
                    queueA.add(poll);
                    count += 1;
                }
            }

            return -1;
        }
    }

    class Timeout {

        public int simplify(int[] queue1, int[] queue2) {
            int answer = Integer.MAX_VALUE;
            List<Integer> queue = new ArrayList<>();
            int totalSum = 0;
            for (int i = 0; i < queue1.length; i++) {queue.add(queue1[i]); totalSum+= queue1[i];}
            for (int i = 0; i < queue2.length; i++) {queue.add(queue2[i]); totalSum+= queue2[i];}
            int totalHalf = totalSum / 2;

            for (int searchCount = queue.size() - 1; searchCount >= 0; searchCount--) {
                for(int i = 0; i < queue.size(); i++) {
                    int number = 0;
                    for(int j = 0; j < searchCount; j++) {
                        int index = (i + j) % queue.size();
                        number += queue.get(index);
                    }


                    if( number == totalHalf ) {
                        answer = Math.min(i + Math.abs((queue.size() / 2) - (i+searchCount)), answer);
                    }
                }
            }

            if( answer == Integer.MAX_VALUE) return  -1;
            else return answer;
        }

        public int solution(int[] queue1, int[] queue2) {
            int answer = Integer.MAX_VALUE;

            int totalSum = 0;
            Queue<Integer> queued1 = new LinkedList<>();
            Queue<Integer> queued2 = new LinkedList<>();
            List<Integer>  global  = new ArrayList<>();
            for (int i = 0; i < queue1.length; i++) {
                totalSum += queue1[i];
                totalSum += queue2[i];

                queued1.add(queue1[i]);
                queued2.add(queue2[i]);
            }
            int totalHalf = totalSum / 2;

            global.addAll(queued1);
            global.addAll(queued2);


            for(int i = global.size() - 1; i > 0; i--) {
                answer = Math.min(answer, search(global, i, totalHalf));
            }

            if( answer == Integer.MAX_VALUE) return  -1;
            else return answer;
        }


        private int search(List<Integer> global, int searchCount, int goal) {
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < global.size(); i++) {
            int number = 0;

            for(int j = 0; j < searchCount; j++) {
                int index = (i + j) % global.size();
                number += global.get(index);
            }


            if( number == goal ) {
                answer = Math.min(i + Math.abs((global.size() / 2) - (i+searchCount)), answer);
            }
        }

        return answer;
    }
    }

//    private int check( int totalHalf, int answer, List<Integer> total, List<Integer> listUp) {
//
//        System.out.println(answer);
//        if(totalHalf == answer) return 1;
//        else if( totalHalf < answer) return 0;
//        int count = 0;
//
//        for(int i = 0; i < total.size(); i++) {
//            boolean[] check = new boolean[total.size()];
//            listUp.add(total.get(i));
//            check[i] = true;
//            count += check( totalHalf, answer + total.get(i), total, listUp);
//            check[i] = false;
//            listUp.remove(total.get(i));
//        }
//
//        return count;
//    }
}
