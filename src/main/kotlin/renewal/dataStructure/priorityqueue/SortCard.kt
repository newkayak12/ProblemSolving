package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class SortCard {
    /**
     * 카드 정렬하기 (백준 1715번)
     * 문제 설명
     * 정렬된 두 묶음의 숫자 카드가 있을 때, 각 묶음의 카드의 수를 A, B라 하면 보통 두 묶음을 합쳐서 하나로 만드는 데에는 A+B 번의 비교가 필요하다.
     * 예를 들어 20장의 카드 묶음과 30장의 카드 묶음을 합치려면 50번의 비교가 필요하다.
     * 매우 많은 카드 묶음을 차례로 합쳐나가며 하나의 묶음으로 만들려고 한다. 최종적으로 필요한 최소 비교 횟수를 구하는 프로그램을 작성하시오.
     * 입출력 예
     * 카드 묶음: [10, 20, 40]
     * 최적 순서:
     * 1. 10 + 20 = 30 (비교 30회)
     * 2. 30 + 40 = 70 (비교 70회)
     * 총 비교: 30 + 70 = 100회
     *
     * vs 다른 순서:
     * 1. 20 + 40 = 60 (비교 60회)
     * 2. 10 + 60 = 70 (비교 70회)
     * 총 비교: 60 + 70 = 130회
     *
     * → 첫 번째가 더 효율적!
     */


    @Test
    fun solution() {
        val pileOfCards = intArrayOf(10, 20, 40)
        val expected = 100

        assertEquals(expected, pq(pileOfCards))
    }

    private fun pq(pileOfCards: IntArray): Int {
        val pq = PriorityQueue<Int>(compareBy { it })
        var result = 0

        for (cards in pileOfCards) {
            pq.offer(cards)
        }

        while (pq.isNotEmpty() && pq.size > 1) {

            // p1 + p2 + ((p1 + p2) + p3)
            val number = pq.poll() + pq.poll()
            result += number
            pq.offer(number)

        }

        return result
    }
}