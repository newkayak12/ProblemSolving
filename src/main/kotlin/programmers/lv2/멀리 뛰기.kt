package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `멀리 뛰기` {
    //DFS -> Timeout (DP (TopDown(Memoization), BottomUp(점화식))
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12914?language=kotlin
     */

    @Test
    fun solution1() {
        val n = 4
        val expected = 5L


        assertEquals(expected, `top-down`(n))
        assertEquals(expected, `bottom-up`(n))
        assertEquals(expected, useStack(n))
        assertEquals(expected, useRecursive(n))
    }

    @Test
    fun solution2() {
        val n = 3
        val expected = 3L

        assertEquals(expected, `top-down`(n))
        assertEquals(expected, `bottom-up`(n))
        assertEquals(expected, useStack(n))
        assertEquals(expected, useRecursive(n))
    }

    private fun `bottom-up`(n: Int): Long {
        return when (n) {
            1 -> 1
            2 -> 2
            else -> `bottom-upUseRecursive`(1, 2, 2, n)
        }
    }

    private fun `bottom-upUseRecursive`(first: Int, second: Int, count: Int, limit: Int): Long {
        if (count == limit) return second.toLong()
        return `bottom-upUseRecursive`(second % 1234567, (first + second) % 1234567, count + 1, limit)
    }

    private fun `top-down`(n: Int): Long {
        return `top-downUseMemoization`(mutableMapOf<Long, Long>(), n.toLong())
    }

    private fun `top-downUseMemoization`(memoization: MutableMap<Long, Long>, number: Long): Long {
        return when (number) {
            1L -> 1L
            2L -> 2L
            else -> memoization[number] ?: run {
                val result = `top-downUseMemoization`(memoization, number - 1)  % 1234567 +
                             `top-downUseMemoization`(memoization, number - 2)  % 1234567

                memoization[number] = result
                result
            }
        }

    }

    private fun useStack(n: Int): Long {
        var count = 0L
        val stack = ArrayDeque<Long>()
        stack.add(n.toLong())

        while (stack.isNotEmpty()) {
            val pop = stack.removeLast()
            if (pop <= 1) {
                count++
                continue
            }

            stack.addLast(pop - 1)
            stack.addLast(pop - 2)
        }

        return count % 1234567
    }

    private fun useRecursive(n: Int): Long = recursive(n)

    private fun recursive(n: Int): Long {
        if (n <= 1) return 1


        var count = 0L;
        for (i in 1..2) {
            count += recursive(n - i)
        }

        return count % 1234567
    }
}