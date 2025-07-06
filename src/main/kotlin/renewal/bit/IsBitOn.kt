package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IsBitOn {
    /**
     * 🧪 예제 문제 2 – 특정 비트가 켜져 있는지 확인
     *
     * 📘 문제 설명
     *
     * 정수 a와 비트 위치 k가 주어졌을 때,
     * a의 k번째 비트가 1인지 0인지 확인하여 출력하세요.
     *
     * 비트는 오른쪽에서 0번부터 시작합니다.
     * 예: a = 13 (1101)
     *
     * 	•	0번째 비트 = 1
     * 	•	1번째 비트 = 0
     * 	•	2번째 비트 = 1
     * 	•	3번째 비트 = 1
     *
     * ⸻
     *
     * ✅ 입력 예시 1
     * a = 13
     * k = 2
     * 출력: 1
     *
     * ✅ 입력 예시 2
     * a = 13
     * k = 1
     * 출력: 0
     */

    @Test
    fun solution1() {
        val a = 13
        val k = 2
        val expected = 1

        assertEquals(expected, bitIsOn(a, k))
    }

    @Test
    fun solution2() {
        val a = 13
        val k = 1
        val expected = 0

        assertEquals(expected, bitIsOn(a, k))
    }

    private fun bitIsOn(a: Int, k: Int): Int {
        return if (a and ( 1 shl k) != 0) 1 else 0
    }
}