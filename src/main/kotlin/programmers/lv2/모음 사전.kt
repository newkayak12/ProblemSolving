package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `모음 사전` {

    @Test
    fun solution1() {

        val word = "AAAAE"
        val expected = 6

        assertEquals(expected, calculate(word))
    }

    @Test
    fun solution2() {

        val word = "AAAE"
        val expected = 10

        assertEquals(expected, calculate(word))
    }

    @Test
    fun solution3() {

        val word = "I"
        val expected = 1563

        assertEquals(expected, calculate(word))
    }

    @Test
    fun solution4() {

        val word = "EIO"
        val expected = 1189

        assertEquals(expected, calculate(word))
    }

    private fun calculate(word: String): Int {
        val list = listOf('A','E','I',"O","U")
        val dictionary = (1..55555).filter {
            !it.toString().contains("0") &&
            !it.toString().contains("6") &&
            !it.toString().contains("7") &&
            !it.toString().contains("8") &&
            !it.toString().contains("9")
        }
        .map {
            it.toString().map { l -> list[l.digitToInt()-1] }.joinToString("")
        }
        .sorted()
        .toList()


        return dictionary.indexOf(word)+ 1
    }
    /**
     * A = 1
     * AA = 2
     * AAA = 3
     * AAAA = 4
     * AAAAA = 5
     * AAAAE = 6
     * AAAAI = 7
     * AAAAO = 8
     * AAAAU = 9
     * AAAE = 10
     */

}