package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 의상 {

    @Test
    fun solution1() {
        val clothes = arrayOf(
            arrayOf("yellow_hat", "headgear"),
            arrayOf("blue_sunglasses", "eyewear"),
            arrayOf("green_turban", "headgear"),
        )
        val expected = 5

        assertEquals(expected, calculate(clothes))
    }

    @Test
    fun solution2() {
        val clothes = arrayOf(
            arrayOf("crow_mask", "face"),
            arrayOf("blue_sunglasses", "face"),
            arrayOf("smoky_makeup", "face")
        )
        val expected = 3

        assertEquals(expected, calculate(clothes))
    }

    private fun calculate(clothes: Array<Array<String>>): Int {
        var answer = 1

        val map = clothes.groupBy({it.last()}){it.first()}
        map.forEach{
            answer *= (it.value.size + 1)
        }

        return answer - 1
    }
}