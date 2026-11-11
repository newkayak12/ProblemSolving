package renewal.others.`floyd-warshall`

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class ShortestPath {
    /**
     * 플로이드-워셜 기본 문제 1: 최단 경로 행렬
     * 문제 설명
     * 정점 n개와 간선 정보가 주어질 때, 모든 정점 쌍 간의 최단 거리를 구하는 프로그램을 작성하세요.
     * 입력
     *
     * n: 정점의 개수 (1 ≤ n ≤ 100)
     * edges: 간선 정보 [출발, 도착, 비용] 형태의 배열
     *
     * 출력
     *
     * 모든 정점 쌍 간의 최단 거리를 담은 2차원 배열
     * 경로가 없으면 Int.MAX_VALUE
     *
     * 예시
     * 입력:
     * n = 4
     * edges = [
     *   [0, 1, 5],
     *   [0, 3, 10],
     *   [1, 2, 3],
     *   [2, 3, 1]
     * ]
     *
     * 출력:
     * [
     *   [0, 5, 8, 9],
     *   [INF, 0, 3, 4],
     *   [INF, INF, 0, 1],
     *   [INF, INF, INF, 0]
     * ]
     *
     * 제한 사항
     * - 모든 간선의 가중치는 양수
     * - 자기 자신으로 가는 거리는 0
     * - 갈 수 없는 경우 Int.MAX_VALUE
     */

    @Test
    fun solution() {
        val n = 4
        val edges = arrayOf(
            intArrayOf(0, 1, 5),
            intArrayOf(0, 3, 10),
            intArrayOf(1, 2, 3),
            intArrayOf(2, 3, 1)
        )
        val expected = arrayOf(
            intArrayOf(0, 5, 8, 9),
            intArrayOf(Int.MAX_VALUE, 0, 3, 4),
            intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, 0, 1),
            intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, 0)
        )

        assertArrayEquals(expected, floydWarshall(n, edges))
    }

    private fun floydWarshall(n: Int, edges: Array<IntArray>): Array<IntArray> {
        val graph = Array(n) { outer -> IntArray(n) { inner -> if (outer == inner) 0 else Int.MAX_VALUE } }

        for (edge in edges) {
            val (a, b, cost) = edge
            graph[a][b] = cost
//            graph[b][a] = cost
        }

        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (graph[i][k] == Int.MAX_VALUE || graph[k][j] == Int.MAX_VALUE) continue
                    graph[i][j] = minOf(graph[i][j], graph[i][k] + graph[k][j])
                }
            }
        }

        return graph
    }
}