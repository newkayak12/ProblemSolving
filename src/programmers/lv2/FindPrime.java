package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import programmers.example.Permutation;

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

        @Test
        public void case4() {
            String numbers = "1234";
            int result = 14;
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
        int answer = 0;
//        Set<Long> numberSet = this.permutation(numbers, "", 0, new boolean[numbers.length()]).stream().collect(Collectors.toSet());
        List<Long> numberSet = this.permutation(numbers);

        for (Long isPrime : numberSet) {  if (eratosthenes(isPrime)) answer += 1; }

        return answer;
    }



    private boolean eratosthenes(long number) {
        if (number < 2) return Boolean.FALSE;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public static class Tuple <T1, T2>{
        public T1 t1;
        public T2 t2;

        public Tuple(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public static  <T1, T2> Tuple of(T1 t1, T2 t2) {
            return new Tuple<>(t1, t2);
        }
    }

    private List<Long> permutation( String n ) {
        String[] strings = n.split("");
        Set<String> set = new HashSet<>();

        for( int i = 0; i < strings.length; i ++ ) {
            Stack<Tuple<String, boolean[]>> charSet = new Stack<>();
            boolean[] visit = new boolean[strings.length];
            String charac = strings[i];
            set.add(charac);
            visit[i] = true;

            charSet.add(Tuple.of(charac, Arrays.copyOf(visit, visit.length)));

            while( !charSet.isEmpty() ){
                Tuple<String, boolean[]> pop = charSet.pop();



                for(int j = 0; j < strings.length; j ++ ) {
                    if(!pop.t2[j]) {

                        boolean[] cloneMap = Arrays.copyOf(pop.t2, pop.t2.length);
                        cloneMap[j] = true;
                        charSet.add(Tuple.of(pop.t1+strings[j], cloneMap));
                        set.add(pop.t1+strings[j]);
                    }
                }
            }
        }

        List<Long> result = set.stream().map(Long::parseLong).distinct().collect(Collectors.toList());
        Collections.sort(result);
        return result;
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
