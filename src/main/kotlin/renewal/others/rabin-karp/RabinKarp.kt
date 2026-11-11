package renewal.others.`rabin-karp`

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertEquals

class RabinKarp {
    /**
     * 🎯 라빈-카프 기본 문제: "문자열 찾기"
     * 주어진 텍스트에서 패턴 문자열이 나타나는 첫 번째 위치를 찾아라.
     * (못 찾으면 -1 반환)
     * 입력:
     * kotlintext = "ABABCABABA"
     * pattern = "ABAB"
     * 출력: 0 (첫 번째 "ABAB"가 인덱스 0에서 시작)
     */

    @Test
    fun solution() {
        val kotlinText = "ABABCABABA"
        val pattern = "ABAB"
        val expected = 0

        assertEquals(expected, rabinKarp(kotlinText, pattern))
    }

    private fun calculateHash(string: String): Long {
        val base = 31
        var hash = 0L
        val charA = 'A'
        val mod = 1000000007

        for (char in string) {
            hash = (hash * base + (char - charA + 1))
        }

        return hash % mod

    }

    private fun rabinKarp(kotlinText: String, pattern: String): Int {
        val patternHash = calculateHash(pattern)
        var windowHash = calculateHash(kotlinText.substring(0, pattern.length))
        val base = 31
        val charA = 'A'
        val mod = 1000000007

        for (i in 0..kotlinText.length - pattern.length) {
            val substring = kotlinText.substring(i, i + pattern.length)
            if (patternHash == windowHash && pattern == substring) {
                return i
            }

            if (i < kotlinText.length - pattern.length) {
                val power = base.toDouble().pow(pattern.length - 1).toLong()
                val oldChar = (kotlinText[i] - charA + 1).toLong()
                val newChar = (kotlinText[i + pattern.length] - charA + 1)
                windowHash = ((windowHash - oldChar * power) * base + newChar) % mod
            }
        }


        return -1
    }
}