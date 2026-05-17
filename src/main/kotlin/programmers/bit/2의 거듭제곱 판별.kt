package programmers.bit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `2의 거듭제곱 판별` {
    /**
     * B
     * 문제 설명
     * 양의 정수 x가 주어졌을 때, x가 2의 거듭제곱인지 판별하세요.
     *
     * x = 1 → true (2^0)
     * x = 8 → true (2^3)
     * x = 1024 → true
     * x = 13 → false
     * x = 0 → false
     *
     * 제약조건
     *
     * 0 ≤ x ≤ 10^18
     */


    @Test
    fun `x가 0이면 false`() {
        assertEquals(false, solution(0L))
    }

    @Test
    fun `x가 1이면 true`() {
        assertEquals(true, solution(1L))
    }

    @Test
    fun `x가 2이면 true`() {
        assertEquals(true, solution(2L))
    }

    @Test
    fun `x가 3이면 false`() {
        assertEquals(false, solution(3L))
    }

    @Test
    fun `x가 8이면 true`() {
        assertEquals(true, solution(8L))
    }

    @Test
    fun `x가 15이면 false`() {
        assertEquals(false, solution(15L))
    }

    @Test
    fun `x가 1024이면 true`() {
        assertEquals(true, solution(1024L))
    }

    @Test
    fun `x가 1023이면 false`() {
        assertEquals(false, solution(1023L))
    }

    fun solution(x: Long): Boolean {
        return x > 0 && (x and (x - 1)) == 0L
    }
}

