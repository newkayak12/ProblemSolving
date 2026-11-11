package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `타겟 넘버` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/43165?language=kotlin
     *
     * DFS
     */

    @Test
    fun solution1() {
        val numbers = intArrayOf(1, 1, 1, 1, 1)
        val target = 3
        val expected = 5

        assertEquals(expected, calculate(numbers, target))
    }

    @Test
    fun solution2() {
        val numbers = intArrayOf(4, 1, 2, 1)
        val target = 4
        val expected = 2

        assertEquals(expected, calculate(numbers, target))
    }

    private fun calculate(numbers: IntArray, target: Int): Int {


        return useRecursive(numbers, 0, target, 0)
    }

    private fun useRecursive(numbers: IntArray, now: Int, target: Int, step: Int): Int {
        if (step == numbers.size) {
            return if (now == target) 1 else 0
        }

        var count = 0
        val value = numbers[step]
        count += useRecursive(numbers, now + value, target, step + 1)
        count += useRecursive(numbers, now - value, target, step + 1)

        return count
    }
}