package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class MeetingRoomMinimum {
    /**
     * 🟩 패턴: 정렬 기반 순서 결정
     *
     * 2. 최소 회의실 문제
     *
     * 💡 문제 이름
     * 🪑 최소 회의실 개수
     *
     * 📘 문제 설명
     * 여러 개의 회의가 주어져 있고, 각 회의는 시작 시간과 종료 시간이 있습니다.
     * 동시에 여러 회의를 열 수 있지만, 같은 회의실에서 겹치는 회의는 불가능합니다.
     * 모든 회의를 열기 위해 필요한 최소 회의실 수를 구하세요.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	첫째 줄: 회의 수 N (1 ≤ N ≤ 1,000)
     * 	•	이후 N줄: 각 회의의 시작 시간, 종료 시간 (0 ≤ s < e ≤ 1,000)
     *
     * ⸻
     *
     * 📤 출력
     * 	•	필요한 최소 회의실의 수
     *
     * ⸻
     *
     * 🧪 예제 입력
     * 3
     * 0 5
     * 1 2
     * 1 10
     *
     * 🧪 예제 출력
     * 3
     */

    @Test
    fun solution() {
        val timetable = arrayOf(
            intArrayOf(0, 5),
            intArrayOf(1, 2),
            intArrayOf(1, 10)
        )

        val expected = 3

        assertEquals(expected, greedy((timetable)))
    }

    private fun greedy(timetable: Array<IntArray>): Int {
        timetable.sortWith(compareBy({ it[0] }, { it[1] }))
        val queue = PriorityQueue<IntArray>(compareBy { it[1] })
        queue.add(timetable[0])

        for (i in 1 until timetable.size) {
            if (queue.peek()[1] <= timetable[i][0]) {
                queue.poll()
            }
            queue.add(timetable[i])
        }


        return timetable.size
    }
}