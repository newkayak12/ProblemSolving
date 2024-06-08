package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Stack;

public class MakeHamburger {
    //https://school.programmers.co.kr/learn/courses/30/lessons/133502
    /**
     * 함께 일을 하는 다른 직원들이 햄버거에 들어갈 재료를 조리해 주면
     * 조리된 순서대로 상수의 앞에 아래서부터 위로 쌓이게 되고,
     * 상수는 순서에 맞게 쌓여서 완성된 햄버거를 따로 옮겨 포장을 하게 됩니다.
     *
     * 상수가 일하는 가게는 정해진 순서(아래서부터, 빵 – 야채 – 고기 - 빵)로
     * 쌓인 햄버거만 포장을 합니다.
     *
     * 상수는 손이 굉장히 빠르기 때문에 상수가 포장하는 동안
     * 속 재료가 추가적으로 들어오는 일은 없으며,
     * 재료의 높이는 무시하여 재료가 높이 쌓여서 일이 힘들어지는 경우는 없습니다.
     *
     *
     * 예를 들어, 상수의 앞에 쌓이는 재료의 순서가
     *
     * [야채, 빵, 빵, 야채, 고기, 빵, 야채, 고기, 빵]일 때,
     *
     * 상수는 여섯 번째 재료가 쌓였을 때,
     * 세 번째 재료부터 여섯 번째 재료를 이용하여 햄버거를 포장하고,
     *
     * 아홉 번째 재료가 쌓였을 때,
     * 두 번째 재료와 일곱 번째 재료부터 아홉 번째 재료를 이용하여 햄버거를 포장합니다.
     *
     * 즉, 2개의 햄버거를 포장하게 됩니다.
     *
     * 1: 빵
     * 2: 아채
     * 3: 고기
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] ingredients = {2, 1, 1, 2, 3, 1, 2, 3, 1};
            int expect = 2;

            Assertions.assertEquals(expect, solution(ingredients));
        }

        @Test
        public void case2 () {
            int[] ingredients = {1, 3, 2, 1, 2, 1, 3, 1, 2};
            int expect = 0;

            Assertions.assertEquals(expect, solution(ingredients));

        }
        @Test
        public void case3() {
            int[] ingredients = {1, 2, 3, 1, 1, 2, 3, 1, 1, 2};
            int expect = 2;

            Assertions.assertEquals(expect, solution(ingredients));

        }

        @Test
        public void case4() {
            int[] ingredients = {1, 3, 3, 1, 1,1, 3, 1, 1, 2};
            int expect = 0;

            Assertions.assertEquals(expect, solution(ingredients));

        }
    }

    public int solution ( int [] ingredient ) {
       int[] ingredients = new int[ingredient.length];

       int answer = 0;
       int i = 0;

       for ( int ingrd : ingredient ) {
           ingredients[i ++] = ingrd;
           if (
                   i >= 4 &&
                   ingredients[i - 1] == 1 &&
                   ingredients[i - 2] == 3 &&
                   ingredients[i - 3] == 2 &&
                   ingredients[i - 4] == 1
           ) {
               i -= 4;
               answer ++;
           }

       }


        return answer;
    }



}
