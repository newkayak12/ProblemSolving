package programmers.hyunsoo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Hotel {
    /**
     * <pre>
     * 정확성 시간 제한 / 메모리 제한
     * 10초/ 2GB
     * 문제 설명
     * 한 층에 w 개의 객실이 있는 h 총 호텔이 있습니다. 각 층의 w 개의 객실은 1~ w 의 번호를 가지고 있습니 다. 어떤 객실을 예약한 손님이 있다면, 그 손님이 체크아웃할 때까지 다른 손님은 해당 객실을 예약할 수 없습니다.
     * 모든 객실의 체크아웃 시각은 오전 11시, 체크인 시각은 오후 2시입니다. (어떤 객실에 대해 체크아웃이 발생 했다면, 같은 날짜에 체크인이 가능합니다.)
     * 호텔은 다음과 같은 형태의 예약을 받습니다.
     * "객실 수 : 체크인 날짜 ~ 체크아웃 날짜"
     * 객실 수가 k 인 예약을 받으려면 같은 층에서 비어있는 k 개의 연속한 번호의 객실을 배정해야 합니다.
     * 다음은 한 층에 6개의 객실이 있는 3층 호텔의 예시입니다.
     *
     *
     * 3 x x o o x x
     * 2 o o x x o o
     * 1 x o o o o o
     *
     * • X 표시는 이미 예약된 객실을 나타냅니다.
     * 현재 호텔의 객실 상황이 위와 같을 때, 이 날짜에 체크인하는 객실 수가 4인 예약을 받는다고 가정합니다. 같은 층 에서 비어있는 4개의 연속한 번호의 객실을 배정하는 방법은 다음과 같이 두 가지가 있습니다.
     * 1. 1층의 2, 3, 4, 5번 객실을 배정
     * 2. 1종의 3, 4, 5, 6번 객실을 배정
     * [2층의 1, 2번 객실 + 3층의 3, 4번 객실을 배정]하거나, [2층의 1, 2, 5, 6번 객실을 배정]하는 방법은 같은 층의
     * 4개의 연속한 번호의 객실이 아니므로 잘못된 배정 방법입니다.
     * 당신은 예약이 들어온 순서대로 객실을 배정합니다. 각 예약의 체크인 시각에 비어있는 k 개의 연속한 객실을 배 정하며, 만약 객실을 배정하는 방법이 여러 개라면 최대한 낮은 층, 그중에서 최대한 낮은 번호의 객실을 배정합니 다. 만약 같은 층에서 객실 수만큼의 연속된 객실을 배정하는 방법이 없을 경우 예약을 거절해야 합니다.
     * 다음은 한 층에 4개의 객실이 있는 2층 호텔에서 예약을 처리하는 예시입니다.
     *
     * 객실 수 체크인 날짜  체크아웃 날짜
     * [ 3 1 3 ]
     * [ 2 1 4 ]
     * [ 1 1 2 ]
     * [ 1 1 5 ]
     * [ 2 2 5 ]
     * [ 2 3 5 ]
     * 호텔에 들어온 예약들의 목록이 위 표와 같을 때, 다음과 같은 순서로 예약을 처리합니다.
     * 1. 첫 번째 예약은 객실 수 = 3인 예약입니다. 가능한 객실 배정 방법 중 최대한 낮은 총, 낮은 번호인 1층의 1~3번 객실을 배정하게 됩니다.
     * 2. 두 번째 예약은 객실 수= 2인 예약입니다. 가능한 객실 배정 방법 중 최대한 낮은 층, 낮은 번호인 2층의 1~2번 객실을 배정하게 됩니다.
     * 3. 세 번째 예약은 객실 수 = 1인 예약입니다. 가능한 객실 배정 방법 중 최대한 낮은 총, 낮은 번호인 1층의 4번 객실을 배정하게 됩니다.
     * 4. 네 번째 예약은 객실 수 = 1인 예약입니다. 가능한 객실 배정 방법 중 최대한 낮은 층, 낮은 번호인 2층의 3 번 객실을 배정하게 됩니다. 이후 호텔의 객실 상황은 아래 그림과 같은 상태가 됩니다.
     *
     *
     * • 1 <= h <= 10
     * • 1 <= w ≤ 100
     * • 1 <= books 의 길이 <= 1,000
     *  • books 의 1 번째 원소는 1 번째로 들어온 예약정보를 나타내며, [amount, in, out]니다.
     *  • amount 는 객실 수, in 은 체크인 날짜, out은 체크아웃 날짜를 의미합니다.
     *  • 1≤ amount<=W
     *  • 1s in < out ≤ 100
     *  • 예약이 들어오는 순서는 항상 체크인 날짜가 증가하는 순서입니다. 즉, i< j books[i][1] <= books[j][1]을 만족합니다.
     *  </pre>
     */
    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int h = 2;
            int w = 4;
            int[][] books = {{3, 1, 3}, {2, 1, 4}, {1, 1, 2}, {1, 1, 5}, {2, 2, 5}, {2, 3, 5}};

            int[] result = new int[]{1, 1, 1, 1, 0, 1};
            Assertions.assertArrayEquals(result, solution(w, h, books));
        }

        @Test
        public void case2 () {
            int h = 3;
            int w = 4;
            int[][] books = {{4, 1, 5}, {4, 1, 3}, {4, 1, 7}, {1, 2, 10}, {1, 3, 10}, {1, 4, 10}};

            int[] result = new int[]{1, 1, 1, 0, 1, 1};
            Assertions.assertArrayEquals(result, solution(w, h, books));
        }

        @Test
        public void case3 () {
            int h = 1;
            int w = 5;
            int[][] books = {{1, 1, 2}, {1, 1, 10}, {1, 1, 2}, {1, 1, 10}, {2, 4, 7}};

            int[] result = new int[]{1, 1, 1, 1, 0};
            Assertions.assertArrayEquals(result, solution(w, h, books));
        }

        @Test
        public void case4 () {
            int h = 1;
            int w = 100;
            int[][] books = {{100, 1, 10}, {100, 1, 2}, {1, 2, 3}, {1, 3, 4}};

            int[] result = new int[]{1, 0, 0, 0};
            Assertions.assertArrayEquals(result, solution(w, h, books));
        }

        @Test
        public void case5 () {
            int h = 1;
            int w = 100;
            int[][] books = {{100, 1, 2}, {100, 1, 10}, {7, 2, 3}, {1, 3, 4}};

            int[] result = new int[]{1, 0, 1, 1};

            Assertions.assertArrayEquals(result, solution(w, h, books));
        }
    }

    int[] solution( int w, int h, int[][] books) {
        int[] answer = new int[books.length];
        int[][] hotel = new int[h][w];

        //객실 수 체크인 날짜  체크아웃 날짜
        for( int i = 0; i < books.length; i ++ ) {
            int[] book = books[i];
            int roomCount = book[0];
            int checkIn = book[1];
            int checkOut = book[2];



            check: for( int he = 0; he < h; he ++ ) {
                for ( int we = 0; we < w; we ++ ) {
                    int count = this.check(hotel[he], we, 1, checkIn, roomCount);
                    System.out.println(count);
                    if( count < roomCount ) we += count;
                    else {
                        for (int checking = we; checking < we + count; checking ++) hotel[he][checking] = checkOut;
                        answer[i] = 1;
                        break check;
                    }
                }
            }


            // {3, 1, 3},
            // {2, 1, 4},
            // {1, 1, 2},
            // {1, 1, 5},
            // {2, 2, 5},
            // {2, 3, 5}
            for( int j = hotel.length - 1; j >= 0; j -- ) {
                System.out.println(Arrays.toString(hotel[j]));
            }
            System.out.println("---------------");
        }


        System.out.println(Arrays.toString(answer));
        return answer;
    }

    private int check( int[] schedule, int index, int count, int checkIn, int roomCount ) {

        if(
            index < schedule.length  &&
            schedule[index] <= checkIn &&
            count <= roomCount
        ) return 1 + check(schedule, index + 1, count + 1, checkIn, roomCount);
        else return 0;
    }
}

