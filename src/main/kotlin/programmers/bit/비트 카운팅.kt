package programmers.bit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `비트 카운팅` {
    /**
     * A
     * 문제 설명
     *     양의 정수 x가 주어졌을 때, x의 이진 표현에서 1 비트의 개수를 구하세요.
     *
     *     예: x = 13 = 1101 → 3
     *     예: x = 7 = 0111 → 3
     *     예: x = 16 = 10000 → 1
     *     예: x = 0 → 0
     *
     *     제약조건
     *
     *     0 ≤ x ≤ 10^18
     *
     */



    @Test
    fun `x가 0이면 0`() {
        assertEquals(0, solution(0L))
    }

    @Test
    fun `x가 1이면 1`() {
        assertEquals(1, solution(1L))
    }

    @Test
    fun `x가 5(101)이면 2`() {
        assertEquals(2, solution(5L))
    }

    @Test
    fun `x가 7(111)이면 3`() {
        assertEquals(3, solution(7L))
    }

    @Test
    fun `x가 13(1101)이면 3`() {
        assertEquals(3, solution(13L))
    }

    @Test
    fun `x가 16(10000)이면 1`() {
        assertEquals(1, solution(16L))
    }

    @Test
    fun `x가 255(11111111)이면 8`() {
        assertEquals(8, solution(255L))
    }

    @Test
    fun `x가 1024(10000000000)이면 1`() {
        assertEquals(1, solution(1024L))
    }

    fun solution(x: Long): Int{

        var count = 0
        var number = x
        while(number > 0L) {
            number = (number - 1) and number
            count ++
        }

        return count
    }

}