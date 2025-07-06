package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CommonBit {

    /**
     * ⸻
     *
     * 🧪 예제 문제: 비트 연산 실습 1 – 두 수의 공통 비트 확인
     *
     * 문제 설명
     *
     * 정수 a와 b가 주어졌을 때,
     * 둘 사이에 공통으로 1인 비트가 존재하는지 확인하세요.
     * 	•	공통 비트가 있다면 "YES",
     * 	•	없다면 "NO"를 출력하세요.
     *
     * 입력 예시1
     * a = 12
     * b = 10
     *
     * 출력 예시1
     * YES
     *
     * 입력 예시2
     * a = 8
     * b = 4
     * 출력 예시
     * NO
     */

    @Test
    fun solution1(){
        val a = 12
        val b = 10
        val expected = "YES"
        assertEquals(expected, commonBit(a, b))
    }

    @Test
    fun solution2(){
        val a = 8
        val b = 4
        val expected = "NO"
        assertEquals(expected, commonBit(a, b))
    }

    private fun commonBit(a: Int, b: Int): String {
        return if(a and b > 0) "YES" else "NO"
    }

}