package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 프로세스 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42587?language=kotlin
     */

    @Test
    fun solution1(){
        val priorities = intArrayOf(2, 1, 3, 2)
        val location = 2
        val expected = 1

        assertEquals(expected, calculate(priorities, location))
    }


    @Test
    fun solution2(){
        val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
        val location = 0
        val expected = 5

        assertEquals(expected, calculate(priorities, location))
    }

    fun calculate(priorities: IntArray, location: Int): Int {
        val queue = ArrayDeque<Int>()
        val array = priorities.copyOf()
        array.indices.forEach{queue.add(it)}
        var max = array.maxOrNull()!!

        while(queue.isNotEmpty()) {
            val index = queue.removeFirst()
            if(location == index && max == array[index]) break

            if(array[index] >= max) {
                array[index] = -1
                max = array.maxOrNull()!!
            }
            else {
                queue.addLast(index)
            }
        }


        return array.size - queue.size
    }
}