package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `롤케이크 자르기` {
    @Test
    fun solution1() {
        val topping = intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)
        val expected = 2

        assertEquals(expected, calculate(topping))
    }

    @Test
    fun solution2() {
        val topping = intArrayOf(1, 2, 3, 1, 4)
        val expected = 0

        assertEquals(expected, calculate(topping))
    }

    private fun calculate(topping: IntArray): Int {
        var sameCount = 0
        val right = topping.groupBy { it }.mapValues { it.value.size }.toMutableMap()
        val left = mutableSetOf<Int>()



        for (pointer in topping.indices) {
            val number = topping[pointer]
            right[number] = right[number]!! - 1
            if(right[number]!! <= 0) {
                right.remove(number)
            }
            left.add(number)

            val subCount = left.size
            val otherCount = right.keys.size
            //right.count -> O(n)


            if (subCount == otherCount) {
                sameCount++
            }
        }
        return sameCount
    }

    //pointer 개념이어야만 함


}