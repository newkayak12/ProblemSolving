package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SumOfContinuousNumbers {
    //https://school.programmers.co.kr/learn/courses/30/lessons/178870
    /**
     * <pre>
     * 비내림차순으로 정렬된 수열이 주어질 때, 다음 조건을 만족하는 부분 수열을 찾으려고 합니다.
     *
     * 기존 수열에서 임의의 두 인덱스의 원소와
     * 그 사이의 원소를 모두 포함하는 부분 수열이어야 합니다.
     *
     * 부분 수열의 합은 k입니다.
     *
     * 합이 k인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾습니다.
     * 길이가 짧은 수열이 여러 개인 경우 앞쪽(시작 인덱스가 작은)에 나오는 수열을 찾습니다.
     *
     * 수열을 나타내는 정수 배열 sequence와
     * 부분 수열의 합을 나타내는 정수 k가 매개변수로 주어질 때,
     * 위 조건을 만족하는 부분 수열의 시작 인덱스와 마지막 인덱스를 배열에 담아
     * return 하는 solution 함수를 완성해주세요.
     *
     * 이때 수열의 인덱스는 0부터 시작합니다.
     *
     * 제한사항
     *  - 5 ≤ sequence의 길이 ≤ 1,000,000
     *      - 1 ≤ sequence의 원소 ≤ 1,000
     *      - sequence는 비내림차순으로 정렬되어 있습니다.
     *  - 5 ≤ k ≤ 1,000,000,000
     *      - k는 항상 sequence의 부분 수열로 만들 수 있는 값입니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int[] sequence = {1,2,3,4,5};
            int k = 7;
            int[] result = {2, 3};

            Assertions.assertArrayEquals(result, solution(sequence, k));
        }

        @Test
        public void case2 () {
            int[] sequence = {1, 1, 1, 2, 3, 4, 5};
            int k = 5;
            int[] result = {6, 6};

           Assertions.assertArrayEquals(result, solution(sequence, k));
        }

        @Test
        public void case3 () {
            int[] sequence = {2, 2, 2, 2, 2};
            int k = 6;
            int[] result = {0, 2};

           Assertions.assertArrayEquals(result, solution(sequence, k));
        }
    }

    public int[] solution(int[] sequence, int k) {
//{1,2,3,4,5};
        int start = 0;
        int end = 0;
        int number = sequence[start];
        List<Map<String, Integer>> maps = new ArrayList<>();

        while ( true ){

            if( number == k ) {
                maps.add(Map.of(
                        "length", end - start,
                        "start", start,
                        "end", end
                ));
            }

            if( start == sequence.length && end == sequence.length ) break;
            if( number <= k && end < sequence.length) {
                if( ++end < sequence.length ) number += sequence[end];
            } else {
                if( start < sequence.length ) number -= sequence[start];
                start ++;
            }
        }

        Collections.sort(maps, (o1, o2) -> o1.get("length") - o2.get("length"));
        Map<String , Integer> found = maps.stream().findFirst().get();
        int startPoint = found.get("start");
        int endPoint = found.get("end");

        return new int[] { startPoint, endPoint };
    }

    class Timeout {
        public int[] solution(int[] sequence, int k) {
            int[] answer = {0, sequence.length - 1};

            for( int i = 0; i < sequence.length; i ++ ) {
                int number = 0;
                int start = i;
                int end = i;



                for( int j = i; j < sequence.length; j++) {
                    number += sequence[j];


                    if( number == k ) {
                        end = j;
                        break;
                    }

                }

                if( number == k ) {
                    int gap = answer[1] - answer[0] + 1;
                    int newGap = end - start + 1;

                    if( newGap < gap) {
                        answer[0] = start;
                        answer[1] = end;
                    }
                }
            }
            return answer;
        }
    }
}
