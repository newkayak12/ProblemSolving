package renewal.dataStructure.stack

import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.test.assertEquals

class Histogram {
    /**
     * 📘 실습 문제 – 가장 큰 직사각형 (Histogram)
     *
     * 💡 문제 설명
     *
     * 세로선의 높이가 주어졌을 때, 연속된 막대들로 만들 수 있는 가장 큰 직사각형의 넓이를 구하세요.
     * (단, 모든 막대의 너비는 1입니다.)
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	정수 배열 형태로 n개의 막대 높이가 주어짐
     *
     * 예: [2, 1, 5, 6, 2, 3]
     *
     * ⸻
     *
     * 📤 출력 형식
     * 	•	만들 수 있는 최대 직사각형 넓이를 출력
     *
     * ⸻
     *
     * 🧮 예시
     *
     * |입력|출력|
     * |:---:|:---:|
     * |[2, 1, 5, 6, 2, 3]|10|
     * |[2, 4]|4|
     * |[6, 2, 5, 4, 5, 1, 6]|12|
     *
     */

    @Test
    fun solution1() {
        val input = listOf(2, 1, 5, 6, 2, 3)
        val expected = 10
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution2() {
        val input = listOf(2, 4)
        val expected = 4
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution3() {
        val input = listOf(6, 2, 5, 4, 5, 1, 6)
        val expected = 12
        assertEquals(expected, useStack(input))
    }

    private fun useStack(input: List<Int>): Int {
        val extendedInput = List(input.size + 1) { input.getOrElse(it) { 0 } }
        val stack = ArrayDeque<Int>()
        var maxArea = 0


        for (i in extendedInput.indices) {

            while (stack.isNotEmpty() && extendedInput[i] < extendedInput[stack.last()]) {
                val topIndex = stack.removeLast()
                val height = extendedInput[topIndex]
                val width = if (stack.isEmpty()) i else i - stack.last() - 1
                maxArea = max(maxArea, width * height)
            }

            stack.addLast(i)
        }

        return maxArea
    }
}