package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Fibonacci {
    @Test
    fun calculate() {
        val limit = 6
        val expected = 8;
        val result = fibonacci(limit)

        assertEquals(expected, result)
    }
    private fun fibonacci (index: Int): Int {
        return when(index) {
            0 -> 0
            1 -> 1
            else -> recursive(0,1,2, index)
        }
    }
    private fun recursive (first: Int, second: Int, count: Int, targetIndex: Int): Int {
        if(targetIndex < count) return second
        return recursive(second, first + second, count + 1, targetIndex)
    }
}