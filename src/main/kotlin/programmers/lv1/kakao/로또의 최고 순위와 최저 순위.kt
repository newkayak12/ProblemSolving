package programmers.lv1.kakao

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `로또의 최고 순위와 최저 순위` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/77484?language=kotlin
     */


    @Test
    fun solution1() {
        val lottos = intArrayOf(44, 1, 0, 0, 31, 25)
        val win_nums = intArrayOf(31, 10, 45, 1, 6, 19)
        val result = intArrayOf(3, 5)

        assertArrayEquals(result, calculate(lottos, win_nums))
    }

    @Test
    fun solution2() {
        val lottos = intArrayOf(0, 0, 0, 0, 0, 0)
        val win_nums = intArrayOf(38, 19, 20, 40, 15, 25)
        val result = intArrayOf(1, 6)

        assertArrayEquals(result, calculate(lottos, win_nums))
    }

    @Test
    fun solution3() {
        val lottos = intArrayOf(45, 4, 35, 20, 3, 9)
        val win_nums = intArrayOf(20, 9, 3, 45, 4, 35)
        val result = intArrayOf(1, 1)

        assertArrayEquals(result, calculate(lottos, win_nums))
    }

    private fun calculate(lottos: IntArray, winNums: IntArray): IntArray {
        val lot = lottos.filter { it != 0 }
        val win = winNums.associateBy({ it }) { false }

        var best = lottos.size - lot.size
        var worst = 0


        for (value in lot) {
            if(win[value] != null) {
                best ++
                worst ++
            }
        }

        return intArrayOf(scoring(best), scoring(worst))
    }

    private fun scoring(count: Int): Int =
        when (count) {
            6 -> 1
            5 -> 2
            4 -> 3
            3 -> 4
            2 -> 5
            else -> 6
        }
}