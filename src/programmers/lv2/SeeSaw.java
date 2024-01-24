package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SeeSaw {
    //https://school.programmers.co.kr/learn/courses/30/lessons/152996
    /**
     * 어느 공원 놀이터에는 시소가 하나 설치되어 있습니다.
     * 이 시소는 중심으로부터 2(m), 3(m), 4(m) 거리의 지점에 좌석이 하나씩 있습니다.
     *
     * 이 시소를 두 명이 마주 보고 탄다고 할 때,
     * 시소가 평형인 상태에서 각각에 의해 시소에 걸리는
     * 토크의 크기가 서로 상쇄되어 완전한 균형을 이룰 수 있다면
     * 그 두 사람을 시소 짝꿍이라고 합니다.
     *
     * 즉, 탑승한 사람의 무게와 시소 축과 좌석 간의
     * 거리의 곱이 양쪽 다 같다면 시소 짝꿍이라고 할 수 있습니다.
     *
     * 사람들의 몸무게 목록 weights이 주어질 때,
     * 시소 짝꿍이 몇 쌍 존재하는지 구하여 return 하도록 solution 함수를 완성해주세요.
     */

    @Nested
    class TestCase {
        @Test
        public void case1 () {
            int[] weights = {100,180,360,100,270};
            int result = 4;

            Assertions.assertEquals(result, solution(weights));
        }

        @Test
        public void case2 () {
            int[] weights = {100,100,100,100,100};
            int result = 10;

            Assertions.assertEquals(result, solution(weights));
        }
    }

    public long solution(int[] weights) {
        List<Integer> ws = Arrays.stream(weights).boxed().sorted().collect(Collectors.toList());
        long count = 0;

        return count;
    }

}

