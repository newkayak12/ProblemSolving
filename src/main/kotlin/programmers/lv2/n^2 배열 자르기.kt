package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `n^2 배열 자르기` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/87390?language=kotlin
     *
     *
     *    1 2 3 4
     *    2 2 3 4
     *    3 3 3 4
     *    4 4 4 4
     *
     *    (0,0) (1,0) (2,0) (3,0) (4,0)
     *    (0,1) (1,1) (2,1) (3,1) (4,1)
     *    (0,2) (1,2) (2,2) (3,2) (4,2)
     *    (0,3) (1,3) (2,3) (3,3) (4,3)
     *    (0,4) (1,4) (2,4) (3,4) (4,4)
     */

    @Test
    fun solution1() {
        val n = 3
        val left = 2L
        val right = 5L
        val expected = intArrayOf(3, 2, 2, 3)

        assertArrayEquals(expected, calculate(n, left, right))
        assertArrayEquals(expected, withNoArray(n, left, right))
        assertArrayEquals(expected, withNoArrayOptimized(n, left, right))
    }

    @Test
    fun solution2() {
        val n = 4
        val left = 7L
        val right = 14L
        val expected = intArrayOf(4, 3, 3, 3, 4, 4, 4, 4)

        assertArrayEquals(expected, calculate(n, left, right))
        assertArrayEquals(expected, withNoArray(n, left, right))
        assertArrayEquals(expected, withNoArrayOptimized(n, left, right))
    }


    fun calculate(n: Int, left: Long, right: Long): IntArray {
        val arrays = Array(n) { outerIndex ->
            IntArray(n) { innerIndex ->
                maxOf(innerIndex + 1, outerIndex + 1)
            }
        }
        val answer = IntArray((right - left + 1).toInt())

        for (i in left..right) {
            val row = i / n
            val col = i % n

            println("$row $col")

            answer[(i - left).toInt()] = arrays[row.toInt()][col.toInt()]
        }


        return answer
    }

    private fun withNoArray(n: Int, left: Long, right: Long): IntArray {
        val answer = IntArray((right - left + 1).toInt())

        for (i in left..right) {
            val row = i / n
            val col = i % n

            answer[(i - left).toInt()] = maxOf(row.toInt() + 1, col.toInt() + 1)
        }


        return answer
    }

    private fun withNoArrayOptimized(n: Int, left: Long, right: Long): IntArray =
        (left..right).map { maxOf((it / n) + 1, (it % n) + 1).toInt() }.toIntArray()
}