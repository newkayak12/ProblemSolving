package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Virus {
    /**
     *<pre>
     * 🧩 문제 3. 바이러스
     *
     * 설명
     * 1번 컴퓨터가 바이러스에 걸렸을 때, 1번을 통해 바이러스에 걸리는 컴퓨터의 수를 구하세요.
     *
     * 입력
     * 	•	첫 줄에 컴퓨터의 수 N (2 ≤ N ≤ 100)이 주어집니다.
     * 	•	다음 줄에 간선의 수 M이 주어집니다.
     * 	•	그 다음 M줄에는 연결된 두 컴퓨터의 번호가 한 줄에 하나씩 주어집니다.
     *
     * 출력
     * 	•	1번 컴퓨터를 통해 바이러스에 걸리는 컴퓨터 수를 출력하세요. (1번 제외)
     *
     * 예시 입력
     * 7
     * 6
     * 1 2
     * 2 3
     * 1 5
     * 5 2
     * 5 6
     * 4 7
     * 예시 출력
     * 4
     * </pre>
     */

    @Test
    fun solution() {
        val vertex = 7
        val edge = 6
        val table = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(1, 5),
            intArrayOf(5, 2),
            intArrayOf(5, 6),
            intArrayOf(4, 7),
        )

        val expected = 4

        assertEquals(expected, dfsWithStack(vertex, table))
    }

    private fun createGraph(vertex: Int, table: Array<IntArray>): Array<MutableList<Int>> {
        val graph = Array(vertex + 1) { mutableListOf<Int>() }
        table.sortedWith(compareBy({ it[0] }, { it[1] }))

        for (element in table) {
            val u = element.first()
            val v = element.last()

            graph[u].add(v)
            graph[v].add(u)
        }

        for (element in graph) {
            element.sort()
        }
        return graph
    }

    private fun createVisited(vertex: Int): BooleanArray = BooleanArray(vertex + 1)

    private fun dfsWithStack(vertex: Int, table: Array<IntArray>): Int {
        val graph = createGraph(vertex, table)
        val visited = createVisited(vertex)
        val stack = ArrayDeque<Int>()

        visited[1] = true
        stack.addLast(1)
        var count = 0

        while (stack.isNotEmpty()) {
            val now = stack.removeLast()
            count += 1


            for (next in graph[now]) {
                if (!visited[next]) {
                    visited[next] = true
                    stack.addLast(next)
                }
            }
        }

        return count - 1
    }
}