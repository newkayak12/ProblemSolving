package programmers.lv1.kakao

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `신규 아이디 추천` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/72410?language=kotlin
     *
     */

    @Test
    fun solution1() {
        val newId = "...!@BaT#*..y.abcdefghijklm"
        val expected = "bat.y.abcdefghi"

        assertEquals(expected, calculate1(newId))
        assertEquals(expected, calculate2(newId))
    }


    @Test
    fun solution2() {
        val newId = "z-+.^."
        val expected = "z--"

        assertEquals(expected, calculate1(newId))
        assertEquals(expected, calculate2(newId))
    }


    @Test
    fun solution3() {
        val newId = "=.="
        val expected = "aaa"

        assertEquals(expected, calculate1(newId))
        assertEquals(expected, calculate2(newId))
    }


    @Test
    fun solution4() {
        val newId = "123_.def"
        val expected = "123_.def"

        assertEquals(expected, calculate1(newId))
        assertEquals(expected, calculate2(newId))
    }

    @Test
    fun solution5() {
        val newId = "abcdefghijklmn.p"
        val expected = "abcdefghijklmn"

        assertEquals(expected, calculate1(newId))
        assertEquals(expected, calculate2(newId))
    }


    /**
     * 규칙
     * 아이디의 길이는 3자 이상 15자 이하여야 합니다.
     * 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
     * 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.
     *
     * 단계별 적용
     * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
     * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
     * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
     * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
     * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
     * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
     * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
     */
    fun calculate1(new_id: String): String {
        var answer: String = new_id
        val range = ('a'.code..'z'.code).asSequence() +
                    ('0'.code..'9'.code).asSequence() +
                    sequenceOf('-'.code, '_'.code, '.'.code)
                    .toList()


        //1단계
        answer = answer.lowercase()

        //2단계
        val builder = StringBuilder()
        for (char in answer) {
            if (range.contains(char.code)) {
                builder.append(char)
            }
        }
        answer = builder.toString()

        //3단계
        while (answer.contains("..")) {
            answer = answer.replace("..", ".")
        }

        while (
            (answer.length < 3 || answer.length > 15) ||
            (answer.startsWith(".") || answer.endsWith("."))

        ) {
            //4단계
            while (answer.startsWith(".")) {
                answer = answer.substring(1)
            }
            while (answer.endsWith(".")) {
                answer = answer.substring(0, answer.length - 1)
            }

            //5단계
            if (answer.isEmpty()) {
                answer += "a"
            }

            //6단계
            if (answer.length > 15) {
                answer = answer.substring(0, 15)
            }

            //7단계
            while (answer.length < 3) {
                answer += answer[answer.lastIndex]
            }
        }

        return answer
    }

    fun calculate2(new_id: String): String {
        return new_id
            .lowercase()
            .filter {
                it.isLowerCase() ||
                it.isDigit() ||
                it == '-' ||
                it == '_' ||
                it == '.'
            }
            .replace("\\.+".toRegex(), ".")
            .removePrefix(".")
            .removeSuffix(".")
            .let { it.ifEmpty { "a" } }
            .let { if(it.length > 15) it.substring(0, 15) else it}
            .removeSuffix(".")
            .let {
                if(it.length <= 2) {
                    StringBuilder(it).run {
                        while(length < 3) append(it.last())

                        toString()
                    }
                }
                else it
            }
    }


}