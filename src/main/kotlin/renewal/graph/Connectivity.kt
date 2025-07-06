package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Stack
import kotlin.system.measureTimeMillis

class Connectivity {
    /**
     * 문제 1. 연결 요소의 개수
     *
     * 설명
     *
     * 하나의 무방향 그래프가 주어졌을 때, 이 그래프에서 연결 요소가 몇 개인지 구하세요.
     * 연결 요소란, 노드들끼리 서로 도달 가능한 그룹을 말합니다.
     *
     * 입력
     * 	•	첫 줄에 정점의 개수 N (1 ≤ N ≤ 1,000)과 간선의 개수 M (0 ≤ M ≤ N×(N-1)/2)이 주어집니다.
     * 	•	다음 줄부터 M개의 줄에 걸쳐 두 정점 u, v가 주어집니다. 이는 정점 u와 v가 연결되어 있다는 의미입니다.
     *
     * 출력
     * 	•	연결 요소의 개수를 한 줄에 출력하세요.
     *
     * 예시 입력
     * 6 5
     *
     * 1 2
     * 2 5
     * 5 1
     * 3 4
     * 4 6
     *
     * 예시 출력
     * 2
     *
     *
     * 1. 정점
     * [1, 2, 3, 4, 5, 6]
     * 2. 간선
     * [(1,2), (2,5), (5,1), (3,4), (4,6)]
     *
     * 3. 방법?
     * 1. DFS/BFS -> DFS (모든 노드를 돌면서 확인)
     *
     * 2. DisjointSetUnion
     */

    @Test
    fun solution() {
        val vertex = 6
        val edge = 5

        val table = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 5),
            intArrayOf(5, 1),
            intArrayOf(3, 4),
            intArrayOf(4, 6),
        )
        val expected = 2
        val repeatCount = 10_000

        val stack = measureTimeMillis {
            repeat(repeatCount) {
                assertEquals(expected, useStack(vertex, edge, table))
            }
        }
        val recursive = measureTimeMillis {
            repeat(repeatCount) {
                assertEquals(expected, useRecursive(vertex, edge, table))
            }
        }
        val disjointSetUnion = measureTimeMillis {
            repeat(repeatCount) {
                assertEquals(expected, useDisjointSetUnion(vertex, edge, table))
            }
        }


        println("""
    ArrayDeque total: $stack ms
    Recursive total: $recursive ms
    DSU total: $disjointSetUnion ms
    (per call: stack=${stack / repeatCount}ms, recursive=${recursive / repeatCount}ms, dsu=${disjointSetUnion / repeatCount}ms)
""".trimIndent())
    }

    private fun useStack(vertex: Int, edge: Int, table: Array<IntArray>): Int {
        val graph = Array(vertex + 1) { mutableListOf<Int>() }
        for (element in table) {
            val u = element[0]
            val v = element[1]

            graph[u].add(v)
            graph[v].add(u)
        }

        val stack = ArrayDeque<Int>()
        val visited = BooleanArray(vertex + 1)
        var count = 0


        for (i in 1..vertex) {
            if (!visited[i]) {
                stack.addLast(i)
                visited[i] = true
                count += 1
            }

            while (stack.isNotEmpty()) {
                val node = stack.removeLast()
                for (next in graph[node].reversed()) {
                    if (!visited[next]) {
                        stack.addLast(next)
                        visited[next] = true
                    }
                }
            }
        }

        return count
    }

    private fun useRecursive(vertex: Int, edge: Int, table: Array<IntArray>): Int {
        val graph = Array(vertex + 1) { mutableListOf<Int>() }
        for (element in table) {
            val u = element[0]
            val v = element[1]

            graph[u].add(v)
            graph[v].add(u)
        }

        val visited = BooleanArray(vertex + 1)
        var count = 0


        for (i in 1..vertex) {
            if (!visited[i]) {
                count++
                recursive(i, graph, visited)
            }
        }

        return count
    }

    private fun recursive(node: Int, graph: Array<MutableList<Int>>, visited: BooleanArray) {
        visited[node] = true

        for (next in graph[node].reversed()) {
            if (!visited[next]) {
                recursive(next, graph, visited)
            }
        }
    }

    private fun useDisjointSetUnion(vertex: Int, edge: Int, table: Array<IntArray>): Int {
        val dsu = DisjointSetUnion(vertex + 1)
        for( (a,b) in table) { dsu.union(a,b) }


        val rootSet = mutableSetOf<Int>()
        for ( node in 1 .. vertex) {
            rootSet.add(dsu.find(node))
        }

        return rootSet.size
    }

    inner class DisjointSetUnion(size: Int) {
        private val parents = IntArray(size) { it }
        private val ranking = IntArray(size) { 0 }


        fun find(x: Int): Int {
            if (x != parents[x]) {
                parents[x] = find(parents[x])
            }
            return parents[x]
        }

        fun union(a: Int, b: Int): Boolean {

            val rootA = find(a)
            val rootB = find(b)

            if (rootA == rootB) return false

            if( rootA > rootB ) {
                parents[rootB] = rootA
            }
            else if( rootA < rootB) {
                parents[rootA] = rootB
            }
            else {
                parents[rootB] = rootA
                ranking[rootA] ++
            }

            return true
        }
    }
}