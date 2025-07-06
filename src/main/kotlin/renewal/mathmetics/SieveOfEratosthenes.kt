package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals

class SieveOfEratosthenes {
    /**
     *
     * ⸻
     *
     * ✅ 문제: N 이하의 소수를 모두 출력하라
     *
     * 입력:
     * 정수 N (2 ≤ N ≤ 10,000)
     *
     * 출력:
     * 2 이상 N 이하의 모든 소수를 오름차순으로 출력
     *
     * ⸻
     *
     * 예시 입력
     * 30
     * 예시 출력
     * 2 3 5 7 11 13 17 19 23 29
     */

    @Test
    fun solution() {
        val targets = 30
        val expected = "2 3 5 7 11 13 17 19 23 29"

        assertEquals(expected, eratosthenes(targets))
    }

    private fun eratosthenes(number: Int): String {
        val isPrime = BooleanArray(number + 1) {true}
        if( number == 0) isPrime[0] = false
        if( number == 1) isPrime[1] = false

        for(i in 2 .. sqrt(number.toDouble()).toInt()) {
            if(isPrime[i]) {
                for( j in i*i .. number step i) {
                    isPrime[j] = false
                }
            }
        }

        return (2..number).filter { isPrime[it] }.joinToString(" ")
    }
}