package programmers.dfs

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Graph {
    /**
     * ### 예시 문제: 네트워크
     *
     * **문제**:
     * n개의 컴퓨터가 있습니다.
     * 연결 정보가 담긴 2차원 배열 computers가 주어질 때,
     * 네트워크의 개수를 return 하세요.
     *
     * **제한사항**:
     * - n: 1~200
     * - computers[i][j]: i와 j 연결 여부 (1: 연결, 0: 미연결)
     * - computers[i][i]는 항상 1
     *
     * **입출력 예**:
     * ```
     * n: 3
     * computers: [[1,1,0],
     *             [1,1,0],
     *             [0,0,1]]
     * return: 2
     * // 0-1 연결, 2 독립 → 2개 네트워크
     * ```
     */

    @Test
    fun solution() {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 1)
        )
        val expected = 2


        assertEquals(expected, calculate(n, computers))
    }


    fun calculate(n: Int, computers: Array<IntArray>): Int {
        val visit = BooleanArray(n)
        var count = 0

        for (i in 0 until n) {
            if (visit[i]) continue

            visit[i] = true
            useRecursive(i, computers, visit)
            count++
        }

        return count
    }

    private fun useRecursive(node: Int, computers: Array<IntArray>, visit: BooleanArray) {

        for ((index, value) in computers[node].withIndex()) {
            if (index == node) continue
            if (visit[index]) continue

            if (value == 1) {
                visit[index] = true
                useRecursive(index, computers, visit)
            }
        }
    }
}