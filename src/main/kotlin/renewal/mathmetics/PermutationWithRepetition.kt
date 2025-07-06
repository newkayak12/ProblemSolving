package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertEquals

class PermutationWithRepetition {
    /**
     *
     *
     * ✅ 문제: 중복 가능한 정수 선택 나열
     *
     * N개의 서로 다른 정수가 주어진다.
     * 이 중에서 중복을 허용하여 R개를 골라 순서 있게 나열하는 모든 경우의 수를 구하라.
     * 결과는 1,000,000,007로 나눈 나머지를 출력한다.
     *
     * ⸻
     *
     * ⛳ 입력 형식
     * 	•	첫째 줄에 두 정수 N, R이 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ R ≤ 1,000)
     *
     * ⸻
     *
     * ⛳ 출력 형식
     * 	•	가능한 모든 나열의 수를 1,000,000,007로 나눈 나머지를 출력한다.
     *
     * ⸻
     *
     * 📘 예제 입력 1
     * 3 2
     * 📗 예제 출력 1
     * 9
     */


    @Test
    fun solution() {
        val n = 3
        val r = 2
        val expected = 9

        assertEquals(expected, permutationWithRepetition(n, r))
    }

    private fun permutationWithRepetition(n: Int, r: Int): Int {
        return n.toDouble().pow(r).toInt()
    }

}