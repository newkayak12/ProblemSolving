package renewal.dataStructure.stack

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Postfix {
    /**
     * 📘 실습 문제 – 후위 표기식 계산 (기초 버전)
     *
     * 🧾 문제 설명
     *
     * 중위 표기식(예: 2 + 3)이 아닌 후위 표기식(Postfix, 예: 2 3 +)이 주어집니다.
     * 이를 스택을 이용해서 계산하세요.
     *
     * 📥 입력 형식
     * 	•	문자열로 된 후위 표기식이 한 줄에 주어집니다.
     * 	•	피연산자는 한 자리 숫자(0~9)로만 주어지며, 공백으로 구분되어 있습니다.
     * 	•	연산자는 +, -, *, / 중 하나입니다.
     *
     * 예: "2 3 + 4 *"
     * → (2 + 3) * 4 = 20
     *
     * 📤 출력 형식
     * 	•	계산 결과를 출력하세요. (정수만)
     *
     * ⸻
     *
     * ✅ 예시 입출력
     *
     * |입력 | 출력|
     * |:---:|:---:|
     * |2 3 + | 5|
     * |2 3 + 4 * | 20|
     * |5 1 2 + 4 * + 3 - | 14|
     *
     */
    @Test
    fun solution1() {
        val input = listOf("2", "3", "+")
        val expected = 5
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution2() {
        val input = listOf("2", "3", "+", "4", "*")
        val expected = 20
        assertEquals(expected, useStack(input))
    }

    @Test
    fun solution3() {
        val input = listOf("5", "1", "2", "+", "4", "*", "+", "3", "-")
        val expected = 14
        /**
         * 5 + (((1 + 2) * 4) - 3)
         */
        assertEquals(expected, useStack(input))
    }

    private fun useStack(input: List<String>): Int {
        val stack = ArrayDeque<Int>()
        val plus = "+"
        val minus = "-"
        val multiply = "*"
        val divide = "/"
        val operator = listOf(plus, minus, multiply, divide)

        for(element in input) {

            if(operator.contains(element)) {
                val first = stack.removeLast()
                val second = stack.removeLast()
                val result = calculate(element, second, first)
                stack.addLast(result)
            }
            else {
                stack.addLast(element.toInt())

            }
        }


        return stack.removeLast()
    }

    private fun calculate(operator: String, prev:Int, next: Int): Int {
        val plus = "+"
        val minus = "-"
        val multiply = "*"
        val divide = "/"

        return when (operator) {
            plus -> prev + next;
            minus -> prev - next;
            multiply -> prev * next;
            divide -> prev / next;
            else -> prev
        }
    }
}