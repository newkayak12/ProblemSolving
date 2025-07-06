package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class FruitsBox {
    /**
     * 🟩 문제 이름: 📦 과일 바구니 채우기
     *
     * 📘 문제 설명
     * 당신은 다양한 종류의 과일을 팔고 있는 상인이며, 각각의 과일에는 가격이 정해져 있습니다.
     * 당신은 하루 동안 한 종류의 과일을 하나씩만 팔 수 있으며, 과일을 팔 수 있는 기간도 정해져 있습니다.
     * 당신은 하루에 한 번, 팔 수 있는 과일 중 가장 비싼 과일을 팔아야 합니다.
     *
     * 과일마다 주어진 판매 가능 기간 중, 가장 비싼 과일을 팔아 얻을 수 있는 총 수익을 구하세요.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	첫 번째 줄: 과일의 개수 N (1 ≤ N ≤ 1,000)
     * 	•	이후 N줄: 각 과일의 판매 시작일 start, 판매 종료일 end, 가격 price (1 ≤ start ≤ end ≤ 1,000 / 1 ≤ price ≤ 100,000)
     *
     * ⸻
     *
     * 📤 출력
     * 	•	하루에 하나씩만 팔 수 있을 때, 가능한 최대 수익을 출력하세요.
     *
     * ⸻
     *
     * 🧪 예제 입력
     * 4
     * 1 2 50
     * 1 3 10
     * 2 2 20
     * 3 3 30
     *
     * 🧪 예제 입력
     * 100
     */
    @Test
    fun solution() {
        val table = arrayOf(
            intArrayOf(1, 2, 50),
            intArrayOf(1, 3, 10),
            intArrayOf(2, 2, 20),
            intArrayOf(3, 3, 30)
        )

        val expected = 100

        assertEquals(expected, greedy(table))
    }

    private fun greedy(table: Array<IntArray>): Int {
        table.sortWith(compareBy ({ it[0] }, {it[1]}))
        val maxDay = table.maxOf { it[1] }
        val queue = PriorityQueue<IntArray>(compareByDescending { it[2] })
        var index = 0
        var result = 0

        for(day in 1 .. maxDay) {

            while( index < table.size && day >= table[index][0]) {
                queue.add(table[index++])
            }


            while (queue.isNotEmpty()) {
                val element = queue.poll()
                if( element[1] >= day ) {
                    result += element[2]
                    break
                }
            }
        }

        return result
    }
}