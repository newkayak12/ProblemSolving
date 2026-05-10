package renewal.tree

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class SubtreeSize {

    @Test
    fun solution() {
        val nodeSize = 9
        val root = 1

        val tree = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
            intArrayOf(2, 5),
            intArrayOf(4, 6),
            intArrayOf(4, 7),
            intArrayOf(5, 8),
            intArrayOf(5, 9),
        )

        /**
         *            1
         *          /  \
         *        2     3
         *      /   \
         *     4     5
         *   /  \   / \
         *  6   7  8   9
         */

        val question = intArrayOf(1, 2, 5)
        val expected = intArrayOf(9, 7, 3)

        assertArrayEquals(expected, useStack(nodeSize, root, tree, question))
        assertArrayEquals(expected, useRecursive(nodeSize, root, tree, question))
    }

    private fun useStack(nodeSize: Int, root: Int, tree: Array<IntArray>, question: IntArray): IntArray {
        val graph = Array<MutableList<Int>>(nodeSize + 1) { mutableListOf() }


        for (element in tree) {
            val first = element[0]
            val second = element[1]
            graph[first].add(second)
        }

        val score = IntArray(nodeSize + 1)  {1}
        val trace = ArrayDeque<IntArray>()
        val stack = ArrayDeque<Int>()
        trace.addLast(intArrayOf(0, root))
        stack.addLast(root)



        while (stack.isNotEmpty()) {
            val now = stack.removeLast()
            val child = graph[now]
            for (element in child) {
                stack.addLast(element)
                trace.addLast(intArrayOf(now, element))
            }
        }

        while (trace.isNotEmpty()) {
            val (parents, child) = trace.removeLast()
            score[parents] += score[child]
        }

        val result = IntArray(question.size)
        for ((index, value) in question.withIndex()) {
            result[index] = score[value]
        }

        return result
    }

    private fun useRecursive(nodeSize: Int, root: Int, tree: Array<IntArray>, question: IntArray): IntArray {
        val graph = Array<MutableList<Int>>(nodeSize + 1) { mutableListOf() }


        for (element in tree) {
            val first = element[0]
            val second = element[1]
            graph[first].add(second)
        }

        val score = IntArray(nodeSize + 1)
        val result = IntArray(question.size)
        recursive(root, graph, score)


        for((index, value) in question.withIndex()) {
            result[index] = score[value]
        }


        return result
    }

    private fun recursive(now: Int, graph: Array<MutableList<Int>>, score: IntArray) {
        val child = graph[now]
        if (child.isEmpty()) {
            score[now] = 1
            return
        }

        for (element in child) {
            recursive(element, graph, score)
        }

        score[now] = child.sumOf { score[it] } + 1
    }
}