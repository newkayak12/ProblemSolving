package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CombinationWithRepetitionExample1 {
    /**
     * ✅ 중복 조합 예제 문제
     *
     * 문제: 사탕 고르기
     *
     * 색이 다른 사탕이 n종류 있습니다. 각 종류의 사탕은 무한히 많다고 할 때, 이 중에서 중복을 허용하여 사탕 r개를 고르는 방법의 수를 구하시오.
     *
     * 단, 정답은 1,000,000,007로 나눈 나머지를 출력합니다.
     *
     * ⸻
     *
     * 입력 형식
     * n r
     * 	•	1 ≤ n, r ≤ 1,000
     *
     * 출력 형식
     * 중복 조합 (n + r - 1) C r 값
     *
     * 예제 입력
     *  3 2
     * 예제 출력
     *  6
     */

    @Test
    fun solution() {
        val n = 3L
        val r = 2L

        val expected = 6L

        assertEquals(expected, combinationWithRepetition(n, r))
    }

    private fun combinationWithRepetition(n: Long, r: Long): Long {
        return (factorial(n + r - 1) / (factorial(r) * factorial(n - 1))) % 1_000_000_007
    }

    private fun factorial(n: Long): Long {
        if (n <= 1) return 1

        return (2..n).reduce { acc, i -> acc * i }
    }
}