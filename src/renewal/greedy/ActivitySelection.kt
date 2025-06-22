package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ActivitySelection {
    /**
     * 문제: 활동 선택 (Activity Selection)
     *
     * 여러 개의 활동이 있고, 각 활동은 시작 시간과 종료 시간이 정해져 있습니다.
     * 한 사람이 겹치지 않게 수행할 수 있는 활동의 최대 개수를 구하세요.
     *
     * ⸻
     *
     * 입력
     * 	•	n: 활동의 개수 (1 ≤ n ≤ 100,000)
     * 	•	다음 n개의 줄에는 각각 활동의 시작 시간과 종료 시간이 주어집니다. (시작 시간 < 종료 시간)
     *
     * ⸻
     *
     * 출력
     * 	•	겹치지 않게 선택할 수 있는 최대 활동 개수를 출력하세요.
     *
     * 예시 입력
     * 활동의 개수 : 3
     * 시작  종료
     *  1    4
     *  3    5
     *  0    6
     *
     * 예시 출력
     * 1
     */

    @Test
    fun solution(){
        val activity = 3
        val table = arrayOf(
            intArrayOf(1, 4),
            intArrayOf(3, 5),
            intArrayOf(0, 6)
        )
        val expected = 1


        assertEquals(expected, greedy(table))
    }

    fun greedy(table: Array<IntArray>): Int {
        table.sortBy { it[1] }

        var count = 1;
        var end = table.first().last()

        for(i in 1 until table.size){
            if(end <= table[i][0]) {
                count ++
                end = table[i][1]
            }
        }

        return count
    }
}