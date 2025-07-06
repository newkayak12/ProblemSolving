package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EuclideanAlgorithm {
    /**
     * ✅ 문제: GCD와 LCM을 함께 구하라
     *
     * 입력
     * 두 정수 A, B (1 ≤ A, B ≤ 10,000)
     *
     * 출력
     * 첫 줄에 A와 B의 최대공약수 (GCD)
     * 둘째 줄에 A와 B의 최소공배수 (LCM)
     *
     * ⸻
     *
     * 📘 예시 입력
     * 24 36
     * 📗 예시 출력
     * 12
     * 72
     */

    @Test
    fun solution() {
        val a = 24
        val b = 36

        val expected = """
            12
            72
        """.trimIndent()

        assertEquals(expected, euclid(a, b))
    }


    private fun euclid(a: Int, b:Int):String {
        val builder = StringBuilder()
        builder.append("${gcm(a, b)}\n")
        builder.append(lcm(a, b))

        return builder.toString()
    }

    private fun gcm(a: Int, b: Int): Int {
        if( b == 0) return a

        return gcm(b, a % b)
    }

    private fun lcm(a:Int, b:Int): Int  {
        return (a * b) / gcm(a, b)
    }
}