package renewal.others.`rabin-karp`

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.math.pow

class FindDuplicatedWord {
    /**
     * 🎯 라빈-카프 기본 문제 2번: "중복 문자열 찾기"
     * 주어진 문자열에서 길이가 K인 부분 문자열 중에 2번 이상 나타나는 문자열을 모두 찾아라.
     * 입력:
     * kotlintext = "ABCABCABC"
     * k = 3
     * 출력: ["ABC", "BCA", "CAB"] (길이 3인 부분문자열 중 중복되는 것)
     *
     * 다른 예:
     * kotlintext = "AABAABA"
     * k = 3
     * 출력: ["AAB", "ABA"]
     */

    @Test
    fun solution1() {
        val kotlinText = "ABCABCABC"
        val k = 3
        val expected = arrayOf("ABC", "BCA", "CAB")
        assertArrayEquals(expected, rabinKarp(kotlinText, k))
    }


    @Test
    fun solution2() {
        val kotlinText = "AABAABA"
        val k = 3
        val expected = arrayOf("AAB", "ABA")
        assertArrayEquals(expected, rabinKarp(kotlinText, k))
    }

    @Test
    fun solution3() {
        val kotlinText = "AABAACAAB"
        val k = 3
        val expected = arrayOf("AAB")
        assertArrayEquals(expected, rabinKarp(kotlinText, k))
    }


    private fun rabinKarp(kotlinText: String, patternSize: Int): Array<String> {
        val patternSet = mutableMapOf<Long, MutableList<String>>()
        var hash = calculateHash(kotlinText.substring(0, patternSize))
        patternSet.getOrPut(hash) { mutableListOf() }.add(kotlinText.substring(0, patternSize))

        for (i in 1..kotlinText.length - patternSize) {
            hash = rollHash(hash, kotlinText[i - 1], kotlinText[i + patternSize - 1], patternSize)
            val substring = kotlinText.substring(i, i + patternSize)
            patternSet.getOrPut(hash) { mutableListOf() }.add(substring)
        }

        return patternSet.values.filter { it.size > 1 }.flatten().distinct().toTypedArray()
    }


    private fun rollHash(hash: Long, oldChar: Char, newChar: Char, patternSize: Int): Long {
        val charA = 'A'
        var mod = 1000000007
        val base = 31
        val oldCharHash = (oldChar - charA + 1).toLong()
        val newCharHash = (newChar - charA + 1).toLong()

        val power = base.toDouble().pow(patternSize - 1).toLong()

        return ((hash - oldCharHash * power) * base + newCharHash) % mod
    }


    private fun rabinKarpBack(kotlinText: String, patternSize: Int): Array<String> {
        val patternSet = mutableMapOf<String, Int>()

        for (i in 0..kotlinText.length - patternSize) {
            val key = kotlinText.substring(i until i + patternSize)
            patternSet.putIfAbsent(key, 0)
        }

        for (key in patternSet.keys) {
            patternSet[key] = checkDuplicatedCount(kotlinText, key)
        }



        return patternSet.keys.filter { patternSet[it] ?: 0 > 1 }.toTypedArray()
    }

    private fun calculateHash(word: String): Long {
        var hash = 0L
        val base = 31
        var mod = 1000000007
        val charA = 'A'

        for (char in word) {
            hash = (hash * base + (char - charA + 1))
        }


        return hash % mod
    }

    private fun checkDuplicatedCount(kotlinText: String, pattern: String): Int {
        val charA = 'A'
        var mod = 1000000007
        val base = 31
        val patternHash = calculateHash(pattern)
        val patternSize = pattern.length
        var windowHash = calculateHash(kotlinText.substring(0, pattern.length))
        var count = 0


        for (i in 0..kotlinText.length - patternSize) {
            val substring = kotlinText.substring(i until i + patternSize)

            if (windowHash == patternHash && pattern == substring) {
                count += 1
            }

            if (i < kotlinText.length - patternSize) {
                val power = base.toDouble().pow(patternSize - 1).toLong()
                val oldChar = (kotlinText[i] - charA + 1).toLong()
                val newChar = (kotlinText[i + pattern.length] - charA + 1)
                windowHash = ((windowHash - oldChar * power) * base + newChar) % mod
            }
        }

        return count
    }
}