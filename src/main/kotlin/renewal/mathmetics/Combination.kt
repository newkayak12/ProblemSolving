package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Combination {
    /**
     * ✅ 문제: n개의 원소 중에서 r개를 고르는 조합의 수를 구하시오.
     * 	•	원소는 서로 다른 정수이며, 같은 원소를 두 번 이상 선택할 수 없습니다.
     * 	•	출력은 1,000,000,007로 나눈 나머지입니다.
     *
     * ⸻
     *
     * ⛳ 입력 형식
     * n r
     * 	•	1 ≤ r ≤ n ≤ 1,000
     *
     * ⸻
     *
     * ⛳ 출력 형식
     * 	•	(n / r) mod 1,000,000,007
     *
     *
     * 	예제 입력 1
     * 	5 2
     * 	예제 출력 1
     * 	10
     *
     * 	예제 입력 2
     * 	10 3
     * 	예제 출력 2
     * 	120
     *
     */


    @Test
    fun solution1() {
        val n = 5
        val r = 2
        val expected = 10

        assertEquals(expected, combination(n, r))
    }


    @Test
    fun solution2() {
        val n = 10
        val r = 3
        val expected = 120

        assertEquals(expected, combination(n, r))
    }

    private fun combination(n: Int, r: Int): Int {

        return (factorial(n) / (factorial(r) * factorial(n - r)) ) % 1_000_000_007
    }

    private fun factorial(n: Int): Int {
        if(n <= 1) return 1

        return (2..n).reduce{acc, i -> i * acc }
    }
}