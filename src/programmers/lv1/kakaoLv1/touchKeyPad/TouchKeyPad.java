package programmers.lv1.kakaoLv1.touchKeyPad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TouchKeyPad {
        //https://school.programmers.co.kr/learn/courses/30/lessons/67256
        /**
         * 왼손 엄지손가락은 * 키패드에 오른손 엄지손가락은 # 키패드 위치에서 시작하며, 엄지손가락을 사용하는 규칙은 다음과 같습니다.
         * 엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
         * 왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다.
         * 오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다.
         * 가운데 열의 4개의 숫자 2, 5, 8, 0을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.
         * 4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.
         */

      @Nested
       class testCase {
            @Test
            public void case1() {
                int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
                String hand = "right";
                String result = "LRLLLRLLRRL";

                String answer = solution(numbers, hand);
                Assertions.assertEquals( result, answer );


            }
            @Test
            public void case2() {
                int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
                String hand = "left";
                String result = "LRLLRRLLLRR";

                String answer = solution(numbers, hand);
                Assertions.assertEquals( result, answer );
            }
            @Test
            public void case3() {
                int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
                String hand = "right";
                String result = "LLRLLRLLRL";

                String answer = solution(numbers, hand);
                Assertions.assertEquals( result, answer );
            }
        }

        public static String solution(int[] numbers, String hand ){
            StringBuilder answer = new StringBuilder();
            int left = 10; // *
            int right = 12; // #

//            System.out.println(Arrays.stream(numbers).boxed().map(String::valueOf).collect(Collectors.joining()));

            for ( int number : numbers ) {
                int beforeLeft = left;
                int beforeRight = right;

                int tmpNumber = number == 0 ? 11 : number;

                if( tmpNumber % 3 == 0 ) {
                    right = tmpNumber;
                    answer.append("R");
                    continue;
                }
                if( tmpNumber % 3 == 1 ) {
                    left = tmpNumber;
                    answer.append("L");
                    continue;
                }
                if( tmpNumber % 3 == 2 ) {
                    int rightDistance = 0;
                    int leftDistance = 0;

                    int tmpRight = (right == 0 ? 11 : right);
                    int tmpLeft = left == 0 ? 11 : left;



                    if ( tmpRight % 3 == 2 || right == 0) {
                        rightDistance  = Math.abs(tmpNumber -  (right == 0 ? 11 : right)) / 3;
                    }

                    if ( tmpLeft  % 3 == 2 || left == 0) { //4
                        leftDistance  = Math.abs(tmpNumber -  (left == 0 ? 11 : left)) / 3;
                    }



                    if ( tmpRight % 3 == 0 ){
                        int rightPick = tmpNumber + 1;
                        rightDistance = (Math.abs( rightPick - tmpRight ) / 3) + 1;
                    }

                    if ( tmpLeft % 3 == 1 ) {
                        int leftPick = tmpNumber - 1;
                        leftDistance = (Math.abs( leftPick - tmpLeft ) / 3) + 1;
                    }



                    if ( leftDistance > rightDistance ) {
                        right = number;
                        answer.append("R");
                        continue;
                    }

                    if ( leftDistance < rightDistance ) {
                        left = number;
                        answer.append("L");
                        continue;
                    }

                    if ( leftDistance == rightDistance ) {
                        if ( hand.equalsIgnoreCase("left") ) {
                            left = number;
                            answer.append("L");
                        } else {
                            right = number;
                            answer.append("R");
                        }
                    }

                }

            }


            return answer.toString();
        }

}
