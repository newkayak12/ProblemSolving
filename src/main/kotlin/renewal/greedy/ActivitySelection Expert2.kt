package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `ActivitySelection Expert2` {
    /**
     *
     * 문제 설명
     * 	•	여러 활동이 있는데, 이번에는 활동마다 중요도가 추가되어 있습니다.
     * 	•	시작 시간과 종료 시간은 같고, 동시에 겹치지 않게 활동을 선택하되,
     * 	•	최대 중요도의 합을 구하는 문제입니다.
     *
     * ⸻
     *
     * 입력
     * 	•	N : 활동 개수 (1 ≤ N ≤ 100,000)
     * 	•	각 줄에 시작 시간, 종료 시간, 중요도 (모두 정수)
     *
     * ⸻
     *
     * 출력
     * 	•	겹치지 않는 활동들 중 중요도의 최대 합
     *
     * ⸻
     *
     * 예제 입력
     * 4
     * 1 3 10
     * 2 5 15
     * 4 6 10
     * 7 8 5
     * 예제 출력
     * 25
     */

    @Test
    fun solution() {
        val table = arrayOf(
            intArrayOf(1, 3, 10),
            intArrayOf(2, 5, 15),
            intArrayOf(4, 6, 10),
            intArrayOf(7, 8, 5),
        )

        val expected = 25
        assertEquals(expected, greedy(table))
        assertEquals(expected, dp(table))
    }

    private fun greedy(table: Array<IntArray>): Int {
        table.sortBy { it[1] }

        var totalScore = table.first()[2]
        var prev = table.first()[1]

        for (i in 1 until table.size) {
            val (start, end, score) = table[i]
            if (prev <= start) {
                totalScore += score
                prev = end
            }
        }

        return totalScore
    }

    private fun dp(table: Array<IntArray>): Int {
        table.sortBy { it[1] }
        val dp = IntArray(table.size)

        fun binarySearch(start: Int): Int {
            var left = 0
            var right = table.size - 1
            var result = -1
            while (left <= right) {
                val mid = (left + right) / 2

                if (table[mid][1] > start) {
                    right = mid - 1
                } else if (table[mid][1] <= start) {
                    left = mid + 1
                    result = mid
                }
            }

            return result
        }

        dp[0] = table[0][2]

        for (i in 1 until table.size) {
            val (start, end, score) = table[i]
            val idx = binarySearch(start)

            val result = score + if (idx != -1) dp[idx] else 0
            dp[i] = maxOf(dp[i - 1], result)
        }


        return dp[table.size - 1]
    }

}