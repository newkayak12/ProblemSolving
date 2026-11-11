package programmers.lv1.kakao

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `숫자 문자열과 영단어` {

    @Test
    fun solution1(){
        val string = "one4seveneight"
        val expected = 1478

        assertEquals(expected, calculate(string))
    }
    @Test
    fun solution2(){
        val string = "23four5six7"
        val expected = 234567

        assertEquals(expected, calculate(string))
    }
    @Test
    fun solution3(){
        val string = "2three45sixseven"
        val expected = 234567

        assertEquals(expected, calculate(string))
    }
    @Test
    fun solution4(){
        val string = "123"
        val expected = 123

        assertEquals(expected, calculate(string))
    }


    private fun calculate(s: String): Int {
        var string = s
        val map = mapOf(
            "zero" to 0,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )


        for( entries in map.entries) {
            val key = entries.key
            val value = entries.value
            string = string.replace(key, value.toString(), true)
        }


        return string.toInt()
    }
}