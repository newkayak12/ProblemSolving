package programmers.lv2.revision

import java.util.PriorityQueue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `호텔 대실` {


    @Test
    fun test1() {
        val book_time =
            arrayOf(
                arrayOf("15:00", "17:00"),
                arrayOf("16:40", "18:20"),
                arrayOf("14:20", "15:20"),
                arrayOf("14:10", "19:20"),
                arrayOf("18:20", "21:20")
            )
        val result = 3

        assertEquals(result, solution(book_time))
    }

    @Test
    fun test2() {
        val book_time = arrayOf(arrayOf("09:10", "10:10"), arrayOf("10:20", "12:20"))
        val result = 1

        assertEquals(result, solution(book_time))
    }

    @Test
    fun test3() {
        val book_time = arrayOf(arrayOf("10:20", "12:30"), arrayOf("10:20", "12:30"), arrayOf("10:20", "12:30"))
        val result = 3

        assertEquals(result, solution(book_time))
    }

    private fun parseTime(time: String): Int {
        val split = time.split(":")
        val hour = split[0].toInt() * 60
        val minute = split[1].toInt()


        return hour + minute
    }

    fun solution(book_time: Array<Array<String>>): Int {
        /**
         * 정렬된 시간
         * PQ
         *
         * for 정렬된 시간
         *
         *    if pq가 비어있지 않고 pq에 저장한 끝나는 시간 + 10이 현재 시작 시간보다 작으면
         *      pq에 first를 remove 한다
         *
         *
         *    PQ에 추가
         *
         *
         * pq의 카운트
         */
        val parsedBookTime = book_time.map { it.map { time -> parseTime(time) } }
            .sortedWith(compareBy({ it[0] }, { it[1] }))
        val priorityQueue = PriorityQueue<Int>()



        for (element in parsedBookTime) {
            if (priorityQueue.isNotEmpty() && priorityQueue.peek() + 10 <= element[0]) {
                priorityQueue.poll()
            }



            priorityQueue.add(element[1])
        }

        return priorityQueue.size
    }
}