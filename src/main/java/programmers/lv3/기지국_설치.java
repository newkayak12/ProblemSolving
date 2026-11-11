package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 기지국_설치 {

    public int solution(int n, int[] stations, int w) {
        int coverage = 2 * w + 1;
        int lastIndex = 0;
        int count = 0;

        for (int stationPosition : stations) {
            int asIndex = stationPosition - 1;
            int beforeIndex = asIndex - w;
            int afterIndex = asIndex + w;


            if(lastIndex >= n) break;

            int gap = beforeIndex - lastIndex ;
            if(gap > 0) count += (int) Math.ceil((double) gap / coverage);
            lastIndex = afterIndex + 1;
        }


        if(lastIndex < n) {
            int gap =  n - lastIndex;
            if(gap > 0) count += (int) Math.ceil((double) gap / coverage);
        }

        return count;
    }


    @Test
    void test1() {
        int N = 11;
        int[] stations = {4, 11};
        int W = 1;
        int answer = 3;

        assertEquals(answer, solution(N, stations, W));
    }

    @Test
    void test2() {
        int N = 16;
        int[] stations = {9};
        int W = 2;
        int answer = 3;

        assertEquals(answer, solution(N, stations, W));
    }

    @Test
    void test3() {
        int N = 16;
        int[] stations = {1};
        int W = 1;
        int answer = 5;

        assertEquals(answer, solution(N, stations, W));
    }

    @Test
    void test4() {
        int N = 5;
        int[] stations = {3};
        int W = 2;
        int answer = 0;

        assertEquals(answer, solution(N, stations, W));
    }

    @Test
    void test5() {
        int N = 5;
        int[] stations = {4};
        int W = 0;
        int answer = 4;

        assertEquals(answer, solution(N, stations, W));
    }


}
