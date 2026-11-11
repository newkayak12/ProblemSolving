package programmers.lv1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.lang.Integer.parseInt

class `자연수 뒤집어 배열로 만들기` {

    /**
     * 문제 설명
     *
     * 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열 형태로 리턴해주세요. 예를들어 n이 12345이면 [5,4,3,2,1]을 리턴합니다.
     * 제한 조건
     * n은 10,000,000,000이하인 자연수입니다.
     *
     */


    @Test
    fun problem1() {
        val n = 12345L
        val expected = intArrayOf(5, 4, 3, 2, 1)
        assertArrayEquals(expected, solution(n))
    }

    private fun solution(n: Long): IntArray {
        val list = mutableListOf<Int>()
        val ten = 10
        var number = n

        while(number > 0) {
            list.add((number % ten).toInt())
            number /= ten
        }

        return list.toIntArray()
    }
}