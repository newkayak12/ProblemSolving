package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BackpackOneDimensional {
    /**
     * ✅ 문제 이름: 가방에 넣을 수 있는 최대 가치
     * ⸻
     * 📘 문제 설명
     *
     * 당신은 N개의 물건을 가지고 있고,
     * 각 물건은 무게(weight)와 가치(value)를 가집니다.
     * 당신은 최대 W 무게까지 담을 수 있는 배낭을 가지고 있습니다.
     *
     * 각 물건은 한 번만 선택할 수 있습니다.
     *
     * 최대 무게를 초과하지 않으면서 담을 수 있는 물건들의 조합 중,
     * 총 가치의 합이 가장 큰 경우를 구하세요.
     * ⸻
     * 🧾 입력
     * 	•	첫째 줄에 물건의 개수 N (1 ≤ N ≤ 100), 배낭의 최대 무게 W (1 ≤ W ≤ 10000)
     * 	•	이후 N개의 줄에 각 물건의 weight, value (1 ≤ weight, value ≤ 1000)
     * ⸻
     * ✅ 출력
     * 	•	배낭에 담을 수 있는 최대 가치의 합을 출력하세요.
     * ⸻
     *
     */

    @Test
    fun solution() {
        val weight = 7
        val items = arrayOf(
            intArrayOf(3, 4),
            intArrayOf(4, 5),
        )
        val expected = 9

        assertEquals(expected, oneDimensional(weight, items))
    }

    private fun oneDimensional(weight: Int, items: Array<IntArray>): Int {
        val table = IntArray(weight + 1)

        for( i in 1 .. items.size){
            val (itemWeight, itemValue) = items[i - 1]
            for( j in weight downTo itemWeight) {
                table[j] = maxOf(
                    table[j],
                    table[j - itemWeight] + itemValue
                )
            }
        }

        return table.maxOrNull() ?: 0
    }
}