package programmers.dfs

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertArrayEquals

class Combination {
    /**
     * 2. 조합
     * 문제: [1,2,3,4]에서 2개를 선택하는 모든 조합
     *
     * 입력: [1,2,3,4], r=2
     * 출력: [[1,2], [1,3], [1,4], [2,3], [2,4], [3,4]]
     */

    @Test
    fun solution() {
        val input = intArrayOf(1, 2, 3, 4)
        val choice = 2
        val expected = arrayOf(intArrayOf(1,2), intArrayOf(1,3), intArrayOf(1,4), intArrayOf(2,3), intArrayOf(2,4), intArrayOf(3,4))


        assertArrayEquals(expected, calculate(input, choice))
    }

    private fun calculate(input: IntArray, choice: Int): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        useRecursive(0, input, choice, 0, mutableListOf(), result)

        return result.toTypedArray()
    }

    private fun useRecursive(
        start: Int,
        input: IntArray,
        choice: Int,
        count: Int,
        path: MutableList<Int>,
        result: MutableList<IntArray>
    ) {


        if(choice == count) {
            result.add(path.toIntArray())
            return
        }

        for(i in start until  input.size) {
            path.add(input[i])
            useRecursive(i + 1, input, choice, count + 1, path, result)
            path.removeLast()
        }
    }
}