package renewal.disjoinunionset

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CycleDetector {
    /**
     * 문제: 사이클 탐지
     * 문제 설명
     * 무방향 그래프가 주어졌을 때, 그래프에 사이클이 존재하는지 판별하는 함수를 작성하세요.
     * 제약사항
     *
     * 1 ≤ n ≤ 1,000 (정점의 개수)
     * 0 ≤ edges.length ≤ 10,000 (간선의 개수)
     * edges[i] = [u, v]는 정점 u와 v 사이에 간선이 있음을 의미
     *
     * 입출력 예
     *
     * |n |edges| return|
     * |:---:|:---:|:---:|
     * |4|[[0,1],[1,2],[2,3]]|false|
     * |4|[[0,1],[1,2],[2,3],[3,0]]|true|
     * |3|[[0,1],[1,2],[0,2]]|true|
     */

    @Test
    fun solution1() {
        val n = 4
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 3)
        )
        val expected = false
        assertEquals(expected, hasCycle(n, edges))
    }

    @Test
    fun solution2() {
        val n = 4
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 0)
        )
        val expected = true
        assertEquals(expected, hasCycle(n, edges))
    }

    @Test
    fun solution3() {
        val n = 3
        val edges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(0, 2)
        )
        val expected = true
        assertEquals(expected, hasCycle(n, edges))
    }


    private fun hasCycle(n: Int, edges: Array<IntArray>): Boolean {
        val unionSet = DisjointUnionSet(n)

        for(edge in edges) {
            val (a, b) = edge
            if(!unionSet.union(a, b)) return true
        }


        return false
    }

    private inner class DisjointUnionSet(size: Int) {
        val parent = IntArray(size + 1) { it }
        val rank = IntArray(size + 1) { 0 }

        fun find(x: Int): Int {
            if (x != parent[x]) {
                parent[x] = find(parent[x])
            }

            return parent[x]
        }

        fun union(a: Int, b: Int): Boolean {
            val rootA = find(a)
            val rootB = find(b)

            if (rootA == rootB) return false
            when {
                rank[rootA] > rank[rootB] -> parent[rootB] = rootA
                rank[rootA] < rank[rootB] -> parent[rootA] = rootB
                else -> {
                    parent[rootB] = rootA
                    rank[rootA]++
                }
            }

            return true
        }
    }
}