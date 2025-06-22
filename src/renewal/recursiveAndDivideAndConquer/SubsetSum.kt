package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SubsetSum {
    /**
     * ✅ 문제 설명
     * 	•	정수 배열 nums와 목표 값 target이 주어집니다.
     * 	•	nums의 일부 원소를 선택하여 합이 target이 되도록 할 수 있는지 확인하세요.
     * ⸻
     * ✅ 입력
     * 	•	정수 배열 nums (예: [3, 34, 4, 12, 5, 2])
     * 	•	정수 target (예: 9)
     * ⸻
     * ✅ 출력
     * 	•	합이 target인 부분집합이 존재하면 True, 아니면 False
     */

    @Test
    fun solution() {
        val array = intArrayOf(3, 34, 4, 12, 5, 2)
        val target = 9

        val result = useBackTracking(array, BooleanArray(array.size), target, 0)
        assertTrue(result)
    }

    fun useBackTracking(array: IntArray, table: BooleanArray, target: Int, number: Int): Boolean {

        var result = false
        if (target == number) return true

        for (i in 0 until array.size) {
            if (table[i]) continue

            table[i] = true
            result = result || useBackTracking(array, table, target, number + array[i])
            table[i] = false
        }
        return result

    }

}