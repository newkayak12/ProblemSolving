package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UnboundMagicalBackpack {
    /**
     * ✅ 문제 이름: 무제한 물약 제조
     * ⸻
     * 📘 문제 설명
     * 당신은 다양한 종류의 마법 재료를 가지고 있습니다.
     * 각 재료는 일정한 무게와 **효능(가치)**을 가지며,
     * 같은 재료는 여러 번 사용할 수 있습니다.
     *
     * 당신은 최대 W 무게까지 담을 수 있는 마법 가방을 가지고 있으며,
     * 무게를 초과하지 않으면서 효능의 합이 최대가 되도록 재료를 선택하려고 합니다.
     * ⸻
     * 🧾 입력
     * 	•	첫째 줄에 재료의 개수 N (1 ≤ N ≤ 100), 가방의 최대 무게 W (1 ≤ W ≤ 10,000)
     * 	•	이후 N개의 줄에 각 재료의 무게와 효능이 주어집니다.
     * (1 ≤ 무게, 효능 ≤ 1000)
     * ⸻
     * ✅ 출력
     * 	•	가방에 담을 수 있는 최대 효능의 합을 출력하세요.
     * 💡 예제 입력
     * 3(개수) 7(최대 가방 무게)
     *
     * 무게 효능
     * 2  10
     * 3  15
     * 5  30
     * ✅ 출력 예제
     * 40
     */

    @Test
    fun solution() {
        val N = 3
        val W = 7
        val items = arrayOf(
            intArrayOf(2, 10),
            intArrayOf(3, 15),
            intArrayOf(5, 30)
        )
        val expected = 40


        assertEquals(expected, unbounded(N, W, items))

    }

    private fun unbounded(N: Int, W: Int, items: Array<IntArray>): Int {
        val table = IntArray(W + 1)

        for (i in 1..N) {
            val (weight, value) = items[i - 1]
            for (j in weight..W) {
                table[j] = maxOf(table[j], table[j - weight] + value)
            }
        }

        return table.maxOrNull() ?: 0
    }
}