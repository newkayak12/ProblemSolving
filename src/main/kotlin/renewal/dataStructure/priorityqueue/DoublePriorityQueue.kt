package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class DoublePriorityQueue {
    /**
     * 이중우선순위큐 (프로그래머스)
     * 문제 설명
     * 이중우선순위큐는 다음 연산을 지원하는 자료구조입니다.
     *
     *  명령어:
     * - I 숫자 - 큐에 주어진 숫자를 삽입
     * - D 1 - 큐에서 최댓값을 삭제
     * - D -1 - 큐에서 최솟값을 삭제
     *
     * 제한사항:
     * - 빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시
     * - 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 반환
     * - 비어있지 않으면 [최댓값, 최솟값] 반환
     *
     * 입출력 예
     * operations: ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]
     * return: [0, 0]
     *
     * 과정:
     * 1. I 16 → [16]
     * 2. I -5643 → [-5643, 16]
     * 3. D -1 → [16] (최솟값 -5643 삭제)
     * 4. D 1 → [] (최댓값 16 삭제)
     * 5. D 1 → [] (빈 큐라 무시)
     * 6. I 123 → [123]
     * 7. D -1 → [] (최솟값 123 삭제)
     * 최종: 빈 큐 → [0, 0]
     */


    @Test
    fun solution() {
        val operations = arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1")
        val expected = intArrayOf(0, 0)

        assertArrayEquals(expected, pq(operations))
    }

    private fun pq(operations: Array<String>): IntArray {
        val minPq = PriorityQueue<Int>(compareBy { it })
        val maxPq = PriorityQueue<Int>(compareByDescending { it })
        val result = IntArray(2)



        for (element in operations) {
            val split = element.split(" ")
            val operation = split[0]
            val number = split[1].toInt()


            when (operation) {
                "I" -> {
                    minPq.offer(number)
                    maxPq.offer(number)
                };
                "D" -> {

                    if (number == -1 && minPq.isNotEmpty()) {
                        maxPq.remove(minPq.poll())
                    }
                    if (number == 1 && maxPq.isNotEmpty()) {
                        minPq.remove(maxPq.poll())
                    }
                }
            }
        }

        if(maxPq.isNotEmpty()) {
            result[0]  = maxPq.poll()
        }
        if(minPq.isNotEmpty()) {
            result[1]  = minPq.poll()
        }

        return result
    }
}