package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FermatModularInverse {

    private fun modularInverse(a: Int, mod: Int): Int {
        for( x in 1 until  mod){
            if((a*x)%mod ==1) return x
        }

        throw IllegalArgumentException("X")
    }

    @Test
    fun solution(){
        val a = 3
        val mod = 7
        val expected = 5

        assertEquals(expected, modularInverse(a, mod))
    }
}