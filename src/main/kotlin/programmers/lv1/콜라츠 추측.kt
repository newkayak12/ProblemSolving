package programmers.lv1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `콜라츠 추측` {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12943?language=kotlin
     */

    @Test
    fun solution1() {
        val n = 6
        val expected = 8

        assertEquals(expected, calculate(n))
    }

    @Test
    fun solution2() {
        val n = 16
        val expected = 4

        assertEquals(expected, calculate(n))
    }

    @Test
    fun solution3() {
        val n = 626331
        val expected = -1

        //Int Overflow 21억
        assertEquals(expected, calculate(n))
    }

    private fun calculate(n: Int): Int {
        var number: Long = n.toLong()
        var count = 0

        while (number != 1L && count <= 500) {

            when {
                number % 2 == 0L -> number = (number / 2)
                number % 2 != 0L -> number = (3 * number )+ 1
            }
            count++
        }


        return if (count > 500) -1 else count
    }
}