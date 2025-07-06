package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketSelling {
    /**
     * 🟩 문제 이름: 🎟️ 티켓 판매
     *
     * 📘 문제 설명
     * 한 가게에서는 매일 일정 수의 티켓을 판매하고 있습니다.
     * 각 손님은 도착 시간과 기다릴 수 있는 최대 시간(대기 한도)이 주어져 있으며, 가장 빨리 가능한 시간에 티켓을 받을 수 있다면 바로 사갑니다.
     * 티켓은 한 번에 한 사람에게만 판매할 수 있습니다.
     * 모든 손님이 가능한 한 티켓을 받을 수 있도록, 손님들을 정렬하여 판매를 진행할 때, 몇 명이 티켓을 받을 수 있는지 구하세요.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	첫째 줄: 손님 수 N (1 ≤ N ≤ 1,000)
     * 	•	이후 N줄: 각 손님의 도착 시간 arrival, 기다릴 수 있는 최대 시간 wait (1 ≤ arrival ≤ 1,000 / 1 ≤ wait ≤ 1,000)
     *
     * ⸻
     *
     * 📤 출력
     * 	•	티켓을 받을 수 있는 최대 손님 수
     *
     * ⸻
     *
     * 🧪 예제 입력1
     * 4
     * 1 2
     * 2 2
     * 4 1
     * 5 1
     *
     * 🧪 예제 출력1
     * 4
     *
     * 🧪 예제 입력2
     * 5
     * 1 2
     * 3 1
     * 2 3
     * 4 2
     * 5 1
     * 🧪 예제 출력1
     * 5
     */

    @Test
    fun solution1() {
        val timetable = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 2),
            intArrayOf(4, 1),
            intArrayOf(5, 1),
        )
        val expected = 4

        assertEquals(expected, greedy(timetable))
    }

    @Test
    fun solution2() {
        val timetable = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 1),
            intArrayOf(2, 3),
            intArrayOf(4, 2),
            intArrayOf(5, 1),
        )
        val expected = 5

        assertEquals(expected, greedy(timetable))
    }

    @Test
    fun solution3() {
        val timetable = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(5, 1),
        )
        val expected = 4

        assertEquals(expected, greedy(timetable))
    }


    private fun greedy(timetable: Array<IntArray>): Int {
        timetable.sortWith(compareBy { it[0] + it[1] })
        var day = 1
        var count = 0

        for (time in timetable) {
            val start = time[0]
            val end = time[0] + time[1]

            if (start > day) {
                day = start
            }

            if( day in start..end) {
                count ++
                day ++
            }
        }

        return count
    }
}