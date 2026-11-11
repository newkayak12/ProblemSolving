package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class 야근_지수 {

    /**
     * 야근 피로도: 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 갑 야근 피로도를 최소화
     * <p>
     * 아 n의 리소스에서 array를 최대한 normalize 하고 리턴하라는 뜻으로 보임 큰 순서대로 정렬하고 앞의 수에서 하나씩 빼면 딱 좋을거 같은데 PQ?
     */

    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int work : works) {
            pq.add(work);
        }

        int i = 0;
        while(!pq.isEmpty() && i < n) {
            int adjust = pq.poll() - 1;
            if(adjust > 0) {pq.add(adjust);}
            i++;
        }

        if(pq.isEmpty()) return 0;
        else {
            long answer = 0;
            while(!pq.isEmpty()) {
                answer += (long) Math.pow(pq.poll(), 2.0);
            }
            return answer;
        }
    }

    @Test
    void test1() {
        int[] works = {4, 3, 3};
        int n = 4;
        int result = 12;

        assertEquals(result, solution(n, works));
    }

    @Test
    void test2() {
        int[] works = {2, 1, 2};
        int n = 1;
        int result = 6;

        assertEquals(result, solution(n, works));
    }

    @Test
    void test3() {
        int[] works = {1, 1};
        int n = 3;
        int result = 0;

        assertEquals(result, solution(n, works));
    }


}
