package renewal.others.kmp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KMPExample {

    @Test
    fun solution1() {
        val string = "ABABABCABABABCAB"
        val reference = "ABABAB"
        val expected = listOf(0, 7)
        assertEquals(expected, kmpSearch(string, reference))
    }

    @Test
    fun solution2() {
        val string = "XYABCXY"
        val reference = "ABC"
        val expected = listOf(2)
        assertEquals(expected, kmpSearch(string, reference))
    }

    @Test
    fun solution3() {
        val string = "ABCDEF"
        val reference = "XYZ"
        val expected = emptyList<Int>()
        assertEquals(expected, kmpSearch(string, reference))
    }

    private fun stringArray(pattern: String): IntArray {
        val n = pattern.length
        val prefix = IntArray(n)
        var j = 0

        for (i in 1 until n) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = prefix[j - 1]
            }

            if (pattern[i] == pattern[j]) {
                j++
            }

            prefix[i] = j
        }

        return prefix
    }

    private fun kmpSearch(text: String, pattern: String): List<Int> {
        val prefix = stringArray(pattern)
        val result = mutableListOf<Int>()

        var j = 0

        for (i in text.indices) {
            while (j > 0 && text[i] != pattern[j]) {
                j = prefix[j - 1]
            }

            if (text[i] == pattern[j]) {
                j++
            }

            if (j == pattern.length) {
                result.add(i - pattern.length + 1)
                j = prefix[j - 1]
            }
        }

        return result
    }
}