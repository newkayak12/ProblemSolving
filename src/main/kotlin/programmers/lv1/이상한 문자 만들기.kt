package programmers.lv1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `이상한 문자 만들기` {

    @Test
    fun solution() {
        val s = "try hello world"
        val expected = "TrY HeLlo WoRlD"

        assertEquals(expected, calculate(s))
    }

    private fun calculate(s: String): String {
        var index = 0
        return s.map {
            if (it == ' ') {
                index = 0
                return@map ' '
            }

            var result =
                if (index % 2 == 0) it.uppercaseChar()
                else it.lowercaseChar()

            index++

            return@map result
        }.joinToString("")
    }
}