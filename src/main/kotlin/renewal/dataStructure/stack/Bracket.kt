package renewal.dataStructure.stack

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Bracket {

    /**
     * 🧪 10-1-2. 스택 – 개념 문제
     *
     * 💡 문제: 올바른 괄호
     *
     * 설명
     * 문자열이 주어졌을 때, 모든 괄호가 올바르게 열고 닫혀 있는지 판단하시오.
     * 	•	입력: "(()())"
     * 	•	출력: "YES"
     * 	•	입력: ")(()"
     * 	•	출력: "NO"
     *
     * ⸻
     *
     * ✅ 조건
     * 	•	괄호 종류는 ( 와 ) 만 있음
     * 	•	길이 최대 100,000
     *
     */

    @Test
    fun solution1() {
        val input = "(()())"
        val expected = "YES"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution2() {
        val input = ")(()"
        val expected = "NO"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution3() {
        val input = "()"
        val expected = "YES"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution4() {
        val input = "(()())"
        val expected = "YES"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution5() {
        val input = ")("
        val expected = "NO"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution6() {
        val input = "((("
        val expected = "NO"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution7() {
        val input = "(()"
        val expected = "NO"
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution8() {
        val input = "())"
        val expected = "NO"
        assertEquals(expected, useStack(input))
    }


    private fun useStack(input: String): String {
        val stack = ArrayDeque<Char>()
        val left = '('
        var index = 0



        while (index < input.length) {
            val char = input[index++]

            if (char == left) {
                stack.addLast(char)
                continue
            }

            if (stack.isEmpty()) {
                return "NO"
            }

            stack.removeLast()
        }

        return if (stack.isEmpty()) "YES" else "NO"
    }


}