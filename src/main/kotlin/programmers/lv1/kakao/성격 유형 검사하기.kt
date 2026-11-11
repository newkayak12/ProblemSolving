package programmers.lv1.kakao

import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class `성격 유형 검사하기` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/118666?language=kotlin
     *
     * val survey = ["RT", "TR", "FC", "CF", "MJ", "JM", "AN", "NA"]
     * survey.length == choice.length
     * survey left char ---- survey right char
     * 1     2     3     4     5     6     7
     *
     * //점수가 같으면 사전 순
     */


    @Test
    fun solution1() {
        val survey = arrayOf("AN", "CF", "MJ", "RT", "NA")
        val choice = intArrayOf(5, 3, 2, 7, 5)

        val expected = "TCMA"

        assertEquals(expected, calculate1(survey, choice))
        assertEquals(expected, calculate2(survey, choice))
    }

    @Test
    fun solution2() {
        val survey = arrayOf("TR", "RT", "TR")
        val choice = intArrayOf(7, 1, 3)

        val expected = "RCJA"

        assertEquals(expected, calculate1(survey, choice))
        assertEquals(expected, calculate2(survey, choice))
    }

    // 1번 survey에 점수에 따른 채점
    // 2번 1~4유형까지 순서는 정해져 있음
    // 3번 각 유형별 점수가 같으면 알파벳순

    //Array<Pair<Char, Int>>
    //survey loop + choice로 결정

    fun calculate1(survey: Array<String>, choices: IntArray): String {
        val mid = 4
        val tableMap = mutableMapOf(
            "RT" to 0,
            "CF" to 0,
            "JM" to 0,
            "AN" to 0
        )



        for ((index, value) in survey.withIndex()) {
            val score = choices[index] - mid
            if (tableMap.keys.contains(value)) {
                tableMap[value] = (tableMap[value] ?: 0) + score
            }
            else {
                val reversed = value.reversed()
                tableMap[reversed] = (tableMap[reversed] ?: 0) - score
            }
        }

        println(tableMap)

        var answer = StringBuilder()
        tableMap.forEach { (key, value) -> answer.append(if (value > 0) key[1] else key[0]) }

        return answer.toString()
    }

    fun calculate2(survey: Array<String>, choices: IntArray): String {
        val table = listOf("RT", "CF", "JM", "AN")
        val map = mutableMapOf(
            'R' to 0,
            'T' to 0,
            'C' to 0,
            'F' to 0,
            'J' to 0,
            'M' to 0,
            'A' to 0,
            'N' to 0,
        )
        val mid = 4


        for ((index, value) in survey.withIndex()) {
            val score = choices[index] - mid
            if (score == 0) continue
            val key = if (score < 0) {
                value[0]
            } else {
                value[1]
            }

            map.computeIfPresent(key) { _, v -> v + abs(score) }
        }


        return table.map {
            val left = it[0]
            val right = it[1]

            val leftValue = map[left]?:0
            val rightValue = map[right]?:0

            if(leftValue == rightValue) return@map it.toCharArray().minOf { value -> value }
            if( leftValue > rightValue) left
            else right
        }.joinToString("")
    }


    /**
     * - 문제 조금 더 정리하면서 읽기 MJ, JM 케이스 등
     * - 자잘한 가공 줄여서 최적화 하기
     * - 산술기호에 대한 감각
     */
}