package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `멀리 뛰기` {

    @Test
    fun solution1() {
        val n = 4
        val result = 5L

        assertEquals(result, calculate(n))
        assertEquals(result, calculateBottomUp(n))
    }

    @Test
    fun solution2() {
        val n = 3
        val result = 3L

        assertEquals(result, calculate(n))
        assertEquals(result, calculateBottomUp(n))
    }

    fun calculate(n: Int): Long {
        /**
         * 1칸 -> (1)
         * 2칸 -> (1,1) (2)
         * 3칸 -> (1, 1, 1), (1, 2), (2, 1)
         * 4칸 -> (1, 1, 1, 1), (1, 2, 1), (1, 1, 2), (2, 1, 1), (2, 2)
         * 5칸 -> (1, 1, 1, 1, 1), (1, 1, 1, 2), (1, 1, 2, 1), (1, 2, 1, 1), (2, 1, 1, 1), (1, 2, 2), (2, 1, 2), (2, 2, 1)
         *
         * fibonacci?
         */

        return fibonacci(n)
    }


    fun calculateBottomUp(n: Int) = fibonacciBottomUp(n)

    fun fibonacci(n: Int): Long =
        when (n) {
            1 -> 1L
            2 -> 2L
            else -> (fibonacci(n - 1) + fibonacci(n - 2)) % 1234567
        }

    fun fibonacciBottomUp(n: Int): Long =
        when (n) {
            1 -> 1L
            2 -> 2L
            else -> bottomUp(1L, 2L, 2, n)
        }

    tailrec fun bottomUp(first: Long, second: Long, count: Int, limit: Int): Long {
        if (count == limit) return second
        return bottomUp(second % 1234567, (first + second) % 1234567, count + 1, limit)
    }

}