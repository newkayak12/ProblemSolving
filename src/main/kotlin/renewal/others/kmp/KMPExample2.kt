package renewal.others.kmp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KMPExample2 {

    @Test
    fun solution1() {
        val text = "ABABABCABABABCAB"
        val pattern = "ABABAB"
        val expected = listOf(0, 7)
        assertEquals(expected, kmpSearch(text, pattern))
    }

    @Test
    fun solution2() {
        val text = "XYABCXY"
        val pattern = "ABC"
        val expected = listOf(2)
        assertEquals(expected, kmpSearch(text, pattern))
    }

    @Test
    fun solution3() {
        val text = "ABCDEF"
        val pattern = "XYZ"
        val expected = emptyList<Int>()
        assertEquals(expected, kmpSearch(text, pattern))
    }

    private fun failureTable(pattern: String): IntArray {
        val n = pattern.length
        val index = IntArray(n)
        var j = 0

        for (i in 1 until n) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = index[j - 1]
            }

            if (pattern[i] == pattern[j]) {
                j++
            }

            index[i] = j
        }

        return index
    }

    private fun kmpSearch(text: String, pattern: String): List<Int> {
        val index = failureTable(pattern)
        val result = mutableListOf<Int>()
        var j = 0

        for (i in text.indices) {
            while (j > 0 && text[i] != pattern[j]) {
                j = index[j - 1]
            }

            if (text[i] == pattern[j]) {
                j++
            }

            if (j == pattern.length) {
                result.add(i - pattern.length + 1)
                j = index[j - 1]
            }
        }

        return result
    }
}