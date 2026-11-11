package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.test.assertEquals

class `주차 요금 계산` {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/92341?language=kotlin
     *
     * 입차 후 출차가 없으면 23:59
     * 누적 주차 시간을 계산
     */


    @Test
    fun solution1() {
        val fees = intArrayOf(
            180, //기본 시간
            5000, // 기본 요금
            10, //단위 시간
            600 //단위 요금
        )
        val records = arrayOf(
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT"
        )
        val expected = intArrayOf(14600, 34400, 5000)

        assertArrayEquals(expected, calculate(fees, records))
    }

    @Test
    fun solution2() {
        val fees = intArrayOf(120, 0, 60, 591)
        val records = arrayOf("16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN")
        val expected = intArrayOf(0, 591)

        assertArrayEquals(expected, calculate(fees, records))
    }

    @Test
    fun solution3() {
        val fees = intArrayOf(1, 461, 1, 10)
        val records = arrayOf("00:00 1234 IN")
        val expected = intArrayOf(14841)

        assertArrayEquals(expected, calculate(fees, records))
    }

    private fun calculate(fees: IntArray, records: Array<String>): IntArray {
        val baseTime = fees[0]
        val baseFee = fees[1]
        val additionalTime = fees[2]
        val additionalFee = fees[3]

        val limit = 23 * 60 + 59
        val times = mutableMapOf<String, Int>()
        val map = mutableMapOf<String, Int>()

        for (record in records) {
            val (time, number, inout) = record.split(" ")
            val (hour, minutes) = time.split(":")

            when (inout) {
                "IN" -> {
                    map[number] = hour.toInt() * 60 + minutes.toInt()
                }

                "OUT" -> {
                    times[number] = (times[number] ?: 0) + (hour.toInt() * 60 + minutes.toInt()) - (map[number] ?: 0)
                    map.remove(number)
                }
            }
        }

        for(last in map) {
            val (key, value) = last
            times[key] = (times[key] ?: 0) + limit - value
        }


        return times.toSortedMap().map {
            if(it.value < baseTime)  baseFee
            else {
                baseFee + ceil((it.value - baseTime) / additionalTime.toDouble()).toInt() * additionalFee
            }
        }.toIntArray()
    }
}