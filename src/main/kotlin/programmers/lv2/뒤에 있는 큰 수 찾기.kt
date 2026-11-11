package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `뒤에 있는 큰 수 찾기` {

    @Test
    fun solution1() {

        val numbers = intArrayOf(2, 3, 3, 5)
        val expected = intArrayOf(3, 5, 5, -1)

        assertArrayEquals(expected, calculate(numbers))
    }

    @Test
    fun solution2() {

        val numbers = intArrayOf(9, 1, 5, 3, 6, 2)
        val expected = intArrayOf(-1, 5, 6, 6, -1, -1)

        assertArrayEquals(expected, calculate(numbers))
    }


    @Test
    fun solution3() {

        val numbers = intArrayOf(9, 1, 1, 1, 1, 9, 1, 1, 1, 2)
        val expected = intArrayOf(-1, 9, 9, 9, 9, -1, 2, 2, 2, -1)

        assertArrayEquals(expected, calculate(numbers))
    }


    @Test
    fun solution4() {

        val numbers = intArrayOf(9, 1, 1, 1, 2, 9, 1, 1, 2, 3)
        val expected = intArrayOf(-1, 2, 2, 2, 9, -1, 2, 2, 3, -1)

        assertArrayEquals(expected, calculate(numbers))
    }

    @Test
    fun solution5() {

        val numbers = intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1)
        val expected = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        assertArrayEquals(expected, calculate(numbers))
    }

    @Test
    fun solution6() {

        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = intArrayOf(2, 3, 4, 5, 6, 7, 8, 9, -1)

        assertArrayEquals(expected, calculate(numbers))
    }

    fun calculate(numbers: IntArray): IntArray {
        val result = IntArray(numbers.size)
        val stack = ArrayDeque<Int>()

        for (i in 0 until numbers.size - 1) {
            val now = numbers[i]
            val next = numbers[i + 1]

            if(now < next) {
                result[i] = next

                while(stack.isNotEmpty()) {
                    val stackedIndex = stack.last()
                    if(numbers[stackedIndex] >= next) break
                    result[stack.removeLast()] = next
                }

            }
            else  {
                stack.addLast(i)
            }
        }



        for(index in result.indices) {
            if(result[index] == 0) result[index] = -1
        }

        return  result
    }
}