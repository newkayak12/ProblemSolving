package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.LinkedBlockingQueue

class Traverse {
    /**
     * 🧩 문제 2. DFS와 BFS 순서 출력
     *
     * 설명
     *
     * 하나의 무방향 그래프가 주어졌을 때,
     * 특정한 정점에서 DFS와 BFS를 수행한 결과의 노드 방문 순서를 각각 출력하세요.
     * 단, 방문할 수 있는 노드가 여러 개인 경우, 정점 번호가 작은 것부터 먼저 방문합니다.
     *
     * ⸻
     *
     * 입력
     * 	•	첫 줄에 정점 수 N, 간선 수 M, 시작 정점 V가 주어집니다.
     * 	•	다음 M개의 줄에는 간선 정보 u v가 주어집니다.
     * (u와 v는 양방향으로 연결됨)
     *
     * 예시 입력:
     * 4 5 1
     *
     * 1 2
     * 1 3
     * 1 4
     * 2 4
     * 3 4
     *
     * 출력
     * 	•	첫 줄: DFS 방문 순서
     * 	•	둘째 줄: BFS 방문 순서
     * 	예시 출력:
     * 1 2 4 3
     * 1 2 3 4
     */

    /**
     * 기본 사이즈
     */
    @Test
    fun solution1() {
        val vertex = 4
        val edge = 5
        val start = 1

        val table = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 4),
            intArrayOf(3, 4),
        )

        val dfsExpected = "1 2 4 3"
        val bfsExpected = "1 2 3 4"

        assertEquals(dfsExpected, dfsUseStack(vertex, edge, start, table))
        assertEquals(dfsExpected, dfsUseRecursive(vertex, edge, start, table))
        assertEquals(bfsExpected, bfsUseQueue(vertex, edge, start, table))
    }

    /**
     * 더 큰 사이즈
     */
    @Test
    fun solution2() {
        /**
         * 정점 수: 10
         * 간선 수: 9
         * 시작 정점: 1
         * 간선 목록:
         * 1 2
         * 1 3
         * 2 4
         * 2 5
         * 5 6
         * 3 7
         * 3 8
         * 8 9
         * 9 10
         *
         * 시각화
         *          1
         *        /   \
         *       2     3
         *      / \     \
         *     4   5     8
         *          \     \
         *           6     9
         *                  \
         *                  10
         */
        val vertex = 10
        val edge = 9
        val start = 1

        val table = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
            intArrayOf(2, 5),
            intArrayOf(5, 6),
            intArrayOf(3, 7),
            intArrayOf(3, 8),
            intArrayOf(8, 9),
            intArrayOf(9, 10),
        )

        val dfsExpected = "1 2 4 5 6 3 7 8 9 10"
        val bfsExpected = "1 2 3 4 5 7 8 6 9 10"

        assertEquals(dfsExpected, dfsUseStack(vertex, edge, start, table))
        assertEquals(dfsExpected, dfsUseRecursive(vertex, edge, start, table))
        assertEquals(bfsExpected, bfsUseQueue(vertex, edge, start, table))
    }

    /**
     * 비연결 그래프
     */
    @Test
    fun solution3() {
        val vertex = 8
        val edge = 5
        val start = 1

        val table = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(4, 5),
            intArrayOf(6, 7),
            intArrayOf(7, 8),
        )

        val dfsExpected = "1 2 3"
        val bfsExpected = "1 2 3"

        assertEquals(dfsExpected, dfsUseStack(vertex, edge, start, table))
        assertEquals(dfsExpected, dfsUseRecursive(vertex, edge, start, table))
        assertEquals(bfsExpected, bfsUseQueue(vertex, edge, start, table))
    }

    private fun createGraph(vertex: Int, table: Array<IntArray>): Array<MutableList<Int>> {
        val graph = Array(vertex + 1) { mutableListOf<Int>() }
        for (element in table.sortedBy { it[1] }) {
            val u = element.first()
            val v = element.last()

            graph[u].add(v)
            graph[v].add(u)
        }

        for (adj in graph) {
            adj.sort()
        }

        return graph
    }

    private fun createVisit(vertex: Int) = BooleanArray(vertex + 1)

    private fun dfsUseStack(vertex: Int, edge: Int, start: Int, table: Array<IntArray>): String {
        val graph = createGraph(vertex, table)
        val visited = createVisit(vertex)
        val stack = ArrayDeque<Int>()
        val builder = StringBuilder()
        stack.add(start)

        while (stack.isNotEmpty()) {
            val now = stack.removeLast()
            if(visited[now]) continue

            visited[now] = true
            builder.append("$now ")

            for (next in graph[now].reversed()) {
                if (!visited[next]) {
                    stack.addLast(next)
                }
            }
        }

        return builder.toString().trim()
    }

    private fun dfsUseRecursive(vertex: Int, edge: Int, start: Int, table: Array<IntArray>): String {
        val graph = createGraph(vertex, table)
        val visited = createVisit(vertex)
        val builder = StringBuilder()
        recursive(start, graph, visited, builder)

        return builder.toString().trim()
    }

    private fun recursive(node: Int, graph: Array<MutableList<Int>>, visited: BooleanArray, builder: StringBuilder) {
        if (visited[node]) return

        visited[node] = true
        builder.append("$node ")

        for (next in graph[node]) {
            recursive(next, graph, visited, builder)
        }
    }

    private fun bfsUseQueue(vertex: Int, edge: Int, start: Int, table: Array<IntArray>): String {
        val graph = createGraph(vertex, table)
        val visited = createVisit(vertex)
        val builder = StringBuilder()
        val queue = LinkedBlockingQueue<Int>()
        queue.add(start)

        while(queue.isNotEmpty()) {
            val now = queue.poll()

            if(visited[now]) continue
            builder.append("$now ")
            visited[now] = true

            for(next in graph[now]) {
                queue.add(next)
            }
        }


        return builder.toString().trim()
    }
}