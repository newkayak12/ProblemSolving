package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `할인 행사` {
    //SlidingWindow

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/131127?language=kotlin
     */

    @Test
    fun solution1() {
        val want = arrayOf("banana", "apple", "rice", "pork", "pot")
        val number = intArrayOf(3, 2, 2, 2, 1)
        val discount = arrayOf(
            "chicken",
            "apple",
            "apple",
            "banana",
            "rice",
            "apple",
            "pork",
            "banana",
            "pork",
            "rice",
            "pot",
            "banana",
            "apple",
            "banana"
        )
        val expected = 3

        assertEquals(expected, calculate(want, number, discount))
    }


    @Test
    fun solution2() {
        val want = arrayOf("apple")
        val number = intArrayOf(10)
        val discount =
            arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana")
        val expected = 0

        assertEquals(expected, calculate(want, number, discount))
    }

    private fun calculate(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val length = 10
        val wantMap = want.withIndex().associate { it.value to number[it.index] }
        val discountMap = discount.take(length).groupBy { it }.mapValues { it.value.size }.toMutableMap()
        val valid = wantMap.all { (key, count) -> (discountMap[key]?:0) >= count }
        var count = if (valid) 1 else 0

        for (i in 0 until discount.size - length) {

            val minus = discount[i]
            val plus = discount[i + length]


            discountMap[minus] = (discountMap[minus]?:0)+1
            discountMap[plus] = (discountMap[plus]?:0)+1

            val valid = wantMap.all { (key, count) -> (discountMap[key]?:0) >= count }

            count += if (valid) 1 else 0
        }


        return count
    }
}

