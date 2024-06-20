package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LotteryMinMax {
    // https://school.programmers.co.kr/learn/courses/30/lessons/77484
    /**
     *
     * 1	6개 번호가 모두 일치
     * 2	5개 번호가 일치
     * 3	4개 번호가 일치
     * 4	3개 번호가 일치
     * 5	2개 번호가 일치
     * 6(낙첨)	그 외
     *
     *
     *
     * 민우의 동생이 로또에 낙서를 하여, 일부 번호를 알아볼 수 없게 되었습니다. 당첨 번호 발표 후,
     * 민우는 자신이 구매했던 로또로 당첨이 가능했던 최고 순위와 최저 순위를 알아보고 싶어 졌습니다.
     * 알아볼 수 없는 번호를 0으로 표기하기로 하고, 민우가 구매한 로또 번호 6개가 44, 1, 0, 0, 31 25라고 가정해보겠습니다
     *
     *
     * 당첨 번호	31	10	45	1	6	19	결과
     * 최고 순위 번호	31	0→10	44	1	0→6	25	4개 번호 일치, 3등
     * 최저 순위 번호	31	0→11	44	1	0→7	25	2개 번호 일치, 5등
     *
     *
     * 순서와 상관없이, 구매한 로또에 당첨 번호와 일치하는 번호가 있으면 맞힌 걸로 인정됩니다.
     *
     * - 알아볼 수 없는 두 개의 번호를 각각 10, 6이라고 가정하면 3등에 당첨될 수 있습니다.
     *  - 3등을 만드는 다른 방법들도 존재합니다. 하지만, 2등 이상으로 만드는 것은 불가능합니다.
     *
     * - 알아볼 수 없는 두 개의 번호를 각각 11, 7이라고 가정하면 5등에 당첨될 수 있습니다.
     *  - 5등을 만드는 다른 방법들도 존재합니다. 하지만, 6등(낙첨)으로 만드는 것은 불가능합니다.
     *
     *  제한 사항
     * - lottos는 길이 6인 정수 배열입니다.
     * - lottos의 모든 원소는 0 이상 45 이하인 정수입니다.
     * - 0은 알아볼 수 없는 숫자를 의미합니다.
     * - 0을 제외한 다른 숫자들은 lottos에 2개 이상 담겨있지 않습니다.
     * - lottos의 원소들은 정렬되어 있지 않을 수도 있습니다.
     * - win_nums은 길이 6인 정수 배열입니다.
     * - win_nums의 모든 원소는 1 이상 45 이하인 정수입니다.
     * - win_nums에는 같은 숫자가 2개 이상 담겨있지 않습니다.
     * - win_nums의 원소들은 정렬되어 있지 않을 수도 있습니다.
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] lottos = {44,1,0,0,31,25};
            int[] winNumber = {31,10,45,1,6,19};
            int[] expect = {3, 5};

            Assertions.assertArrayEquals(solution(lottos, winNumber), expect);
        }

        @Test
        public void case2 () {
            int[] lottos = {0, 0, 0, 0, 0, 0};
            int[] winNumber = {38, 19, 20, 40, 15, 25};
            int[] expect = {1, 6};



            Assertions.assertArrayEquals(solution(lottos, winNumber), expect);

        }

        @Test
        public void case3 () {
            int[] lottos = {45, 4, 35, 20, 3, 9};
            int[] winNumber = {20, 9, 3, 45, 4, 35};
            int[] expect = {1, 1};
            Assertions.assertArrayEquals(solution2(lottos, winNumber), expect);


        }
    }

    private static void print (int [] target) {
        System.out.println(Arrays.stream(target).boxed().map(String::valueOf).collect(Collectors.joining(", ")));
    }

    private static int[] copyArray ( int[] target ) {
      return  Arrays.copyOf(target, target.length );
    }

    private static int[] sort ( int[] target ) {
       Integer[] tmpArr = Arrays.stream(target).boxed().toArray(Integer[]::new);
       Arrays.sort(tmpArr, Comparator.reverseOrder());
       print(Arrays.stream(tmpArr).mapToInt(i -> i).toArray());
       return  Arrays.stream(tmpArr).mapToInt(i -> i).toArray();
    }

    private static int matchRank ( int number ) {
        final int firstPrize = 6;
        final int secondPrize = 5;
        final int thirdPrize = 4;
        final int fourthPrize = 3;
        final int fifthPrize = 2;

        switch (number) {
            case firstPrize :
                return 1;
            case secondPrize :
                return 2;
            case thirdPrize :
                return 3;
            case fourthPrize :
                return 4;
            case fifthPrize :
                return 5;
            default :
                return 6;
        }
    }

    public static int[] solution2(int[] lottos, int[] win_nums ) {

        int maximumMatch = 0;
        int minimumMatch = 0;



        int[] lottery = copyArray(lottos);
        int[] winNums = copyArray(win_nums);
        lottery = sort(lottery);
        winNums = sort(winNums);

        if( Arrays.equals(lottery, winNums) ) return new int[] {1, 1};
        if( Arrays.equals(lottery, new int[]{0,0,0,0,0,0})) return new int[] {1,6};


        outer : for ( int i = 0; i < winNums.length; i ++ ) {
            int innerIndex = 0;
            int win = winNums[i];


            inner :  for ( int j = innerIndex; j < lottery.length;  j ++) {
                int ref = lottery[j];

              if ( ref != 0 && win == ref) {
                  maximumMatch += 1;
                  minimumMatch += 1;
                  innerIndex += 1;
                  break inner;
              }
              if ( ref == 0 ) {
                  maximumMatch += 1;
                  innerIndex += 1;
                  break inner;
              }
            }


            System.out.println(maximumMatch);
        }


        int maxRank = matchRank(maximumMatch);
        int minRank = matchRank(minimumMatch);


        System.out.println("\n===============================" +
                        "\nmaximumMatch : " + maximumMatch +
                        "\nminimumMatch : "+ minimumMatch +
                        "\n==============================="
        );
        return new int[] {maxRank, minRank};
    }

    public static int[] solution(int[] lottos, int[] win_nums ) {

        int maximumMatch = 0;
        int minimumMatch = 0;


        int[] lottery = copyArray(lottos);
        int[] winNums = copyArray(win_nums);
        lottery = sort(lottery);
        winNums = sort(winNums);

        if (Arrays.equals(lottery, winNums)) return new int[]{1, 1};
        if (Arrays.equals(lottery, new int[]{0, 0, 0, 0, 0, 0})) return new int[]{1, 6};


        int ref = 0;
        for (int lot = 0; lot < lottery.length; lot++) {
            if (lottery[lot] == 0) {
                maximumMatch += 1;
                continue;
            }

            for (int win = ref; win < winNums.length; win++) {
                if (lottery[lot] == winNums[win]) {
                    maximumMatch += 1;
                    minimumMatch += 1;
                    ref = win + 1;
                    break;
                }
            }
        }
        return new int[]{matchRank(maximumMatch), matchRank(minimumMatch)};
    }
}
