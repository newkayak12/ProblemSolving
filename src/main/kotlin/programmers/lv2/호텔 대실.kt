package programmers.lv2

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class `호텔 대실` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/155651
     */

    @Test
    fun solution1() {
        val book_time = arrayOf(
            arrayOf("15:00", "17:00"),
            arrayOf("16:40", "18:20"),
            arrayOf("14:20", "15:20"),
            arrayOf("14:10", "19:20"),
            arrayOf("18:20", "21:20")
        )
        val expected = 3

        assertEquals(expected, calculate(book_time))
    }

    @Test
    fun solution2() {
        val book_time = arrayOf(
            arrayOf("09:10", "10:10"),
            arrayOf("10:20", "12:20")
        )
        val expected = 1

        assertEquals(expected, calculate(book_time))
    }

    @Test
    fun solution3() {
        val book_time = arrayOf(
            arrayOf("10:20", "12:30"),
            arrayOf("10:20", "12:30"),
            arrayOf("10:20", "12:30")
        )
        val expected = 3

        assertEquals(expected, calculate(book_time))
    }

    @Test
    fun test() {
        println("00:00" <= "01:00")
        println("11:30" <= "12:30")
        println("12:30" <= "23:30")
        println(calculateTime("12:30"))
        println(calculateTime("12:59"))
        println(calculateTime("23:59"))
    }

    private fun calculate(book_time: Array<Array<String>>): Int {
        val priorityQueue = PriorityQueue<String>()
        val sortedArray = book_time.sortedWith(compareBy({ it[0] }, { it[1] }))


        for (i in sortedArray.indices) {
            val time = sortedArray[i]
            if (priorityQueue.isNotEmpty() && priorityQueue.peek() <= time[0]) {
                priorityQueue.poll()
            }
            priorityQueue.add(calculateTime(time[1]))
        }


        return priorityQueue.size
    }

    private fun calculateTime(time: String): String {
        val split = time.split(":")
        var hours = split[0].toInt()
        var minutes = split[1].toInt() + 10
        if (minutes >= 60) {
            minutes -= 60
            hours += 1
        }

        return "${if(hours < 10) "0$hours" else hours}:${if (minutes < 10) "0$minutes" else minutes}"
    }
}