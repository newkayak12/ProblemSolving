package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LeastCommonMultiple {
    /**
     * a = 12, b = 18
     * GCD(12, 18) = 6
     * LCM(12, 18) =
     *  (12 × 18) / 6 = 216
     *  216 / 6 = 36
     */


    @Test
    fun solution() {
        val a = 12
        val b = 18

        val expected = 36

        assertEquals(expected, lcm(a, b))
    }


    private fun gcd(a: Int, b: Int): Int {
        if( b == 0) return a
        return gcd(b, a % b)
    }

    private fun lcm(a: Int, b: Int): Int {
        return (a * b) / gcd(a, b)
    }
}