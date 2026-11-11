package programmers

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 주휴수당 {

    fun solution(works: Array<IntArray>, wage: Int): Int {

        val allWorkTime = works.flatMap { calculateDate(it) }

        val totalBasicTime = allWorkTime.sumOf { it.basic }
        val totalExtendTime = allWorkTime.sumOf { it.extend }
        val totalNightTime = allWorkTime.sumOf { it.night }
        val totalWeekEndTime = allWorkTime.sumOf { it.weekend }
        val totalWeeklyTime = allWorkTime.filter { it.date < WEEKLY_BOUND_EXCEPT_DATE_BOUND }
            .groupBy { (it.date - 1)/7 }
            .values
            .sumOf { week ->
                val weeklyTime = week.sumOf { it.basic }
                if(weeklyTime >= WEEKLY_BOUND) weeklyTime else 0
            }



            return (totalBasicTime * wage)+
            (totalExtendTime * (wage * SPECIAL_WORK_RATE)).toInt()+
            (totalNightTime * (wage * SPECIAL_WORK_RATE) ).toInt()+
            (totalWeekEndTime * (wage * SPECIAL_WORK_RATE) ).toInt() +
            if(totalWeeklyTime >= WEEKLY_BOUND) (totalWeeklyTime * (wage * WEEKLY_WORK_RATE)).toInt() else 0
    }

    private fun isWeekend(day: Int): Boolean = day % 7 == 6 || day % 7 == 0


    companion object {
        const val EXTEND_TIME_LIMIT = 8
        const val NIGHT_BOUND = 22
        const val SPECIAL_WORK_RATE = 0.5
        const val WEEKLY_BOUND = 15
        const val WEEKLY_BOUND_EXCEPT_DATE_BOUND = 29
        const val WEEKLY_WORK_RATE = 0.2
    }

    private fun calculateTime(date: Int, startTime: Int, endTime: Int): WorkTime {

//        println("\ndate: $date startTime: $startTime endTime: $endTime NIGHT_BOUND: $NIGHT_BOUND")
        val basic = (endTime - startTime)
        val extend = (if (basic - EXTEND_TIME_LIMIT > 0) basic - EXTEND_TIME_LIMIT else 0)
        val night = (if (endTime > NIGHT_BOUND) endTime - NIGHT_BOUND else 0)
        val weekend = (if (isWeekend(date)) basic else 0)

        return WorkTime(
            date = date,
            basic = basic,
            extend = extend.toInt(),
            night = night.toInt(),
            weekend = weekend.toInt(),
        )
    }

    private fun calculateDate(work: IntArray): List<WorkTime> {
        val (startDate, startTime, endDate, endTime) = work

        val adjustEndDate = if(endTime == 0) endDate - 1 else endDate
        val twentyFourHourEndTime = if (endTime == 0) 24 else endTime

        if (adjustEndDate - startDate == 0) return listOf(calculateTime(startDate, startTime, twentyFourHourEndTime))

        var date = startDate
        val mutableWorkDays = mutableListOf<WorkTime>()


        while (date <= adjustEndDate) {
            val beginTime = if (date == startDate) startTime else 0
            val finishTime = if (date == adjustEndDate) twentyFourHourEndTime else 24
            mutableWorkDays.addLast(calculateTime(date, beginTime, finishTime))
            date++
        }

        return mutableWorkDays
    }


    data class WorkTime(
        val date: Int,
        val basic: Int,
        val extend: Int,
        val night: Int,
        val weekend: Int,
    )

    /// 1일
    @Test // overNight
    fun test1() {
        val works = arrayOf(
            intArrayOf(1, 8, 1, 13),
            intArrayOf(5, 18, 6, 9),
        )
        val wage = 10000
        val result = 300000

        assertEquals(result, solution(works, wage))
    }

    @Test // overNight
    fun test2() {
        val works = arrayOf(
            intArrayOf(1, 22, 3, 8),
            intArrayOf(13, 12, 13, 17),
            intArrayOf(14, 22, 15, 0),
        )
        val wage = 15000
        val result = 934500

        assertEquals(result, solution(works, wage))
    }


}