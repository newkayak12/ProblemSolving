package renewal.lcs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EditDistance {

    /**
     * ✅ Step 3 — 편집 거리 (Edit Distance) 개념 정리
     *
     * ⸻
     *
     * 📌 기본 개념
     * 	•	한 문자열을 다른 문자열로 바꾸기 위한 최소 연산 횟수
     * 	•	허용 연산: 삽입, 삭제, 교체
     * 	•	보통 두 문자열 A, B가 주어짐
     *
     * ⸻
     *
     * 예시
     *
     * A = "kitten"
     * B = "sitting"
     *
     * 바꾸기 과정
     *
     * 1️⃣ "kitten" → "sitten" (첫 번째 글자 k → s, 교체 1회)
     * 2️⃣ "sitten" → "sittin" (마지막 글자 e → i, 교체 2회)
     * 3️⃣ "sittin" → "sitting" (g 추가, 삽입 1회)
     *
     * → 최소 연산 횟수는 3회
     *
     * ⸻
     *
     * 📎 구조적 질문 (추론 유도)
     *
     * 1️⃣ dp[i][j]가 담아야 하는 상태는?
     *  -> i, j 간 변경 횟수(편집 거리)
     * 2️⃣ 현재 위치에서 삽입/삭제/교체 연산 중 어떤 선택을 할지 어떻게 결정할까?
     *  -> 1️⃣ 교체 (대각선)
     *  -> 2️⃣ 삽입 (왼쪽)
     *  -> 3️⃣ 삭제 (위쪽)
     * 3️⃣ 문자가 같을 때는 어떤 최적화를 할 수 있을까?
     *  -> 교체 연산을 하지 않는다.
     * 4️⃣ 테이블의 시작점(0행, 0열)은 어떤 의미를 가져야 할까?
     *  -> dp[0][*]는 빈 문자 -> A
     *  -> dp[*][0]는 빈 문자 -> B
     *
     * table
     *   \ -  K  I  T  T  E  N
     *  -  0  1  2  3  4  5  6
     *  S  1  1  2  3  4  5  6
     *  I  2  2  1  2  3  4  5
     *  T  3  3  2  1  2  3  4
     *  T  4  4  3  2  1  2  3
     *  I  5  5  4  3  2  2  3
     *  N  6  6  5  4  3  3  2
     *  G  7  7  6  5  4  4  3
     *
     * 위 표 기준으로  ➡️ + 1 (삭제)
     * 위 표 기준으로  ⬇️ + 1 (삽입)
     * 위 표 기준으로  ↘️ + 1 (교체)
     * 위 표 기준으로  ↘️ + 0 (일치)
     *
     *
     */

    @Test
    fun solution() {
        val aString = "sitting"
        val bString = "kitten"

        val expected = 3

        assertEquals(expected, useLcs(aString, bString))
    }

    private fun useLcs(aString: String, bString: String): Int {
        val table: Array<IntArray> = Array(aString.length + 1) {
            IntArray(bString.length + 1) { i: Int ->
                if (it == 0) i
                else if (i == 0) it
                else 0
            }
        }



        for (i in 1..aString.length) {
            for (j in 1..bString.length) {
                table[i][j] =
                    if(aString[i - 1] != bString[j - 1]) {
                        minOf(
                            table[i - 1][j - 1],
                            table[i - 1][j],
                            table[i][j - 1]
                        ) + 1
                    }
                    else table[i-1][j-1]
            }
        }

        return table[aString.length][bString.length]
    }
}