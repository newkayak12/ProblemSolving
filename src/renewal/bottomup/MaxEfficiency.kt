package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.max

class MaxEfficiency {
    /**
     * 🔹 문제 2. 시간 내 최대 업무 효율
     *
     * 설명
     *
     * 당신에겐 T시간이 있습니다.
     * 일 N개가 있고, 각 일은 걸리는 시간과 효율 점수가 있습니다.
     * 정해진 시간 내에 몇 개의 일을 골라서 효율의 총합이 최대가 되도록 하세요.
     *
     * ⸻
     *
     * 입력 예시
     * 	•	시간 제한: T = 8
     * 	•	일 목록:
     * 	```kotlin
     * 	val tasks = arrayOf(
     *     intArrayOf(2, 3),  // 시간 2, 점수 3
     *     intArrayOf(4, 5),  // 시간 4, 점수 5
     *     intArrayOf(3, 6)   // 시간 3, 점수 6
     * )
     * 	```
     */

    @Test
    fun solution(){
        val time = 8
        val tasks = arrayOf(
            intArrayOf(2, 3),  // 시간 2, 점수 3
            intArrayOf(4, 5),  // 시간 4, 점수 5
            intArrayOf(3, 6)   // 시간 3, 점수 6
        )
        val expected = 11

        assertEquals(expected, maxEfficiency(time, tasks))
    }

    private fun maxEfficiency(time: Int, tasks: Array<IntArray>): Int {
        val table = Array(tasks.size + 1) { IntArray(time + 1) }

        for(i in 1 .. tasks.size){
            val (spendTime, score) = tasks[i - 1]

            for( j in 0 .. time) {
                table[i][j] =
                if(j < spendTime)  table[i - 1][j]
                else maxOf(table[i - 1][j], table[i - 1][j - spendTime] + score)
            }
        }

        return table[tasks.size][time]
    }
}