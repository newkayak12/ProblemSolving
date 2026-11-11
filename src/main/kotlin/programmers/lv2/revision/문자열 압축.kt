package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `문자열 압축` {



    @Test
    fun solution1() {
        val s = "aabbaccc"
        val result = 7
        assertEquals(result, solution(s))
    }

    @Test
    fun solution2() {
        val s = "ababcdcdababcdcd"
        val result = 9
        assertEquals(result, solution(s))
    }

    @Test
    fun solution3() {
        val s = "abcabcdede"
        val result = 8
        assertEquals(result, solution(s))
    }

    @Test
    fun solution4() {
        val s = "abcabcabcabcdededededede"
        val result = 14
        assertEquals(result, solution(s))
        }

    @Test
    fun solution5() {
        val s = "xababcdcdababcdcd"
        val result = 17
        assertEquals(result, solution(s))
    }

    @Test
    fun solution6() {
        val s = "aaaaaaaaaaaaaaaaaaaaaaaaaa"
        val result = 3
        assertEquals(result, solution(s))
    }

    @Test
    fun solution7() {
        val s = "a"
        val result = 1
        assertEquals(result, solution(s))
    }



    fun solution(s: String): Int {
        val chunkLimit = s.length / 2
        var length = Int.MAX_VALUE
        if(s.length == 1) return 1

        for(chunkSize in chunkLimit downTo 1) {
            length = minOf(length, chunk(s, chunkSize))
        }

        return length
    }


    fun chunk(s: String, chunkSize: Int): Int {
        var duplicateCount = 1
        val chunked = s.chunked(chunkSize)
        val builder = StringBuilder()
        for(i in 1 ..< chunked.size){
            if(chunked[i - 1] == chunked[i]) {
                duplicateCount++
            }
            else {
                val prefix = if(duplicateCount >= 2) duplicateCount.toString() else ""
                builder.append("${prefix}${chunked[i - 1]}")
                duplicateCount = 1
            }
        }

        builder.append("${if(duplicateCount >= 2) duplicateCount.toString() else ""}${chunked.last()}")
        return builder.length
    }
}