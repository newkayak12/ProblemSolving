package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InBudgetMaxValue {
    /**
     * 🔹 문제 1. 예산 안에서 최대 가치 얻기
     *
     * 설명
     *
     * 당신은 예산 B만큼을 갖고 있습니다.
     * 물건 N개가 있고, 각 물건은 가격과 만족도가 주어집니다.
     * 예산을 넘지 않게 몇 개의 물건을 선택했을 때, 총 만족도의 최대값은?
     *
     * 입력 예시
     * 	•	예산: B = 10
     * 	•	물건:
     * 	```kotlin
     *      val goods = arrayOf(
     *          intArrayOf(2, 1),  // 가격 2, 만족도 1
     *          intArrayOf(3, 4),  // 가격 3, 만족도 4
     *          intArrayOf(5, 5)   // 가격 5, 만족도 5
     *      )
     * 	```
     */

    @Test
    fun solution() {
        val budget = 10
        val goods = arrayOf(
            intArrayOf(2, 1),  // 가격 2, 만족도 1
            intArrayOf(3, 4),  // 가격 3, 만족도 4
            intArrayOf(5, 5)   // 가격 5, 만족도 5
        )
        val expected = 10

        assertEquals(expected, budgetAndMaxValue(budget, goods))
    }

    private fun budgetAndMaxValue(budget: Int, goods: Array<IntArray>): Int {
        val table = Array(goods.size + 1) { IntArray(budget+1)}

        for(i in 1 ..  goods.size) {
            val price = goods[i - 1][0]
            val satisfaction = goods[i - 1][1]

            for(j in 0 .. budget) {
                table[i][j] = if(j < price) table[i - 1][j]
                else maxOf(table[i - 1][j], table[i - 1][j - price] + satisfaction)
            }
        }


        return table[goods.size][budget]
    }
}