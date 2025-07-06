package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChangeConnection {
    /**
     * 문제: 체인 연결 (Chain Connection)
     *
     * ⸻
     *
     * 문제 설명
     * 	•	여러 개의 체인이 있습니다.
     * 	•	각 체인은 양쪽 끝에 숫자가 적혀 있습니다.
     * 	•	체인을 최대한 길게 연결하려고 할 때, 연결 조건은 체인의 오른쪽 숫자와 다음 체인의 왼쪽 숫자가 같아야 합니다.
     * 	•	체인을 적절히 재배치하거나 뒤집을 수 있습니다.
     * 	•	최대 몇 개의 체인을 연결할 수 있는지 구하세요.
     *
     * ⸻
     *
     * 입력
     * 	•	첫 줄에 체인의 개수 N (1 ≤ N ≤ 100,000)
     * 	•	다음 N줄에 각각 체인 한 개의 왼쪽 숫자와 오른쪽 숫자 (1 ≤ 숫자 ≤ 1,000,000)
     *
     * ⸻
     *
     * 출력
     * 	•	최대 연결할 수 있는 체인의 개수를 출력하세요.
     *
     * ⸻
     *
     * 예제 입력
     * 5
     * 1 2
     * 2 3
     * 3 4
     * 5 1
     * 4 5
     *
     * 예제 출력
     * 5
     */

    @Test
    fun solution()  {
        val table = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(5, 1),
            intArrayOf(4, 5),
        )
        val expected = 5

        assertEquals(expected, greedy(table))
    }

    fun greedy(table: Array<IntArray>): Int {
        val dsu = DisjointUnion((table.flatMap { listOf(it[0], it[1]) }.maxOrNull() ?: 0) + 1)

        for((first, second) in table) {
            dsu.union(first, second)
        }


        var max = Int.MIN_VALUE
        val sizeMap = mutableMapOf<Int, Int>()
        for((first, _) in table) {
            val root = dsu.find(first)

            sizeMap[root] = (sizeMap[root] ?: 0)+ 1
            max = maxOf(max, sizeMap[root]!!)
        }

        println(sizeMap)


        return max
    }

    inner class DisjointUnion(size: Int) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size) { 0 }


        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }

            return parent[x]
        }

        fun union(a: Int, b: Int): Boolean {
            val rootA = find(a)
            val rootB = find(b)

            if (rootA == rootB) return false

            if( rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB
            }
            else if(rank[rootA] > rank[rootB]){
                parent[rootB] = rootA
            }
            else {
                parent[rootB] = rootA
                rank[rootA] ++
            }

            return true
        }
    }
}