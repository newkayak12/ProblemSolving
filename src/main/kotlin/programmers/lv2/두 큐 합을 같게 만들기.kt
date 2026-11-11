package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class `두 큐 합을 같게 만들기` {


    @Test
    fun solution1() {
        val queue1 = intArrayOf(3, 2, 7, 2)
        val queue2 = intArrayOf(4, 6, 5, 1)
        val expected = 2

        assertEquals(expected, calculate(queue1, queue2))
    }


    @Test
    fun solution2() {
        val queue1 = intArrayOf(1, 2, 1, 2)
        val queue2 = intArrayOf(1, 10, 1, 2)
        val expected = 7

        assertEquals(expected, calculate(queue1, queue2))
    }


    @Test
    fun solution3() {
        val queue1 = intArrayOf(1, 1)
        val queue2 = intArrayOf(1, 5)
        val expected = -1

        assertEquals(expected, calculate(queue1, queue2))
    }


    private fun calculate(queue1: IntArray, queue2: IntArray): Int {
        var count = 0;
        val limit = (queue1.size + queue2.size) * 2
        val q1 = ArrayDeque<Long>()
        val q2 = ArrayDeque<Long>()

        var queue1Total = queue1.sum().toLong()
        var queue2Total = queue2.sum().toLong()
        val total = queue1Total + queue2Total


        if(total % 2 != 0L) return  -1

        for (element in queue1) q1.addLast(element.toLong())
        for (element in queue2) q2.addLast(element.toLong())

        while(
            queue1Total != queue2Total &&
            count < limit
        ) {
            if(queue1Total > queue2Total) {
                val pop = q1.removeFirst()
                queue1Total -= pop
                queue2Total += pop
                q2.addLast(pop)
            }
            else {
                val pop = q2.removeFirst()
                queue1Total += pop
                queue2Total -= pop
                q1.addLast(pop)
            }

            count ++
        }

        return if(count >= limit) -1 else  count;
    }
}