package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.collections.ArrayDeque

class MeetingRoomMaximum {
    /**
     * 🟩 회의실 배정 문제
     *
     * ✅ 문제 설명
     * 한 개의 회의실이 있습니다.
     * N개의 회의가 있고, 각 회의마다 시작 시간과 끝나는 시간이 주어집니다.
     * 각 회의는 한 번에 한 개씩만 진행할 수 있으며,
     * 회의가 끝나는 시간과 다음 회의의 시작 시간은 같아도 됩니다.
     *
     * 👉 회의실을 사용할 수 있는 회의의 최대 개수를 구하세요.
     *
     * ⸻
     *
     * ✅ 입력
     * 	•	첫째 줄: 회의의 개수 N (1 ≤ N ≤ 100,000)
     * 	•	둘째 줄부터 N개의 줄에: 회의의 시작 시간과 끝나는 시간이 주어집니다.
     * 	•	시작 시간과 끝나는 시간은 0 이상 2^31-1 이하의 정수입니다.
     *
     * ✅ 출력
     * 	•	회의실에서 진행할 수 있는 최대 회의 개수를 출력하세요.
     *
     * ✅ 예제 입력
     * 회의 개수: 5
     * | 시작 | 끝 |
     * |  1  | 4 |
     * |  2  | 3 |
     * |  3  | 5 |
     * |  0  | 6 |
     * |  5  | 7 |
     *
     * ✅ 예제 출력
     * 3
     */
    @Test
    fun solution(){
        val timetable: Array<IntArray> = arrayOf(
                intArrayOf(1, 4),
                intArrayOf(2, 3),
                intArrayOf(3, 5),
                intArrayOf(0, 6),
                intArrayOf(5, 7)
            )
        val excepted = 3

        /**
         * 2-3 -> 3-5 -> 5-7로 총 3개로 예상
         *
         * 1. 이전 시간과 겹치는지 확인
         *  1. 겹치면 시간 체크
         *      1. running 타임 비교
         *  2. 안겹치면 추가
         */

        assertEquals(excepted, useGreedy(timetable))
        assertEquals(excepted, optimizeUseGreedy(timetable))
    }

    private fun useGreedy(timetable: Array<IntArray>): Int {
        val deque = ArrayDeque<IntArray>();
        deque.add(timetable[0])

        for( i in 1 ..< timetable.size) {
            val before = deque.last()

            if( before[1] <= timetable[i][0]) {
                deque.add(timetable[i])
            }
            else {
                val beforeRunningTime = before[1] - before[0]
                val nowRunningTime = timetable[i][1] - timetable[i][0]

                if(
                    nowRunningTime < beforeRunningTime ||
                    nowRunningTime == beforeRunningTime && before[1] > timetable[i][1]
                ){
                    deque.removeLast()
                    deque.add(timetable[i])
                }
            }
        }


        return deque.size
    }

    private fun optimizeUseGreedy(timetable: Array<IntArray>): Int {
        timetable.sortBy { it[1] }
        var count = 1
        var lastEndTime = timetable[0][1]

        for( i in 1 until  timetable.size) {
            if(timetable[i][0] >= lastEndTime) {
                count ++
                lastEndTime = timetable[i][1]
            }
        }

       return count
    }
}