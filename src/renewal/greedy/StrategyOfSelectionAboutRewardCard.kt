package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class StrategyOfSelectionAboutRewardCard {
    /**
     * 🟩 문제 이름: 보상 카드 사용 전략
     *
     * 📘 문제 설명
     * 당신에게는 다양한 보상 카드를 사용할 기회가 주어집니다. 각 보상 카드는 사용 기한(날짜)과 보상 금액이 정해져 있습니다.
     * 하루에 한 번씩, 기한이 되기 전(포함)까지 사용할 수 있는 카드 중 보상이 가장 큰 카드를 선택할 수 있습니다.
     *
     * 주어진 카드 정보로, 보상 금액의 합이 최대가 되도록 사용하는 경우의 총 보상 금액을 구하세요.
     *
     * ⸻
     *
     * 📥 입력
     *	•	첫째 줄: 카드의 개수 N (1 ≤ N ≤ 1,000)
     * 	•	둘째 줄 ~ N+1번째 줄: 각 카드의 사용 기한 d(1 ≤ d ≤ 1,000)와 보상 금액 p(1 ≤ p ≤ 100,000)
     *
     * ⸻
     *
     * 📤 출력
     *	•	보상 금액의 합이 최대가 되는 값을 출력하세요.
     *
     *
     *
     * 🧪 예제 입력 1
     * 	3
     * 1 10
     * 2 20
     * 3 30
     * -> 1일차(10) + 2일차(20) +3일차(30)
     *
     * 🧪 예제 출력 1
     * 60
     *
     * 🧪 예제 입력 2
     * 5
     * 3 50
     * 3 10
     * 1 20
     * 2 40
     * 2 30
     *
     * 🧪 예제 출력 2
     * 120
     *
     * -> 1일차 (3 50), (3 10), (1 20), (2 40), ✅ (2 30)
     * -> 2일차 (3 50), (3 10), ✅ (2 40)
     * -> 3일차 ✅ (3 50), (3 10)
     */


    @Test
    fun solution1() {
        val table = arrayOf(
            intArrayOf(1, 10),
            intArrayOf(2, 20),
            intArrayOf(3, 30),
        )

        val expected = 60

        assertEquals(expected, greedy(table))
    }

    @Test
    fun solution2() {
        val table = arrayOf(
            intArrayOf(3, 50),
            intArrayOf(3, 10),
            intArrayOf(1, 20),
            intArrayOf(2, 40),
            intArrayOf(2, 30),
        )

        val expected = 110

        assertEquals(expected, greedy(table))
    }

    @Test
    fun solution3() {
        val table = arrayOf(
            intArrayOf(1, 10),
            intArrayOf(2, 20),
            intArrayOf(3, 100),
            intArrayOf(1, 100),
            intArrayOf(2, 1),
        )

        val expected = 220

        assertEquals(expected, greedy(table))
    }


    private fun greedy(table: Array<IntArray>): Int {
        val sortedTable = table.sortedBy { it[0] }
        val dueDate = sortedTable.maxOf { it[0] }
        val queue = PriorityQueue<Int> (compareByDescending { it })

        var result = 0
        var index = 0

        for (day in 1 .. dueDate) {
           while(index < sortedTable.size && sortedTable[index][0] <= day){
               queue.add(sortedTable[index][1])
               index++
           }


            if(queue.isNotEmpty()) {
                result += queue.poll()
            }
        }


        return result
    }
}