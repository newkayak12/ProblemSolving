package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Tangerine {
    //https://school.programmers.co.kr/learn/courses/30/lessons/138476
    /**
     * <pre>
     * 경화는 과수원에서 귤을 수확했습니다.
     * 경화는 수확한 귤 중 'k'개를 골라 상자 하나에 담아 판매하려고 합니다.
     * 그런데 수확한 귤의 크기가 일정하지 않아 보기에 좋지 않다고 생각한
     * 경화는 귤을 크기별로 분류했을 때 서로 다른 종류의 수를 최소화하고 싶습니다.
     *
     * 예를 들어, 경화가 수확한 귤 8개의 크기가 [1, 3, 2, 5, 4, 5, 2, 3] 이라고 합시다.
     * 경화가 귤 6개를 판매하고 싶다면,
     * 크기가 1, 4인 귤을 제외한 여섯 개의 귤을 상자에 담으면,
     * 귤의 크기의 종류가 2, 3, 5로 총 3가지가 되며 이때가 서로 다른 종류가 최소일 때입니다.
     *
     * 경화가 한 상자에 담으려는 귤의 개수 k와
     * 귤의 크기를 담은 배열 tangerine이 매개변수로 주어집니다.
     * 경화가 귤 k개를 고를 때 크기가
     * 서로 다른 종류의 수의 최솟값을 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     *   - 1 ≤ k ≤ tangerine의 길이 ≤ 100,000
     *   - 1 ≤ tangerine의 원소 ≤ 10,000,000
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int k = 6;
            int [] tangerine = new int[] {1, 3, 2, 5, 4, 5, 2, 3};
            int result = 3;

            Assertions.assertEquals(result, solution(k, tangerine));
        }

        @Test
        public void case2 () {
            int k = 4;
            int [] tangerine = new int[] {1, 3, 2, 5, 4, 5, 2, 3};
            int result = 2;

            Assertions.assertEquals(result, solution(k, tangerine));
        }

        @Test
        public void case3 () {
            int k = 2;
            int [] tangerine = new int[] {1, 1, 1, 1, 2, 2, 2, 3};
            int result = 1;

            Assertions.assertEquals(result, solution(k, tangerine));
        }
    }


    public int solution( int k, int[] tangerine ) {
        Map<Integer, Integer> tangerineMap = new HashMap<>();
        List<Integer> index = new LinkedList<>();

        for( int tan : tangerine ) {
            tangerineMap.computeIfPresent(tan, (key, value) ->  value + 1);
            tangerineMap.putIfAbsent(tan, 1);
        }

        tangerineMap.keySet()
                .stream()
                .sorted((o1, o2) -> tangerineMap.get(o2) - tangerineMap.get(o1))
                .forEachOrdered(v ->index.add(v));


        int count = 0;


        for (int i = 0; i < index.size(); i ++ ) {
            if( k <= 0 ) break;
            else {
                count += 1;
                k -= tangerineMap.get(index.get(i));
            }
        }

        return count;
    }

    class Success {
        public int solution( int k, int[] tangerine) {
            Map<Integer, Integer> map = new HashMap<>();
            List< String > array = new ArrayList<>();
            int answer = 0;
            int count = k;

            for ( int  tanger : tangerine ) {
                map.computeIfPresent(tanger, (key, value) ->  value + 1);
                map.putIfAbsent(tanger, 1);
            }
            for (Integer integer : map.keySet()) {
                array.add(String.format("%d %d", integer, map.get(integer)));
            }
            Collections.sort(array, (a, b) -> {
                String[] aSplit = a.split(" ");
                String[] bSplit = b.split(" ");
                return Integer.parseInt(bSplit[1]) - Integer.parseInt(aSplit[1]);
            });

            for( String piece : array ) {
                if ( count <= 0 ) break;
                String[] split = piece.split(" ");
                answer += 1;
                count -= Integer.parseInt(split[1]);
            }
            return answer;
        }
    }


}
