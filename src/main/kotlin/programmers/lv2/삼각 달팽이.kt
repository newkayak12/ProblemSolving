package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `삼각 달팽이` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/68645?language=kotlin
     *
     * n = 1
     *     1
     *
     * n = 2
     *
     *     1
     *     2 3
     *  2 * 2 - 1
     * n = 3
     *
     *     1
     *     2 6
     *     3 4 5
     *   3 * 3 - 3
     * n = 4
     *
     *      1
     *      2  9
     *      3 10 8
     *      4  5 6 7
     *    4 * 4 - 6
     * n = 5
     *      1
     *      2 12
     *      3 13 11
     *      4 14 15 10
     *      5  6  7  8  9
     *    5 * 5 - 10
     * n = 6
     *      1
     *      2 15
     *      3 16 14
     *      4 17 21 13
     *      5 18 19 20  12
     *      6  7  8  9  10  11
     *    6 * 6 - 16
     *
     *    6 -> 4 -> 4 -> 3 -> 2 -> 1
     */

    //패턴 시작은 1
    //맨 왼쪽 아래는 n
    //맨 오른쪽 아래는 n + (n - 1)

    @Test
    fun solution0() {
        val n = 1
        val expected = intArrayOf(1)

        assertArrayEquals(expected, calculate(n))
    }

    @Test
    fun solution1() {
        val n = 4
        val expected = intArrayOf(1, 2, 9, 3, 10, 8, 4, 5, 6, 7)

        assertArrayEquals(expected, calculate(n))
    }

    @Test
    fun solution2() {
        val n = 5
        val expected = intArrayOf(1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9)

        assertArrayEquals(expected, calculate(n))
    }

    @Test
    fun solution3() {
        val n = 6
        val expected = intArrayOf(1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11)

        assertArrayEquals(expected, calculate(n))
    }

    @Test
    fun solution4() {
        val n = 3
        val expected = intArrayOf(1, 2, 6, 3, 4, 5)

        assertArrayEquals(expected, calculate(n))
//        assertArrayEquals(expected, edge(n))
    }

    private fun calculate(n: Int): IntArray {
        val array = Array(n) { IntArray(n) }


        //내려가는거
        //우측으로 가는거
        //왼쪽 대각선으로 올라가는거

        var movement = n
        var i = 1
        var x = 0
        var y = 0
        while (movement > 0) {

            while (y in 0 until n && array[y][x] == 0) {
                array[y++][x] = i++
            }
            movement -= 1

            y--
            x++

            while (x in 0 until n && array[y][x] == 0) {
                array[y][x++] = i++
            }
            movement -= 1

            x -= 2
            y--

            while (
                x in 0 until n &&
                y in 0 until n &&
                array[y][x] == 0
            ) {
                array[y--][x--] = i++
            }
            movement -= 1

            y += 2
            x++
        }

        return array.flatMap { it.filter { element -> element != 0 } }.toIntArray()
    }

    fun edge(n :Int): IntArray{
        val array = Array(n) { IntArray(n) }


        //내려가는거
        //우측으로 가는거
        //왼쪽 대각선으로 올라가는거

        var i = 1
        var x = 0
        var y = 0
        var enable = true
        while (enable) {

            enable = false
            //내려가는거
            while (y in 0 until n && array[y][x] == 0) {
                array[y++][x] = i++
                enable = true
            }

            y--
            x++

            while (x in 0 until n && array[y][x] == 0) {
                array[y][x++] = i++
                enable = true
            }

            x -= 2
            y--

            while (
                x in 0 until n &&
                y in 0 until n &&
                array[y][x] == 0
            ) {
                array[y--][x--] = i++
                enable = true
            }

            y += 2
            x++
        }


        return array.flatMap { it.filter { element -> element != 0 } }.toIntArray()
    }
}
