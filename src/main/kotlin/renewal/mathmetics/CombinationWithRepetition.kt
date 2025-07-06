package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CombinationWithRepetition {
    /**
     * 📘 문제: 중복 조합 구하기
     *
     * 문제 설명
     * 서로 다른 n개의 종류의 사탕이 있다.
     * 이 중에서 중복을 허용하여 r개를 고르는 방법의 수를 구하시오.
     *
     * 	•	입력: n = 2, r = 3
     * 	•	출력: 4
     *
     */

    @Test
    fun solution(){
        val n = 2
        val r = 3
        val expected = 4

        assertEquals(expected, combinationWithRepetition(n, r))
    }

    private fun combinationWithRepetition(n: Int, r: Int): Int{
        return factorial(n + r - 1) /  factorial(r) * factorial(n - 1)
    }

    private fun factorial(n: Int): Int {
        if( n == 0 || n == 1) return 1


        return (2 .. n).reduce{ acc, i -> acc * i}
    }

}