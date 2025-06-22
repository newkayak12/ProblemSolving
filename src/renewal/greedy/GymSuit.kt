package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GymSuit {
    /**
     *<pre>
     * ## 체육복
     *
     * 문제 설명
     *
     * 학교에는 체육 수업이 있습니다.
     * 모든 학생은 체육복을 한 벌씩 가지고 있습니다.
     * 하지만 일부 학생들은 체육복을 도난당했고, 일부 학생들은 여벌의 체육복을 가지고 있습니다.
     * 체육복이 없는 학생은 앞번호나 뒷번호의 학생에게만 빌릴 수 있습니다.
     * 모든 학생이 체육 수업을 들을 수 있는 최대 학생 수를 구하세요.
     * ⸻
     *
     * 입력
     * 	•	학생 수 N (2 ≤ N ≤ 30)
     * 	•	도난당한 학생 번호 목록 lost[] (번호는 1부터 N까지)
     * 	•	여벌 체육복을 가진 학생 번호 목록 reserve[] (번호는 1부터 N까지)
     * ⸻
     * 출력
     * 	•	수업을 들을 수 있는 최대 학생 수
     * ⸻
     *
     * 조건
     * 	•	도난당한 학생이 여벌을 가지고 있다면, 그 학생은 자신의 여벌을 사용합니다.
     * 	•	빌려줄 수 있는 조건은 앞번호, 뒷번호 학생만 가능.
     * 	•	모든 입력 값은 자연수이며, 입력 목록의 길이는 학생 수 이하입니다.
     * ⸻
     * 예제 입력
     *
     * N=5
     * lost=[2, 4]
     * reserve=[3]
     * ⸻
     * 예제 출력
     * 4
     * </pre>
     */

    @Test
    fun solution() {
        val student = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(3)

        val expected = 4

        assertEquals(expected, greedy(student, lost, reserve))
    }

    private fun greedy(student: Int, lost: IntArray, reserve: IntArray): Int {
        val lostStudent = lost.toMutableList()
        val reserveStudent = reserve.toMutableList()
        lostStudent.removeAll(reserve.toList())
        reserveStudent.removeAll(lost.toList())
        var count = student - lostStudent.size

        for( i in lostStudent) {
            when {
                reserveStudent.contains(i - 1) -> {
                    reserveStudent.remove(i - 1)
                    count++
                    lostStudent.remove(i)
                }
                reserveStudent.contains(i + 1) -> {
                    reserveStudent.remove(i + 1)
                    count++
                    lostStudent.remove(i)
                }
            }
        }

        return count
    }
}