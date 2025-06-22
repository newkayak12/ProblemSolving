package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MaxStudyTime {
    /**
     * 🔹 문제 3. 최대 공부 점수
     *
     * 설명
     *
     * 학생은 k시간 동안 공부할 수 있습니다.
     * 각 과목마다 공부 시간과 점수가 있습니다.
     * 어떤 과목을 공부할지 선택해서, 총 점수가 최대가 되도록 하세요.
     * ⸻
     * 입력 예시
     * 	•	공부 시간 한도: k = 5
     * 	•	과목 목록:
     * 	```kotlin
     * 	val subjects = arrayOf(
     *     intArrayOf(1, 2),  // 시간 1, 점수 2
     *     intArrayOf(2, 4),  // 시간 2, 점수 4
     *     intArrayOf(3, 5)   // 시간 3, 점수 5
     * )
     * 	```
     */

    @Test
    fun solution() {
        val maxTime = 5
        val subjects = arrayOf(
            intArrayOf(1, 2),  // 시간 1, 점수 2
            intArrayOf(2, 4),  // 시간 2, 점수 4
            intArrayOf(3, 5)   // 시간 3, 점수 5
        )

        val expected = 9

        assertEquals(expected, study(maxTime, subjects))
    }

    private fun study(time: Int, subject: Array<IntArray>): Int {
        val table = Array(subject.size + 1) { IntArray(time + 1) }

        for(i in 1 .. subject.size) {
            val (spendTime, score) = subject[i]
            for (j in 0 .. time) {
                table[i][j] = if(j < spendTime) table[i - 1][j]
                else maxOf(table[i - 1][j], table[i - 1][j - spendTime] + score)
            }
        }

        return table[subject.size][time]
    }
}