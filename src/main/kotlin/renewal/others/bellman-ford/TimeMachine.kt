package renewal.others.`bellman-ford`

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TimeMachine {
    /**
     * 문제: 타임머신
     * 문제 설명
     * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다.
     * 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다.
     * 시간 C가 양수가 아닌 경우가 있다. C = 0인 경우는 순간이동을 하는 경우이고, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.
     * 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.
     * 제약사항
     *
     * 2 ≤ N ≤ 500 (도시 개수)
     * 1 ≤ M ≤ 6,000 (버스 개수)
     * 만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다.
     *
     * 입출력 예
     * 입력:
     * N=3, M=4, edges=[[1,2,4], [1,3,3], [2,3,-1], [3,1,-2]]
     * 출력:
     * -1  // 음수 사이클 존재
     */

    @Test
    fun solution1() {
        val N = 3
        val M = 3
        val edges = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 3, -2),
            intArrayOf(3, 1, -2),
        )
        val expected = -1

        assertEquals(expected, bellmanFord(N, M, edges))
    }

    @Test
    fun solution2() {
        val N = 3
        val M = 3
        val edges = arrayOf(
            intArrayOf(1, 2, 4),
            intArrayOf(1, 3, 3),
            intArrayOf(2, 3, -4),
        )
        val expected = 0

        assertEquals(expected, bellmanFord(N, M, edges))
    }

    private fun bellmanFord(N: Int, M: Int, edges: Array<IntArray>): Int {
        val start = 1
        val dist = IntArray(N + 1) {
            if (it == start) 0
            else Int.MAX_VALUE
        }

        for (index in 0..<N - 1) {
            for (edge in edges) {
                val (from, to, score) = edge

                if (dist[from] != Int.MAX_VALUE && dist[from] + score < dist[to]) {
                    dist[to] = dist[from] + score
                }
            }
        }

        for (edge in edges) {
            val (from, to, score) = edge
            if (dist[from] != Int.MAX_VALUE && dist[from] + score < dist[to]) {
                return -1
            }
        }

        return dist.slice(2..<dist.size).minOf { it }
    }
}