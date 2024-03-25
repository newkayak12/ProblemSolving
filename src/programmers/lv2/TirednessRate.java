package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TirednessRate {
    //https://school.programmers.co.kr/learn/courses/30/lessons/87946
    /**
     * <pre>
     *  XX게임에는 피로도 시스템(0 이상의 정수로 표현합니다)이 있으며,
     *  일정 피로도를 사용해서 던전을 탐험할 수 있습니다.
     *
     *  이때, 각 던전마다 탐험을 시작하기 위해 필요한
     *  "최소 필요 피로도"와 던전 탐험을 마쳤을 때 소모되는 "소모 피로도"가 있습니다.
     *  "최소 필요 피로도"는 해당 던전을 탐험하기 위해 가지고 있어야 하는
     *  최소한의 피로도를 나타내며, "소모 피로도"는 던전을 탐험한 후
     *  소모되는 피로도를 나타냅니다.
     *
     *  예를 들어
     *  "최소 필요 피로도"가 80,
     *  "소모 피로도"가 20인 던전을 탐험하기 위해서는
     *  유저의 현재 남은 피로도는 80 이상 이어야 하며,
     *  던전을 탐험한 후에는 피로도 20이 소모됩니다.
     *
     * 이 게임에는 하루에 한 번씩 탐험할 수 있는 던전이 여러개 있는데,
     * 한 유저가 오늘 이 던전들을 최대한 많이 탐험하려 합니다.
     * 유저의 현재 피로도 k와 각 던전별
     * "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열 dungeons 가 매개변수로 주어질 때,
     * 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.
     *
     *
     * 제한사항
     *  - k는 1 이상 5,000 이하인 자연수입니다.
     *  - dungeons의 세로(행) 길이(즉, 던전의 개수)는 1 이상 8 이하입니다.
     *       - dungeons의 가로(열) 길이는 2 입니다.
     *       - dungeons의 각 행은 각 던전의 ["최소 필요 피로도", "소모 피로도"] 입니다.
     *       - "최소 필요 피로도"는 항상 "소모 피로도"보다 크거나 같습니다.
     *       - "최소 필요 피로도"와 "소모 피로도"는 1 이상 1,000 이하인 자연수입니다.
     *       - 서로 다른 던전의 ["최소 필요 피로도", "소모 피로도"]가 서로 같을 수 있습니다.
     * </pre>
     */
    @Nested
    public class TestCases {
        @Test
        public void case1 () {
            int k = 80; // 현재
            int[][] dungeons = new int[][]{{80,20},{50,40},{30,10}}; //{최소, 소모}
            int result = 3; //최대

            Assertions.assertEquals(result, solution(k, dungeons));
        }
    }

    //BFS?
    boolean[] probeMap = null;
    public int solution(int k, int[][] dungeons) {
        probeMap = new boolean[dungeons.length];
        return Math.max(0, prob(dungeons, probeMap, k, 1));
    }

    private int prob( int[][] dungeons, boolean[] probMap, int tired, int count ) {
        int innerCount = count;

        print(probMap);
        for ( int i = 0; i < dungeons.length; i ++ ) {
            if ( probMap[i] || dungeons[i][0] > tired) continue;

            probMap[i] = true;
            Math.max(innerCount, prob(dungeons, probMap, tired - dungeons[i][1], count + 1));
            probMap[i] = false;
        }

        return innerCount;
    }


    private int probFailure( int[][] dungeons, boolean[] probMap, int idx, int tired, int count ) {
        int[] start = dungeons[idx];
        probMap[idx] = true;
        int max = count;


//        print(probMap);
//        System.out.println("> "+idx);

        if( start[0] > tired && probMap[idx]) return count;
        else {
            for ( int i = 1; i <= dungeons.length; i ++  ) {

                int next = idx + i;
                if( next >= dungeons.length ) next -= dungeons.length;
                if(probMap[next]) continue;

                boolean[] tmpMap = Arrays.copyOf(probMap, probMap.length);
                int result = probFailure(dungeons, tmpMap, next, tired -= start[1], count + 1 );


                max = Math.max(max, result);

            }
        }

        return max;
    }


    public void print( boolean[] probeMap ) {
        for (boolean r : probeMap) System.out.print(r+", ");
        System.out.println();
    }
}
