package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `큰 수 만들기` {

    @Test
    fun solution1() {
        val number = "1924"
        val k = 2
        val expected = "94"

        assertEquals(expected, calculate(number, k))
    }

    @Test
    fun solution2() {
        val number = "1231234"
        val k = 3
        val expected = "3234"

        assertEquals(expected, calculate(number, k))
    }

    @Test
    fun solution3() {
        val number = "4177252841"
        val k = 4
        val expected = "775841"

        assertEquals(expected, calculate(number, k))
    }

    private fun calculate(number: String, k: Int): String {
        val stack = ArrayDeque<Int>()
        var removeCount = 0

        for(index in number.indices) {
            val now = number[index].digitToInt()

            while(removeCount < k && stack.isNotEmpty()) {
                val peek = stack.last()
                if(peek >= now) break

                stack.removeLast()
                removeCount++
            }

            stack.addLast(now)
        }

        while (removeCount < k) {
            stack.removeLast()
            removeCount++
        }

        return stack.joinToString("")
    }
}