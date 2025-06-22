package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Backpack {
    /**
     * 🧪 문제 3. 0/1 배낭 문제 (기초)
     * ⸻
     * 📄 문제 설명
     * 무게 제한이 W인 배낭이 있고,
     * 각 아이템은 (무게, 가치) 쌍으로 주어진다.
     * 가치의 합이 최대가 되도록 아이템을 고를 때,
     * 최대 가치를 구하시오.
     * (각 아이템은 한 번만 사용 가능)
     * ⸻
     * 📥 입력
     * 	•	정수 N (아이템 수), 정수 W (배낭 최대 무게)
     * 	•	다음 N줄에 걸쳐 각 줄마다 weight, value 정보
     * ⸻
     * 📤 출력
     * 	•	배낭에 담을 수 있는 최대 가치
     */

    @Test
    fun solution() {
        val weight = 7
        val itemCount = 3
        val item = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(3, 4),
            intArrayOf(4, 5),
        )
        val expected = 9

        assertEquals(expected, backPack(itemCount, weight, item))
    }

    /**
     * dp[i][j] => i 번째 물건까지 고려해서, 배낭 무게 j일 때 가능한 최대 가치
     * i: 물건 개수
     * j: 배낭 남는 무게
     * dp[i][j]: 그 상태에서 만들 수 있는 최대 가치
     *
     *
     * -> 구하고자 하는 것 : 최대 합
     * -> dp[i][j] 자체에 대해서 생각할 수 있어야 한다.
     *      -> 구하고자 하는 것에서 경우의 수를 줄이지 못해서 여러 경우의 수가 발생하는 것을 표현하기 위해서 그래프를 생각했음
     *      -> 공간 당 상태가 아니라 파생되는 흐름과 경우의 수로 생각
     *
     * -> DP는 탐색이 아닌 기록이다.
     */

    private fun backPack(itemCount: Int, capacity: Int, items: Array<IntArray>): Int {
        val table = Array(itemCount + 1) { IntArray(capacity + 1) }
        for (numberOfItem in 1..itemCount) {
            val weight = items[numberOfItem - 1][0]
            val value = items[numberOfItem - 1][1]

            for (currentWeight in 0..capacity) {

                table[numberOfItem][currentWeight] =
                    if (currentWeight < weight) table[numberOfItem - 1][currentWeight]
                    else maxOf(
                        table[numberOfItem - 1][currentWeight],
                        table[numberOfItem - 1][currentWeight - weight] + value
                    )

            }
        }

        return table[itemCount][capacity]
    }
}