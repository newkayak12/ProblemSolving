package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KruskalExample2 {
    /**
     *```
     * 📘 문제: 도시 분할 계획 (백준 1647 변형)
     *
     * 어떤 나라에는 N개의 집이 있다. 이 집들을 연결하는 M개의 길이 있고, 각 길은 유지비가 있다.
     * 이 나라의 수도는 너무 넓어지지 않도록 마을을 두 개로 분리하려 한다.
     * 조건은 다음과 같다:
     * 	•	모든 집은 두 마을 중 하나에 포함되어야 한다.
     * 	•	각 마을 안에 있는 집들은 모두 서로 연결되어야 한다.
     * 	•	마을 간에는 길이 없어야 한다.
     * 	•	두 마을로 나누되, 유지비의 총합이 최소가 되어야 한다.
     *
     * 🧾 입력 형식
     * 	•	첫 줄에 집의 수 N, 길의 수 M이 주어진다. (2 ≤ N ≤ 100,000, 1 ≤ M ≤ 1,000,000)
     * 	•	둘째 줄부터 M개의 줄에 걸쳐 A B C가 주어진다.
     * A번 집과 B번 집 사이에 유지비가 C인 길이 존재한다. (1 ≤ C ≤ 1,000)
     *
     * 🧾 출력 형식
     * 	•	두 마을로 나눌 때의 최소 유지비 총합을 출력한다.
     *
     * ⸻
     *
     * 예제 입력
     * 7 12
     * 1 2 3
     * 1 3 2
     * 3 2 1
     * 2 5 2
     * 3 4 4
     * 7 3 6
     * 5 1 5
     * 1 6 2
     * 6 4 1
     * 6 5 3
     * 4 5 3
     * 6 7 4
     *
     * 예제 출력
     * 8
     * ```
     */

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

            if (rootA == rootB) return false

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA
            } else {
                parent[rootB] = rootA
                rank[rootA]++
            }

            return true
        }
    }

    @Test
    fun solution() {
        val table = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(1, 3, 2),
            intArrayOf(3, 2, 1),
            intArrayOf(2, 5, 2),
            intArrayOf(3, 4, 4),
            intArrayOf(7, 3, 6),
            intArrayOf(5, 1, 5),
            intArrayOf(1, 6, 2),
            intArrayOf(6, 4, 1),
            intArrayOf(6, 5, 3),
            intArrayOf(4, 5, 3),
            intArrayOf(6, 7, 4),
        )
        val expected = 8

        assertEquals(expected, kruskal(table))
    }

    private fun kruskal(table: Array<IntArray>): Int {
        val count = table.flatMap { intArrayOf(it[0], it[1]).asSequence() }.distinct().count()
        table.sortBy { it[2] }

        val unionFind = DisjointSetUnion(count)

        var totalScore = 0
        var max = Int.MIN_VALUE

        for ((a, b, score) in table) {
            if( unionFind.union(a, b)) {
                totalScore += score
                max = maxOf(score, max)
            }
        }

        return totalScore - max
    }
}