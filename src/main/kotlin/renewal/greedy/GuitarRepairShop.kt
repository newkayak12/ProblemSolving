package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class GuitarRepairShop {
    /**
     * 🟩 문제 이름: 🎸 기타 수리
     *
     * 📘 문제 설명
     * 당신은 기타 수리점의 기사입니다.
     * 각 손님은 **도착일(start)**과 **최대 기다릴 수 있는 기간(wait)**을 가지고 있습니다.
     * 하루에 기타 하나만 수리할 수 있으며, 손님은 자신이 도착한 날부터 기다릴 수 있는 날짜까지 수리를 맡기고 싶어 합니다.
     *
     * 모든 손님의 기타를 최대한 많이 수리하려고 합니다.
     * 당신이 수리 가능한 기타의 개수를 구하세요.
     * ⸻
     * 📥 입력
     * 	•	첫째 줄: 손님 수 N (1 ≤ N ≤ 1,000)
     * 	•	이후 N줄: 각 손님의 도착일 start, 최대 대기일 wait (1 ≤ start ≤ 1,000, 1 ≤ wait ≤ 1,000)
     * ⸻
     * 📤 출력
     * 	•	수리할 수 있는 기타의 최대 개수
     * ⸻
     * 🧪 예제 입력
     * 5
     * 1 2
     * 3 1
     * 2 3
     * 4 2
     * 5 1
     * 🧪 예제 출력
     * 5
     */

    @Test
    fun solution() {
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
    fun solution1() {
        val timetable = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 2),
            intArrayOf(2, 1),
            intArrayOf(2, 1),
            intArrayOf(1, 2),
        )

        val expected = 3

        assertEquals(expected, greedy(timetable))
    }

    @Test
    fun solution2() {
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


    @Deprecated("손님들이 서로 범위가 겹치면 처리할 수 없다.")
    private fun greedyOther(timetable: Array<IntArray>): Int {
        timetable.sortWith(compareBy({ it[0] }, { it[1] }))
        val queue = PriorityQueue<IntArray>(compareBy({ -it[0] }, { -it[1] }))
        val maxDay = timetable.maxOf { it[0] }
        var day = 1
        var index = 0
        var count = 0

        while (day <= maxDay) {
            while (index < timetable.size && timetable[index][0] <= day) {
                queue.offer(timetable[index])
                index++
            }

            while (queue.isNotEmpty()) {
                val poll = queue.poll()
                val start = poll[0]
                val end = poll[0] + poll[1]

                if (day in start.. end) {
                    day ++
                    count ++
                }
            }

            if(queue.isEmpty()) {
                day ++
            }
        }


        return count
    }
}