package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `과제 진행하기` {


    @Test
    fun solution1() {
        val plans = arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40")
        )
        val result = arrayOf("korean", "english", "math")
        assertArrayEquals(result, calculate(plans))
    }

    @Test
    fun solution2() {
        val plans = arrayOf(
            arrayOf("science", "12:40", "50"),
            arrayOf("music", "12:20", "40"),
            arrayOf("history", "14:00", "30"),
            arrayOf("computer", "12:30", "100")
        )

        val result = arrayOf("science", "history", "computer", "music")
        assertArrayEquals(result, calculate(plans))
    }

    @Test
    fun solution3() {
        val plans = arrayOf(
            arrayOf("aaa", "12:00", "20"),
            arrayOf("bbb", "12:10", "30"),
            arrayOf("ccc", "12:40", "10"),
        )

        val result = arrayOf("bbb", "ccc", "aaa")
        assertArrayEquals(result, calculate(plans))
    }


    @Test
    fun solution4() {
        val plans = arrayOf(
            arrayOf("a", "12:00", "10"),
            arrayOf("b", "12:05", "3"),
            arrayOf("c", "12:30", "5"),
        )

        val result = arrayOf("b", "a", "c")
        assertArrayEquals(result, calculate(plans))
    }

    private fun parseTime(time: String): Long {
        val split = time.split(":")
        val hour = split[0].toLong()
        val min = split[1].toLong()

        return hour * 60 + min
    }

    fun calculate(plans: Array<Array<String>>): Array<String> {

        val answer = mutableListOf<String>()
        val arrayDeque = ArrayDeque<Pair<String, Long>>()
        val calculated = plans.map {
            it[0] to longArrayOf(parseTime(it[1]), it[2].toLong())
        }
            .sortedWith(compareBy({ it.second[0] }))

        var time = calculated.first().second[0]
        val map = calculated.associate { it.second[0] to (it.first to it.second[1]) }
        var remain = calculated.size

        var currentName = ""
        var currentDuration = -1L

        while (remain > 0) {

            /**
             * A: 10:00, 3분
             * B: 10:01, 10분
             * C: 10:02, 1분
             *
             * A(10:01)에 범춤 / B(10:01) 시작 [A 2분 남음]
             * B(10:02)에 멈춤 / C(10:02) 시작 [A 2분 남음, B 9분 남음]
             * C(10:03)에 종료 / B시작
             * B(10:12)에 종료 / A시작
             *
             */


            val work = map[time]
            if(work != null) {
                if(currentName.isNotBlank()) {
                    arrayDeque.addLast(currentName to currentDuration)
                }

                currentName = work.first
                currentDuration = work.second
            }


            if(currentName.isBlank() && arrayDeque.isNotEmpty()) {
                val (queueName, queueDuration) = arrayDeque.removeLast()
                currentName = queueName
                currentDuration = queueDuration
            }


            if(currentName.isNotBlank()) {
                currentDuration --
                if(currentDuration == 0L) {
                    answer.add(currentName)
                    remain --
                    currentName = ""
                }
            }

            time++
        }



        return answer.toTypedArray()
    }
}