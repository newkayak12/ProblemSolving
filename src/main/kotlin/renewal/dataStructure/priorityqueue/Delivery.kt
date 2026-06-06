package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class Delivery {
    /**
     * 배달 (프로그래머스)
     * 문제 설명
     * N개의 마을로 이뤄진 나라가 있습니다. 이 나라의 각 마을에는 1부터 N까지의 번호가 각각 하나씩 부여되어 있습니다.
     * 각 마을은 양방향으로 통행할 수 있는 도로로 연결되어 있는데, 서로 다른 마을 간에 이동할 때는 이 도로를 지나가야 합니다.
     * 1번 마을에 있는 음식점에서 각 마을로 음식을 배달하려고 합니다.
     * 각 마을로 배달하는 경우, 1번 마을에서 해당 마을로 가는 최단시간이 K 이하인 마을에서만 주문을 받으려고 합니다.
     * 입출력 예
     * N: 5
     * roads: [[1,2,1], [2,3,3], [5,2,2], [1,4,2], [5,3,1], [5,4,2]]
     * K: 3
     * return: 4
     *
     * 설명:
     * 1번 마을에서 각 마을까지의 최단거리:
     * - 1→1: 0 ≤ 3 ✅
     * - 1→2: 1 ≤ 3 ✅
     * - 1→3: 4 > 3 ❌
     * - 1→4: 2 ≤ 3 ✅
     * - 1→5: 3 ≤ 3 ✅
     *
     * 배달 가능: 4개 마을 (1,2,4,5)
     *       (1)    (3)
     *     1 --- 2 --- 3
     *  (2)|  (2)|   / (1)
     *     |     | /
     *     4 --- 5
     *       (2)
     *
     */

    @Test
    fun solution() {
        val N = 5
        val roads = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 3, 3),
            intArrayOf(5, 2, 2),
            intArrayOf(1, 4, 2),
            intArrayOf(5, 3, 1),
            intArrayOf(5, 4, 2)
        )
        val K = 3
        val expected = 4


        assertEquals(expected, dijkstra(N, roads, K))
    }

    private fun dijkstra(N: Int, roads: Array<IntArray>, K: Int): Int {
        val graph = (0..N).associateBy({ it }) { mutableListOf<Pair<Int, Int>>() }
        val scoreBoard = IntArray(N + 1) { if (it == 1) 0 else Int.MAX_VALUE }
        for (road in roads) {
            val (from, to, score) = road
            graph[from]?.add(to to score)
            graph[to]?.add(from to score)
        }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(1 to 0)

        while (pq.isNotEmpty()) {
            val (current, currentScore) = pq.poll()

            if (currentScore > scoreBoard[current]) continue

            graph[current]?.forEach { (next, weight) ->
                val newDist = weight + currentScore
                if (newDist < scoreBoard[next]) {
                    scoreBoard[next] = newDist
                    pq.offer(next to newDist)
                }
            }
        }


        return scoreBoard.count{ it <= K}
    }
}