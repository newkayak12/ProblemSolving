package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `숫자 카드 나누기` {
    //철수의 GCD이 영희의 GCD이 아닌 경우
    //반대인 경우



    @Test
    fun test1() {
        val arrayA = intArrayOf(10, 17)
        val arrayB = intArrayOf(5, 200)
        val result = 0

        assertEquals(result, solution(arrayA, arrayB))
    }

    @Test
    fun test2() {
        val arrayA = intArrayOf(10, 20)
        val arrayB = intArrayOf(5, 17)
        val result = 10

        assertEquals(result, solution(arrayA, arrayB))
    }

    @Test
    fun test3() {
        val arrayA = intArrayOf(14, 35, 119)
        val arrayB = intArrayOf(18, 30, 1020)
        val result = 7

        assertEquals(result, solution(arrayA, arrayB))
    }

    @Test
    fun test4() {
        val arrayA = intArrayOf(10)
        val arrayB = intArrayOf(5)
        val result = 10

        assertEquals(result, solution(arrayA, arrayB))
    }



    private tailrec fun gcd(a: Int, b: Int): Int {
        if(b == 0) return a
        else return gcd(b, a % b)
    }

    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        println("${arrayA.contentToString()}  ${arrayB.contentToString()}")
        val gcdOfArrayA = arrayA.reduce { a, b -> gcd(a, b) }
        val gcdOfArrayB = arrayB.reduce { a, b -> gcd(a, b) }


        val numberA = if(arrayB.none { it % gcdOfArrayA == 0 } ) gcdOfArrayA else 0
        val numberB = if(arrayA.none { it % gcdOfArrayB == 0 }) gcdOfArrayB else 0

        return maxOf(numberA, numberB)
    }
}