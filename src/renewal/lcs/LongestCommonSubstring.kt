package renewal.lcs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LongestCommonSubstring {
    /**
     * ✅ 문제: 공통 부분 문자열
     *
     * 문제 설명
     * 두 문자열 A와 B가 주어질 때,
     * 두 문자열에 공통으로 포함되는 연속된 부분 문자열 중
     * 가장 긴 문자열의 길이를 구하세요.
     * ⸻
     * 입력
     * 	•	문자열 A (1 ≤ A.length ≤ 1000)
     * 	•	문자열 B (1 ≤ B.length ≤ 1000)
     * 	•	알파벳 대문자로만 구성됨
     * ⸻
     * 출력
     * 	•	공통으로 등장하는 연속된 부분 문자열 중 가장 긴 길이
     * ⸻
     * 예제
     * A = "ABABC"
     * B = "BABCAC"
     * → 정답: 4 ("BABC")
     */

    @Test
    fun solution(){
        val A = "ABABC"
        val B = "BABCAC"
        val expected = 4

        assertEquals(expected, lcs(A,B))
    }

    private fun lcs(A: String, B:String): Int{
        val table = Array(B.length + 1){IntArray(A.length + 1)}

        for( i in 1 .. B.length) {
            for(j in 1 .. A.length) {
                table[i][j] =
                    if(B[i - 1] == A[j - 1]) table[i-1][j-1] + 1
                    else 0
            }
        }

        var max = 0
        for(row in table){
            max = maxOf(max, row.maxOrNull() ?: 0)
        }
        return max
    }
}