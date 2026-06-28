package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class DiskController {
    /**
     * 디스크 컨트롤러 (프로그래머스)
     * 문제 설명
     * 하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다.
     * 디스크 컨트롤러를 구현하여 각 작업의 요청부터 종료까지의 시간의 평균을 가장 줄이려고 합니다.
     *
     *  입력:
     * jobs[i] = [요청시점, 소요시간]
     *
     *
     *  목표:
     * 평균 작업 완료시간을 최소화
     *
     * 입출력 예
     * jobs: [[0,3], [1,9], [2,6]]
     * return: 9
     */

    @Test
    fun solution() {
        val jobs = arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 9),
            intArrayOf(2, 6)
        )
        val expected = 9

        assertEquals(expected, pq(jobs))
    }

    private fun pq(jobs: Array<IntArray>): Int {
        val jobQueue = PriorityQueue<IntArray>(compareBy { it[0] })
        val availableQueue = PriorityQueue<IntArray>(compareBy { it[1] })

        var currentTime = 0
        var waitTime = 0


        for (element in jobs) {
            jobQueue.add(element)
        }

        while(jobQueue.isNotEmpty() || availableQueue.isNotEmpty()) {
            while(jobQueue.isNotEmpty() && jobQueue.peek()[0] <= currentTime) {
                availableQueue.add(jobQueue.poll())
            }

            if( availableQueue.isNotEmpty()) {
                val (start, duration) = availableQueue.poll()
                currentTime += duration
                waitTime += (currentTime - start)
            }
            else {
                currentTime = jobQueue.peek()[0]
            }
        }


        return waitTime / jobs.size
    }
}