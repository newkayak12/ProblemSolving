package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GreatestCommonDivisor {
    /**
     * GCD(48, 18)
     * → GCD(18, 48 % 18) = GCD(18, 12)
     * → GCD(12, 6)
     * → GCD(6, 0)
     * → 결과: 6
     */

    @Test
    fun solution() {
        val first = 48
        val second = 18
        val expected = 6

        assertEquals(expected, gcd(first, second))
    }

    private fun gcd(first: Int, second: Int ): Int {
        if(second == 0) return first
        else return gcd(second, first % second)
    }

}