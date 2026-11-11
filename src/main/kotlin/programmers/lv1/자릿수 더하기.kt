package programmers.lv1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `자릿수 더하기` {
    /**
     * 문제 설명
     *
     * 자연수 N이 주어지면, N의 각 자릿수의 합을 구해서 return 하는 solution 함수를 만들어 주세요.
     * 예를들어 N = 123이면 1 + 2 + 3 = 6을 return 하면 됩니다.
     * 제한사항
     * N의 범위 : 100,000,000 이하의 자연수
     *
     * 입력1
     * 123
     * 출력1
     * 6
     *
     * 입력2
     * 987
     * 출력2
     * 24
     */


    @Test
    fun problem1() {
        val N = 123
        val expected = 6
        assertEquals(expected, solution(N))
    }


    @Test
    fun problem2() {
        val N = 987
        val expected = 24
        assertEquals(expected, solution(N))
    }

    private fun solution(n: Int): Int {
        val ten = 10
        var number = n
        var answer = 0
        while (number > 0) {
            answer += number % ten
            number /= ten
        }
        return answer
    }
}