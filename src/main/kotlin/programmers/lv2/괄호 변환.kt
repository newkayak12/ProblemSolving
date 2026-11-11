package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `괄호 변환` {
    /**
     * 균형 잡힌 괄호: ()가 최소 한 쌍
     * 올바른 괄호 : ()로 열고 닫힘
     *
     * w -> u:(균형 잡힌 괄호 문자열 or 올바른 괄호) -> stack으로 열닫 확인
     *   -> v: w - u
     *     -> w = v
     *
     * if(u == 올바른 괄호) {
     *   u는 string buffer 에 저장
     *   w = v
     *   다시 실행
     * }
     * else {
     *   var str = "("
     *
     * }
     *
     *
     *
     * ()))((()
     *  ㄴ() + ))((()
     *    ㄴ ))((
     *      ㄴ ()
     *          ( + () + ) +()
     *
     * ())))(((()
     *  ㄴ() + )))(((()
     *    ㄴ )))((( + ()
     *      ㄴ ( + () + )
     *          ㄴ [)] + ))(( [(]
     *             ㄴ (())
     *
     *  ()))))((((()
     *
     *  () + ))))((((()
     *   ㄴ ))))(((( + ()
     *      ㄴ ( + () + )
     *         ㄴ [)] + )))((( + [(]
     *           ㄴ ((()))
     *
     *   ())))()()(((()
     *    ㄴ () + )))()()(((()
     *      ㄴ )))()()((( + ()
     *         ㄴ ( + () + )
     *            ㄴ [)] + ))()()(( + [(]
     *             ㄴ (()()())
     */

    @Test
    fun solution1() {
        val P = "(()())()"
        val result = "(()())()"

        assertEquals(result, calculate(P))
    }

    @Test
    fun solution2() {
        val P = ")("
        val result = "()"

        assertEquals(result, calculate(P))
    }

    @Test
    fun solution3() {
        val P = "()))((()"
        val result = "()(())()"

        assertEquals(result, calculate(P))
    }

    fun calculate(p: String): String {

        /**
         * 1. 균형잡힌 괄호 찾기
         * 2. 완전한 괄호인지 찾기
         *  2-1. 완전한 괄호라면 substring 후 1번으로 회귀
         *  2-2. ( + <1번 회귀> + ) + 완전한 괄호(substring(1, length - 1).replace
         */
        return recursive(p)
    }

    private fun recursive(parentheses: String): String {

        if(parentheses.isEmpty()) return ""

        val endIndex = parentheses.findBalancedIndex()

        if (endIndex == -1) return parentheses

        val isRight = parentheses.isStartWithLeftParentheses()



        val left = parentheses.substring(0..endIndex)
        val last = parentheses.substring(endIndex + 1)



        return if (isRight) left + recursive(last)
        else "(${recursive(last)})${left.removeFirst().removeLast().reverseParentheses()}"
    }

    private fun String.findBalancedIndex(): Int {
        var value = 0

        this.forEachIndexed { index, char ->
            if (index != 0 && value == 0) return index - 1

            if (char == '(') value++
            else if (char == ')') value--
        }

        return this.length - 1
    }

    private fun String.isStartWithLeftParentheses(): Boolean {
        return this[0] == '('
    }

    private fun String.removeFirst(): String = if (this.isEmpty()) "" else this.substring(1)
    private fun String.removeLast(): String = if (this.isEmpty()) "" else this.substring(0, this.length - 1)
    private fun String.reverseParentheses(): String = this.replace('(', '-')
        .replace(')', '(')
        .replace('-', ')')
}