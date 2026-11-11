package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `비밀 코드 해독` {
    /**
     * 모든 경우의 수
     * reference를 넣어서 answer를 만족하는 경우의 수를 구한다.
     *
     * 10 <= n <= 30
     * 5개, 오름 차순
     *
     * m 번 시도(배열 수)
     * q의 시스템 응답
     *
     */


    @Test
    fun solution1(){
        val n = 10
        val q = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(6, 7, 8, 9, 10),
            intArrayOf(3, 7, 8, 9, 10),
            intArrayOf(2, 5, 7, 9, 10),
            intArrayOf(3, 4, 5, 6, 7),
        )
        val answer = intArrayOf(2, 3, 4, 3, 3)
        val result = 3

        assertEquals(result, calculate(n, q, answer))
    }

    @Test
    fun solution2(){
        val n = 15
        val q = arrayOf(
            intArrayOf(2, 3, 9, 12, 13),
            intArrayOf(1, 4, 6, 7, 9),
            intArrayOf(1, 2, 8, 10, 12),
            intArrayOf(6, 7, 11, 13, 15),
            intArrayOf(1, 4, 10, 11, 14),
        )
        val answer = intArrayOf(2, 1, 3, 0, 1)
        val result = 5

        assertEquals(result, calculate(n, q, answer))
    }

    private fun check(
        array: IntArray,
        q: Array<IntArray>,
        ans: IntArray
    ): Int {

        for(i in q.indices) {
            val result = array.filter { q[i].contains(it) }.map { 1 }.sum()
            if(ans[i] != result) {
                return 0
            }
        }

        return 1
    }

    private fun numberOfCases(
        max: Int,
        start: Int,
        index: Int,
        array: IntArray,
        q: Array<IntArray>,
        ans: IntArray
    ): Int {
        if (array.size <= index) return check(array, q, ans)

        var count = 0
        for (number in start ..  max - (array.size - 1 - index)) {
            array[index] = number
            count += numberOfCases(max, number + 1, index + 1, array, q, ans)
            array[index] = 0
        }


        return count
    }

    fun calculate(n: Int, q: Array<IntArray>, ans: IntArray): Int {
       return numberOfCases(n, 1, 0, intArrayOf(0, 0, 0, 0, 0), q, ans)
    }
}