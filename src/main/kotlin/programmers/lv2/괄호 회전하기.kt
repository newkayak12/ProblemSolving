package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `괄호 회전하기` {
    //sliding?


    @Test
    fun solution1() {
        val s = "[](){}"
        val expected = 3

        assertEquals(expected, calculate(s))
    }

    @Test
    fun solution2() {
        val s = "}]()[{"
        val expected = 2

        assertEquals(expected, calculate(s))
    }

    @Test
    fun solution3() {
        val s = "[)(]"
        val expected = 0

        assertEquals(expected, calculate(s))
    }

    @Test
    fun solution4() {
        val s = "}}}"
        val expected = 0

        assertEquals(expected, calculate(s))
    }

    private fun calculate(s: String): Int {
        val extended = s + s
        var count = 0
        val stack = ArrayDeque<Char>()


        for (index in s.indices) {
            val addedIndex = index + s.length
            stack.clear()

            for (l in index  until addedIndex){

                if(stack.isEmpty() && (extended[l] == ']' || extended[l] == ')' || extended[l] == '}')){
                    stack.addLast('F')
                    break
                }

                if(extended[l] == '[' || extended[l] == '(' || extended[l] == '{') {
                    stack.addLast(extended[l])
                    continue
                }

                if(extended[l] == ']' && stack.last() == '[') {
                    stack.removeLast()
                    continue
                }

                if(extended[l] == ')' && stack.last() == '(') {
                    stack.removeLast()
                    continue
                }

                if(extended[l] == '}' && stack.last() == '{') {
                    stack.removeLast()
                    continue
                }

            }

            if(stack.isEmpty()) count ++
        }

        return count
    }


}