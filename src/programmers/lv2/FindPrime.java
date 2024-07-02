package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FindPrime {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42839

    /**
     * <pre>
     *     한자리 숫자가 적힌 종이 조각이 흩어져있습니다.
     *     흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
     *
     *      각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때,
     *      종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     *  - numbers는 길이 1 이상 7 이하인 문자열입니다.
     *  - numbers는 0~9까지 숫자만으로 이루어져 있습니다.
     *  - "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1() {
            String numbers = "17";
            int result = 3;
            Assertions.assertEquals(result, solution(numbers));
        }

        @Test
        public void case2() {
            String numbers = "011";
            int result = 2;
            Assertions.assertEquals(result, solution(numbers));
        }

        @Test
        public void case3() {
            String numbers = "232";
            int result = 4;
            Assertions.assertEquals(result, solution(numbers));
        }

//        @Test
//        public void numberFormatException() {
//            String numbers = "0001";
//            // 0
//            // 1
//            // 10
//            // 11
//            // 101
//            // 110
//            Set<Long> permutations = permutation(numbers, "", 0, new boolean[ numbers.length() ]);
//            Set<Long> numberSet = permutations;
//            System.out.println(numberSet);
//        }
    }

    public int solution(String numbers) {
        Set<String> numberSet = new HashSet<>();
        String[] split = numbers.split("");

        for( int i = 1; i <= numbers.length(); i ++ ) {
            for( int j = 0; j < numbers.length(); j ++ ) {
                StringBuilder set = new StringBuilder();
                for( int l = j; l < j + i; l ++ ) {
                    set.append(split[l % numbers.length()]);
                }


                System.out.println(set.toString());
                numberSet.add(set.toString());
            }
        }


        System.out.println(numberSet);
        return 0;
    }
    class Success {
        public int solution(String numbers) {
            int answer = 0;
            Set<Long> numberSet = this.permutation(numbers, "", 0, new boolean[numbers.length()])
                    .stream()
                    .collect(Collectors.toSet());

            for (Long isPrime : numberSet) {
                if (eratosthenes(isPrime)) answer += 1;
            }
            return answer;
        }


        private Set<Long> permutation(String numbers, String prev, int depth, boolean[] visit) {


            Set<Long> result = new HashSet<>();
            if (numbers.length() < depth) return result;

            String[] arr = numbers.split("");
            for (int i = 0; i < arr.length; i++) {
                if (visit[i]) continue;

                visit[i] = true;
                result.add(Long.parseLong(prev + arr[i]));
                result.addAll(permutation(numbers, prev + arr[i], depth + 1, visit));
                visit[i] = false;
            }

            return result;
        }

        private boolean eratosthenes(long number) {
            if (number < 2) return Boolean.FALSE;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) return Boolean.FALSE;
            }

            return Boolean.TRUE;
        }
    }
}
