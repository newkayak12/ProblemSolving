package programmers.dfs

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TargetNumber {
    /**
     * **문제**:
     * 배열 numbers의 숫자를 순서대로 더하거나 빼서 target을 만드는 방법의 수를 return 하세요.
     *
     * **제한사항**:
     * - numbers 길이: 2~20
     * - numbers 원소: 1~50
     * - target: 1~1000
     *
     * **입출력 예**:
     * ```
     * numbers: [1, 1, 1, 1, 1]
     * target: 3
     * return: 5
     */

    @Test
    fun solution(){
        val numbers = intArrayOf(1, 1, 1, 1, 1)
        val target = 3
        val expected = 5

        assertEquals(expected, calculate(numbers, target))
    }

    private fun calculate(numbers: IntArray, target: Int): Int {
        return useRecursive(numbers, target, 0, 0)
    }

    private fun useRecursive(numbers: IntArray, target: Int, sum: Int, step: Int): Int {
        if(numbers.size == step) {
            return if(target == sum) 1 else 0
        }
        var count = 0

        val value = numbers[step]
        count += useRecursive(numbers, target, sum - value, step + 1)
        count += useRecursive(numbers, target, sum + value, step + 1)


        return count
    }
}