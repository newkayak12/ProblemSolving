package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `멀쩡한 사각형` {

    @Test
    fun solution1() {
        val w = 8
        val h = 12
        val result = 80L

        println(gcd(3, 3))

        assertEquals(result, calculate(w, h))
    }


    fun calculate(w: Int, h: Int): Long = (w.toLong() * h) - (w + h - gcd(w, h))

    // ((w / gcd) + (h / gcd) - 1 ) * gcd


    fun gcd(a: Int, b: Int): Long {
        val large = if (a > b) a else b
        val small = if (a < b) a else b
        if (small == 0) return large.toLong()

        return gcd(small, large % small)
    }
}