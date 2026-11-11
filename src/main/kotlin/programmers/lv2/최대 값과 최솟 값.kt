package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `최대 값과 최솟 값` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12939?language=kotlin
     */

    @Test
    fun solution1(){
        val s = "1 2 3 4"
        val expected = "1 4"

        assertEquals(expected, calculate(s))
    }

    @Test
    fun solution2(){
        val s = "-1 -2 -3 -4"
        val expected = "-4 -1"

        assertEquals(expected, calculate(s))
    }

    @Test
    fun solution3(){
        val s = "-1 -1"
        val expected = "-1 -1"

        assertEquals(expected, calculate(s))
    }

    private fun calculate(s: String): String {
        return s
            .split(" ")
            .map { it.toInt() }
            .let { "${it.minOrNull()} ${it.maxOrNull()}" }
    }
}