package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Step {
    /**
     *
     * ⸻
     * 🧪 문제 1. 계단 오르기
     * 📄 문제 설명
     * 한 번에 1칸 또는 2칸을 오를 수 있다.
     * 총 n칸의 계단이 있을 때, 정확히 n번째 칸에 도달하는 방법의 수를 구하시오.
     * 입력
     * 	•	정수 n (1 ≤ n ≤ 10⁶)
     * 출력
     * 	•	방법의 수 (mod 1,000,000,007)
     *
     * n = 1 (1) 1
     * n = 2 (1+1) (2) 2
     * n = 3 (1+1+1) (1+2) (2+1) 3
     * n = 4 (1+1+1+1) (1+1+2) (1+2+1) (2+1+1) (2+2) 5
     * n = 5 (1+1+1+1+1) (1+1+1+2) (1+1+2+1) (1+2+1+1) (2+1+1+1) (1+2+2) (2+1+2) (2+2+1)
     * --> fibonacci
     */

    @Test
    fun solution(){

        val expected = 8
        val result = useFibonacci(5) % 1_000_000_007

        assertEquals(expected, result)
    }

    private fun useFibonacci(number: Int): Int {
        val table = Array(number + 1){
            i -> when(i) {
                0 -> 1
                1 -> 2
                else -> 0
            }
        }

        for(i in 2 .. number) {
            table[i] = table[i - 1] + table[i - 2]
        }

        return table[number - 1]
    }
}