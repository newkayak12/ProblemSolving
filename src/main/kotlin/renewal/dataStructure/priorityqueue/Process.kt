package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class Process {
    /**
     * 프로세스 (프로그래머스)
     * 문제 설명
     * 운영체제의 역할 중 하나는 컴퓨터 시스템의 자원을 효율적으로 관리하는 것입니다. 이 문제에서는 운영체제가 다음 규칙에 따라 프로세스를 관리한다고 가정합니다.
     * 실행 규칙:
     *
     * 실행 대기 큐에서 우선순위가 가장 높은 프로세스를 실행
     * 실행 중인 프로세스보다 높은 우선순위 프로세스가 있다면:
     *
     * 실행 중인 프로세스를 멈추고 맨 뒤로 보냄
     * 우선순위가 가장 높은 프로세스를 실행
     *
     *
     *
     * 목표: 특정 프로세스가 몇 번째로 실행되는지 구하기
     * 입출력 예
     * priorities: [2, 1, 3, 2]
     * location: 2 (인덱스 2번, 우선순위 3인 프로세스)
     * return: 1
     *
     * 실행 과정:
     * 1. [2,1,3,2] → 3이 최고 우선순위 → 인덱스 2 실행 (1번째)
     *
     *
     * priorities: [1, 1, 9, 1, 1, 1]
     * location: 0
     * return: 5
     *
     * 실행 과정:
     * 1. [1,1,9,1,1,1] → 9 실행
     * 2. [1,1,1,1,1] → 모두 동일하므로 순서대로 → 인덱스 0은 5번째
     */

    @Test
    fun solution1() {
        val priorities = intArrayOf(2, 1, 3, 2)
        val location = 2
        val expected = 1

        assertEquals(expected, pq(priorities, location))
    }

    @Test
    fun solution2() {
        val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
        val location = 0
        val expected = 2

        assertEquals(expected, pq(priorities, location))
    }

    private fun pq(priorities: IntArray, location: Int): Int {
        val pq = PriorityQueue(compareByDescending<Int> { priorities[it] }.thenBy { it })
        for(index in priorities.indices) {
            pq.add(index)
        }
        var count = 0

        while(pq.isNotEmpty()) {
            count ++
            if(pq.poll() == location) break
        }


        return count
    }
}