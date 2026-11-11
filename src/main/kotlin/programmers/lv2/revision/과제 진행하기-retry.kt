package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.component3

class `과제 진행하기-retry` {

    /**
     * 시작 시간으로 정리
     * 단위 당 이전 내용들을 flush하는 구조로 구성
     * stack 개념으로 이전 작업이 쌓이는 구조
     * resource는 시간, delay되는 시간을 별도 관리하여 보정이 필요
     *
     * for
     *    while()
     *
     *
     */
    private fun parseTime(plan: Array<String>): Triple<String, Int, Int> {
        val (subject, time, duration) = plan

        val split = time.split(":")
        val hours = split[0].toInt() * 60
        val minutes = split[1].toInt()
        return Triple(subject, hours + minutes, duration.toInt())
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val sortedPlans = plans.map { parseTime(it) }.sortedBy { it.second } //시작 시간으로 정리
        val stack = ArrayDeque<Pair<Int, Int>>()
        val result = mutableListOf<String>()
        for ((index, value) in sortedPlans.withIndex()) {
            val (_, time, duration) = value

            var gap = if(stack.isNotEmpty()) time -sortedPlans[index - 1].second else 0


            while (stack.isNotEmpty() && gap > 0) {
                val (stackedIndex, stackedDuration) = stack.removeLast()

                if(stackedDuration <= gap) {
                    val (previousSubject, _, _) = sortedPlans[stackedIndex]
                    result.add(previousSubject)
                    gap -= stackedDuration
                }
                else {
                    stack.addLast(stackedIndex  to stackedDuration - gap)
                    gap = 0
                }
            }

            stack.addLast(index to duration)
        }

        while(stack.isNotEmpty()) {
            val (index,_) = stack.removeLast()
            val (subject, _, _) = sortedPlans[index]
            result.add(subject)
        }


        return result.toTypedArray()
    }


    @Test
    fun test1() {
        val plans = arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40")
        )
        val result = arrayOf("korean", "english", "math")

        assertArrayEquals(result, solution(plans))
    }

    @Test
    fun test2() {
        val plans = arrayOf(
            arrayOf("science", "12:40", "50"),
            arrayOf("music", "12:20", "40"),
            arrayOf("history", "14:00", "30"),
            arrayOf("computer", "12:30", "100"),
        )
        val result = arrayOf("science", "history", "computer", "music")

        assertArrayEquals(result, solution(plans))
    }

    @Test
    fun test3() {
        val plans = arrayOf(
            arrayOf("aaa", "12:00", "20"),
            arrayOf("bbb", "12:10", "30"),
            arrayOf("ccc", "12:40", "10"),
        )
        val result = arrayOf("bbb", "ccc", "aaa")

        assertArrayEquals(result, solution(plans))
    }

}