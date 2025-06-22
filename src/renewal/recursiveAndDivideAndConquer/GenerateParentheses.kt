package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GenerateParentheses {
    /**
     *      문제 이름
     *      올바른 괄호 문자열 생성 (Generate Parentheses)
     *      ⸻
     *      문제 설명
     *      n쌍의 괄호 ()를 사용해서 만들 수 있는 모든 올바른 괄호 문자열을 출력하세요.
     *      올바른 괄호란, 여는 괄호 (가 닫는 괄호 )보다 먼저 나오고, 짝이 맞는 구조입니다.
     *      ⸻
     *      입력
     *      	•	정수 n (1 ≤ n ≤ 10)
     *      ⸻
     *      출력
     *      	•	가능한 모든 괄호 문자열을 사전순으로 정렬하여 출력
     *
     *      항목             제한
     *      최대 괄호 쌍 수  |  10
     *      출력 개수       |카탈란 수 (C_n), n=3이면 5개
     *      제약           | 여는 괄호 수 ≤ 닫는 괄호 수
     *
     *
     *      예시
     *      입력: n = 3
     *      출력:
     *      ((()))
     *      (()())
     *      (())()
     *      ()(())
     *      ()()()
     */
    @Test
    fun solution() {
        val number = 3
        val expected = listOf("((()))", "(()())", "(())()", "()(())", "()()()")
        val list = mutableListOf<String>();
        useBacktracking("", 0, 0, number, list)
        println(list)
        assertEquals(expected, list.sorted())
    }


    private fun useBacktracking(current: String, openCount: Int, closeCount: Int, n: Int, result: MutableList<String>) {
        if (2 * n == (openCount + closeCount)) {
            result.add(current)
            return
        }


        /**
         * 백트래킹 개념이 여기서 사용된다.
         * 추가로 열기 전에 닫을 수 없다는 것이 (closeCount < openCount) 이미 조건에 명시되어 )가 먼저나오는 상황은 없음
         */
        if (openCount < n) {
            useBacktracking("$current(", openCount + 1, closeCount, n, result)
        }

        if (closeCount < openCount) {
            useBacktracking("$current)", openCount, closeCount + 1, n, result)
        }
    }
}
