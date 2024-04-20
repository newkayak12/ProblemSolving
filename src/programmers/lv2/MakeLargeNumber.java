package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MakeLargeNumber {
    //https://school.programmers.co.kr/learn/courses/30/lessons/42883

    /**
     * <pre>
     * 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
     * 예를 들어, 숫자 1924에서 수 두 개를 제거하면
     * [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다.
     * 이 중 가장 큰 숫자는 94 입니다.
     *
     * 문자열 형식으로 숫자 number와 제거할 수의
     * 개수 k가 solution 함수의 매개변수로 주어집니다.
     * number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중
     * 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
     *
     *  제한 조건
     *  - number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
     *  - k는 1 이상 number의 자릿수 미만인 자연수입니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1() {
            String number = "1924";
            int k = 2;
            String result = "94";
            /**
             * 1924 -> 2개를 지운다.
             * 1회차-----------
             * 가장 큰 숫자를 찾는다. 9
             *      9 전의 숫자만큼 지운다. 924
             *      remove Count를 지운 만큼 줄인다. 1
             *      9 이후의 숫자를 넘긴다.
             *      9를 저장한다.
             *
             * save [9] next 24
             * 2회차-----------
             * 가장 큰 숫자를 찾는다. 4
             *
             */

            Assertions.assertEquals(result, solution(number, k));
        }

        @Test
        public void case2() {
            String number = "1231234";
            int k = 3;
            String result = "3234";

            Assertions.assertEquals(result, solution(number, k));
        }

        @Test
        public void case3() {
            String number = "4177252841";
            int k = 4;
            String result = "775841";

            Assertions.assertEquals(result, solution(number, k));
        }

        @Test
        public void test() {
            int findIdx = findIdx("31234", 2, 1);
            System.out.println(findIdx);
        }
    }


    //Greedy
    public String solution(String number, int k) {
        /**
         * 1924 -> 2개를 지운다.
         * 1회차-----------
         * 가장 큰 숫자를 찾는다. 9  -> 지울 카운트 보다 남은 요소가 적으면 조정
         *      9 전의 숫자만큼 지운다. 924
         *      remove Count를 지운 만큼 줄인다. 1
         *      9 이후의 숫자를 넘긴다.
         *      9를 저장한다.
         *
         * save [9] next 24
         * 2회차-----------
         * 가장 큰 숫자를 찾는다. 4
         *
         */
        return this.search(number, k, 0, number.length() - k);
    }

    private String search(String number, int removeCount, int start, int targetSize) {
        // 큰 수 찾음
        // 버릴 수 보다 큰 수 이후 수가 적으면
        // 큰 수 인덱스 이전에서 큰 수 다음으로 큰 수를 찾음

        if (number.length() <= targetSize) return number;
        else {

            int find = findIdx(number, removeCount, start);
            String nextNumber = number.substring(find + 1); //다음 탐색
            int nextRemoveCount = removeCount - find; //다음 removeCount
            int passCount = start + 1;


            return this.search(nextNumber, nextRemoveCount, passCount, targetSize);
        }
    }

    private int findIdx(String number, int deleteCount, int passCount) {
        int maxNumber = Integer.parseInt(number.split("")[passCount]);
        int maxIdx = passCount;

        for (int i = maxIdx; i <= number.length() - deleteCount; i++) {
            int now = Integer.parseInt(number.split("")[i]);
            if (maxNumber < now) {
                maxIdx = i;
                maxNumber = now;
            }
        }

        return maxIdx;
    }

    private List<String> search2(String number, int removeCount, List<String> store) {
        System.out.println(store + "/" + removeCount);
        if (removeCount <= 0) return store;
        int findIdx = this.findMaxIdx(number, removeCount);
        System.out.println(findIdx);
        store.add(number.charAt(findIdx) + "");
        return search2(number.substring(findIdx + 1), removeCount - findIdx, store);
    }

    private int findMaxIdx(String number, int removeCount) {
        System.out.println(number + "//" + removeCount);
        int resultIdx = 0;
        int first = Integer.parseInt(number.charAt(0) + "");
        for (int i = 0; i <= number.length() - removeCount; i++) {
            if (first < Integer.parseInt(number.charAt(i) + "")) {
                resultIdx = i;
            }
        }

        //[0,1,2,3,4,5]
        // resultIdx => 3
        // removeCount => 4

//        0 ~ 3
        if (number.length() - (resultIdx + 1) > 0) return resultIdx;
        else return this.findMaxIdx(number.substring(0, resultIdx), removeCount - resultIdx);
    }

    private List<String> search3(String[] numbers, int k, int start, int end, List<String> result) {
        System.out.println(result);
        System.out.println(start + "-" + end);
        if (result.size() >= k || end < result.size()) return result;

        int lastIdx = start;
        int picked = Integer.parseInt(numbers[start]);
        System.out.println(picked);
        for (; start <= end; start++) {
            int now = Integer.parseInt(numbers[start]);
            if (picked <= now) {
                lastIdx = start;
                picked = now;

            }
        }
        result.add(picked + "");

        return search3(numbers, k, lastIdx + 1, end + 1, result);
    }

    //OOM, TIMEOUT
    public String solutionOomTm(String number, int k) {
        List<String> result = new ArrayList<>(this.eject(number.split(""), new boolean[number.length()], k, 0, "", new HashSet<>()));

        Collections.sort(result);
        return result.get(result.size() - 1);
    }

    private Set<String> eject(String[] numbers, boolean[] check, int k, int start, String now, Set<String> result) {

        if (now.length() >= numbers.length - k) {
            result.add(now);
            return result;
        }
        ;

        for (int i = start; i < numbers.length; i++) {
            if (!check[i]) {
                check[i] = true;
                String pick = numbers[i];
                eject(numbers, check, k, i + 1, now + pick, result);
                check[i] = false;
            }
        }

        return result;
    }

}
