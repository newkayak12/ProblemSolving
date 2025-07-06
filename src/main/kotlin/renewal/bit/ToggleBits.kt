package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ToggleBits {
    /**
     *
     * ⸻
     *
     * 🧪 예제 문제 3 – 비트를 켜기 / 끄기 / 토글하기
     *
     * 📘 문제 설명
     *
     * 정수 a와 비트 위치 k가 주어졌을 때,
     * 	•	setBit(a, k) → k번째 비트를 1로 켜기
     * 	•	clearBit(a, k) → k번째 비트를 0으로 끄기
     * 	•	toggleBit(a, k) → k번째 비트를 반전시키기 (1 → 0, 0 → 1)
     *
     * 이 3개의 동작을 각각 함수로 구현하세요.
     *
     * ⸻
     *
     * ✅ 예시
     * a = 9 (1001)
     *
     * setBit(a, 1)    → 1011 (11)
     * clearBit(a, 0)  → 1000 (8)
     * toggleBit(a, 3) → 0001 (1)
     */

    @Test
    fun solution() {
        val a = 9
        val setBitK = 1
        val clearBitK = 0
        val toggleBitK = 3

        val expectedSetBit = 11
        val expectedClearBit = 8
        val expectedToggleBit = 1

        assertEquals(expectedSetBit, setBit(a, setBitK))
        assertEquals(expectedClearBit, clearBit(a, clearBitK))
        assertEquals(expectedToggleBit, toggleBit(a, toggleBitK))
    }

    private fun setBit(a: Int, k: Int): Int {
        return a or (1 shl k)
    }

    private fun clearBit(a: Int, k: Int): Int {
        return a and (1 shl k).inv()
    }

    private fun toggleBit(a: Int, k: Int): Int {
        return a xor (1 shl k)
    }
}