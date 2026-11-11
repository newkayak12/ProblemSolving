package programmers.lv1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `나누어 떨어지는 숫자 배열` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12910?language=kotlin
     */

    @Test
    fun solution(){
        val arr = intArrayOf(5, 9, 7, 10)
        val divisor = 5

        val expected = intArrayOf(5, 10)

        assertArrayEquals(expected, calculate(arr, divisor))
    }

    private fun calculate(arr: IntArray, divisor: Int): IntArray {
        val list = mutableListOf<Int>()

        for(i in arr) {
            if(i % divisor == 0) {
                list.add(i)
            }
        }

        return if(list.isEmpty()) intArrayOf(-1) else list.sorted().toIntArray()
    }

}