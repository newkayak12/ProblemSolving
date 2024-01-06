package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WalletAndNameCard {
    //https://school.programmers.co.kr/learn/courses/30/lessons/86491
    /**
     * 명함 지갑을 만드는 회사에서 지갑의 크기를 정하려고 합니다.
     * 다양한 모양과 크기의 명함들을 모두 수납할 수 있으면서,
     * 작아서 들고 다니기 편한 지갑을 만들어야 합니다.
     * 이러한 요건을 만족하는 지갑을 만들기 위해 디자인팀은
     * 모든 명함의 가로 길이와 세로 길이를 조사했습니다.
     * 아래 표는 4가지 명함의 가로 길이와 세로 길이를 나타냅니다.
     *
     * 명함 번호	가로 길이	세로 길이
     *   1        60     50
     *   2        30     70
     *   3        60     30
     *   4        80     40
     *
     *
     *   가장 긴 가로 길이와 세로 길이가 각각 80, 70이기 때문에 80(가로) x 70(세로) 크기의 지갑을 만들면 모든 명함들을 수납할 수 있습니다.
     *   하지만 2번 명함을 가로로 눕혀 수납한다면 80(가로) x 50(세로) 크기의 지갑으로 모든 명함들을 수납할 수 있습니다.
     *   이때의 지갑 크기는 4000(=80 x 50)입니다.
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
            int expected = 4000;
            System.out.println(solution(sizes));
            Assertions.assertEquals(expected, solution(sizes));
        }

        @Test
        public void case2 () {
            int[][] sizes = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
            int expected = 120;
            Assertions.assertEquals(expected, solution(sizes));
        }

        @Test
        public void case3 () {
            int[][] sizes = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
            int expected = 133;
            Assertions.assertEquals(expected, solution(sizes));
        }
    }
    public static int solution(int[][] sizes) {
        List<Integer> width = new ArrayList<>();
        List<Integer> height = new ArrayList<>();

        for ( int[] size : sizes ) {
            width.add(Math.min(size[0], size[1]));
            height.add(Math.max(size[0], size[1]));
        }

        width.sort((o1, o2) -> o1 - o2);
        height.sort(((o1, o2) -> o1 - o2));

        return width.get(width.size() - 1) * height.get(height.size() - 1);
    }
}
