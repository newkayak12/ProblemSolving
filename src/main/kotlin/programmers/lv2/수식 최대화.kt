package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class `수식 최대화` {

    @Test
    fun solution1() {
        val expression = "100-200*300-500+20"
        val result = 60420L

        assertEquals(result, calculate(expression))
        assertEquals(result, calculate2(expression))
    }

    @Test
    fun solution2() {
        val expression = "50*6-3*2"
        val result = 300L

        assertEquals(result, calculate(expression))
        assertEquals(result, calculate2(expression))
    }

    //factorial 개수
    //tokenize +

    private fun tokenize(expression: String): List<String> {
        val deque = ArrayDeque<Char>()
        val tree = mutableListOf<String>()

        for (char in expression) {
            if (!char.isDigit()) {
                val digit = StringBuilder()
                while (deque.isNotEmpty()) {
                    digit.append(deque.removeFirst())
                }

                tree.add(digit.toString())
                tree.add(char.toString())
            }

            if (char.isDigit()) {
                deque.add(char)
            }
        }

        val buffer = StringBuilder()
        while (deque.isNotEmpty()) {
            buffer.append(deque.removeFirst())
        }

        if (buffer.isNotEmpty()) {
            tree.add(buffer.toString())
        }

        return tree
    }

    fun calculate(expression: String): Long {
        var score = Long.MIN_VALUE
        val tokenized = tokenize(expression)
        val priorities = listOf(
            listOf("+", "-", "*"),
            listOf("+", "*", "-"),
            listOf("-", "*", "+"),
            listOf("-", "+", "*"),
            listOf("*", "+", "-"),
            listOf("*", "-", "+"),
        )

        for (case in priorities) {
            val temporary: MutableList<String> = tokenized.toMutableList()
            for (operator in case) {
                var index = temporary.indexOf(operator)

                while (true) {
                    if(index == -1) break
                    val left = temporary[index - 1].toLong()
                    val right = temporary[index + 1].toLong()

                    val result = when (operator) {
                        "+" -> left + right
                        "-" -> left - right
                        "*" -> left * right
                        else -> error("invalid")
                    }

                    temporary[index - 1] = result.toString()
                    temporary.removeAt(index)
                    temporary.removeAt(index)

                    index = temporary.indexOf(operator)
                }
            }

            score = maxOf(score, abs(temporary.first().toLong()))
        }


        return score
    }


    private fun tokenizeNumber(expression: String): List<Long> {
        return expression.split("*", "-", "+").map { it.toLong() }
    }
    private fun tokenizeOps(expression: String): List<Char> {
        return expression.filter { it in "*-+"}.toList()
    }

    fun calculate2(expression: String): Long {
        var answer: Long = 0
        val cases = listOf("*+-","*-+", "+*-", "+-*", "-*+", "-+*")
        val numsOrder = tokenizeNumber(expression)
        val opsOrder = tokenizeOps(expression)

        cases.forEach { case ->
            val tempNums = numsOrder.toMutableList()
            val tempOps = opsOrder.toMutableList()

            case.forEach { op ->
                var index = tempOps.indexOfFirst { it == op }

                while (index >= 0) {
                    val result = when(op) {
                        '+' -> tempNums[index] + tempNums[index + 1]
                        '*' -> tempNums[index] * tempNums[index + 1]
                        '-' -> tempNums[index] - tempNums[index + 1]
                        else -> 0
                    }

                    tempNums[index + 1] = result
                    tempNums.removeAt(index)
                    tempOps.removeAt(index)

                    index = tempOps.indexOfFirst { it == op }
                }

            }

            answer = answer.coerceAtLeast(Math.abs(tempNums[0]))
        }

        return answer
    }

}