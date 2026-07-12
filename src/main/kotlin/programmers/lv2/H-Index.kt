package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `H-Index` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42747?language=kotlin
     */

    @Test
    fun solution1(){
        val citation = intArrayOf(3, 0, 6, 1, 5)
        val expected = 3

        assertEquals(expected, calculate(citation))
    }

    @Test
    fun solution2(){
        val citation = intArrayOf(5, 5, 5, 5, 5, 1, 2, 3, 4)
        val expected = 5

        assertEquals(expected, calculate(citation))
    }

    private fun calculate(citations: IntArray): Int {
        val descend = citations.sortedDescending()


        for(i in descend.indices) {
            if(descend[i] < i + 1) return  i
        }

        return descend.size
    }
}