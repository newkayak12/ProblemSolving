package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals

class `K진수에서 소수 개수 구하기` {

    @Test
    fun solution1() {
        val n = 437674
        val k = 3
        val expected = 3

        assertEquals(expected, calculate(n, k))
    }

    @Test
    fun solution2() {
        val n = 110011
        val k = 10
        val expected = 2

        assertEquals(expected, calculate(n, k))
    }


    private fun calculate(n: Int, k: Int): Int {
        val number = n.toString(k)
            .replace("0+".toRegex(),"#")
            .split("#")
            .filter { it.isNotBlank() }

        return number.count { isPrime(it.toLong()) }
    }

    private fun isPrime(number: Long): Boolean {
        if (number <= 1L) return false
        for (i in 2..sqrt(number.toDouble()).toLong()) {
            if (number % i == 0L) return false
        }

        return true
    }

}