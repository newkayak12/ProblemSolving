package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `피보나치 수` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12945?language=kotlin
     */

    @Test
    fun solution1() {
        val n = 3
        val expected = 2

        assertEquals(expected, Old.recursive(n))
        assertEquals(expected, Old.useStack(n))

        assertEquals(expected, Optimized.useRecursive(n))
        assertEquals(expected, Optimized.useStack(n))
        assertEquals(expected, Optimized.ordinal(n))
    }

    @Test
    fun solution2() {
        val n = 5
        val expected = 5

        assertEquals(expected, Old.recursive(n))
        assertEquals(expected, Old.useStack(n))

        assertEquals(expected, Optimized.useRecursive(n))
        assertEquals(expected, Optimized.useStack(n))
        assertEquals(expected, Optimized.ordinal(n))
    }


    object Old {
        fun recursive(n: Int): Int {
            return if (n - 1 <= 0) n
            else recursive(n - 2) + recursive(n - 1)
        }

        fun useStack(n: Int): Int {
            var num = 0
            val stack = ArrayDeque<Int>()
            stack.addLast(n)

            while (stack.isNotEmpty()) {
                val pop = stack.removeLast()
                if (pop - 1 <= 0) num += pop

                if (pop - 1 > 0) stack.addLast(pop - 1)
                if (pop - 2 > 0) stack.addLast(pop - 2)
            }

            return num % 1234567
        }
    }

    object Optimized {
        fun useRecursive(n: Int): Int {
            return when (n) {
                0 -> 0
                1 -> 1
                else -> (recursive(0L, 1L, 1, n) % 1234567).toInt()
            }
        }

        private tailrec fun recursive(first: Long, second: Long, count: Int, target: Int): Long {
            return if (count == target) second
            else recursive(second % 1234567, (first + second) % 1234567, count + 1, target)
        }

        fun useStack(n: Int): Int {
            val stack = ArrayDeque<Pair<Int, Int>>()
            var count = 1
            stack.addLast(0 to 1)

            while (count < n) {
                val (first, second) = stack.removeLast()
                stack.addLast(second % 1234567 to (first + second) % 1234567)
                count++
            }

            val (_, second) = stack.removeLast()
            return second
        }

        fun ordinal(n: Int): Int {
            var count = 1
            var first = 0
            var second = 1

            while (count < n) {
                val temporary = (first + second) % 1234567
                first = second
                second = temporary

                count ++
            }

            return second
        }
    }


}