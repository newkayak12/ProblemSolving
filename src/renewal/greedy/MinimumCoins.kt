package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MinimumCoins {
    /**
     * 🟩 문제 이름: 최소 동전 개수 계산
     *
     * 📘 문제 설명
     * 주어진 동전으로 목표 금액을 맞추려고 합니다.
     * 동전 단위는 서로 배수 관계를 가질 수 있으며, 가능한 경우 가장 적은 동전 개수를 사용해야 합니다.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	첫째 줄: 동전의 개수 N (1 ≤ N ≤ 10)
     * 	•	둘째 줄: 동전 단위 (공백으로 구분된 N개의 자연수)
     * 	•	셋째 줄: 목표 금액 M (1 ≤ M ≤ 10,000)
     *
     * ⸻
     *
     * 📤 출력
     * 	•	목표 금액 M을 만들기 위한 최소 동전 개수를 출력하세요.
     *
     * 🧪 예제 입력 1
     * 동전 개수 : 4
     * 동전 단위 : 500 100 50 10
     * 목표 금액 : 1260
     * 🧪 예제 출력 1
     * 6
     *
     * 🧪 예제 입력 2
     * 동전 개수 : 3
     * 동전 단위 : 9 6 1
     * 목표 금액 : 15
     * 🧪 예제 출력 2
     * 2
     */

    @Test
    fun solution1() {
        val coinCount = 4
        val coinTypes = intArrayOf(500, 100, 50, 10)
        val target = 1260
        val expected = 6

        assertEquals(expected, greedy(coinTypes, target))
    }

    @Test
    fun solution2() {
        val coinCount = 3
        val coinTypes = intArrayOf(9,6,1)
        val target = 15
        val expected = 2

        assertEquals(expected, greedy(coinTypes, target))
    }

    private fun greedy(coinTypes: IntArray, target: Int): Int {
        var usedCount = 0
        var targetAmount = target
        coinTypes.sortDescending()


        for( coin in coinTypes ) {
            if( targetAmount <= 0) break
            if( targetAmount / coin > 0) {
                val div = targetAmount/coin
                usedCount += div
                targetAmount -= coin * div
            }
        }

        return usedCount
    }

}