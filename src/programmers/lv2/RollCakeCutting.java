package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


//retry
public class RollCakeCutting {
    //https://school.programmers.co.kr/learn/courses/30/lessons/132265
    /**
     * <pre>
     *     철수는 롤케이크를 두 조각으로 잘라서 동생과 한 조각씩 나눠 먹으려고 합니다.
     *     이 롤케이크에는 여러가지 토핑들이 일렬로 올려져 있습니다.
     *     철수와 동생은 롤케이크를 공평하게 나눠먹으려 하는데,
     *     그들은 롤케이크의 크기보다 롤케이크 위에 올려진 토핑들의 종류에 더 관심이 많습니다.
     *     그래서 잘린 조각들의 크기와 올려진 토핑의 개수에 상관없이
     *     각 조각에 동일한 가짓수의 토핑이 올라가면
     *     공평하게 롤케이크가 나누어진 것으로 생각합니다.
     *
     *
     *     예를 들어,
     *     롤케이크에 4가지 종류의 토핑이 올려져 있다고 합시다.
     *     토핑들을 1, 2, 3, 4와 같이 번호로 표시했을 때,
     *     케이크 위에 토핑들이 [1, 2, 1, 3, 1, 4, 1, 2] 순서로 올려져 있습니다.
     *
     *     만약 세 번째 토핑(1)과 네 번째 토핑(3) 사이를 자르면
     *     롤케이크의 토핑은 [1, 2, 1], [3, 1, 4, 1, 2]로 나뉘게 됩니다.
     *
     *     철수가 [1, 2, 1]이 놓인 조각을,
     *     동생이 [3, 1, 4, 1, 2]가 놓인 조각을 먹게 되면
     *
     *     철수는 두 가지 토핑(1, 2)을 맛볼 수 있지만,
     *     동생은 네 가지 토핑(1, 2, 3, 4)을 맛볼 수 있으므로,
     *     이는 공평하게 나누어진 것이 아닙니다.
     *
     *     만약 롤케이크의 네 번째 토핑(3)과 다섯 번째 토핑(1)
     *     사이를 자르면 [1, 2, 1, 3], [1, 4, 1, 2]로 나뉘게 됩니다.
     *     이 경우 철수는 세 가지 토핑(1, 2, 3)을,
     *     동생도 세 가지 토핑(1, 2, 4)을 맛볼 수 있으므로,
     *     이는 공평하게 나누어진 것입니다.
     *     공평하게 롤케이크를 자르는 방법은 여러가지 일 수 있습니다.
     *
     *     위의 롤케이크를 [1, 2, 1, 3, 1],
     *     [4, 1, 2]으로 잘라도 공평하게 나뉩니다.
     *     어떤 경우에는 롤케이크를 공평하게 나누지 못할 수도 있습니다.
     *
     *     롤케이크에 올려진 토핑들의 번호를 저장한 정수 배열 topping이 매개변수로 주어질 때,
     *     롤케이크를 공평하게 자르는 방법의 수를 return 하도록 solution 함수를 완성해주세요.
     *
     *  제한사항
     * -  1 ≤ topping의 길이 ≤ 1,000,000
     * -  1 ≤ topping의 원소 ≤ 10,000
     *
     * </pre>
     */
    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int[] topping = new int[]{1, 2, 1, 3, 1, 4, 1, 2};
            int result = 2;

            Assertions.assertEquals(result, solution(topping));
        }

        @Test
        public void case2 () {
            int[] topping = new int[]{1, 2, 3, 1, 4};
            int result = 0;

            Assertions.assertEquals(result, solution(topping));
        }

        @Test
        public void case3 () {
            int[] topping = new int[]{1, 2, 3, 4};
            int result = 1;

            Assertions.assertEquals(result, solution(topping));
        }

        @Test
        public void case4 () {
            int[] topping = new int[]{2, 1,  1, 1, 3};
            int result = 2;

            Assertions.assertEquals(result, solution(topping));
        }
    }

    /**
     * 완전 탐색하면 타임 아웃 예정?
     * 병합 정렬같이 잘라서 해결할 수 있을까?
     *
     * 음 원소 개수 세고,
     * 짝수 개수 일 때,  그 앞뒤 인덱스로 잘라서 비교하면?
     * 이걸 따로 어디에 넣어 체크하고 반환하면?
     */


    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> preSet = new HashMap<>();
        Set<Integer> left = new HashSet<>();
        for(int element : topping) {
            preSet.computeIfPresent(element, (key, value) -> value + 1);
            preSet.putIfAbsent(element, 1);
        }


        for(int element : topping) {
            left.add(element);

            if( preSet.get(element) <= 1 ) preSet.remove(element);
            else  preSet.computeIfPresent(element, (k, v) -> v - 1);


            if(left.size() == preSet.keySet().size()) answer += 1;

        }

        return answer;
    }



    class Success {
        public int solution(int[] topping) {
            Map<Integer, Integer> leftSide = Arrays.stream(topping)
                    .boxed()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            key -> 1,
                            (keyV1, keyV2) -> keyV1 + keyV2
                    ));

            Map<Integer, Integer> rightSide = new HashMap<>();

            int count = 0;

            for( int i = 0; i < topping.length; i ++ ) {
                int number = topping[i];
                int countNumber = leftSide.getOrDefault(number, 0);

                if( countNumber - 1 > 0) leftSide.put(number, leftSide.get(number) - 1);
                else leftSide.remove(number);

                rightSide.putIfAbsent(number, 0);
                rightSide.computeIfPresent(number, (k, v) -> v + 1);

                if(
                        leftSide.keySet().size() ==
                                rightSide.keySet().size()
                ) {
                    count += 1;
                }
            }


            return count;
        }
    }

    class SuccessSolv {
        public int solution(int[] topping) {
            Map<Integer, Integer> leftSide = new HashMap<>();
            Map<Integer, Integer> rightSide = Arrays.stream(topping).boxed()
                    .collect(Collectors.toMap(Function.identity(), integer -> 1, (integer, integer2) -> integer + integer2));

            int count = 0;

            for( int i = 0; i < topping.length; i ++ ) {
                Integer number = topping[i];

                leftSide.putIfAbsent(number, 1);
                leftSide.computeIfPresent(number, (k, v) -> leftSide.get(k) + 1);

                Integer right = rightSide.get(number);
                if(right <= 1) rightSide.remove(number);
                else rightSide.put(number, right - 1);
                if( leftSide.keySet().size() == rightSide.keySet().size()) count ++ ;
            }

            return count;
        }
        class Success {
            public int solution(int[] topping) {
                int answer = 0;
                Set<Integer> set = new HashSet<>();
                int[] partA = new int[topping.length];
                int[] partB = new int[topping.length];

                for ( int i = 0; i < topping.length; i ++ ) {
                    set.add(topping[i]);
                    partA[i] = set.size();
                }
                set.clear();
                for ( int i = topping.length - 1; i >= 0 ; i -- ) {
                    set.add(topping[i]);
                    partB[i] = set.size();
                }

                for ( int i = 0; i < partA.length - 1; i ++ ) {
                    if( partA[i] == partB[i + 1] ) answer += 1;
                }


                return answer;
            }
        }


        public int solutionErrorAndTimeout(int[] topping){
            int answer = 0;
            Map<Integer, Long> map = Arrays.stream(topping).boxed()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            int avg = (int) Math.ceil(topping.length/ map.size() * 1.0);
            Set<Integer> keySets = map.keySet().stream()
                    .filter(k -> map.get(k) <= avg)
                    .sorted((o1, o2) -> map.get(o1).intValue() - map.get(o2).intValue())
                    .collect(Collectors.toSet());

            for ( int i = 1; i < topping.length;  i ++ ) {
                if( !keySets.contains(topping[i]) ) continue;
                else {

                    System.out.println(i+"----------");
                    if( check (topping, i) ) answer += 1;
                    System.out.println((i + 1)+"--------------");
                    if( check (topping, i + 1))  answer += 1;
                }
            }


            return answer;
        }
        private boolean check (int[] topping,  int idx) {
            if( idx >= topping.length) return false;

            int[] partA = Arrays.copyOfRange(topping, 0, idx);
            int[] partB = Arrays.copyOfRange(topping, idx , topping.length);
            long countA = count(partA);
            long countB = count(partB);

            print(partA);
            print(partB);
            if( countA == countB) return true;
            else return false;
        }


        public int solutionTimout(int[] topping) {

            int answer = 0;
            for ( int i  = 1; i < topping.length - 1; i ++ ) {
                int[] partA = Arrays.copyOfRange(topping, 0, i);
                int[] partB = Arrays.copyOfRange(topping, i , topping.length);

                long countA = count(partA);
                long countB = count(partB);

//            print(partA);
//            print(partB);
//            System.out.println(countA);
//            System.out.println(countB);
//            System.out.println("--------");
                if( countA == countB) answer += 1;


            }
            return answer;
        }
        private long count ( int [] topping ) {
            return Arrays.stream(topping).boxed().distinct().count();
        }
        private void print( int [] topping ) {
            for( int top : topping) System.out.print(top +", ");
            System.out.println();
        }
    }

}
