package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `과제 진행하가` {
    /**
     * 시작 시간, 플레이타임(시작 + 플레이 타임 = 종료)
     * 시작 시간으로 정렬 (시작시간이 같은 경우 X)
     * stack 사용
     * 종료 시간, 시작 시간 비교
     * 종료시간이 더 남았다면 그대로 두고 현재 내용 스택에 새로 넣는다.
     * 종료시간이 안남았거나 시작시간과 같다면 이전 내용 Pop 후 스택에 넣는다.
     *
     * ---> 폐기
     * ("aaa", "12:00", "20")
     * ("bbb", "12:10", "30")
     * ("ccc", "12:40", "10")
     *
     *
     *  aaa 시작(12:00) - 10분 - bbb 시작(12:10) - 30분 - bbb 종료 + ccc 시작(12:40) - 10분
     *
     *                         (기다리는 시간 30분 추가)             )
     *
     * 시작 시간
     * 처리 하는 시간
     * 딜레이된 시간
     *
     */

    private fun toNumber(time: String): Int {
        val split = time.split(":")
        val hoursToMinutes = split[0].toInt() * 60
        val minutes = split[1].toInt()

        return hoursToMinutes + minutes
    }


    private fun refine(plans: Array<Array<String>>): List<Triple<String, Int, Int>> {
        return plans.map { it ->
            val toNumber = toNumber(it[1])
            val duration = it[2].toInt()
            Triple(it[0], toNumber, duration)
        }.toList()
    }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val refinedPlans = refine(plans).sortedBy { it.second }
        val stack = ArrayDeque<Pair<Int, Int>>()
        val finished = mutableListOf<String>()

        for (index in refinedPlans.indices) {

            val startTime = refinedPlans[index].second
            val duration = refinedPlans[index].third

            var gap = if(stack.isNotEmpty()) startTime - refinedPlans[index - 1].second else 0
            while (stack.isNotEmpty() && gap > 0) {
                val (prevIndex, remaining) = stack.removeLast()
                if (remaining <= gap) {
                    finished.add(refinedPlans[prevIndex].first)
                    gap -= remaining
                } else {
                    stack.addLast(prevIndex to remaining - gap)
                    gap = 0
                    break
                }
            }
            stack.addLast(index to duration)
        }


        while (stack.isNotEmpty()) {
            val (index, _) = stack.removeLast()
            finished.add(refinedPlans[index].first)
        }

        return finished.toTypedArray()
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
// 끝 시간에 밀려 이전 작업이 딜레이되는 케이스
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
//시간이 겹치는 케이스
        assertArrayEquals(result, solution(plans))
    }


}