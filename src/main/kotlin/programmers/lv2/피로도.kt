package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 피로도 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/87946?language=kotlin
     */

    @Test
    fun solution1() {
        val k = 80
        val dungeons = arrayOf(intArrayOf(80, 20), intArrayOf(50, 40), intArrayOf(30, 10))
        val expected = 3

        assertEquals(expected, calculate(k, dungeons))
        assertEquals(expected, useStack(k, dungeons))
    }

    @Test
    fun solution2() {
        val k = 80
        val dungeons = arrayOf(intArrayOf(80, 20))
        val expected = 1

        assertEquals(expected, useStack(k, dungeons))
    }


    fun calculate(k: Int, dungeons: Array<IntArray>): Int {
        return recursive(k, BooleanArray(dungeons.size), dungeons, 0)
    }

    private fun recursive(k: Int, visit: BooleanArray, dungeons: Array<IntArray>, count: Int): Int {
        var result = count

        for ((index, value) in dungeons.withIndex()) {
            val (limit, use) = value
            if (visit[index]) continue
            if (limit <= k) {
                visit[index] = true
                val exploreCount = recursive(k - use, visit, dungeons, count + 1)
                visit[index] = false
                result = maxOf(result, exploreCount)
            }
        }

        return result
    }

    private fun useStack(k: Int, dungeons: Array<IntArray>): Int {
        val stack = ArrayDeque<Triple<Int, BooleanArray, Int>>()
        stack.addLast(Triple(k, BooleanArray(dungeons.size), 0))
        var answer = 0
        while (stack.isNotEmpty()) {
            val (fatigue, visit, count) = stack.removeLast()
            answer = maxOf(answer, count)

            for ((index, value) in dungeons.withIndex()) {
                val (limit, use) = value
                if (visit[index]) continue
                if (limit <= fatigue) {
                    val newVisit = visit.copyOf()
                    newVisit[index] = true
                    stack.addLast(Triple(fatigue - use, newVisit, count + 1))
                }
            }

        }

        return answer
    }
}