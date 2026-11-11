package programmers.lv2

import java.util.PriorityQueue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 배달 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12978
     * dijkstra
     */

    @Test
    fun solution1() {
        val N = 5
        val road = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 3, 3),
            intArrayOf(5, 2, 2),
            intArrayOf(1, 4, 2),
            intArrayOf(5, 3, 1),
            intArrayOf(5, 4, 2)
        )
        val K = 3
        val expected = 4

        assertEquals(expected, calculate(N, road, K))
    }


    @Test
    fun solution2() {
        val N = 6
        val road = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 2),
            intArrayOf(3, 4, 3),
            intArrayOf(3, 5, 2),
            intArrayOf(3, 5, 3),
            intArrayOf(5, 6, 1)
        )
        val K = 4
        val expected = 4

        assertEquals(expected, calculate(N, road, K))
    }


    private fun calculate(N: Int, road: Array<IntArray>, k: Int): Int {
        var count = 0
        val visited = BooleanArray(N + 1)
        val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        val queue = PriorityQueue<Pair<Int, Int>>{ o1, o2 -> o1.second - o2.second }

        for ((from, to, cost) in road) {
            graph.putIfAbsent(from, mutableListOf())
            graph.putIfAbsent(to, mutableListOf())
            graph[from]?.add(to to cost)
            graph[to]?.add(from to cost)
        }
        queue.add(Pair(1, 0))



        while (queue.isNotEmpty()) {
            val (from, usedCost) = queue.poll()

            if(visited[from]) continue
            visited[from] = true
            count ++

            val roads = graph[from] ?: continue
            for ((to, cost) in roads) {
                if (usedCost + cost > k) continue
                queue.add(to to usedCost + cost)
            }
        }


        return count
    }
}