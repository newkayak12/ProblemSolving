package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `가장 큰 수` {


    @Test
    fun test1(){
        val numbers = intArrayOf(6, 10, 2)
        val returnValue = "6210"

        assertEquals(returnValue, solution(numbers))
    }


    @Test
    fun test2(){
        val numbers = intArrayOf(3, 30, 34, 5, 9)
        val returnValue = "9534330"

        assertEquals(returnValue, solution(numbers))
    }

    @Test
    fun test3(){
        val numbers = intArrayOf(0, 0, 0)
        val returnValue = "0"

        assertEquals(returnValue, solution(numbers))
    }

    fun solution(numbers: IntArray) = numbers
            .sortedWith({o1, o2 ->  "$o2$o1".compareTo("$o1$o2")})
            .joinToString("")
            .let {
                if(it.replace("0", "").isBlank()) "0"
                else it
            }


}