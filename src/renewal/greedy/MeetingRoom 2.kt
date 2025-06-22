package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `MeetingRoom 2` {
    /**
     * 알겠습니다. 회의실 배정 문제부터 진행해드리겠습니다.
     *
     * ⸻
     *
     * 문제 이름: 🏢 회의실 배정
     *
     * 문제 설명
     * 	•	여러 개의 회의가 있고, 각 회의는 시작 시간과 종료 시간이 주어진다.
     * 	•	동시에 한 회의실에서 두 개 이상의 회의를 열 수 없다고 할 때, 하나의 회의실에서 가능한 한 많은 회의를 열 수 있도록 회의실 배정 개수를 구하시오.
     *
     * 입력
     * 	•	첫째 줄: 회의 수 N (1 ≤ N ≤ 1,000)
     * 	•	이후 N줄: 각 회의의 시작 시간 s, 종료 시간 e (0 ≤ s < e ≤ 1,000)
     *
     * 출력
     * 	•	하나의 회의실에서 가능한 회의의 최대 개수
     *
     * 입력
     * 5
     * 1 4
     * 2 3
     * 3 5
     * 0 6
     * 5 7
     *
     * 출력
     *  3
     */

    @Test
    fun solution() {
        val timetable = arrayOf(
            intArrayOf(1, 4),
            intArrayOf(2, 3),
            intArrayOf(3, 5),
            intArrayOf(0, 6),
            intArrayOf(5, 7),
        )

        val expected = 3
        assertEquals(expected, greedy(timetable))
    }

    private fun greedy(timetable: Array<IntArray>): Int {
        timetable.sortWith(compareBy { it[1] })
        var last = 0
        var count = 0

        for (table in timetable) {

            if( last <= table[0]) {
                count ++
                last = table[1]
            }
        }

        return count
    }
}