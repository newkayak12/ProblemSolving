package programmers.lv2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class `하노이의 탑` {

    @Test
    fun solution1() {
        val n = 2;
        val result = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
        )

        Assertions.assertArrayEquals(result, calculate(n))
    }

    @Test
    fun solution2() {
        val n = 3;
        val result = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(1, 2),
            intArrayOf(3, 2),

            intArrayOf(1, 3),

            intArrayOf(2, 1),
            intArrayOf(2, 3),
            intArrayOf(1, 3),
        )

        Assertions.assertArrayEquals(result, calculate(n))
    }

    @Test
    fun solution3() {
        val n = 4;
        val result = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(1, 2),
            intArrayOf(3, 1),
            intArrayOf(3, 2),
            intArrayOf(1, 2),

            intArrayOf(1, 3),

            intArrayOf(2, 3),
            intArrayOf(2, 1),
            intArrayOf(3, 1),
            intArrayOf(2, 3),
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
        )

        Assertions.assertArrayEquals(result, calculate(n))
    }


    fun calculate(n: Int): Array<IntArray> {
        val list = mutableListOf<IntArray>()
        hanoi(n, 1, 3, 2, list)
        return list.toTypedArray()
    }

    private fun hanoi(n: Int, from: Int, to: Int, via: Int, array: MutableList<IntArray>) {
        if (n < 1) return
        hanoi(n - 1, from, via, to, array)
        move(from, to, array)
        hanoi(n - 1, via, to, from, array)
    }

    private fun move(from: Int, to: Int, array: MutableList<IntArray>) {
        array.add(intArrayOf(from, to))
    }
}