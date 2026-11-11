package programmers.lv2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class 카펫 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42842?language=kotlin
     *
     * BBBB
     * BYYB
     * BBBB
     *
     * 갈색 격자 수 brown 8 <= B <= 5000
     * 노란색 격자 수 yellow 1 <= Y <= 2000000
     * 가로 길이 >= 세로 길이
     */


    @Test
    fun solution1() {
        val brown = 10
        val yellow = 2
        val expected = intArrayOf(4, 3)

        assertArrayEquals(expected, calculate(brown, yellow))
    }

    @Test
    fun solution2() {
        val brown = 8
        val yellow = 1
        val expected = intArrayOf(3, 3)

        assertArrayEquals(expected, calculate(brown, yellow))
    }

    @Test
    fun solution3() {
        val brown = 24
        val yellow = 24
        val expected = intArrayOf(8, 6)

        assertArrayEquals(expected, calculate(brown, yellow))
    }


    private fun calculate(brown: Int, yellow: Int): IntArray {
        val total = brown + yellow
        var answer = intArrayOf(0, 0)

        for(w in 1 .. yellow) {
            if( yellow % w != 0) continue
            val h = yellow / w

            if((w + 2) * (h + 2) == total) {
                val max = maxOf(w+2, h+2)
                val min = minOf(w+2, h+2)
                answer[0] = max
                answer[1] = min
            }
        }


        return answer
    }
}