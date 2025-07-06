package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UnboundedCook {
    /**
     * ✅ 문제 이름: 무제한 재료로 요리하기
     *
     * ⸻
     *
     * 📘 문제 설명
     *
     * 요리사 찬우는 여러 가지 재료를 가지고 요리를 합니다.
     * 각 재료는 고정된 무게와 맛 점수를 가지고 있으며,
     * 같은 재료는 여러 번 사용할 수 있습니다.
     *
     * 찬우는 조리법상 정확히 무게 W만큼 재료를 사용해야 하며,
     * 이때 만들 수 있는 최대 맛 점수를 계산하려고 합니다.
     *
     * 단, 어떤 조합으로도 정확히 무게 W를 만들 수 없다면,
     * 찬우는 요리를 포기하고 맛 점수 0을 받습니다.
     *
     * ⸻
     *
     * 🧾 입력
     * 	•	첫째 줄에 재료의 개수 N (1 ≤ N ≤ 100), 정확한 요리 무게 W (1 ≤ W ≤ 10,000)
     * 	•	이후 N개의 줄에 각 재료의 무게, 맛 점수가 주어집니다. (1 ≤ 무게, 맛 점수 ≤ 1000)
     *
     * ✅ 출력
     * 	•	정확히 W 무게를 사용했을 때 만들 수 있는 최대 맛 점수를 출력하세요.
     * 	•	불가능한 경우 0 출력
     *💡 예제 입력
     * - 재료 개수 : 3
     * - 맛 점수 : 7
     *
     * |무게 | 맛점수|
     * | 2  |   5 |
     * | 3  |   8 |
     * | 4  |   9 |
     *  ✅ 출력 예제
     *  18
     */

    @Test
    fun solution() {
        val N = 3
        val W = 7
        val items = arrayOf(
            intArrayOf(2, 5),
            intArrayOf(3, 8),
            intArrayOf(4, 9),
        )
        val expected = 18

        assertEquals(expected, unbounded(N, W, items))
    }
    private fun unbounded(N: Int, W: Int, items: Array<IntArray>): Int {
        val table = IntArray(W + 1){
            i -> if(i == 0) 0
                else Int.MIN_VALUE
        }

        for( i in 1 .. N) {
            val (weight, score) = items[i - 1]
            for( j in weight .. W) {
                if (table[j - weight] != Int.MIN_VALUE) {
                    table[j] = maxOf(table[j], table[j - weight] + score)
                }
            }
        }

        return if(table[W] == Int.MIN_VALUE) 0 else table[W]
    }
}