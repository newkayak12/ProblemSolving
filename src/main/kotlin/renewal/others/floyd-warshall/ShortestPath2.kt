package renewal.others.`floyd-warshall`

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class ShortestPath2 {
    /**
     * 플로이드-워셜 기본 문제 2: 경로
     * 문제 설명
     * n개의 도시가 있고, 한 도시에서 출발하여 다른 도시에 도착하는 m개의 버스가 있다. 각 버스는 한 번 사용할 때 필요한 비용이 있다.
     * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
     * 입력
     *
     * n: 도시의 개수 (2 ≤ n ≤ 100)
     * buses: 버스 정보 [시작도시, 도착도시, 비용] 형태의 배열
     * 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
     *
     * 출력
     *
     * n×n 크기의 배열에서 i번째 줄의 j번째 숫자는 도시 i에서 j로 가는데 필요한 비용의 최솟값
     * i에서 j로 갈 수 없는 경우에는 0을 출력한다.
     *
     * 예시
     * 입력
     * n = 5
     * buses = [
     *   [1, 2, 2],
     *   [1, 3, 3],
     *   [1, 4, 1],
     *   [1, 5, 10],
     *   [2, 4, 2],
     *   [3, 4, 1],
     *   [3, 5, 1],
     *   [4, 5, 3],
     *   [3, 5, 10],  // 중복 경로 (더 비쌈)
     *   [3, 1, 8]    // 역방향
     * ]
     * 출력
     * [
     *   [0, 2, 3, 1, 4],
     *   [0, 0, 0, 2, 5],
     *   [8, 0, 0, 1, 1],
     *   [0, 0, 0, 0, 3],
     *   [0, 0, 0, 0, 0]
     * ]
     */

    @Test
    fun solution() {
        val n = 5
        val buses = arrayOf(
            intArrayOf(1, 2, 2),
            intArrayOf(1, 3, 3),
            intArrayOf(1, 4, 1),
            intArrayOf(1, 5, 10),
            intArrayOf(2, 4, 2),
            intArrayOf(3, 4, 1),
            intArrayOf(3, 5, 1),
            intArrayOf(4, 5, 3),
            intArrayOf(3, 5, 10),
            intArrayOf(3, 1, 8)
        )

        val expected = arrayOf(
            intArrayOf(0, 2, 3, 1, 4),
            intArrayOf(0, 0, 0, 2, 5),
            intArrayOf(8, 10, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 3),
            intArrayOf(0, 0, 0, 0, 0)
        )


        assertArrayEquals(expected, floydWarshall(n, buses))
    }

    private fun floydWarshall(n: Int, buses: Array<IntArray>): Array<IntArray> {
        val graph = Array(n) { outer -> IntArray(n) { inner -> if (outer == inner) 0 else Int.MAX_VALUE } }
        for (bus in buses) {
            val (a, b, cost) = bus
            graph[a - 1][b - 1] = minOf(cost, graph[a - 1][b - 1])
        }

        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (graph[i][k] == Int.MAX_VALUE || graph[k][j] == Int.MAX_VALUE) continue
                    graph[i][j] = minOf(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }


        return graph.map { it.map { element -> if (element == Int.MAX_VALUE) 0 else element }.toIntArray() }
            .toTypedArray()
    }
}