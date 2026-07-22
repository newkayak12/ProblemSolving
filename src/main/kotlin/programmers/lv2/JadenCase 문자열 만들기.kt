package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `JadenCase 문자열 만들기` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12951?language=kotlin
     *
     * JadenCase, 모든 단어의 첫 문자가 대문자, 나머지는 소문자
     */


    @Test
    fun solution1() {
        val s = "3people unFollowed me"
        val expected = "3people Unfollowed Me"

        assertEquals(expected, calculate(s))
    }

    @Test
    fun solution2() {
        val s = "for the last week"
        val expected = "For The Last Week"

        assertEquals(expected, calculate(s))
    }

    private fun calculate(s: String): String {
        var index = 0
        return s.map {
            val char: Char = if (index == 0) it.uppercaseChar() else it.lowercaseChar()
            if (it == ' ') index = 0
            else index++

            char
        }
            .joinToString("")
    }
}