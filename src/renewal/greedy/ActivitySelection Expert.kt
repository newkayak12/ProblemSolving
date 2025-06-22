package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `ActivitySelection Expert` {
    /**
     * 문제: 활동 선택 문제 (Activity Selection Problem)
     *
     * 여러 개의 활동이 있고, 각 활동은 시작 시간과 종료 시간이 정해져 있습니다.
     * 한 사람이 겹치지 않게 수행할 수 있는 활동의 최대 개수를 구하세요.
     *
     * ⸻
     *
     * 입력
     * 	•	첫째 줄에 활동의 개수 n이 주어집니다. (1 ≤ n ≤ 100,000)
     * 	•	다음 n개의 줄에는 각 활동의 시작 시간 s와 종료 시간 e가 공백으로 구분되어 주어집니다. (1 ≤ s < e ≤ 10^9)
     *
     * ⸻
     *
     * 출력
     * 	•	겹치지 않게 선택할 수 있는 최대 활동 개수를 출력하세요.
     *
     * 	예시 입력
     * 	8
     * 1 3
     * 2 5
     * 4 7
     * 6 9
     * 8 10
     * 9 11
     * 11 13
     * 12 14
     *
     *  예시 출력
     *  4
     */

    @Test
    fun solution() {
        val activity = 8
        val table = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 5),
            intArrayOf(4, 7),
            intArrayOf(6, 9),
            intArrayOf(8, 10),
            intArrayOf(9, 11),
            intArrayOf(11, 13),
            intArrayOf(12, 14),
        )
        val expected = 4


        assertEquals(expected, greedy(table))
    }

    fun greedy(table: Array<IntArray>): Int {
        table.sortBy { it[1] }

        var count = 1;
        var end = table.first().last()

        for (i in 1 until table.size) {
            if (end <= table[i][0]) {
                count++
                end = table[i][1]
            }
        }

        return count
    }
}