package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class OptimizeDeliverySchedule {
    /**
     * 🟩 문제 이름: 🛒 배송 스케줄 최적화
     *
     * 📘 문제 설명
     * 당신은 한 마을의 배송 담당자입니다.
     * 각 배송 요청은 **도착일(start)**과 **배송 마감 기한(deadline)**이 주어집니다.
     * 하루에 한 번씩만 배송할 수 있고, 같은 날 여러 배송을 할 수 없습니다.
     * 가능한 한 많은 배송을 처리하려고 합니다.
     * ⸻
     * 📥 입력
     * 	•	첫째 줄: 배송 요청 수 N (1 ≤ N ≤ 1,000)
     * 	•	이후 N줄: 각 배송의 도착일 start, 마감일 deadline (1 ≤ start ≤ deadline ≤ 1,000)
     * ⸻
     * 📤 출력
     * 	•	처리할 수 있는 최대 배송 수
     * ⸻
     * 🧪 예제 입력
     * 5
     * 1 3
     * 2 2
     * 3 5
     * 4 4
     * 5 6
     * 🧪 예제 출력
     * 5
     */

    @Test
    fun solution() {
        val timetable = arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 2),
            intArrayOf(3, 5),
            intArrayOf(4, 4),
            intArrayOf(5, 6),
        )

        val expected = 5

        assertEquals(expected, greedy(timetable))
//        assertEquals(expected, greedyOther(timetable))
    }

    private fun greedy(timetable: Array<IntArray>): Int {
        timetable.sortWith(compareBy { it[0] + it[1] })
        var day = 1
        var count = 0

        for (table in timetable) {
            val start = table[0]
            val end = table[0] + table[1]

            if (day < start) {
                day = start
            }

            if (day in start..end) {
                count++
                day++
            }
        }

        return count
    }

    @Deprecated("불가")
    private fun greedyOther(originalTimetable: Array<IntArray>): Int {
        val timetable = originalTimetable.map { it.copyOf() }.toTypedArray()
        timetable.sortWith(compareBy({ it[0] }, { it[1] }))
        val queue = PriorityQueue<IntArray>(compareBy({ -it[0] }, { -it[1] }))
        val maxDay = timetable.maxOf { it[0] }
        var day = 1
        var count = 0
        var index = 0

        while (day <= maxDay) {

            while (index < timetable.size && day >= timetable[index][0]) {
                queue.offer(timetable[index++])
            }

            while (queue.isNotEmpty()) {
                val offer = queue.poll()
                val start = offer[0]
                val end = offer[0] + offer[1]

                if (day in start.. end) {
                    day++
                    count++
                }
            }

            if (queue.isEmpty()) {
                day++
            }
        }

        return count
    }
}