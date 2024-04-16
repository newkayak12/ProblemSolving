package programmers.hyunsoo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Shopping {
    /**
     * <pre>
     * 쇼핑몰에서 고객의 구매 기록을 이용하여 제품을 추천하려 합니다.
     * 당신이 가지고 있는 데이터는 다음과 같습니다.
     *   • 쇼핑몰의 제품 목록
     *    • 쇼핑몰에 상품으로 등록된 모든 제품들의 목록입니다.
     *    • 각 제품의 이름과 특성을 제공합니다.
     *   • 고객이 구매했던 제품 목록
     *    • 고객이 쇼핑몰에서 구매했던 제품들의 목록입니다.
     *    • 각 제품의 이름만을 제공합니다.
     * 다음은 쇼핑몰의 제품 목록과 고객이 구매했던 제품 목록을 나타내는 예시입니다.
     *
     * 이름 | 특성
     * sofa | red long
     * blanket | blue long
     * towel | red
     * mattress |long
     * curtain | blue long cheap
     *
     * 고객이 구매한 목록
     * 이름
     * towel
     * mattress
     * curtain
     *
     *
     * 당신은 고객에게 다음과 같이 제품을 추천하려 합니다.
     *  • 고객이 구매한 제품 목록을 이용해서 제품의 우선순위를 정합니다.
     *      • 제품의 우선순위는 각 제품의 특성을 이용하여 결정합니다.
     *  • 고객이 구매하지 않은 제품 중에서 우선순위가 가장 높은 하나를 선택해서 추천합니다.
     *
     * 특성의 우선순위는 다음과 같은 방법으로 정합니다.
     *  • 고객이 구매했던 제품들의 특성이 각각 몇 번 나타나는지 셉니다.
     *  • 더 많이 나타난 특성의 우선순위가 더 높습니다.
     *      • 나타난 횟수가 같은 경우에는 이름이 사전 순으로 앞서는 특성이 더 높은 우선순위를 갖습니다.
     *
     * 제품의 우선순위는 다음과 같은 방법으로 정합니다.
     *  • 제품들이 가지고 있는 특성 중에서 가장 높은 우선순위의 특성을 비교했을 때, 더 높은 우선순위의 특성을 가 진 제품의 우선순위가 더 높습니다.
     *  • 가장 높은 우선순위의 특성이 같다면, 그다음으로 우선순위가 높은 특성들을 차례대로 비교합니다.
     *      • 더 높은 우선순위의 특성이 먼저 등장한 제품의 우선순위가 상대적으로 높습니다.
     *      • 단, 비교할 특성이 먼저 바닥난 제품의 우선순위가 상대적으로 낮습니다.
     *
     * 쇼핑몰의 제품 목록을 담고 있는 문자열 배열 products 와
     * 고객이 구매했던 제품 목록을 담고 있는 문자열 배열 purchased 가 주어졌을 때,
     * 고객에게 추천할 제품의 이름을 return 하도록 solution 함수를 완성해주세요
     *
     *
     * 제한사항
     * • 2<= products 의 길이 ≤ 100
     *  - products 의 각 원소는 제품 이름과 여러 특성들을 띄어쓰기로 구분한 " [제품 이름] [특성1] [특성2] ..."형태의 문자열입니다.
     *  - products 의 모든 원소의 제품 이름은 서로 다릅니다.
     *  - Products 의 모든 제품은 하나 이상의 특성을 가지고 있습니다.
     *
     * • 1<= purchased 의 길이 products 의 길이
     *  • purchased 의 각 원소는 서로 다른 제품 이름을 가지고 있습니다.
     *  • purchased 에 나오는 제품 이름은 모두 products 에 들어있습니다.
     *
     *  • 1<= 제품 이름의 길이 <= 10
     *  • 1<= 특성의 길이 <=10
     *  • 1 <= 각 제품별 특성의 개수 <= 100
     *  • 1<= 특성의 가짓수 <= 100
     *  • 모든 제품 이름과 특성은 소문자 알파벳으로만 이루어져 있습니다.
     *  • 제품 이름과 특성이 같은 경우는 없습니다.
     *       • 즉, 제품 이름으로 등장하는 문자열이 특성에 해당하는 문자열로 등장하는 경우는 없습니다.
     *  • 제품 하나에 동일한 특성이 여러 번 들어있는 경우는 없습니다.
     *       • 예를 들어,"towel red red" 와 같은 경우는 없습니다.
     *  • 서로 다른 두 제품의 특성 수와 특성 종류가 완전히 같은 경우는 없습니다.
     *  • 고객에게 추천해 줄 수 있는 물품의 모든 특성은 고객이 구매한 제품들의 특성에서 적어도 한 번 이상 나타 납니다.
     *       • 즉, 고객에게 추천해 줄 제품(고객이 구매한 적 없는 제품)에 등장하는 특성 중 우선순위를 정할 수 없 는 경우는 주어지지 않습니다.
     * </pre>
     */

     @Nested
    public class TestCases {
        @Test
        void case1 () {
            String[] products = new String[]{"sofa red long", "blanket blue long", "towel red", "mattress long", "curtain blue long cheap"};
            String[] purchased = new String[]{"towel","mattress","curtain"};
            String result = "blanket";


            Assertions.assertEquals(result, solution(products, purchased));
        }

        @Test
        void case2 () {
            String[] products = new String[]{"towel red long thin", "blanket red thick short",
                    "curtain red long wide", "mattress thick", "hat red thin", "pillow red long",
                    "muffler blue thick long"};
            String[] purchased = new String[]{"blanket","curtain", "hat", "muffler"};
            String result = "towel";


            Assertions.assertEquals(result, solution(products, purchased));
        }
    }


    public String solution( String[] products, String[] purchased) {
        //구매하지 않은 것 중에서 우선순위가 높은 하나를 추천
        // - 특성
        // 구매한 제품의 특성이 몇 번 나타나는지 확인
        // 더 많이 나타난게 높은 순위
        // 만약 출현 횟수가 같으면 사전순
        // - 제품
        // 가장 높은 우선순위의 특성을 비교했을 떄 더 높은 우선 순위 특성을 가지면 제품 간 우선 순위가 높음
        // 우선 순위가 같다면 그 다음끼리 비교
        // 우선 순위 비교 때 바닥나면 낮은 것으로

        Map<String, String[]> attributeProductMap = new HashMap<>();
        Map<String, Integer> purchasedAttributeCount = new HashMap<>();
        List<String> purchasedAttributeRank = new ArrayList<>();
        List<String> promote = new ArrayList<>();

        for( String product : products) {
            String[] split = product.split(" ");
            String[] attributes = Arrays.copyOfRange(split, 1, split.length);
            attributeProductMap.put(split[0], attributes);

            if (Arrays.stream(purchased).noneMatch(i -> i.equals(split[0]))) {
                promote.add(split[0]);
                continue;
            }
            for( String attribute: attributes) {
                purchasedAttributeCount.putIfAbsent(attribute, 0);
                purchasedAttributeCount.computeIfPresent(attribute, (s, integer) -> integer + 1);
            }
       }

        purchasedAttributeRank = purchasedAttributeCount.keySet()
                .stream()
                .sorted((o1, o2) -> {
                    int count = purchasedAttributeCount.get(o2) - purchasedAttributeCount.get(o1);
                    if( count != 0) return count;
                    else return o1.compareTo(o2);
                }).collect(Collectors.toList());



        List<String> result = new ArrayList<>(promote);


        outer: for( int i = 0; i < 10; i ++ ){
            int target = -1;
            int delete = Integer.MAX_VALUE;


            inner: for( int j = promote.size() - 1; j >= 0; j -- ) {
                String attribute = null;
                String[] attributeArray = attributeProductMap.get(promote.get(j));


                if( attributeArray.length - 1 > i) attribute = attributeArray[i];
                if(Objects.isNull(attribute)) {
                    target = j;
                    break inner;
                }

                int size = purchasedAttributeRank.size();
                int rankIdx = purchasedAttributeRank.indexOf(attribute);

                int ranking = size - rankIdx;

                if( delete > ranking ) {
                    target = j;
                    delete = ranking;
                }
            }


            if( result.stream().filter(v -> !v.equals("")).count() > 1L) {
                result.remove(target);
                result.add(target, "");
            } else {
                break outer;

            }

        }
        return result.stream().filter(v -> !v.equals("")).findFirst().get();
    }
    public String solutionFail( String[] products, String[] purchased) {

         //구매하지 않은 것 중에서 우선순위가 높은 하나를 추천
         // - 특성
         // 구매한 제품의 특성이 몇 번 나타나는지 확인
         // 더 많이 나타난게 높은 순위
         // 만약 출현 횟수가 같으면 사전순
         // - 제품
         // 가장 높은 우선순위의 특성을 비교했을 떄 더 높은 우선 순위 특성을 가지면 제품 간 우선 순위가 높음
         // 우선 순위가 같다면 그 담음끼리 비교
         // 우선 순위 비교 때 바닥나면 낮은 것으로

        Map<String, List<String>> productAttribute = new HashMap<>();
        for ( int i = 0; i < products.length; i ++ ) {
            String[] split = products[i].split(" ");
            productAttribute.putIfAbsent(split[0], Arrays.stream(Arrays.copyOfRange(split, 1, split.length)).collect(Collectors.toList()));
        }

        Map<String, Long> attributeMap =  Arrays.stream(purchased)
                .flatMap(key -> productAttribute.get(key).stream())
                .collect(Collectors.toMap(Function.identity(), o -> 1L, (aLong, aLong2) -> aLong+aLong2));
        List<String> keySet = attributeMap.keySet().stream().sorted((k1,k2) -> {
            long sort = attributeMap.get(k2) - attributeMap.get(k1);
            if (sort != 0) return  (int) sort;
            else return k1.compareTo(k2);
        }).collect(Collectors.toList());
        List<String> notPurchased = productAttribute.keySet()
                .stream()
                .filter(k -> Arrays.stream(purchased).noneMatch(p -> p.equals(k)))
                .peek(k ->  productAttribute.get(k).sort((o1, o2) -> keySet.indexOf(o1) - keySet.indexOf(o2)))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>(notPurchased);

        for ( int i = 0; i < 10; i ++ ) {
            int min = 2000;
            int maxIdx = -1;
            for( int j = notPurchased.size() - 1; j >= 0; j -- ) {
                if( productAttribute.get(notPurchased.get(j)).size() < i + 1) continue;
                int calc = keySet.size() - keySet.indexOf(productAttribute.get(notPurchased.get(j)).get(i));
                if( min > calc  ) {
                    maxIdx = j;
                    min = calc;
                }
            }

            if( result.size() != 1) result.remove(notPurchased.get(maxIdx));
            if( result.size() == 1) break;
        }

        System.out.println(result);

        return result.get(0);
     }
}
