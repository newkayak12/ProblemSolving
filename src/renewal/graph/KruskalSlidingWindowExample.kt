package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.Int.Companion

class KruskalSlidingWindowExample {
    /**
     * 📘 문제: 공장 전력망 구축 (가중치 범위 제한 MST)
     *
     * 일부 케이블은 전력 품질 문제가 있어 너무 높은 전압과 너무 낮은 전압의 차이가 크면 과부하가 발생합니다.
     * 전선의 전압 차이 범위를 최소화하면서 모든 공장을 연결해야 합니다.
     *
     * ⸻
     *
     * ✅ 문제 설명
     * 	•	N개의 공장이 있고, M개의 전선으로 연결 가능하다.
     * 	•	각 전선은 두 공장을 잇고, 전압 값이 주어진다.
     * 	•	MST를 구성하되, 사용된 전선들 중 최대 전압 - 최소 전압의 차이를 최소화해야 한다.
     *
     * ⸻
     *
     * ✅ 입력 형식
     * N M
     * A1 B1 V1
     * ...
     * AM BM VM
     * 	•	N: 공장 수 (2 ≤ N ≤ 1000)
     * 	•	M: 전선 수 (1 ≤ 10,000)
     * 	•	Ai, Bi: 연결되는 공장 번호
     * 	•	Vi: 전선의 전압 (1 ≤ Vi ≤ 100,000)
     * 	✅ 출력 형식
     * 	•	MST를 구성할 때 사용된 전선 중에서 최대 전압 - 최소 전압의 최소값을 출력한다.
     * 	•	MST 구성 불가능하면 -1 출력
     *
     * ⸻
     *
     * 🧪 예제 입력
     * 5 7
     * 1 2 3
     * 2 4 4
     * 2 3 6
     * 3 4 7
     * 4 5 8
     * 3 5 9
     * 1 3 10
     * 🧾 예제 출력
     * 5
     *
     *  •	2-3(6)
     * 	•	3-4(7)
     * 	•	4-5(8)
     * 	•	1-3(10)
     *
     * → 전압 차이: 10 - 6 = 4
     *
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
            val rankA = rank[rootA]
            val rankB = rank[rootB]

            if (rootA == rootB) return false
            if (rankB > rankA) {
                rank[rootA] = rootB
            } else if (rankA < rankB) {
                rank[rootB] = rootA
            } else {
                rank[rootB] = rootA
                rank[rootA]++
            }
            return true
        }
    }

    @Test
    fun solution() {
        val factory = 5
        val lane = 7
        val table = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(1, 3, 10),
            intArrayOf(2, 3, 6),
            intArrayOf(2, 4, 4),
            intArrayOf(3, 4, 7),
            intArrayOf(4, 5, 8),
            intArrayOf(3, 5, 9),
        )
        val expected = 4

        assertEquals(expected, kruskal(factory, lane, table))
    }

    private fun kruskal(factory: Int, lane: Int, table: Array<IntArray>): Int {
        table.sortBy { it[2] }

        /**
         * 1. DSU를 전체로 두고 구하면 최적해를 구할 수 없는 구조다.
         * 2. 일정 거리를 두고 table을 범위로 순회하면서 MST를 구성한다.
         *      1. MST 구성 여부를 체크해야 한다. -> 어떻게 체크할 것인가?
         *      2. 일정 거리는 어떻게 지정해야 하는가?
         *
         *
         * PSEUDO-code
         * var result = 0
         * var startPoint = 0
         * var endPoint = startPoint
         *
         * while (startPoint <= endPoint && endPoint < table.size) {
         *   val dsu = DSU(factory + 1)
         *   var connectivity = 0
         *   var min = Int.MAX_VALUE
         *   var max = Int.MIN_VALUE
         *
         *   for( element in startPoint .. endPoint)) {
         *      val (a, b, score) = table[element]
         *      if(dsu.union(a,b)) {
         *        min = minOf(min, score)
         *        max = maxOf(max, score)
         *        connectivity += 1
         *      }
         *   }
         *
         *   if( connectivity == factory - 1) {
         *        result = minOf(result, max - min)
         *        startPoint ++
         *   }
         *   else {
         *      endPoint +=1
         *   }
         */
        var result = Int.MAX_VALUE
        var startPoint = 0
        var endPoint = 0


        while (startPoint <= endPoint && endPoint < table.size) {
            val dsu = DisjointSetUnion(factory + 1)
            var connectivity = 0
            var min = Int.MAX_VALUE
            var max = Int.MIN_VALUE

            for (element in startPoint..endPoint) {
                val (a, b, score) = table[element]
                if (dsu.union(a, b)) {
                    min = minOf(min, score)
                    max = maxOf(max, score)
                    connectivity += 1
                }
            }

            if (connectivity == factory - 1) {
                println("max: $max min: $min")
                result = minOf(result, max - min)
                startPoint++
            } else {
                endPoint += 1
            }


        }


        return if(result == Int.MAX_VALUE) -1 else result
    }
}