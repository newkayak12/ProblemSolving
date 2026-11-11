package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `가장 큰 수` {

    @Test
    fun solution1() {
        val numbers = intArrayOf(6, 10, 2)
        val expected = "6210"

        assertEquals(expected, calculate(numbers))
    }

    @Test
    fun solution2() {
        val numbers = intArrayOf(3, 30, 34, 5, 9)
        val expected = "9534330"

        assertEquals(expected, calculate(numbers))
    }

    @Test
    fun solution3() {
        val numbers = intArrayOf(0, 0, 0, 0)
        val expected = "0"

        assertEquals(expected, calculate(numbers))
    }


    fun calculate(numbers: IntArray): String {
        return numbers
            .map { it.toString() }
            .sortedWith { o1: String, o2: String -> (o2 + o1).compareTo(o1 + o2) }
            .joinToString("")
            .let {
                if(it.replace('0', ' ').isBlank()) "0"
                else it
            }
    }
}