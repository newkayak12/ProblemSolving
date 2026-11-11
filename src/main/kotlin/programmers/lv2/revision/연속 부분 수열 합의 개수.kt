package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.math.sin
import kotlin.test.assertEquals

class `연속 부분 수열 합의 개수` {

    @Test
    fun solution1() {
        val element = intArrayOf(7, 9, 1, 1, 4)
        val result = 18


        assertEquals(result, calculate(element))
    }


    fun calculateBad(elements: IntArray): Int {
        val set = mutableSetOf<Int>()

        for (i in 0..elements.size - 1) {
            for (j in elements.indices) {
                set.add((j..j + i).map { elements[it % elements.size] }.sum())
            }

        }

        return set.size
    }


    fun calculate(elements: IntArray): Int {
        val elementArray = elements + elements
        val set = mutableSetOf<Int>()

        for (i in 1..elements.size) {
            var sum = elementArray.take(i).sum()
            set.add(sum)

            for(j in 0 + i until elements.size + i - 1) {
                sum -= elementArray[j - i]
                sum += elementArray[j]

                set.add(sum)
            }

        }


        return set.size
    }
}