package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MakeLine {
    /**
     * 문제: 줄 세우기
     *
     * 문제 설명
     * 학생들이 키 순서대로 줄을 서야 합니다.
     * 학생들은 키와 번호가 주어집니다. 키가 작은 학생이 앞에 서야 하며,
     * 키가 같은 경우 번호가 작은 학생이 앞에 서야 합니다.
     *
     * 입력
     * 	•	첫 줄: 학생 수 N (1 ≤ N ≤ 100,000)
     * 	•	다음 N줄: 각 학생의 번호(정수)와 키(정수)가 공백으로 구분되어 주어집니다.
     *
     * 출력
     * 	•	줄을 선 학생들의 번호를 한 줄에 공백으로 구분해 출력하세요.
     *
     * 제한
     * 	•	번호와 키는 모두 1 이상 1,000,000 이하의 정수입니다.
     *
     * 예제 입력 1
     * 5
     * 3 160
     * 1 150
     * 2 150
     * 5 170
     * 4 160
     *
     * 예제 출력 1
     * 1 2 3 4 5
     */

    @Test
    fun solution() {
        val size = 5
        val table = arrayOf(
            intArrayOf(3, 160),
            intArrayOf(1, 150),
            intArrayOf(2, 150),
            intArrayOf(5, 170),
            intArrayOf(4, 160),
        )
        val expected = "1 2 3 4 5"
        assertEquals(expected, greedy(table))
    }

    private fun greedy(table: Array<IntArray>): String {
        table.sortBy { it[0] }

        val builder = StringBuilder()
        for( element in table ) {
            builder.append("${element[0]} ")
        }

        return builder.toString().trim()
    }
}