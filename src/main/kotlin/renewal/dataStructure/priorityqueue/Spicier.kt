package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class Spicier {
    /**
     * 더 맵게 (프로그래머스)
     * 문제 설명
     * 매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
     *
     *  섞는 규칙:
     * - 가장 맵지 않은 음식 + (두 번째로 맵지 않은 음식 × 2)
     *
     *  제한사항:
     *
     * - 스코빌 지수 배열 scoville (2 ≤ length ≤ 1,000,000)
     * - K (0 ≤ K ≤ 1,000,000,000)
     * - 모든 음식을 K 이상으로 만들 수 없는 경우 -1 반환
     *
     * 입출력 예
     * scoville: [1, 2, 3, 9, 10, 12]
     * K: 7
     * return: 2
     */

    @Test
    fun solution() {
        val scoville = listOf(1, 2, 3, 9, 10, 12)
        val K = 7

        val expected = 2
        assertEquals(expected, pq(scoville, K))
    }

    private fun pq(scoville: List<Int>, K: Int): Int {
        val pq = PriorityQueue<Int>(compareBy { it })
        var count  = 0
        for(element in scoville){
            pq.add(element)
        }

        while( pq.isNotEmpty() ) {
            if(pq.peek() >= K) break
            if(pq.size < 2){
                count = -1
                break
            }

            pq.add(pq.poll() + (2 * pq.poll()))
            count ++
        }


        return count
    }
}