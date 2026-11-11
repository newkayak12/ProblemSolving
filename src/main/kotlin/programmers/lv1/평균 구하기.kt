package programmers.lv1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `평균 구하기` {
    /**
     * 문제 설명
     *
     * 정수를 담고 있는 배열 arr의 평균값을 return하는 함수, solution을 완성해보세요.
     * 제한사항
     * arr은 길이 1 이상, 100 이하인 배열입니다.
     * arr의 원소는 -10,000 이상 10,000 이하인 정수입니다.
     *
     * 입출력 예
     * arr      	return
     * [1,2,3,4]	2.5
     * [5,5]        5
     */


    @Test
    fun solution1() {
        val arr = intArrayOf(1, 2, 3, 4)
        val expected = 2.5

        assertEquals(expected, avg(arr))
    }

    @Test
    fun solution2() {
        val arr = intArrayOf(5, 5)
        val expected = 5.0

        assertEquals(expected, avg(arr))
    }

    private fun avg(arr: IntArray): Double {
        val number = arr.reduce { a: Int, b: Int -> a + b }
        val size = arr.size.toDouble()

        return number / size
    }
}