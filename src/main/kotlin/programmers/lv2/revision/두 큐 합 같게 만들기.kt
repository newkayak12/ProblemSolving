package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `두 큐 합 같게 만들기` {


    @Test
    fun test1() {
        val queue1 = intArrayOf(3, 2, 7, 2)
        val queue2 = intArrayOf(4, 6, 5, 1)
        val result = 2

        assertEquals(result, solution(queue1, queue2))
    }

    @Test
    fun test2() {
        val queue1 = intArrayOf(1, 2, 1, 2)
        val queue2 = intArrayOf(1, 10, 1, 2)
        val result = 7

        assertEquals(result, solution(queue1, queue2))
    }

    @Test
    fun test3() {
        val queue1 = intArrayOf(1, 1)
        val queue2 = intArrayOf(1, 5)
        val result = -1

        assertEquals(result, solution(queue1, queue2))
    }

    fun solution(queue1: IntArray, queue2: IntArray): Int {

        var queueASum = 0L
        var queueBSum = 0L
        var count = 0
        val limit = (queue1.size + queue2.size) * 2

        val queueA = ArrayDeque<Int>()
        val queueB = ArrayDeque<Int>()

        for (element in queue1) {
            queueA.add(element)
            queueASum += element
        }

        for (element in queue2) {
            queueB.add(element)
            queueBSum += element
        }

        val sum = queueASum + queueBSum

        if (sum % 2 != 0L) return -1

        while (
            count <= limit &&
            queueASum != queueBSum
        ) {
            if (queueASum > queueBSum) {
                val element = queueA.removeFirst()
                queueB.addLast(element)
                queueASum -= element
                queueBSum += element

                count++
            } else {
                val element = queueB.removeFirst()
                queueA.addLast(element)
                queueBSum -= element
                queueASum += element

                count++
            }
        }

        return if(count > limit) -1 else count
    }
}