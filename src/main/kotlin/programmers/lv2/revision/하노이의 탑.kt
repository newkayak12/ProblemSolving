package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `하노이의 탑` {
    /**
     *
     * A 3, 2, 1
     * B
     * C
     * -----------------
     * A 3, 2
     * B 1
     * C
     * -----------------
     * A 3
     * B 1
     * C 2
     * -----------------
     * A 3
     * B
     * C 2, 1
     * -----------------
     * A 3, 1
     * B
     * C 2
     * -----------------
     * A 3, 1
     * B 2
     * C
     * -----------------
     * A 3
     * B 2, 1
     * C
     * -----------------
     * A
     * B 2, 1
     * C 3
     * -----------------
     * A 1
     * B 2
     * C 3
     * -----------------
     * A 1
     * B
     * C 3, 2
     * -----------------
     * A
     * B
     * C 3, 2, 1
     * -----------------
     */

    @Test
    fun test1() {
        val n = 2
        val result = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3)
        )

        assertArrayEquals(result, solution(n))
    }


    fun solution(n: Int): Array<IntArray> {
        val memory = mutableListOf<IntArray>()
        hanoi(n, 1, 3, 2, memory)
        return memory.toTypedArray()
    }

    private fun hanoi(n: Int, from: Int, to: Int, via: Int, memory: MutableList<IntArray>) {
        if (n == 0) return
        hanoi(n - 1, from, via, to, memory)
        move(from, to, memory)
        hanoi(n - 1, via, to, from, memory)
    }

    private fun move(from: Int, to: Int, memory: MutableList<IntArray>) {
        memory.add(intArrayOf(from, to))
    }
}