package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.test.assertEquals

class NightWork {
    /**
     * 야근 지수 (프로그래머스)
     * 문제 설명
     * 회사원 Demi는 가끔은 야근을 하는데요, 야근을 하면 야근 피로도가 쌓입니다.
     * 야근 피로도는 야근을 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값입니다.
     * 목표: 남은 n시간 동안 야근 피로도를 최소화하려고 합니다.
     * 제한사항:
     *
     * works 배열의 크기: 1 ≤ length ≤ 20,000
     * 각 작업량: 1 ≤ works[i] ≤ 50,000
     * 1 ≤ n ≤ 1,000,000
     *
     * 입출력 예
     * works: [4, 3, 3]
     * n: 4
     * return: 12
     *
     * 과정:
     * 1시간: [3, 3, 3] (4에서 1 감소)
     * 2시간: [2, 3, 3] (3에서 1 감소)
     * 3시간: [2, 2, 3] (3에서 1 감소)
     * 4시간: [2, 2, 2] (3에서 1 감소)
     *
     * 야근 지수: 2² + 2² + 2² = 12
     */

    @Test
    fun solution() {
        val works = intArrayOf(4, 3, 3)
        val n = 4
        val expected = 12


        assertEquals(expected, pq(works, n))
    }

    private fun pq(works: IntArray, n: Int): Int {
        val pq = PriorityQueue<Int>(compareByDescending { it })
        for (element in works) {
            pq.offer(element)
        }

        for (i in 0 until n) {
            val number = pq.poll()
            if(number <= 0) break
            pq.offer( number - 1)
        }


        var result = 0
        while (pq.isNotEmpty()) {
            result += pq.poll().toDouble().pow(2.0).toInt()
        }

        return result
    }
}