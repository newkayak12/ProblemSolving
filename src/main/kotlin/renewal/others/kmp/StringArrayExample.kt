package renewal.others.kmp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class StringArrayExample {


    // 테스트 1
    @Test
    fun solution1() {
        val string = "ABABABCAB"
        val expected = listOf(0, 0, 1, 2, 3, 4, 0, 1, 2)
        assertEquals(expected, computePrefix(string))
    }

    // 테스트 2
    @Test
    fun solution2() {
        val string = "ABABA"
        val expected = listOf(0, 0, 1, 2, 3)
        assertEquals(expected, computePrefix(string))
    }

    // 테스트 3
    @Test
    fun solution3() {
        val string = "AAAA"
        val expected = listOf(0, 1, 2, 3)
        assertEquals(expected, computePrefix(string))
    }

    private fun computePrefix(pattern: String): List<Int> {
        val patternLength = pattern.length
        val prefix = IntArray(patternLength)
        var j = 0

        for (i in 1 until patternLength) {

            while (j > 0 && pattern[i] != pattern[j]) {
                j = prefix[j - 1]
            }

            if (pattern[i] == pattern[j]) {
                j++
            }

            prefix[i] = j
        }


        return prefix.toList()
    }
}