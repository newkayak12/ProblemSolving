package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class 오픈채팅방 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42888?language=kotlin
     */

    @Test
    fun solution() {
        val record = arrayOf(
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
        )
        val expected = arrayOf(
            "Prodo님이 들어왔습니다.",
            "Ryan님이 들어왔습니다.",
            "Prodo님이 나갔습니다.",
            "Prodo님이 들어왔습니다."
        )


        assertArrayEquals(expected, calculate(record))
    }

    private fun calculate(record: Array<String>): Array<String> {
        val variables = mutableMapOf<String, String>()
        val logs = mutableListOf<Pair<String, String>>()

        for (log in record) {
            val split = log.split(" ")
            when (split[0]) {
                "Change" -> variables[split[1]] = split[2]
                "Leave" -> logs.add(split[0] to split[1])
                "Enter" -> {
                    variables[split[1]] = split[2]
                    logs.add(split[0] to split[1])
                }
            }
        }


        return logs.map {
            when (it.first) {
                "Enter" -> "${variables[it.second]}님이 들어왔습니다."
                else -> "${variables[it.second]}님이 나갔습니다."
            }
        }.toTypedArray()
    }


}