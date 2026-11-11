package renewal.dataStructure.queue

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Print {
    /**
     * 📘 실습 문제 – 프린터 큐 시뮬레이션 (우선순위 큐 X, 일반 큐 버전)
     *
     * ⸻
     *
     * 🧾 문제 설명
     *
     * 여러 문서가 인쇄 대기 중입니다.
     * 큐에는 문서들이 순서대로 들어 있으며,
     * 사용자는 특정 문서가 언제 인쇄되는지 알고 싶어합니다.
     *
     * 단, 이번 버전은 “우선순위 고려하지 않고”, 그냥 순서대로 인쇄된다고 가정합니다.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	총 문서 수 N (1 ≤ N ≤ 1000)
     * 	•	확인하고 싶은 문서의 위치 targetIndex (0-based)
     * 	•	문서 큐: List<String> 형태로 문서 이름이 들어 있음
     *
     * 	N = 5
     * targetIndex = 2
     * documents = listOf("A", "B", "C", "D", "E")
     *
     * 🧾 출력 형식
     * 	•	타겟 문서(targetIndex)가 몇 번째로 인쇄되는지 출력
     *
     * ⸻
     *
     * 📌 예시 1
     * 입력:
     * N = 5
     * targetIndex = 2
     * documents = [A, B, C, D, E]
     *
     * 출력:
     * 3
     *
     * 📌 예시 2
     * 입력:
     * N = 4
     * targetIndex = 0
     * documents = [Z, A, B, C]
     *
     * 출력:
     * 1
     */

    @Test
    fun solution1(){
        val N = 5
        val targetIndex = 2
        val documents = listOf("A", "B", "C", "D", "E")
        val expected = 3
        assertEquals(expected, useQueue(N, targetIndex, documents))
    }

    @Test
    fun solution2(){
        val N = 4
        val targetIndex = 0
        val documents = listOf("Z", "A", "B", "C")
        val expected = 1
        assertEquals(expected, useQueue(N, targetIndex, documents))
    }

    private fun useQueue(size: Int, targetIndex: Int, documents: List<String>): Int {
        val queue = ArrayDeque<Int>()
        var sequence = 1;
        for(i in  0 until size){
            queue.addLast(i)
        }

        while (documents[queue.first()] != documents[targetIndex]) {
            queue.removeFirst()
            sequence ++
        }

        return sequence
    }
}