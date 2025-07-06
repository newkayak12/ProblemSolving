package renewal.mathmetics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModPow {

    private fun modPow(a: Long, b: Long, m: Long): Long {
        var base = a % m
        var exp = b
        var result = 1L


        while(exp > 0) {
            if(exp % 2 == 1L) {
                result = (result * base) % m
            }

            base = (base * base) % m
            exp /= 2
        }

        return result
    }

    @Test
    fun solutionOdd() {
        val a = 3L
        val b = 13L
        val m = 100L

        // 3^13 = 1594323, 1594323 % 100 = 23
        assertEquals(23L, modPow(a, b, m))
    }

    @Test
    fun solutionEven() {
        val a = 2L
        val b = 10L   // 짝수 지수
        val m = 1000L

        assertEquals(24, modPow(a, b, m))  // 2^10 = 1024 % 1000 = 24
    }
}