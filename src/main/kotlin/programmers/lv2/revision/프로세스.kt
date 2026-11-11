package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 프로세스 {


    @Test
    fun solution1() {
        val priorities = intArrayOf(2, 1, 3, 2)
        val location = 2

        val returnValue = 1

        assertEquals(returnValue, calculate(priorities, location))
    }

    @Test
    fun solution2() {
        val priorities = intArrayOf(1, 1, 9, 1, 1, 1)
        val location = 0

        val returnValue = 5

        assertEquals(returnValue, calculate(priorities, location))
    }



    fun calculate(priorities: IntArray, location: Int): Int {

        /**
         * val queue = ArrayDeque
         * var max = priority.max
         * var count = 0
         *
         * for( i in 0 until priorities.length) {
         *   queue.add(i)
         * }
         *
         * while( queue가 잇으면 ) {
         *   count ++
         *   val index = queue에서 pop
         *
         *   if(priorities[index] == max){
         *      max = 다음 max
         *      if(index == location) return count
         *   }
         *   else {
         *     queue.add(index)
         * }
         *
         */


        val queue = ArrayDeque<Int>()
        val maxArray = priorities.copyOf().sortedArrayDescending()
        var maxIndex = 0
        var count = 0

        for(i in priorities.indices) {
            queue.addLast(i)
        }

        while(queue.isNotEmpty()) {
            val index = queue.removeFirst()


            if(priorities[index] == maxArray[maxIndex]) {
                count++
                maxIndex++
                if(index == location) return count
            }
            else {
                queue.addLast(index)
            }
        }


        return count
    }
}