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
            String numbers = "0123456789";
            int result = 2;
            Assertions.assertEquals(result, solution(numbers));
        }

        @Test
        public void numberFormatException() {
            String numbers = "0123456789";
            for (int i = 0; i < numbers.length(); i++) {
                String pick = String.valueOf(numbers.charAt(0));
                numbers = numbers.substring(1) + pick;
                System.out.println(Long.parseLong(numbers));
            }
        }
    }

    public int solution(String numbers) {
        int answer = 0;
        List<Long> primes = new ArrayList<>();

        for (String isPrime : numbers.split("")) {
            Long prime = Long.parseLong(isPrime);
            if (eratosthenes(prime)) primes.add(prime);
        }
        return answer;
    }


    private boolean eratosthenes(long number) {
        if (number == 0 || number == 1) return Boolean.FALSE;
        for (int i = 2; i * i < number; i++) {
            if (number % i == 0) return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
