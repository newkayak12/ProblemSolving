package programmers.lv1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `약수의 개수와 덧셈` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/77884?language=kotlin
     */

    @Test
    fun solution1() {
        val left = 13
        val right = 17
        val expected = 43

        assertEquals(expected, calculate(left, right))
    }

    @Test
    fun solution2() {
        val left = 24
        val right = 27
        val expected = 52

        assertEquals(expected, calculate(left, right))
    }


    fun calculate(left: Int, right: Int): Int {
        var answer = 0

        for (i in left..right) {
            var count = 0
            for (j in 1..i) {
                if (i % j == 0) count++
            }

            answer += if(count % 2 == 0) i else i * -1
        }

        return answer
    }
}