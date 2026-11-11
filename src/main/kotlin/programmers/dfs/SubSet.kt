package programmers.dfs

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class SubSet {
    /**
     * 3. 부분집합
     * 문제: [1,2,3]의 모든 부분집합
     *
     * 입력: [1,2,3]
     * 출력: [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
     */

    @Test
    fun solution() {
        val input = intArrayOf(1, 2, 3)
        val expected = arrayOf(
            intArrayOf(),
            intArrayOf(1),
            intArrayOf(2),
            intArrayOf(3),
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3),
            intArrayOf(1, 2, 3)
        )

        assertArrayEquals(expected, calculate(input))
    }

    private fun calculate(input: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        useRecursive(input, 0, mutableListOf(), result)


        return result.toTypedArray()
    }

    private fun useRecursive(input: IntArray, start: Int, path: MutableList<Int>, result: MutableList<IntArray>) {

        result.add(path.toIntArray())

        for(index in start until input.size) {
            path.add(input[index])
            useRecursive(input, index + 1, path, result)
            path.removeLast()
        }

    }
}