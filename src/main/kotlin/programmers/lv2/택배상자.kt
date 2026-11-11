package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 택배상자 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/131704?language=kotlin
     *
     * 1 -> 2 -> 3 .. 이렇게 들어오는데 order 순서대로 나가야 한다.
     * order 순서대로 못나가면 더 이상 처리하지 않는다.
     * 임시 레일 5칸이다.
     */


    @Test
    fun solution1() {

        val order = intArrayOf(4, 3, 1, 2, 5)
        val expected = 2

        assertEquals(expected, calculate(order))
    }


    @Test
    fun solution2() {

        val order = intArrayOf(5, 4, 3, 2, 1)
        val expected = 5

        assertEquals(expected, calculate(order))
    }

    private fun calculate(order: IntArray): Int {
        val stack = ArrayDeque<Int>()
        var orderIndex = 0
        var count = 0

        for (i in order.indices) {
            val nowItem = i + 1
            val orderItem = order[orderIndex]


            if (nowItem != orderItem) {
                stack.add(nowItem)
            } else {
                orderIndex++
                count++
                while (stack.isNotEmpty()) {
                    val stacked = stack.last()
                    val orderItem = order[orderIndex]

                    if (stacked != orderItem) break
                    orderIndex++
                    stack.removeLast()
                    count++
                }
            }

        }

        return count
    }


}