package programmers.bit

import java.lang.Math.pow
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `가장 큰 2의 거듭제공 찾기` {
    /**
     * 문제 1: 가장 큰 2의 거듭제곱 찾기 (난이도: 하)
     *     문제 설명
     *     양의 정수 x가 주어졌을 때, x 이하의 가장 큰 2의 거듭제곱을 구하세요.
     *
     *     예: x = 13 → 8 (2^3)
     *     예: x = 16 → 16 (2^4)
     *     예: x = 1 → 1 (2^0)
     *
     *     제약조건
     *
     *     1 ≤ x ≤ 10^18
     *
     */

    @Test
    fun solution1() {
        val number = 1L
        val result = 1L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution2() {
        val number = 2L
        val result = 2L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution3() {
        val number = 3L
        val result = 2L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution4() {
        val number = 7L
        val result = 4L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution5() {
        val number = 8L
        val result = 8L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution6() {
        val number = 13L
        val result = 8L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution7() {
        val number = 16L
        val result = 16L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution8() {
        val number = 100L
        val result = 64L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
        assertEquals(result, solutionC(number))
    }

    @Test
    fun solution9() {
        val number = 1000L
        val result = 512L

        assertEquals(result, solutionA(number))
        assertEquals(result, solutionB(number))
//        assertEquals(result, solutionC(number))
    }


    fun solutionA(x: Long): Long {
        //비트 사이즈 + 최상단만 1
        val SIZE_BITS = 64

        val length = SIZE_BITS - x.countLeadingZeroBits() - 1
        return 1L shl length
    }

    fun solutionB(x: Long): Long {
        //비트끄기 반복
        //0이 되기 전까지


        var number = x
        while((number and (number - 1)) != 0L) {
            number = number and  (number - 1)
        }
        return number
    }

    fun solutionC(x: Long): Long {
        //비트 채우고 시프트
        var number = x
        for(i in 0 ..< 8) number or (number shr pow(2.0, i.toDouble()).toInt())

        return number
    }
}