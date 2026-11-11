package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `귤 고르기` {


    @Test
    fun solution1() {
        val k = 6
        val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)
        val expected = 3

        assertEquals(expected, calculate(k, tangerine))
    }

    @Test
    fun solution2() {
        val k = 4
        val tangerine = intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)
        val expected = 2

        assertEquals(expected, calculate(k, tangerine))
    }

    @Test
    fun solution3() {
        val k = 2
        val tangerine = intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)
        val expected = 1

        assertEquals(expected, calculate(k, tangerine))
    }

    //Greedy
    private fun calculate(k: Int, tangerine: IntArray): Int {
        var limit = k
        var count = 0
        val map = tangerine
            .groupBy { it }
            .mapValues { it.value.size }
            .toList()
            .sortedByDescending { it.second }

        for((_, value) in map){
            if(limit <= 0) break
            limit -= value
            count ++
        }

       return count
    }
}
