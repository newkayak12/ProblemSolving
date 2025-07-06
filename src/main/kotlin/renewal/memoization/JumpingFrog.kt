package renewal.memoization

import org.junit.jupiter.api.Test

class JumpingFrog {
    /**
     * 🧪 문제 이름
     *
     * 개구리 점프
     * ⸻
     * 📄 문제 설명
     * 길이가 n인 직선 위에 개구리가 서 있습니다.
     * 개구리는 1칸 혹은 2칸씩만 앞으로 점프할 수 있습니다.
     * 개구리가 0번 위치에서 출발해서 n번 위치에 도달할 수 있는 경우의 수를 구하세요.
     * ⸻
     * 📥 입력
     * 	•	정수 n (1 ≤ n ≤ 100)
     * ⸻
     * 📤 출력
     * 	•	0번 칸에서 시작해서 n번 칸에 도달하는 총 방법의 수
     * 	🧪 예제
     * 	입력    출력      설명
     *   1      1      0 → 1
     *   2      2      0→1→2, 0→2
     *   3      3      0→1→2→3 0→1→3 0→2→3
     *   4      5      [0→1→2→3→4], [0→1→2→4], [0→2→3→4], [0→1→3→4], [0→2→4]
     *
     */

    @Test
    fun solution() {
        /**
         * f(1) = 1
         * f(2) = 2
         * f(3) = 3
         * f(4) = 5
         *
         * - 현재 해답이 이전 해답을 이용해서 계산하는가? -> 점화식 가능
         * - 같은 계산이 반복되는가? -> 메모이제이션
         * - 하위 문제를 해결하면 상위 문제를 해결할 수 있는가? -> DP
         * => 점화식
         */
    }

    private fun fibonacci(memoization: MutableMap<Int, Int>, number: Int): Int  {
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