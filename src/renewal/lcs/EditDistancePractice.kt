package renewal.lcs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EditDistancePractice {

    /**
     * 🟩 문제 이름: 최소 삽입/삭제 연산 수
     * 📘 문제 설명
     * 문자열 A와 B가 주어집니다.
     * 문자열 A를 문자열 B로 만들기 위해 다음 연산만 허용됩니다:
     * ✅ 문자를 삽입한다.
     * ✅ 문자를 삭제한다.
     * 두 연산만으로 A를 B로 바꾸는 최소 연산 횟수를 구하세요.
     * ⸻
     * 📥 입력
     * 	•	첫째 줄: 문자열 A (1 ≤ A 길이 ≤ 1,000)
     * 	•	둘째 줄: 문자열 B (1 ≤ B 길이 ≤ 1,000)
     * ⸻
     * 📤 출력
     * 	•	A를 B로 만들기 위한 최소 삽입/삭제 연산 횟수를 출력하세요.
     * ⸻
     *
     * 입력
     * heap
     * pea
     *
     * 출력
     * 3
     *
     * \ - h e a p
     * - 0 1 2 3 4
     * p 1 1 2 3 4
     * e 2 2 1 2 3
     * a 3 3 2 1 2
     *
     * ➡️ 추가
     * ⬇️ 삭제
     * ↘️ 교체
     */

    @Test
    fun solution(){
        val aString = "heap"
        val bString = "pea"


        val expected = 3

        assertEquals(expected, lcs(aString, bString))
    }

    private fun lcs(aString: String, bString: String): Int {
        val table = Array(aString.length + 1) { outer: Int ->
            IntArray( bString.length + 1) { inner: Int ->
                if( outer == 0 ) inner
                else if( inner == 0) outer
                else 0
            }
        }


        for( i in 1 .. aString.length) {
            for (j in 1 .. bString.length) {
                table[i][j] =
                    if(aString[i - 1] == bString[j - 1]) table[i - 1][j - 1]
                    else minOf(
//                            table[i - 1][j - 1],  //교체 허용 안 함
                            table[i][j - 1],
                            table[i - 1][j]
                        ) + 1

            }
        }
        return table[aString.length][bString.length]
    }


}