package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class 더_맵게 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42626?language=javascript
     * <p>
     * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
     */


    @Test
    public void solution() {
        int[] scoville = new int[]{1, 2, 3, 9, 10, 12};
        int K = 7;
        int expected = 2;

        assertEquals(expected, calculate(scoville, K));
    }

    private int calculate(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int element : scoville) {
            queue.add(element);
        }

        int count = 0;

        while(!queue.isEmpty()) {
            int first = queue.poll();
            if(first >= K) return count;

            if(queue.size() < 1) return  -1;
            int second = queue.poll();

            queue.add(first + (2 * second));
            count ++;
        }

        return -1;
    }
}
