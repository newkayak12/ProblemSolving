package renewal.tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ParentToChild {
    /**
     * ✅ [트리 개념] - 부모 배열 추적을 통한 루트 → 리프 경로 구성
     *
     * 🔹 핵심 아이디어
     * 	•	DFS를 통해 parents 배열을 구성했으면, 이를 활용해서 특정 노드에서 루트까지의 경로를 거꾸로 복원할 수 있습니다.
     * 	•	코딩 테스트에서는 특정 노드의 조상, 경로, 공통 조상 등을 추적하는 데 자주 쓰입니다.
     *
     * ⸻
     *
     * ✅ 실습 문제: “특정 노드에서 루트까지의 경로 출력”
     *
     * ▪️ 문제 설명
     *
     * 루트가 1번인 트리가 주어졌을 때, 특정 노드 X로부터 루트까지의 경로를 출력하라.
     *
     * ▪️ 입력
     * 7
     * 1 6
     * 6 3
     * 3 5
     * 4 1
     * 2 4
     * 4 7
     * X = 5
     *
     * ▪️ 출력
     * 5 3 6 1
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
        val x = 5
        val expected = "5 3 6 1"

        assertEquals(expected, traceTree(node, table, x))
    }

   private fun traceTree(node: Int, table: Array<IntArray>, target: Int): String {
        val tree = Array(node + 1) { mutableListOf<Int>() }
        val parents = Array(node + 1) { 0 }

        for((a, b) in table) {
            tree[a].add(b)
            tree[b].add(a)
        }

        val stack = ArrayDeque<Int>()
        val visited = BooleanArray(node + 1)

        stack.addLast(1)
        visited[1] = true

        while(stack.isNotEmpty()) {
            val parent = stack.removeLast()

            for(element in tree[parent]) {
                if(!visited[element]) {
                    stack.addLast(element)
                    visited[element] = true
                    parents[element] = parent
                }
            }
        }

        val printStack = ArrayDeque<Int>()
        val builder = StringBuilder()
        printStack.addLast(target)
        builder.append("$target ")

        while(printStack.isNotEmpty()) {
            val prev = printStack.removeLast()

            val parent = parents[prev]
            if(parent > 0) {
                printStack.addLast(parent)
                builder.append("$parent ")
            }
        }

        return builder.toString().trimEnd()
    }
}