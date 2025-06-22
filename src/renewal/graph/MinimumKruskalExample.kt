package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MinimumKruskalExample {
    /**
     * ```
     * 📘 문제: 최소 회의 연결 (예산 제한 MST 변형)
     *
     * 회사에는 N개의 부서가 있으며, 일부 부서는 이미 연결된 회의실이 존재합니다.
     * 이제 새로운 회의 네트워크를 구축하려고 합니다.
     *
     * 조건:
     * 	•	모든 부서는 최소한 하나의 다른 부서와 연결되어야 합니다.
     * 	•	단, 총 연결 비용이 C를 넘지 않아야 합니다.
     * 	•	가능한 많은 부서를 연결했을 때, 연결된 부서의 수를 출력하세요.
     *
     * ⸻
     * ✅ 입력 형식
     * N M C
     * A1 B1 W1
     * A2 B2 W2
     * ...
     * AM BM WM
     *
     * 	•	N: 부서 수 (2 ≤ N ≤ 100,000)
     * 	•	M: 연결 가능한 경로 수 (1 ≤ M ≤ 200,000)
     * 	•	C: 연결 예산 (1 ≤ C ≤ 1,000,000,000)
     * 	•	각 A B W: A번 부서와 B번 부서를 연결하는 비용 W (1 ≤ W ≤ 1,000,000)
     *
     * ⸻
     * ✅ 출력 형식
     * 	•	예산 내에서 연결할 수 있는 부서의 최대 수를 출력 (연결되지 않은 부서는 제외)
     *
     * ⸻
     *
     * 🧪 예제 입력
     * 7 9 15
     * 1 2 3
     * 1 3 2
     * 2 3 1
     * 2 4 4
     * 3 5 6
     * 5 6 2
     * 6 7 1
     * 1 7 8
     * 4 6 7
     * 🧾 예제 출력
     * 6
     * ```
     */

    @Test
    fun solution() {
        val countOfDepartment = 7
        val availableConnection = 9
        val budget = 15

        val table = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 1),
            intArrayOf(2, 4, 4),
            intArrayOf(3, 5, 6),
            intArrayOf(5, 6, 2),
            intArrayOf(6, 7, 1),
            intArrayOf(1, 7, 8),
            intArrayOf(4, 6, 7),
        )

        val expected = 6

        assertEquals(expected, kruskal(countOfDepartment, availableConnection, budget, table))
    }
    private fun kruskal(
        countOfDepartment: Int,
        availableConnection: Int,
        availableBudget: Int,
        table: Array<IntArray>
    ): Int {

        val unionFind = DisjointSetUnion(countOfDepartment)
        var resultCount = 0
        var connection = availableConnection
        var budget = availableBudget
        table.sortBy { it[2] }

        for((a,b,score) in table) {

            if( connection > 0 && budget >= score && unionFind.union(a, b)) {
                connection --
                budget -= score
                resultCount ++
            }
        }

        return resultCount + 1 //간선 + 1 = 노드
    }

    inner class DisjointSetUnion(size: Int) {
        private val parent = IntArray(size + 1){it}
        private val rank = IntArray(size + 1)

        fun find(x: Int): Int {
            if(x != parent[x]) {
                parent[x] = find(parent[x])
            }

            return parent[x]
        }

        fun union(a: Int, b: Int ): Boolean {
            val rootA = find(a)
            val rootB = find(b)
            val rankA = rank[rootA]
            val rankB = rank[rootB]

            if(rootA==rootB) return false

            if( rankA < rankB) {
                parent[rootA] = rootB
            }
            else if (rankA > rankB) {
                parent[rootB] = rootA
            }
            else {
                parent[rootB] = rootA
                rank[rootA]++
            }

            return true
        }
    }


}