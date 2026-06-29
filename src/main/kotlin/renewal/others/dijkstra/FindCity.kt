package renewal.others.dijkstra

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class FindCity {
    /**
     * 다익스트라 응용 문제: 특정 거리의 도시 찾기
     * 문제 설명
     * 어떤 나라에는 N개의 도시가 있다. 그리고 각 도시는 M개의 단방향 도로로 연결되어 있다. 각 도로는 특정한 거리를 가지고 있다.
     * 특정한 도시 X에서 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 정확히 K인 모든 도시들의 번호를 오름차순으로 출력하는 프로그램을 작성하시오.
     * 제약사항
     *
     * 2 ≤ N ≤ 300,000 (도시의 개수)
     * 1 ≤ M ≤ 1,000,000 (도로의 개수)
     * 1 ≤ K ≤ 300,000 (거리)
     * 1 ≤ X ≤ N (출발 도시)
     *
     * 입출력 예
     * 입력:
     * N=4, M=4, K=2, X=1
     * roads = [[1,2,1], [1,3,1], [2,3,1], [2,4,1]]
     * 출력:
     * [4]  // 1번 도시에서 정확히 거리 2로 갈 수 있는 도시
     */

    @Test
    fun solution() {
        val N = 4
        val M = 4
        val K = 2
        val X = 1
        val roads = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 1),
            intArrayOf(2, 3, 1),
            intArrayOf(2, 4, 1)
        )
        val expected = intArrayOf(4)

        assertArrayEquals(expected, dijkstra(N, M, K, X, roads))
    }

    private fun dijkstra(N: Int, M: Int, K: Int, X: Int, roads: Array<IntArray>): IntArray {
        val graph = roads
            .groupBy { it[0] }
            .mapValues { it.value.map { array -> array[1] to array[2] } }
        val scoreBoard = IntArray(N + 1) { if (it == X) 0 else Int.MAX_VALUE }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        pq.offer(X to 0)

        while(pq.isNotEmpty()) {
            val (current, currentScore) = pq.poll()
            if(currentScore > scoreBoard[current]) continue

            graph[current]?.forEach { (next, weight) ->
                val nextScore = currentScore + weight
                if(nextScore < scoreBoard[next]) {
                    scoreBoard[next] = nextScore
                    pq.offer(next to nextScore)
                }
            }
        }

        val result = mutableListOf<Int>()
        for((index, value) in scoreBoard.withIndex()) {
            if(value == K) result.add(index)
        }
        return result.sorted().toIntArray()
    }
}