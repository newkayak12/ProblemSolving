package programmers.lv1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 유연근무제 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/388351
     *
     * 일주일 동안 출근 희망 시각 + 10분까지 어플로 출근
     * 토/ 일은 상관 없음
     * 모든 시각은 시에 100을 곱하고 분을 더한 정수로 표현 (10:13 -> 1013, 09:58 -> 958)
     *
     * schedule i + 1 번째 직원의 출근 희망 시간
     * timelogs i + 1 번째 직원이 이벤트 j + 1 일차에 출근한 시각
     * 600 <= timelog[i][j] <= 2359
     * startday 1은 월 ~ 7은 일요일에 이벤트 시작
     */

    @Test
    fun solution1() {
        val schedules = intArrayOf(700, 800, 1100)
        val timelogs = arrayOf(
            intArrayOf(710, 2359, 1050, 700, 650, 631, 659),
            intArrayOf(800, 801, 805, 800, 759, 810, 809),
            intArrayOf(1105, 1001, 1002, 600, 1059, 1001, 1100)
        )
        val startday = 5
        val expected = 3

        assertEquals(expected, calculate1(schedules, timelogs, startday))
        assertEquals(expected, calculate2(schedules, timelogs, startday))
    }

    @Test
    fun solution2() {
        val schedules = intArrayOf(730, 855, 700, 720)
        val timelogs = arrayOf(
            intArrayOf(710, 700, 650, 735, 700, 931, 912),
            intArrayOf(908, 901, 805, 815, 800, 831, 835),
            intArrayOf(705, 701, 702, 705, 710, 710, 711),
            intArrayOf(707, 731, 859, 913, 934, 931, 905),
        )
        val startday = 1
        val expected = 2

        assertEquals(expected, calculate1(schedules, timelogs, startday))
        assertEquals(expected, calculate2(schedules, timelogs, startday))
    }



    private fun calculate1(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {

        val table = schedules
            .map { it + if (it % 100 >= 50) 50 else 10 }
            .withIndex()
            .associate { it.index + 1 to it.value }.toMutableMap()
        val failure = mutableSetOf<Int>()

        for (index in timelogs.first().indices) {
            val date = if (index + startday < 8) index + startday else index + startday - 7
            if (date in 6..7) continue
            val keys = table.keys - failure
            for (key in keys) {
                val limit = table[key]!!
                val time = timelogs[key - 1][index]

                if (time > limit) {
                    failure.add(key)
                }
            }
        }


        return table.size - failure.size
    }

    private fun calculate2(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
        return timelogs.zip(schedules.map { it + if (it % 100 >= 50) 50 else 10 })
            .filter { (array, limit) ->
                array.filterIndexed { index, time ->
                    time > limit && (index != (6 - startday) % 7 && index != 7 - startday)
                }.isEmpty()
            }
            .size
    }
}