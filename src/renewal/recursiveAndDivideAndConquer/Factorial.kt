package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Factorial {

    @Test
    fun calculate() {

        val expected = 120

        val result = recursive(5)

        assertEquals(expected, result)
    }

    private fun recursive(number: Int): Int {
        if( number <= 0) return 1;

        return number * recursive(number - 1)
    }
}