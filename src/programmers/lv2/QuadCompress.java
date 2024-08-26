package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
//retry
public class QuadCompress {
    //https://school.programmers.co.kr/learn/courses/30/lessons/68936

    /**
     * 0과 1로 이루어진 2n x 2n 크기의 2차원 정수 배열 arr이 있습니다.
     * 당신은 이 arr을 쿼드 트리와 같은 방식으로 압축하고자 합니다. 구체적인 방식은 다음과 같습니다.
     * <p>
     * 1. 당신이 압축하고자 하는 특정 영역을 S라고 정의합니다.
     * 2. 만약 S 내부에 있는 모든 수가 같은 값이라면, S를 해당 수 하나로 압축시킵니다.
     * 3. 그렇지 않다면, S를 정확히 4개의 균일한 정사각형 영역(입출력 예를 참고해주시기 바랍니다.)으로 쪼갠 뒤,
     * 각 정사각형 영역에 대해 같은 방식의 압축을 시도합니다.
     * <p>
     * arr이 매개변수로 주어집니다. 위와 같은 방식으로 arr을 압축했을 때,
     * 배열에 최종적으로 남는 0의 개수와 1의 개수를 배열에 담아서 return 하도록 solution 함수를 완성해주세요.
     * <p>
     * 제한사항
     * - arr의 행의 개수는 1 이상 1024 이하이며, 2의 거듭 제곱수 형태를 하고 있습니다. 즉, arr의 행의 개수는 1, 2, 4, 8, ..., 1024 중 하나입니다.
     * - arr의 각 행의 길이는 arr의 행의 개수와 같습니다. 즉, arr은 정사각형 배열입니다.
     * - arr의 각 행에 있는 모든 값은 0 또는 1 입니다.
     */

    @Nested
    class TestCases {
        @Test
        public void case1() {
            int[][] arr = {
                    {1, 1, 0, 0},
                    {1, 0, 0, 0},
                    {1, 0, 0, 1},
                    {1, 1, 1, 1}
            };
            int[] result = {4,9};

            Assertions.assertArrayEquals(result, solution(arr));
        }

        @Test
        public void case2() {
            int[][] arr = {
                    {1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1},
                    {0, 1, 0, 0, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1}
            };
            int[] result = {10, 15};

            Assertions.assertArrayEquals(result, solution(arr));
        }
    }

    public int[] solution (int [][] arr){
        return quadZip(arr, 0, 0, arr.length, new int[]{0, 0});
    }

    public boolean zippable(int[][] arr, int x, int y, int size) {
        int sum = 0;
        for( int i = 0; i < size; i ++ ) {
            for ( int j = 0; j < size; j ++  ) {

            }
        }
    }
    public int[] quadZip(int[][] arr, int x, int y, int size, int[] answer) {


    }

    class Success {
        public int[] solution (int [][] arr) {
            return quad(arr, 0, 0, arr.length, new int[]{0, 0});
        }

        private boolean zippable(int [][] arr, int x, int y, int size ) {
            int zero  = 0;
            int area = (int) Math.pow(size, 2);
            //sqrt -> 제곱근
            //pow  -> 제곱
            // 헷깔리지 말자...
            int count = 0;
            for( int h = y; h < y + size; h ++) {
                for (int w = x; w < x + size; w ++ ) {
                    count += arr[h][w];
                }
            }

            return  count == zero || count == area;
        }

        private int[] quad(int [][] arr, int x, int y, int size, int[] answer) {
            //전체를 받아서 나눈다.
            //검증 -> OK면 +
            //    -> 아니면 나누고 검증

            // 만약 해당 사이즈에서 압축 가능하면 리턴
            if ( this.zippable(arr, x, y, size) ) {
                if( arr[y][x] == 1) answer[1] += 1;
                else answer[0] += 1;

                return answer;
            }

            // 같은 사이즈이되, 좌표 옮겨가면서 체크
            answer = this.quad(arr, x, y, size/2, answer);
            answer = this.quad(arr, x, y + size/2, size/2,answer);
            answer = this.quad(arr, x + size/2, y, size/2, answer);
            answer = this.quad(arr, x + size/2, y + size/2, size/2, answer);


            System.out.println(Arrays.toString(answer));
            return answer;
        }
    }


}
