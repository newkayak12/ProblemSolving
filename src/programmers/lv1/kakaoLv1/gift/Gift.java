package programmers.lv1.kakaoLv1.gift;

import com.sun.security.jgss.GSSUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Gift {
    //https://school.programmers.co.kr/learn/courses/30/lessons/258712
    /**
     *
     *<pre>
     * 당신의 친구들이 이번 달까지 선물을 주고받은 기록을 바탕으로 다음 달에 누가 선물을 많이 받을지 예측하려고 합니다.
     *
     * 두 사람이 선물을 주고받은 기록이 있다면,
     * 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받습니다.
     *
     *
     * 예를 들어
     *
     * A가 B에게 선물을 5번 줬고,
     * B가 A에게 선물을 3번 줬다면
     *
     * 다음 달엔 A가 B에게 선물을 하나 받습니다.
     *
     *
     * 두 사람이 선물을 주고받은 기록이 하나도 없거나
     * 주고받은 수가 같다면,
     * 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받습니다.
     *
     * 선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값입니다.
     *
     *
     * 예를 들어 A가 친구들에게 준 선물이 3개고 받은 선물이 10개라면 A의 선물 지수는 -7입니다.
     * B가 친구들에게 준 선물이 3개고 받은 선물이 2개라면 B의 선물 지수는 1입니다.
     *
     * 만약 A와 B가 선물을 주고받은 적이 없거나 정확히 같은 수로 선물을 주고받았다면,
     * 다음 달엔 B가 A에게 선물을 하나 받습니다.
     *
     * 만약 두 사람의 선물 지수도 같다면 다음 달에 선물을 주고받지 않습니다.
     *
     * 위에서 설명한 규칙대로 다음 달에 선물을 주고받을 때, 당신은 선물을 가장 많이 받을 친구가 받을 선물의 수를 알고 싶습니다.
     * </pre>
     */


   @Nested
    class Cases {

        @Test
        public void case1 () {
            String[] friends = {"muzi", "ryan", "frodo", "neo"};
            String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
            int expected = 2;

            Assertions.assertEquals(expected, solution(friends, gifts));
        }

        @Test
        public void case2 () {
            String[] friends = {"joy", "brad", "alessandro", "conan", "david"};
            String[] gifts = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
            int expected = 4;

            Assertions.assertEquals(expected, solution(friends, gifts));

        }

        @Test
        public void case3 () {
            String[] friends = {"a", "b", "c"};
            String[] gifts = {"a b", "b a", "c a", "a c", "a c", "c a"};
            int expected = 0;

            Assertions.assertEquals(expected, solution(friends, gifts));

        }
    }

    public int solution ( String[] friends, String[] gifts ){
       // 더 많이 준 사람이 다음 달에 선물을 하나 받는다.
       // -> A = 5 => B, B = 3 => A  ==> B = 1 => A
       // 기록이 없거나 주고 받은 수가 같으면 선물 지수가 더 큰사람이 작은 사람에게 하나 받는다.
       // 선물 지수는 이번 달까지 자신이 선물에게 준 수에서 받은 수를 뺀 값이다.

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for( String gift : gifts ) {
            map.putIfAbsent(gift, 0);
            map.computeIfPresent(gift, (s, integer) -> integer + 1);

            String[] split = gift.split(" ");
            String start = split[0];
            String end = split[1];
            map2.putIfAbsent(start, 0);
            map2.putIfAbsent(end, 0);
            map2.computeIfPresent(start, (s, integer) -> integer + 1);
            map2.computeIfPresent(end, (s, integer) -> integer - 1);
        }

        int[] friendMap = new int[friends.length];



        for (int i = 0; i < friends.length; i ++ ) {
            for (int j = i + 1; j < friends.length; j ++ ) {
                if(i == j) continue;


                int iToj = map.getOrDefault(String.format("%s %s", friends[i], friends[j]), 0);
                int jToi = map.getOrDefault(String.format("%s %s", friends[j], friends[i]), 0);


                if( iToj > jToi ) friendMap[i] += 1;
                else if( iToj < jToi ) friendMap[j] += 1;
                else {
                    int iIdx = map2.getOrDefault(friends[i], 0);
                    int jIdx = map2.getOrDefault(friends[j], 0);
                    if ( iIdx > jIdx ) friendMap[i] += 1;
                    else if (iIdx < jIdx) friendMap[j] +=1;

                }
            }
        }



        Arrays.sort(friendMap);

        return friendMap[friendMap.length - 1];
    }

    class Success {
        public int solution ( String[] friends, String[] gifts ) {


            Map<String, Integer> gift = Arrays.stream(gifts).collect(Collectors.toMap(elem -> elem.split(" ")[0], elem -> 1, (a,b) -> a + b));
            Map<String, Integer> receive = Arrays.stream(gifts).collect(Collectors.toMap(elem -> elem.split(" ")[1], elem -> 1, (a,b) -> a + b));
            Map<String, Integer> giftIndex = new HashMap<>();
            Map<String, Integer> giftMap = Arrays.stream(gifts).collect(Collectors.toMap(elem -> elem, elem -> 1, (a,b) -> a + b));
            Map<String, Integer> afterGift = new HashMap<>();

            for ( String f1 : friends ) {
                giftIndex.put(f1, gift.getOrDefault(f1, 0) - receive.getOrDefault(f1, 0));
                afterGift.put(f1, 0);
            }

            for ( String f1 : friends ) {
                for ( String f2 : friends ) {
                    if( f1.equals(f2) ) continue;

                    Integer f1Tof2 = giftMap.getOrDefault(String.format("%s %s", f1, f2), 0);
                    Integer f2Tof1 = giftMap.getOrDefault(String.format("%s %s", f2, f1), 0);


                    if ( f1Tof2 > f2Tof1  ) afterGift.computeIfPresent(f1, (k, v) -> v + 1);
                    if ( f2Tof1 > f1Tof2  ) afterGift.computeIfPresent(f2, (k, v) -> v + 1);
                    if ( f1Tof2 == f2Tof1 ) {
                        int f1Index = giftIndex.getOrDefault(f1, 0);
                        int f2Index = giftIndex.getOrDefault(f2, 0);
                        if ( f1Index > f2Index) afterGift.computeIfPresent(f1, (k, v) -> v + 1);
                        if ( f1Index < f2Index) afterGift.computeIfPresent(f2, (k, v) -> v + 1);
                    }

                }
            }

            int maximum = afterGift.values().stream()
                    .sorted((a,b) -> b-a)
                    .collect(Collectors.toList()).get(0);

            return maximum / 2;
        }
        public int solution2 ( String[] friends, String[] gifts ) {
            Map<String, Integer> indexMap = new LinkedHashMap<>();
            StringTokenizer tokenizer;
            for ( int i = 0; i < friends.length; i ++ ){
                indexMap.put(friends[i], i);
            }

            int[][] dimensionalArray =  new int[friends.length][friends.length];

            for ( String gift : gifts ) {
                tokenizer = new StringTokenizer(gift, " ");
                String from = tokenizer.nextToken();
                String to = tokenizer.nextToken();
                dimensionalArray[indexMap.get(from)][indexMap.get(to)] += 1;
            }


            return 0;
        }
    }

}

