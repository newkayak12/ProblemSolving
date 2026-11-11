package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `큰 수 만들기` {
    @Test
    fun test1() {
        assertEquals("94", solution("1924", 2))
    }

    @Test
    fun test2() {
        assertEquals("3234", solution("1231234", 3))
    }

    @Test
    fun test3() {
        assertEquals("775841", solution("4177252841", 4))
    }

    @Test
    fun test4() {
        assertEquals("987", solution("98765", 2))
    }


    fun solution(number: String, k: Int): String {
        val arrayDeque = ArrayDeque<Int>()
        var count = k
        val stringBuilder = StringBuilder()

        for((index, value) in number.withIndex()) {
            while(arrayDeque.isNotEmpty() && number[arrayDeque.last()] < value && count > 0) {
                arrayDeque.removeLast()
                count -= 1
            }

            arrayDeque.add(index)
        }



        while (arrayDeque.isNotEmpty()) {
            stringBuilder.append(number[arrayDeque.removeFirst()])
        }

        if(count > 0) {
            return stringBuilder.removeRange(number.length  - k, stringBuilder.length).toString()
        }

        return stringBuilder.toString()
    }



}