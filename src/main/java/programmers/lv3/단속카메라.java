package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class 단속카메라 {

    /**
     * Greedy
     *
     * 회의실 문제랑 비슷한데
     * 동
     *
     *
     *
     */
    public int solution2(int[][] routes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(it -> routes[it][1]));
        for (int index = 0; index < routes.length; index ++ ) pq.add(index);
        int point = Integer.MIN_VALUE;
        int count = 0;

        while(!pq.isEmpty())  {
            int currentIndex = pq.poll();
            int startPoint = routes[currentIndex][0];
            int endPoint = routes[currentIndex][1];

            if(point < startPoint) {
                count ++;
                point = endPoint;
            }
        }


        return count;
    }


    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int index = 0;
        int point = Integer.MIN_VALUE;
        int count = 0;


        while(index < routes.length)  {
            int[] currentPoint = routes[index++];
            int startPoint = currentPoint[0];
            int endPoint = currentPoint[1];

            if(point < startPoint) {
                count ++;
                point = endPoint;
            }

        }


        return count;
    }

    @Test
    void test1() {
        int[][] routes = {
            {-20, -15},
            {-14, -5},
            {-18, -13},
            {-5, -3},
        };
        int result = 2;


        assertEquals(result, solution(routes));
    }

    @Test
    void test2() {
        int[][] routes = {
            {-20, -15},
            {-15, -5},
            {-15, -13},
            {-15, -3},
        };
        int result = 1;


        assertEquals(result, solution(routes));
    }
}
