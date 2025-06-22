package renewal.memoization

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FibonacciWithMemoization {
    /**
     * 🧪 문제 이름
     *
     * 기억하는 피보나치
     * ⸻
     * 📄 문제 설명
     *
     * 정수 n이 주어졌을 때, n번째 피보나치 수를 구하는 프로그램을 작성하세요.
     *
     * 단, 함수 fib(n)은 다음과 같이 정의됩니다:
     * 	•	fib(0) = 0
     * 	•	fib(1) = 1
     * 	•	fib(n) = fib(n-1) + fib(n-2) (n ≥ 2)
     *
     * 단, 이 계산은 반드시 “메모이제이션”을 사용한 방식으로 수행되어야 합니다.
     * (중복 호출을 허용하지 않으며, 이미 계산한 fib(k)는 다시 계산하지 않아야 합니다)
     * ⸻
     * 📥 입력
     * 	•	정수 n (0 ≤ n ≤ 100)
     * ⸻
     * 📤 출력
     * 	•	n번째 피보나치 수
     */

    @Nested
    inner class Solution {

        @Test
        fun fibonacci5() {
            val expected = 5
            val result = fibonacci(mutableMapOf<Int, Int>(), 5)

            assertEquals(expected, result)
        }

        @Test
        fun fibonacci10() {
            val expected = 55
            val result = fibonacci(mutableMapOf<Int, Int>(), 10)

            assertEquals(expected, result)
        }

        @Test
        fun fibonacci20() {
            val expected = 6765
            val result = fibonacci(mutableMapOf<Int, Int>(), 20)

            assertEquals(expected, result)
        }
    }


    private tailrec fun fibonacci(memoization: MutableMap<Int, Int>, number: Int): Int  {
        return when (number) {
            0 -> 0
            1 -> 1
            else -> memoization[number] ?: run {
                val result = fibonacci(memoization, number - 2) + fibonacci(memoization, number - 1)
                memoization[number] = result
                return result
            }
        }
    }
}