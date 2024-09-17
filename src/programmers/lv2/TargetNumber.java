package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TargetNumber {
    //https://school.programmers.co.kr/learn/courses/30/lessons/43165
    /**
     * 문제 설명
     *
     * n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고
     * 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
     * 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
     *
     * > -1+1+1+1+1 = 3
     * > +1-1+1+1+1 = 3
     * > +1+1-1+1+1 = 3
     * > +1+1+1-1+1 = 3
     * > +1+1+1+1-1 = 3
     *
     * 사용할 수 있는 숫자가 담긴 배열 numbers,
     * 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고
     * 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     *  - 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
     *  - 각 숫자는 1 이상 50 이하인 자연수입니다.
     *  - 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
     */

    @Nested
    class TestCases {
        @Test
        public void case1() {
            int[] numbers = {1,1,1,1,1};
            int target = 3;
            int returnNumber = 5;

            Assertions.assertEquals(returnNumber, solution(numbers, target));
        }


        @Test
        public void case2() {
            int[] numbers = {4,1,2,1};
            int target = 4;
            int returnNumber = 2;

            Assertions.assertEquals(returnNumber, solution(numbers, target));
        }
    }

    public int solution(int[] numbers, int target) {
        return search(numbers, 0, 0, target);
    }

    private int search (int[] numbers, int index, int number, int target ) {
        if( numbers.length <= index ) return number == target ? 1 : 0;
        else {
            int count = 0;
            count += search(numbers, index + 1, number + numbers[index], target );
            count += search(numbers, index + 1, number - numbers[index], target );
            return count;
        }
    }


    class Success {

        public int solution(int[] numbers, int target) {

            return calc(numbers,  0,0, target);
        }

        /**
         *
         *              1
         *          +       -
         *       1    1   1   1
         *     +  -
         *
         *
         */

        public int calc ( int[] arr, int idx, int sum, int target) {
            int count = 0;

            if( arr.length  <= idx) return sum == target? 1 : 0;
            count += calc(arr, idx + 1, sum + arr[idx], target);
            count += calc(arr, idx + 1, sum - arr[idx], target);


            return count;
        }
    }
}
