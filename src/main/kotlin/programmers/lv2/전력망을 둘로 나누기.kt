package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class `전력망을 둘로 나누기` {

    @Test
    fun solution1() {
        val n = 9
        val wires = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
            intArrayOf(4, 6),
            intArrayOf(4, 7),
            intArrayOf(7, 8),
            intArrayOf(7, 9)
        )
        val expected = 3

        assertEquals(expected, calculate(n, wires))
    }

    @Test
    fun solution2() {
        val n = 4
        val wires = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
        )
        val expected = 0

        assertEquals(expected, calculate(n, wires))
    }

    @Test
    fun solution3() {
        val n = 7
        val wires = arrayOf(
            intArrayOf(1, 2), intArrayOf(2, 7), intArrayOf(3, 7), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(6, 7)
        )
        val expected = 1

        assertEquals(expected, calculate(n, wires))
    }

    fun calculate(n: Int, wires: Array<IntArray>): Int {
        val map = (1..n).associateWith { mutableListOf<Int>() }
        var min = Int.MAX_VALUE

        for (element in wires) {
            val first = element[0]
            val second = element[1]
            map[first]?.add(second)
            map[second]?.add(first)
        }

        for (element in wires) {
            val start = element[0]
            val end = element[1]
            map[start]?.remove(end)
            map[end]?.remove(start)


            //순회
            val set = mutableSetOf<Int>()
            useRecursive(set, map, 1)
            min = minOf(min, abs(n - set.size - set.size))

            map[start]?.add(end)
            map[end]?.add(start)
        }
        return min
    }

    private fun useRecursive(visitedNodes: MutableSet<Int>, graph: Map<Int, List<Int>>, node: Int) {
        if (visitedNodes.contains(node) || graph[node] != null && graph[node]!!.isEmpty()) return
        visitedNodes.add(node)

        val next = graph[node]!!

        for (element in next) {
            if (visitedNodes.contains(element)) continue
            useRecursive(visitedNodes, graph, element)
        }


    }
}