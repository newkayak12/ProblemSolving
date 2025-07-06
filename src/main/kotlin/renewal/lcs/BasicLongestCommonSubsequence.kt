package renewal.lcs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BasicLongestCommonSubsequence {
    /**
     * ✅ 2단계: 기초 예시
     * 문제:
     * 문자열 A = "ACAYKP"
     * 문자열 B = "CAPCAK" 가 주어졌을 때,
     * 두 문자열의 최장 공통 부분 수열(LCS)의 길이를 구하시오.
     * ⸻
     * 📌 핵심 조건
     * 	•	연속일 필요 없음 (부분 문자열 아님)
     * 	•	순서는 유지해야 함 (예: A에서 나온 문자의 순서와, B에서 나온 문자의 순서가 같아야 함)
     *
     *
     * - i : A의 인덱스 (1부터 시작, 0은 공집합)
     * - j : B의 인덱스 (1부터 시작, 0은 공집합)
     * - dp[i][j] : A[0..i-1]와 B[0..j-1]까지의 최장 공통 부분 수열의 길이
     * - 테이블 크기 : (A.length + 1) x (B.length + 1)
     *
     * - 수열
     *     -  A  C  A  Y  K  P
     *  -  0  0  0  0  0  0  0
     *  C  0  0  1  1  1  1  1
     *  A  0  1  1  2  2  2  2
     *  P  0  1  1  2  2  2  3
     *  C  0  1  2  2  2  2  3
     *  A  0  1  2  3  3  3  3
     *  K  0  1  2  3  3  4  4
     *
     *  - 일치 여부
     *      -  A  C  A  Y  K  P
     *   -  X  X  X  X  X  X  X
     *   C  X  X  O  X  X  X  X
     *   A  X  O  X  O  X  X  X
     *   P  X  x  X  X  X  X  O
     *   C  X  X  O  X  X  X  X
     *   A  X  O  X  O  X  X  X
     *   K  X  X  X  X  X  O  X
     *
     *   - 일치할 때 dp[i][j] = dp[i-1][j - 1] + 1이라고 하자
     *   - 아니면 dp[i][j] = dp[i][j - 1]
     *     - 그러나 [4][6]에서 ACAYKP - CAP로 3개 일치한 상태에서 CAPC로 넘어갔을 때 위의 기준이 일치하지 않음
     *     - dp[i][j] = dp[i-1][j] 도 고려 대상이다.
     *     - 둘 중 큰 값을 수록하는 것으로 보인다.
     *
     * 	pseudo
     *
     * 	val array = [B.length + 1][A.length + 1]
     * 	for( i in 1 .. B.length)
     * 	    for(j in 1..A.length)
     *   	    array[i][j] =
     * 	          if(A[j-1] == B[i-1]]) array[i - 1][j - 1] + 1
     * 	          else maxOf(array[i-1][j], array[i][j-1])
     */


    @Test
    fun solution() {
        val A = "ACAYKP"
        val B = "CAPCAK"
        val expected = 4

        assertEquals(expected, lcs(A,B))
    }

    private fun lcs(A: String, B: String): Int {
        val table = Array(B.length + 1){IntArray(A.length+1)}

        for( i in 1 .. B.length){
            for(j in 1 .. A.length) {
                table[i][j] =
                    if(B[i-1]==A[j-1]) table[i-1][j-1] + 1
                    else maxOf(table[i-1][j], table[i][j-1])
            }
        }


        return table[B.length][A.length]
    }

}