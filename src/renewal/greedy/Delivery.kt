package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class Delivery {
    /**
     * 🟩 문제 이름: 📦 택배 배송
     *
     * 📘 문제 설명
     * 한 마을에 N개의 택배 주문이 있습니다.
     * 각 주문은 배송 시작 가능일(start), 배송 마감일(end), 그리고 **배송비(payment)**가 정해져 있습니다.
     * 하루에 하나의 주문만 처리할 수 있고,
     * 배송비의 합이 최대가 되도록 배송 일정을 정하려고 합니다.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	첫째 줄: 주문의 개수 N (1 ≤ N ≤ 10,000)
     * 	•	이후 N줄: 각 주문의 배송 시작일 start, 배송 마감일 end, 배송비 payment (1 ≤ start ≤ end ≤ 1,000 / 1 ≤ payment ≤ 100,000)
     *
     * ⸻
     *
     * 📤 출력
     * 	•	가능한 최대 배송비의 합을 출력하세요.
     *
     * 🧪 예제 입력
     * 	4
     * 1 2 50
     * 1 3 10
     * 2 2 20
     * 3 3 30
     *
     * 🧪 예제 출력
     * 100
     * 1일 -> ✅(1, 2, 50), (1, 3, 10)
     * 2일 -> ✅(2, 2, 20)
     * 3일 -> (1, 3, 10), ✅(3, 3, 30)
     */

    @Test
    fun solution() {
        val table = arrayOf(
            intArrayOf(1, 2, 50),
            intArrayOf(1, 3, 10),
            intArrayOf(2, 2, 20),
            intArrayOf(3, 3, 30),
        )

        val expected = 100

        assertEquals(expected, greedy(table))
    }

    @Test
    fun solution2() {
        val table = arrayOf(
            intArrayOf(1, 2, 50),
            intArrayOf(1, 2, 40),
            intArrayOf(1, 2, 30),
        )

        val expected = 90

        assertEquals(expected, greedy(table))
    }

    private fun greedy(table: Array<IntArray>): Int {
        val sortedTable = table.sortedWith(compareBy({ it[0] }, { it[1] }))
        val pq = PriorityQueue<IntArray>(compareByDescending { it[2] })
        val maxDay = table.maxOf { it[1] }

        var index = 0
        var result = 0

        for (day in 1..maxDay) {

            while (index < sortedTable.size && sortedTable[index][0] <= day) {
                pq.add(sortedTable[index])
                index++
            }

            while (pq.isNotEmpty()) {
                val content = pq.poll()
                if(content[1] >= day){
                    result += content[2]
                    break
                }
            }

        }

        return result
    }
}