package programmers.lv2.kakaoLv2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColoringBook {
    //https://school.programmers.co.kr/learn/courses/30/lessons/1829
    /**
     * <pre>
     * 출판사의 편집자인 어피치는 네오에게 컬러링북에 들어갈 원화를 그려달라고 부탁하여
     * 여러 장의 그림을 받았다.
     *
     * 여러 장의 그림을 난이도 순으로 컬러링북에 넣고 싶었던 어피치는
     * 영역이 많으면 색칠하기가 까다로워 어려워진다는 사실을 발견하고
     * 그림의 난이도를 영역의 수로 정의하였다.
     * (영역이란 상하좌우로 연결된 같은 색상의 공간을 의미한다.)
     *
     * 그림에 몇 개의 영역이 있는지와
     * 가장 큰 영역의 넓이는 얼마인지 계산하는 프로그램을 작성해보
     * </pre>
     */

    @Nested
    class TestCase {
        @Test
        public void case1 () {
            int m = 6;
            int n = 4;
            int[][] picture = {
                    {1, 1, 1, 0},
                    {1, 2, 2, 0},
                    {1, 0, 0, 1},
                    {0, 0, 0, 1},
                    {0, 0, 0, 3},
                    {0, 0, 0, 3},
            };
            int[] result = {4, 5};


            Assertions.assertArrayEquals(result, solution(m, n, picture));
        }
    }

    private enum direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    public int[] solution ( int m, int n, int[][] picture ) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        Map<Integer, Integer> dotCount = Arrays.stream(picture)
                                               .flatMap(ele -> Arrays.stream(ele).boxed())
                                               .filter(ele -> ele > 0)
                                               .collect(Collectors.toMap(Function.identity(), (ele) -> 1, (a,b) -> a+b));

        System.out.println(dotCount);
        int scanX = 0;
        int scanY = 0;
        int nowPicked = 0;

        Set<String> set = new HashSet<>();


        return new int[]{ numberOfArea, maxSizeOfOneArea};
    }
}

