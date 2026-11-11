package renewal.others.dijkstra

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class ShortestPath {
    /**
     * 문제: 최단경로
     * 문제 설명
     * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.
     * 제약사항
     *
     * 1 ≤ V ≤ 20,000 (정점의 개수)
     * 1 ≤ E ≤ 300,000 (간선의 개수)
     * 1 ≤ start ≤ V (시작 정점)
     * 모든 가중치는 10 이하의 자연수
     *
     * 입출력 예
     * 입력:
     * V=5, E=6, start=1
     * edges = [
     *   [1,2,2],
     *   [1,3,3],
     *   [1,4,1],
     *   [1,5,10],
     *   [2,4,2],
     *   [3,4,1]
     * ]
     * 출력:
     * [0, 2, 3, 1, 10]  // 1번부터 각 정점까지의 최단거리
     */


    @Test
    fun solution() {
        val V = 5
        val E = 6
        val start = 1
        val edges = arrayOf(
            intArrayOf(1, 2, 2),
            intArrayOf(1, 3, 3),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 5, 10),
            intArrayOf(2, 4, 2),
            intArrayOf(3, 4, 1),
        )

        val expected = intArrayOf(0, 2, 3, 1, 10)


        assertArrayEquals(expected, dijkstra(V, E, start, edges))
    }

    private fun dijkstra(V: Int, E: Int, start: Int, edges: Array<IntArray>): IntArray {
        val graph: Map<Int, List<Pair<Int, Int>>> = edges
            .groupBy { it[0] }
            .mapValues { it.value.map { array -> array[1] to array[2] } }
        val scoreBoard = IntArray(V + 1) { if (it == start) 0 else Int.MAX_VALUE }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.offer(start to 0)

        while (pq.isNotEmpty()) {
            val (current, currentScore) = pq.poll()

            if (currentScore > scoreBoard[current]) continue
            graph[current]?.forEach { (next, weight) ->
                val newDistance = weight + currentScore
                if (newDistance < scoreBoard[next]) {
                    scoreBoard[next] = newDistance
                    pq.offer(next to newDistance)
                }
            }
        }


        return scoreBoard.sliceArray(1 until scoreBoard.size)
    }
}

