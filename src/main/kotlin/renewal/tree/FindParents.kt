package renewal.tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FindParents {
    /**
     * ✅ 문제: 트리의 부모 찾기
     *
     * ⸻
     *
     * ▪️ 문제 설명
     *
     * 루트가 1번인 트리가 주어진다.
     * 각 노드의 부모를 구해서 출력하라.
     *
     * ⸻
     *
     * ▪️ 입력
     * 	•	첫 줄: 노드 개수 N (2 ≤ N ≤ 100,000)
     * 	•	둘째 줄부터 N-1줄: 간선 정보 a\ b
     *
     * ⸻
     *
     * ▪️ 출력
     * 	•	2번 노드부터 N번 노드까지, 각 노드의 부모 노드 번호를 한 줄씩 출력
     *
     * ⸻
     *
     * ▪️ 예시 입력
     * 7
     * 1 6
     * 6 3
     * 3 5
     * 4 1
     * 2 4
     * 4 7
     *
     * ⸻
     *
     * ▪️ 예시 출력
     * 4
     * 6
     * 1
     * 3
     * 1
     * 4
     */

    @Test
    fun solution() {
        val node = 7
        val table = arrayOf(
            intArrayOf(1, 6),
            intArrayOf(6, 3),
            intArrayOf(3, 5),
            intArrayOf(4, 1),
            intArrayOf(2, 4),
            intArrayOf(4, 7),
        )

        val expected = """
            4
            6
            1
            3
            1
            4
        """.trimIndent()

        assertEquals(expected, findParents(node, table))
    }

    private fun findParents(node: Int, table: Array<IntArray>): String {
        val tree = Array(node + 1) { mutableListOf<Int>() }
        val parents = Array(node + 1) { 0 }

        for ((a, b) in table) {
            tree[a].add(b)
            tree[b].add(a)
        }


        val stack = ArrayDeque<Int>()
        val visited = BooleanArray(node + 1)
        stack.add(1)
        visited[1] = true

        while (stack.isNotEmpty()) {
            val parent = stack.removeLast()

            for (element in tree[parent]) {
                if (!visited[element]) {
                    stack.addLast(element)
                    visited[element] = true
                    parents[element] = parent
                }
            }
        }


        val builder = StringBuilder()
        for (i in 2 until parents.size) {
            builder.append("${parents[i]}\n")
        }

        return builder.toString().trimIndent()
    }
}