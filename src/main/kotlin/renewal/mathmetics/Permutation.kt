package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Permutation {
    /**
     * ✅ 문제: 서로 다른 정수 나열
     *
     * N개의 서로 다른 정수가 주어진다.
     * 이 중에서 R개를 선택해 순서 있게 나열할 수 있는 경우의 수를 구하라.
     * 결과는 1,000,000,007로 나눈 나머지를 출력한다.
     *
     * ⸻
     *
     * ⛳ 입력 형식
     * 	•	첫째 줄에 두 정수 N, R이 주어진다. (1 ≤ R ≤ N ≤ 1,000)
     *
     * ⸻
     *
     * ⛳ 출력 형식
     * 	•	가능한 순열의 수를 1,000,000,007로 나눈 나머지를 출력한다.
     *
     * ⸻
     *
     * 📘 예제 입력 1
     *  5 2
     * 📗 예제 출력 1
     *  20
     */

    @Test
    fun solution(){

        val n = 5
        val r = 2
        val expected = 20


        assertEquals(expected, permutation(n, r))
    }

    private fun permutation(n: Int, r: Int): Int {
        return (factory(n) / factory(n - r)) % 1_000_000_007
    }

    private fun factory(n: Int): Int {
        if(n <= 1) return 1

        return (2..n).reduce { acc, i -> acc * i }
    }
}