package programmers.bit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `해밍 거리` {
    /**
     * 문제 C: 해밍 거리 (트릭 #9 연습)
     * 문제 설명
     * 두 양의 정수 a, b가 주어졌을 때, 이진 표현에서 다른 비트의 개수를 구하세요. (해밍 거리)
     *
     * a = 1 = 0001, b = 4 = 0100 → 2 (비트 0과 비트 2가 다름)
     * a = 7 = 0111, b = 0 = 0000 → 3
     * a = 5 = 0101, b = 5 = 0101 → 0
     *
     * 제약조건
     *
     * 0 ≤ a, b ≤ 10^18
     *
     *
     */


    @Test
    fun `a와 b가 같으면 0`() {
        assertEquals(0, solution(5L, 5L))
    }

    @Test
    fun `둘 다 0이면 0`() {
        assertEquals(0, solution(0L, 0L))
    }

    @Test
    fun `a가 1, b가 4면 2`() {
        assertEquals(2, solution(1L, 4L))
    }

    @Test
    fun `a가 7, b가 0이면 3`() {
        assertEquals(3, solution(7L, 0L))
    }

    @Test
    fun `a가 1, b가 2면 2`() {
        assertEquals(2, solution(1L, 2L))
    }

    @Test
    fun `a가 13, b가 4면 2`() {
        assertEquals(2, solution(13L, 4L))
    }

    @Test
    fun `a가 15, b가 0이면 4`() {
        assertEquals(4, solution(15L, 0L))
    }

    @Test
    fun `a가 255, b가 1이면 7`() {
        assertEquals(7, solution(255L, 1L))
    }

    fun solution(a: Long, b: Long): Int{
        var xor = a xor b
        var count = 0
        while (xor > 0){
            xor = xor and (xor - 1)
            count ++
        }
        return count
    }
}