package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `행렬의 곱셈` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12949?language=kotlin
     *
     *  | 1, 4 |  |3, 3|
     *  | 3, 2 |  |3, 3|
     *  | 4, 1 |
     *
     *
     *  |2, 3, 2| |5, 4, 3|
     *  |4, 2, 4| |2, 4, 1|
     *  |3, 1, 4| |3, 1, 1|
     *
     */


    @Test
    fun solution1() {
        val arr1 = arrayOf(intArrayOf(1, 4), intArrayOf(3, 2), intArrayOf(4, 1))
        val arr2 = arrayOf(intArrayOf(3, 3), intArrayOf(3, 3))
        val expected = arrayOf(intArrayOf(15, 15), intArrayOf(15, 15), intArrayOf(15, 15))

        assertArrayEquals(expected, calculate(arr1, arr2))
    }


    @Test
    fun solution2() {
        val arr1 = arrayOf(intArrayOf(2, 3, 2), intArrayOf(4, 2, 4), intArrayOf(3, 1, 4))
        val arr2 = arrayOf(intArrayOf(5, 4, 3), intArrayOf(2, 4, 1), intArrayOf(3, 1, 1))
        val expected = arrayOf(intArrayOf(22, 22, 11), intArrayOf(36, 28, 18), intArrayOf(29, 20, 14))

        assertArrayEquals(expected, calculate(arr1, arr2))
    }

    private fun calculate(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        /**
         *  x,y = (0,y) * (x,0) + (1,y) * (x,1) + (2,y) * (x,2),
         *
         *  y,x = (y,0) * (0,x)
         *
         *  (0,0) = arr1[0][0] * arr2[0][0] + arr1[0][1] * arr2[1][0]
         */

        val array = Array(arr1.size) { IntArray(arr2[0].size) { 0 } }

        for (y in array.indices) {
            for (x in array[0].indices) {
                array[y][x] = arr1[0].indices.sumOf { arr1[y][it] * arr2[it][x] }
            }
        }

        return array
    }
}