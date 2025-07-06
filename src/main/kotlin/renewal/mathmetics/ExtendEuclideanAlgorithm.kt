package renewal.mathmetics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtendEuclideanAlgorithm {

    // 결과를 담을 데이터 클래스
    data class GCDResult(val gcd: Int, val x: Int, val y: Int)

    // 확장 유클리드 알고리즘
    fun extendedGCD(a: Int, b: Int): GCDResult {
        if (b == 0) {
            return GCDResult(a, 1, 0)
        }

        val next = extendedGCD(b, a % b)
        val x1 = next.y
        val y1 = next.x - (a/ b) * next.y

        return GCDResult(next.gcd, x1, y1)
    }


    @Test
    fun testExtendedGCD() {
        val (gcd, x, y) = extendedGCD(30, 12)
        assertEquals(6, gcd)
        assertEquals(true, 30 * x + 12 * y == gcd)
    }

    @Test
    fun testModularInverse() {
        val (gcd, x, _) = extendedGCD(3, 7)
        assertEquals(1, gcd)

        val modInv = (x % 7 + 7) % 7
        assertEquals(1, (3 * modInv) % 7)
    }
}