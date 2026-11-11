package programmers.dfs

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class Permutation {
    /**
     * 문제: [1,2,3]의 모든 순열을 출력하라
     *
     * 입력: [1,2,3]
     * 출력: [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]
     */

    @Test
    fun solution() {
        val input = intArrayOf(1, 2, 3)
        val expected = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 1, 3),
            intArrayOf(2, 3, 1),
            intArrayOf(3, 1, 2),
            intArrayOf(3, 2, 1)
        )

        assertArrayEquals(expected, calculate(input))
    }


    private fun calculate(input: IntArray): Array<IntArray> {

        val result = mutableListOf<IntArray>()
        recursive(input, BooleanArray(input.size), mutableListOf(), result)
        return result.toTypedArray()
    }

    private fun recursive(
        input: IntArray,
        visit: BooleanArray,
        mutableList: MutableList<Int>,
        result: MutableList<IntArray>
    ) {

        if(input.size == mutableList.size) {
            result.add(mutableList.toIntArray())
            return
        }

        for((index, value) in input.withIndex()) {
            if(visit[index]) continue
            mutableList.add(value)
            visit[index] = true
            recursive(input, visit, mutableList, result)
            mutableList.removeLast()
            visit[index] = false
        }


    }
}