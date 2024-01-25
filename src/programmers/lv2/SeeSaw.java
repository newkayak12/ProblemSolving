package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SeeSaw {
    //https://school.programmers.co.kr/learn/courses/30/lessons/152996
    /**
     * <pre>
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
     * </pre>
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
        int answer = 0;
        int[][] ratios = {{1,1},{3,2},{4,2},{4,3}};

        Arrays.sort(weights);

            for ( int i = 0; i < weights.length; i ++ ) {
                int x = weights[i];
                System.out.println("\t >> "+x);
                for( int[] ratio : ratios ) {

                    if( x * ratio[0] % ratio[1] != 0 ) continue;
                    int y = ((x * ratio[0]) / ratio[1]);
                    System.out.println(y);
                }
            }


        return answer;
    }

    public long solution2(int[] weights) {
        long count = 0;
        List<Integer> list = List.of(2,3,4);
        List<Integer> other = List.of(1,2);

        for ( int i = 0; i < weights.length - 1; i++ ) {
            inner: for ( int j = i + 1; j < weights.length; j ++ ) {
                System.out.println("\t>> "+weights[i] +":"+ weights[j]);
                if( weights[i] == weights[j]) {
//                    System.out.println(weights[i] +":"+ weights[j] + " <<");
                    count++;
                    continue;
                }

                int lcmResult = lcm(weights[i], weights[j]);
                int a = lcmResult / weights[i];
                int b = lcmResult / weights[j];

                if(
                        (list.contains(a) && list.contains(b))||
                        (other.contains(a) && other.contains(b))
                ) {
//                    System.out.println(weights[i] +":"+ weights[j] + " <<");
                    count++;
                }
            }
        }

        return count;
    }
    private int gcd(int a, int b ) {
        if( a % b == 0) return b;
        else return gcd( b , a % b);
    }
    private int lcm ( int a , int b ) {
        int max = a;
        int min = b;
        if ( max < b ) {
            max = b;
            min = a;
        }
        return a * b / gcd(max, min);
    }
    public long solution3(int[] weights) {
        long count = 0;

        List<Integer> l = List.of(2,3,4);
        List<Integer> w = Arrays.stream(weights).boxed()
                .sorted().collect(Collectors.toList());

        for ( int i = 0; i < w.size() - 1; i++ ) {
            for ( int j = i + 1; j < w.size(); j ++ ) {
                if ( w.get(i) % w.get(j) == 0 ) {
                    System.out.println(w.get(i) +":"+ w.get(j));
                    count++;
                    continue;
                }
                int div = w.get(j) % w.get(i);
                if ( div == 0) {
                    count++;
                    continue;
                }
                if ( w.get(i) % div != 0 ||  w.get(j) % div != 0) continue;

                int a = w.get(i) / div;
                int b = w.get(j) / div;
                if(
                        (a == 1 && b == 2) ||
                                (a == 2 && b == 1) ||
                                (l.contains(a) && l.contains(b))
                ) {
                    System.out.println(w.get(i) +":"+ w.get(j));
                    count++;
                }
            }
        }

        return count;
    }

    public long solution4(int[] weights) {
        long count = 0;
        Arrays.sort(weights);
        Map<Integer, Boolean> d = Map.of(
                1,true,
                2,true,
                3,true,
                4,true
        );


        for ( int i = 0; i < weights.length - 1; i++ ) {
            for (int j = i + 1; j < weights.length; j++) {
                if(weights[j] == weights[i]){
                    count++;
                    continue;
                }
                if (weights[j] / weights[i] > 2) continue;
                int gcdValue = gcd(weights[j], weights[i]);
                int a = weights[j] / gcdValue;
                int b = weights[i] / gcdValue;
                if (d.getOrDefault(a, false) && d.getOrDefault(b, false)) {
                    count++;
                }
            }
        }
        return count;
    }

    public long solution5(int[] weights) {
        int answer = 0;
//        Map<Integer, List<Integer>> map = new HashMap<>();
//        Map<Integer, Integer> count = Arrays.stream(weights)
//                              .boxed()
//                              .collect(Collectors.toMap(Function.identity(), (elem) -> 1, (a,b) -> a+b));

//        for ( int weight: count.keySet() ) {
//            answer += count.get(weight) / 2;
//        }
        List<List<Integer>> arr = new ArrayList<>();


        for ( int weight : weights ) {
//            map.put(weight, List.of(weight * 2, weight * 3, weight * 4));
            arr.add(List.of(weight * 2, weight * 3, weight * 4));
        }
        Map<Integer, Integer> count2  = arr.stream()
                .flatMap(List::stream)
                .collect(Collectors.toMap(Function.identity(), (ele)->1, (a,b) -> a+b));

        System.out.println(count2);
        for ( int weight: count2.keySet() ) {
            answer += count2.get(weight) / 2;
        }
        return answer;
    }

}

