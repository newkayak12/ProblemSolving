package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class Network {
    /**
     * 네트워크 (프로그래머스)
     * 문제 설명
     * n개의 컴퓨터가 있습니다. 몇 개의 컴퓨터는 서로 네트워크를 통해 연결되어 있습니다.
     * 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어 있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 있다고 할 수 있습니다.
     * 모든 컴퓨터를 연결하는 데 필요한 최소 비용을 구하려고 합니다.
     * 입출력 예
     * n: 4
     * costs: [[0,1,1], [0,2,2], [1,2,5], [1,3,1], [2,3,8]]
     * return: 4
     *
     * 설명:
     * - [0,1]: 비용 1
     * - [0,2]: 비용 2
     * - [1,3]: 비용 1
     * 총 비용: 1 + 2 + 1 = 4로 모든 컴퓨터 연결
     *
     */


    @Test
    fun solution() {
        val n = 4
        val costs = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 2, 2),
            intArrayOf(1, 2, 5),
            intArrayOf(1, 3, 1),
            intArrayOf(2, 3, 8)
        )

        val expected = 4

        assertEquals(expected, pq(n, costs))
    }

    private fun pq(n: Int, costs: Array<IntArray>): Int {
        val pq = PriorityQueue<IntArray>(compareBy { it[2] })
        for (cost in costs) {
            pq.offer(cost)
        }

        var totalCost = 0
        var edgeCount = 0

        val disjointUnion = DisjointSetUnion(n)
        while (pq.isNotEmpty() && edgeCount < n - 1) {
            val (from, to, cost) = pq.poll()

            if(disjointUnion.union(from, to)) {
                totalCost += cost
                edgeCount ++
            }
        }

        return totalCost
    }

    inner class DisjointSetUnion(size: Int) {
        private val parent = IntArray(size + 1) { it }
        private val rank = IntArray(size + 1)

        fun find(x: Int): Int {
            if (x != parent[x]) {
                parent[x] = find(parent[x])
            }

            return parent[x]
        }

        fun union(a: Int, b: Int): Boolean {
            val rootA = find(a)
            val rootB = find(b)

            val rankA = rank[rootA]
            val rankB = rank[rootB]

            if (rootA == rootB) return false

            if (rankA < rankB) {
                parent[rootA] = rootB
            } else if (rankA > rankB) {
                parent[rootB] = rootA
            } else {
                parent[rootB] = rootA
                rank[rootA]++
            }

            return true
        }
    }
}