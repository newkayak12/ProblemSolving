package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.LinkedBlockingQueue

class HideAndSeek {
    /**
     * 🧩 문제 3. 숨바꼭질 (최단 거리 탐색)
     *
     * 설명
     *
     * 수빈이는 현재 위치 N에 있고, 동생은 위치 K에 있다.
     * 수빈이는 걷거나 순간이동할 수 있다.
     * 	•	걷는 경우: X - 1 또는 X + 1 (1초 소요)
     * 	•	순간이동: 2 * X (1초 소요)
     *
     * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간을 구하세요.
     *
     * ⸻
     *
     * 입력
     * 	•	첫 줄에 수빈이의 위치 N과 동생의 위치 K가 주어진다. (0 ≤ N, K ≤ 100,000)
     *
     * 출력
     * 	•	수빈이가 동생을 찾을 수 있는 최소 시간(초)을 출력하세요.
     *
     * ⸻
     *
     * 예시 입력 1
     * 5 17
     * 예시 출력 1
     * 4
     * 5 → 10 → 9 → 18 → 17 (총 4초)
     */

    @Test
    fun solution() {
        val start = 5
        val end = 17

        val expected = 4


        assertEquals(expected, bfs(start, end))
    }

    private fun bfs(start: Int, end: Int): Int {
        val queue = LinkedBlockingQueue<Int>()
        val distance = IntArray(100_001){-1}
        queue.add(start)
        distance[start] = 0




        while (queue.isNotEmpty()) {
            val now = queue.poll()
            if (end == now) return distance[now]
            val direction = listOf(now - 1, now + 1, now * 2)

            for (next in direction) {
                if(next in 0 .. 100_000 && distance[next] == -1) {
                    queue.add(next)
                    distance[next] = distance[now] + 1
                }
            }

        }


        return -1
    }
}