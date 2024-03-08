package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ContinuousSequential {
    //https://school.programmers.co.kr/learn/courses/30/lessons/131701
    /**
     * <pre>
     *     철호는 수열을 가지고 놀기 좋아합니다.
     *     어느 날 철호는 어떤 자연수로 이루어진 원형 수열의 연속하는 부분 수열의 합으로 만들 수 있는 수가
     *     모두 몇 가지인지 알아보고 싶어졌습니다.
     *     원형 수열이란 일반적인 수열에서 처음과 끝이 연결된 형태의 수열을 말합니다.
     *
     *     예를 들어 수열 [7, 9, 1, 1, 4] 로 원형 수열을 만들면 다음과 같습니다.
     *     원형 수열은 처음과 끝이 연결되어 끊기는 부분이 없기 때문에
     *     연속하는 부분 수열도 일반적인 수열보다 많아집니다.
     *     원형 수열의 모든 원소 elements가 순서대로 주어질 때,
     *     원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 개수를 return 하도록
     *     solution 함수를 완성해주세요.
     *
     *     제한사항
     *      -  3 ≤ elements의 길이 ≤ 1,000
     *      -  1 ≤ elements의 원소 ≤ 1,000
     * </pre>
     */
    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] elements = new int[]{ 7, 9, 1, 1, 4 };
            int result = 18;

            Assertions.assertEquals(result, solution(elements));
        }
    }

    public int solution ( int [] elements ) {
        Set<Integer> sumCase = new HashSet<>();
        int length = elements.length;





        System.out.println(sumCase);

        return 0;
    }

    public int solutionFailure ( int [] elements ) {
        for ( int i = 1; i <= elements.length; i ++ ) {
            System.out.println("COMBINE");
            System.out.println(
                    combine(Arrays.stream(elements).boxed().collect(Collectors.toList()), i)
                    .stream().map(elem -> Arrays.stream(elem.split("")).map(Integer::parseInt).reduce((p,n) -> p + n).get())
                    .collect(Collectors.toSet())
            );

            System.out.println("PERMUTATION");
            System.out.println(
                    permutations(Arrays.stream(elements)
                            .boxed().collect(Collectors.toList()), i)
                            .stream().map(elem -> elem.stream().reduce((p, n) -> p + n).get())
                            .collect(Collectors.toSet())
            );

            System.out.println("\n\n");
        }


        return 0;
    }

    List<String> combine( List<Integer> list, int count ) {
        List<String> result = new ArrayList<>();
        if( count == 1 ) return list.stream().map(String::valueOf).collect(Collectors.toList());
        for (AtomicInteger i = new AtomicInteger(0); i.get() < list.size(); i.getAndIncrement() ) {
            List<String> combinations = combine(
                    new ArrayList<>(list.subList(i.get() + 1, list.size())),
                    count - 1
            ).stream()
                    .map(v -> list.get(i.get())+v)
                    .collect(Collectors.toList());

            result.addAll(combinations);
        }

        return result;
    }
    List<List<Integer>> permutations( List<Integer> list, int count ) {
        List<List<Integer>> result = new ArrayList<>();
        if( count == 1 ) {
            return  list.stream()
                    .map(elem -> new ArrayList<>(List.of(elem)))
                    .collect(Collectors.toList());
        }

        for(AtomicInteger i = new AtomicInteger(0); i.get() < list.size(); i.getAndIncrement()) {
            List<Integer> subList = new ArrayList<>();
            subList.addAll(list.subList(0, i.get()));
            subList.addAll(list.subList(i.get() + 1, list.size()));
            List<List<Integer>> permutations = permutations(subList, count - 1);

            permutations.stream().forEach(l -> {
                l.add(list.get(i.get()));
                result.add(l);
            });
        }

        return result;
    }

}

