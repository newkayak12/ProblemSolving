package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `문자열 압축` {
    /**
     * 비손실 압축
     * 문자열에서 같은 값이 연속해서 나타나는 것을 그 문자의 개수와 반복되는 값으로 표현
     * aabbacccc -> 2a2ba3c X
     *
     * abab cdcd abab cdcd -> 2ab 2cd 2ab 2cd ->  2ababcdcd
     * abc abc dede -> 2abcdede
     *
     * 글자 단위로 잘라서 압축
     */

    @Test
    fun solution1() {
        val s = "aabbaccc" //2a2ba3c
        val result = 7
        assertEquals(result, calculate(s))
    }

    @Test
    fun solution2() {
        val s = "ababcdcdababcdcd" // 2ababcdcd
        val result = 9
        assertEquals(result, calculate(s))
    }

    @Test
    fun solution3() {
        val s = "abcabcdede" //2abcdede
        val result = 8
        assertEquals(result, calculate(s))
    }

    @Test
    fun solution4() {
        val s = "abcabcabcabcdededededede" //2abcabc2dedede
        val result = 14
        assertEquals(result, calculate(s))
    }

    @Test
    fun solution5() {
        val s = "xababcdcdababcdcd"
        val result = 17
        assertEquals(result, calculate(s))
    }

    @Test
    fun solution6() {
        val s = "abcdefghijklmnop"
        val result = 16
        assertEquals(result, calculate(s))
    }


    @Test
    fun solution7() {
        val s = "aaaaa"
        val result = 2
        assertEquals(result, calculate(s))
    }

    @Test
    fun solution8() {
        val s = "a"
        val result = 1
        assertEquals(result, calculate(s))
    }


    fun calculate(s: String): Int {
        var min = Int.MAX_VALUE
        if(s.length == 1) return 1
        for (i in 1 until s.length) {
            min = minOf(slide(s, i), min)
        }

        return min
    }

    fun slide(s: String, length: Int): Int {
        var startIndex = 0
        var endIndex = startIndex + length
        var word = ""
        var count = 0

        val builder = StringBuilder()

        while (startIndex < s.length) {
            val substring = s.substring(startIndex, endIndex)

            if (word.isNotBlank() && !word.equals(substring, ignoreCase = true))  {

                if( count > 0 ) {
                    builder.append(if(count == 1) word else "$count$word")
                }

                word = substring
                count = 0
                continue
            }

            word = substring
            count++

            startIndex = endIndex
            endIndex = if(startIndex + length > s.length) s.length else startIndex + length
        }

        if(count > 0) {
            builder.append(if(count == 1) word else "$count$word")
        }

        return builder.length
    }

}