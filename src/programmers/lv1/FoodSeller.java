package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodSeller {
//https://school.programmers.co.kr/learn/courses/30/lessons/135808
    /**
     * 과일 장수가 사과 상자를 포장하고 있습니다.
     * 사과는 상태에 따라 1점부터 k점까지의 점수로 분류하며,
     * k점이 최상품의 사과이고 1점이 최하품의 사과입니다.
     *
     * 사과 한 상자의 가격은 다음과 같이 결정됩니다.
     *
     *  - 한 상자에 사과를 m개씩 담아 포장합니다.
     *  - 상자에 담긴 사과 중 가장 낮은 점수가 p (1 ≤ p ≤ k)점인 경우,
     *    사과 한 상자의 가격은 p * m 입니다.
     *
     * 과일 장수가 가능한 많은 사과를 팔았을 때,
     * 얻을 수 있는 최대 이익을 계산하고자 합니다.
     * (사과는 상자 단위로만 판매하며, 남는 사과는 버립니다)
     *
     *
     * 예를 들어, k = 3, m = 4,
     * 사과 7개의 점수가 [1, 2, 3, 1, 2, 3, 1]이라면,
     *
     * 다음과 같이 [2, 3, 2, 3]으로 구성된
     * 사과 상자 1개를 만들어 판매하여 최대 이익을 얻을 수 있습니다.
     *
     * (최저 사과 점수) x (한 상자에 담긴 사과 개수) x (상자의 개수) = 2 x 4 x 1 = 8
     * 사과의 최대 점수 k, 한 상자에 들어가는 사과의 수 m, 사과들의 점수 score가 주어졌을 때, 과일 장수가 얻을 수 있는 최대 이익을 return하는 solution 함수를 완성해주세요.
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int maxScore = 3; //k
            int packPer = 4;  //m
            int[] score = {1, 2, 3, 1, 2, 3, 1}; //score
            int expect = 8; // result

            Assertions.assertEquals(expect, solution(maxScore, packPer, score));
        }

        @Test
        public void case2 () {
            int maxScore = 4; //k
            int packPer = 3;  //m
            int[] score = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}; //score
            int expect = 33; // result

            Assertions.assertEquals(expect, solution(maxScore, packPer, score));
        }
    }

    public static int solution ( int k, int m, int[] score ) {

        int answer = 0;
        List<Integer> list = Arrays.stream(score).boxed().sorted((a, b) -> b - a).toList();

        int min = k;
        for( int i = 0; i < list.size(); i ++ ) {
            min = Math.min(list.get(i), min);

            if( (i + 1) % m == 0) {
                answer += min * m;
                min = k;
            }
        }

        System.out.println(answer);

        return answer;
    }

//    public static int solution ( int k, int m, int[] score ) {
//        int answer = 0;
//
//        List<Integer> filtered =  Arrays.stream(score)
//                                        .boxed()
//                                        .filter(element -> element <= k)
//                                        .collect(Collectors.toList());
//        filtered.sort((a, b) -> b - a);
//
//        for ( int i = 0; i < filtered.size(); i ++ ) {
//            int select = k;
//            select = Math.min(filtered.get(i), select);
//            if( (i + 1) % m == 0) {
//                answer += (select * m);
//                select = k;
//            }
//        }
//
//
//        return answer;
//    }
}
