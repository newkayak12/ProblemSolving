package renewal.disjoinunionset

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Kruskal {
    /**
     * 문제: 최소 스패닝 트리 (크루스칼)
     * 문제 설명
     * V개의 정점과 E개의 간선으로 이루어진 무방향 가중치 그래프가 주어질 때, 최소 스패닝 트리의 가중치 합을 구하는 프로그램을 작성하세요.
     * 제약사항
     *
     * 1 ≤ V ≤ 10,000
     * 1 ≤ E ≤ 100,000
     * 간선의 가중치는 1 이상 1,000,000 이하
     *
     * 입출력 예
     * 입력:
     * V=4, edges=[[1,2,1],[1,3,3],[2,3,2],[2,4,4],[3,4,5]]
     * 출력:
     * 7
     */

    @Test
    fun solution() {
        val V = 4
        val edges = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 3),
            intArrayOf(2, 3, 2),
            intArrayOf(2, 4, 4),
            intArrayOf(3, 4, 5)
        )
        val expected = 7

        assertEquals(expected, kruskal(V, edges))
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

    private fun kruskal(V: Int, edges: Array<IntArray>): Int {
        val unionSet = DisjointUnionSet(V)
        val list: List<IntArray> = edges.sortedBy { it[2] }
        var score = 0

        for (edge in list) {
            val (a, b, c) = edge
            if(unionSet.union(a, b)) {
                score += c
            }
        }

        return score
    }
}