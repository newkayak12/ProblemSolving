package renewal.lcs

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FileSync {
    /**
     * 🟩 문제 이름: 파일 동기화 최소 작업
     *
     * 📘 문제 설명
     *
     * 두 개의 파일 경로 A와 B가 주어집니다.
     * A를 B로 “동기화”하기 위해서는, 문자열의 삽입/삭제만을 사용하여 A를 B로 만들어야 합니다.
     *
     * 이때 필요한 최소 삽입/삭제 횟수를 구하세요.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	첫째 줄: 문자열 A (1 ≤ A 길이 ≤ 1,000)
     * 	•	둘째 줄: 문자열 B (1 ≤ B 길이 ≤ 1,000)
     *
     * ⸻
     *
     * 📤 출력
     * 	•	A를 B로 동기화하는 최소 작업 횟수
     *
     * 🧪 예제 입력
     * /usr/local
     * /local/bin
     *
     * 🎯 예제 출력
     * 8
     */

    @Test
    fun solution(){
        val aString = "/usr/local"
        val bString = "/local/bin"
        val expected = 8

        assertEquals(expected, useLcs(aString, bString))
    }

    private fun useLcs(aString: String, bString: String) :Int {
        val table = Array(bString.length + 1) { outer ->
            IntArray(aString.length + 1) { inner ->
                if( outer == 0 ) inner
                else if( inner == 0) outer
                else 0
            }
        }



        for(i in 1 .. bString.length) {
            for( j in 1 .. aString.length) {
                table[i][j] = if(bString[i - 1] == aString[j - 1])  table[i - 1][j - 1]
                else minOf(table[i - 1][j], table[i][j - 1]) + 1
            }
        }

        return table[bString.length][aString.length]
    }
}