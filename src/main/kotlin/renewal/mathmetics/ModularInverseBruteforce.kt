package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ModularInverseBruteforce {

    @Test
    fun solution(){
        val a = 3
        val m = 7

        val expected = 5
        assertEquals(expected, bruteforce(a, m))
    }

    private fun bruteforce(a: Int, m: Int): Int? {
        for(x in 1 until m) {
            if((a * x) % m == 1) return x
        }

        return  null
    }
}