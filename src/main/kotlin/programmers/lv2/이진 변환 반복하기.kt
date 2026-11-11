package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `이진 변환 반복하기` {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/70129?language=kotlin
     *
     * 0, 1로 이뤄진 문자열 x
     *
     * 1. x의 모든 0을 제거
     * 2. x의 길이를 c라고 하면 x를 "c를 2진법으로 표현한 문자열"로 바꾼다.
     *
     * x = "0111010"
     * 1 적용 "1111"
     * 2 적용 "100"
     *
     * 1이 될 때까지 반복
     *
     * 1. 이진 변환 횟수
     * 2. 이진 변환 과정에서 제거된 모든 0의 개수
     */



    @Test
    fun solution1(){
        val s = "110010101001"
        val expected = intArrayOf(3,8)

        assertArrayEquals(expected, calculate(s))
    }

    @Test
    fun solution2(){
        val s = "01110"
        val expected = intArrayOf(3,3)

        assertArrayEquals(expected, calculate(s))
    }

    @Test
    fun solution3(){
        val s = "1111111"
        val expected = intArrayOf(4,1)

        assertArrayEquals(expected, calculate(s))
    }

    private fun calculate(s: String): IntArray {
        var string = s
        val result = intArrayOf(0, 0)

        while (string != "1") {
            result[0] ++
            var countOne = 0

            for(element in string) {
                if (element == '0') result[1] ++
                else countOne ++
            }

            string = countOne.toString(2)
        }

        return result
    }
}