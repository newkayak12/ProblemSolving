package renewal.dataStructure.queue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class PrintPriorityQueue {
    /**
     * 📘 실전 문제 – 프린터 큐 (우선순위 기반)
     *
     * ⸻
     *
     * 🧾 문제 설명
     *
     * 여러 개의 문서가 프린터 대기열에 있습니다.
     * 각 문서마다 **중요도(우선순위)**가 주어지고, 프린터는 다음 규칙에 따라 인쇄합니다:
     * 	1.	큐의 가장 앞에 있는 문서를 꺼내서,
     * 	2.	자기보다 중요도가 높은 문서가 큐 안에 있다면 → 다시 맨 뒤로 보냅니다.
     * 	3.	그렇지 않으면 → 그 문서를 인쇄합니다.
     *
     * 내가 요청한 문서가 몇 번째로 인쇄되는지 구하세요.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	문서 개수 N (1 ≤ N ≤ 100)
     * 	•	타겟 문서 인덱스 targetIndex (0-based)
     * 	•	문서 중요도 리스트 priorities: List<Int>
     *   ex: [1, 1, 9, 1, 1, 1]
     *
     * ⸻
     *
     * 📤 출력 형식
     * 	•	타겟 문서가 몇 번째로 인쇄되는지 출력 (1-based index)
     *
     * 	📌 예시 1
     * N = 6
     * targetIndex = 2
     * priorities = [1, 1, 9, 1, 1, 1]
     *
     * → 출력: 1
     * 	C(index=2, 중요도 9) → 바로 인쇄됨
     *
     * 	📌 예시 2
     * N = 4
     * targetIndex = 2
     * priorities = [1, 2, 3, 4]
     *
     * → 출력: 2
     * → D(4) 먼저 인쇄됨 → 그다음 C(3) 인쇄됨 → C는 2번째로 인쇄됨
     *
     *
     */


    @Test
    fun solution1(){
        val N = 6
        val targetIndex = 2
        val priorities = listOf(1, 1, 9, 1, 1, 1)
        val expected = 1

        assertEquals(expected, usePriorityQueue(N, targetIndex, priorities))
    }


    @Test
    fun solution2(){
        val N = 4
        val targetIndex = 2
        val priorities = listOf(1, 2, 3, 4)
        val expected = 2

        assertEquals(expected, usePriorityQueue(N, targetIndex, priorities))
    }

    private fun usePriorityQueue(size: Int, targetIndex: Int, priorities: List<Int>): Int {
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareByDescending{it.second})

        for((index, value) in priorities.withIndex()) {
            priorityQueue.add(index to value)
        }

        var sequence = 1
        while(priorityQueue.peek().first != targetIndex) {
            priorityQueue.poll()
            sequence += 1
        }

        return sequence
    }
}