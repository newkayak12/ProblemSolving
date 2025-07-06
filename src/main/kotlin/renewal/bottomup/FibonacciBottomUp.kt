package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FibonacciBottomUp {

    /**
     * 📄 문제 요약
     *
     * 정수 n이 주어졌을 때, n번째 피보나치 수를 구하세요.
     * (f(0) = 0, f(1) = 1, f(n) = f(n-1) + f(n-2))
     */
    @Test
    fun fibonacciBottomUp(){
        assertEquals(5, bottomUp(5))
    }

    private fun bottomUp(number: Int): Int {
        val table = Array(number + 1) { i ->
            when(i) {
                0 -> 0
                1 -> 1
                else -> 0
            }
        }

        for(i in 2 ..  number) {
            table[i] = table[i - 2] + table[i -1]
        }


        return table[number]
    }

}