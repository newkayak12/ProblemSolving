package programmers.lv2

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class `카드 나누기` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/135807
     */


    @Test
    fun solution1() {
        val arrayA = intArrayOf(10, 17)
        val arrayB = intArrayOf(5, 20)
        val expected = 0

        assertEquals(expected, calculate(arrayA, arrayB))
    }

    @Test
    fun solution2() {
        val arrayA = intArrayOf(10, 20)
        val arrayB = intArrayOf(5, 17)
        val expected = 10

        assertEquals(expected, calculate(arrayA, arrayB))
    }

    @Test
    fun solution3() {
        val arrayA = intArrayOf(14, 35, 119)
        val arrayB = intArrayOf(18, 30, 102)
        val expected = 7

        assertEquals(expected, calculate(arrayA, arrayB))
    }

    private fun calculate(arrayA: IntArray, arrayB: IntArray): Int {
        val gcdA = getGcd(arrayA)
        val gcdB = getGcd(arrayB)

        val number = (getMod(gcdA) + getMod(gcdB)).distinct()
        for(element in number.sortedDescending()) {
            val resultA = arrayA.any { it % element == 0 }
            val resultB = arrayB.any { it % element == 0 }

            if((resultA && resultB) || (!resultA && !resultB)){
                continue
            }

            return element
        }

        return 0
    }

    private fun getGcd(array: IntArray): Int {
        val queue = PriorityQueue<Int> { o1, o2 -> o2 - o1 }
        for (element in array) queue.add(element)

        while (queue.isNotEmpty() && queue.size > 1) {
            val larger = queue.poll()
            val smaller = queue.poll()
            queue.add(gcd(larger, smaller))
        }

        return queue.poll()
    }

    private fun gcd(larger: Int, smaller: Int): Int {
        if (smaller == 0) return larger
        return gcd(smaller, larger % smaller)
    }

    private fun getMod(number: Int): MutableList<Int> {
        val mutableList = mutableListOf<Int>()
        for (i in 1..number) {
            if (number % i === 0) mutableList.add(i)
        }

        return mutableList
    }
}