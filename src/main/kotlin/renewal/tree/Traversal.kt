package renewal.tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Traversal {
    /**
     * ✅ 실습 문제
     *
     * 🟩 문제: 트리 순회 결과 출력
     *
     * 루트 노드가 주어진 트리를 입력으로 받아 전위, 중위, 후위 순회 결과를 출력하라.
     *
     * ▪️ 입력
     * 	•	첫 줄: 노드 개수 N (1 ≤ N ≤ 26)
     * 	•	이후 N줄: 각 줄에 세 개의 값 부모 자식1 자식2
     * 	•	자식이 없으면 .으로 표기
     *
     * ▪️ 출력
     * 	•	전위 순회 결과
     * 	•	중위 순회 결과
     * 	•	후위 순회 결과
     *
     * ⸻
     *
     * ▪️ 예시 입력
     * 7
     * A B C
     * B D E
     * C F .
     * E . .
     * D . .
     * F . .
     * ```text
     *      A
     *     / \
     *    B   C
     *  /  \   \
     * D    E   F
     * ```
     * ▪️ 예시 출력
     * 전위 순회: ABDECF
     * 중위 순회: DBEACF
     * 후위 순회: DEBFCA
     */

    data class Node(
        val character: String,
        val left: Node?,
        val right: Node?
    )

    @Test
    fun solution() {


        val E = Node("E", null, null)
        val D = Node("D", null, null)
        val F = Node("F", null, null)
        val C = Node("C", null, F)
        val B = Node("B", D, E)
        val A = Node("A", B, C)

        val preOrderExpected = "ABDECF";
        val inOrderExpected = "DBEACF";
        val postOrderExpected = "DEBFCA";

        assertEquals(preOrderExpected, preOrderUseStack(A))
        assertEquals(inOrderExpected, inOrderUseStack(A))
        assertEquals(postOrderExpected, postOrderUseStack(A))

        assertEquals(preOrderExpected, preOrderUseRecursive(A))
        assertEquals(inOrderExpected, inorderUseRecursive(A))
        assertEquals(postOrderExpected, postOrderUseRecursive(A))
    }


    private fun preOrderUseStack(tree: Node): String {
        val stack = ArrayDeque<Pair<Node, StringBuilder>>()
        val builder = StringBuilder()
        stack.addLast(tree to builder)

        while (stack.isNotEmpty()) {
            val (node, builder) = stack.removeLast()
            builder.append(node.character)

            if (node?.right != null) {
                stack.addLast(node.right to builder)
            }
            if (node?.left != null) {
                stack.addLast(node.left to builder)
            }
        }

        return builder.toString()
    }

    private fun inOrderUseStack(tree: Node): String {
        val stack = ArrayDeque<Node>()
        val builder = StringBuilder()
        var current: Node? = tree


        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }

            val node = stack.removeLast()
            builder.append(node.character)

            current = node.right
        }

        return builder.toString()
    }

    private fun postOrderUseStack(tree: Node): String {

        val stack = ArrayDeque<Node>()
        val wordStack = ArrayDeque<String>()
        stack.addLast(tree)

        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            wordStack.addLast(node.character)


            if (node?.left != null) {
                stack.addLast(node.left)
            }
            if (node?.right != null) {
                stack.addLast(node.right)
            }
        }

        val builder = StringBuilder()
        while (wordStack.isNotEmpty()) {
            builder.append(wordStack.removeLast())
        }

        return builder.toString()
    }

    private fun preOrderUseRecursive(tree: Node): String {
        val builder = StringBuilder()
        preOrderTraversal(tree, builder)
        return builder.toString()
    }

    private fun preOrderTraversal(tree: Node, builder: StringBuilder) {
        builder.append(tree.character)
        if (tree?.left != null) preOrderTraversal(tree.left, builder)
        if (tree?.right != null) preOrderTraversal(tree.right, builder)
    }


    private fun inorderUseRecursive(tree:Node): String {
        val builder = StringBuilder()
        inorderTraversal(tree, builder)
        return builder.toString()
    }

    private fun inorderTraversal(tree: Node, builder: StringBuilder) {
        if(tree?.left != null) inorderTraversal(tree.left, builder)
        builder.append(tree.character)
        if(tree?.right != null) inorderTraversal(tree.right, builder)
    }

    private fun postOrderUseRecursive(tree:Node): String {
        val builder = StringBuilder()
        postOrderTraversal(tree, builder)
        return builder.toString()
    }

    private fun postOrderTraversal(tree: Node, builder: StringBuilder) {
        if (tree?.left != null) postOrderTraversal(tree.left, builder)
        if (tree?.right != null) postOrderTraversal(tree.right, builder)
        builder.append(tree.character)
    }
}