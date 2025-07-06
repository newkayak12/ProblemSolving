package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KruskalExample {
    /**
     * 🧩 문제: 도시 분할 계획 (BOJ 1647 변형)
     *
     * 문제 설명
     *
     * 어떤 나라에는 N개의 집과 M개의 길이 있습니다.
     * 길은 집과 집을 연결하며, 길마다 유지비용이 존재합니다.
     *
     * 당신은 이 마을에 전기를 설치하려고 합니다.
     * 하지만 모든 집에 전기를 설치하는 비용을 최소화하기 위해,
     * 마을을 두 개의 분리된 마을로 분할하려 합니다.
     *
     * 단, 조건은 다음과 같습니다:
     * 	•	각 마을 안에 있는 모든 집은 서로 연결되어 있어야 합니다.
     * 	•	두 마을은 서로 분리되어 있어야 합니다.
     * 	•	길을 최소한으로 유지하되, 마을을 둘로 나누세요.
     * 	•	마을을 둘로 나눈 뒤, 남은 길들의 유지비 총합을 출력하세요.
     *
     * 	입력 형식
     * 	- 첫째 줄에 집의 개수 N (2 ≤ N ≤ 100,000), 길의 개수 M (1 ≤ M ≤ 1,000,000)
     *  - 둘째 줄부터 M개의 줄에 걸쳐 다음이 주어짐:
     *  - A B C → 집 A와 집 B를 연결하는 길의 유지비 C (1 ≤ C ≤ 1,000)
     *
     * 	출력 형식
     * 	마을을 두 개로 나누었을 때, 남은 길들의 유지비 총합의 최솟값
     *
     *
     * 	예제 입력
     * 	7 12 (집, 길)
     * nodeA, nodeB, 가중치
     * 1        2       3
     * 1        3       2
     * 3        2       1
     * 2        5       2
     * 3        4       4
     * 7        3       6
     * 5        1       5
     * 1        6       2
     * 6        4       1
     * 6        5       3
     * 4        5       3
     * 6        7       4
     *
     *  예제 출력
     *  8
     */

    inner class DisjointSetUnion(
        size: Int
    ) {
        private val parent = IntArray(size + 1){it}
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

        table.sortBy { it[2] }
        val count = table.flatMap { intArrayOf(it[0], it[1]).asSequence() }.distinct().count()
        val unionFind = DisjointSetUnion(count + 1)

        var totalScore = 0;
        var max = Int.MIN_VALUE

        for((a, b, score) in table) {
            if(unionFind.union(a, b)){
                totalScore += score
                max = maxOf(max, score)
            }
        }

        return totalScore - max
    }
}