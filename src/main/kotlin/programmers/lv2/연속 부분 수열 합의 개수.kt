
package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `연속 부분 수열 합의 개수` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/131701?language=kotlin
     */


    @Test
    fun solution() {
        val elements = intArrayOf(7, 9, 1, 1, 4)
        val result = 18

        assertEquals(result, calculate(elements))
        assertEquals(result, useSlidingWindow(elements))
    }

    fun calculate(elements: IntArray): Int {
        var sumSet = mutableSetOf<Int>()

        for(i in 1 .. elements.size) {
            for(j in elements.indices) {
                var sum = 0
                for(l in j until i + j) {
                    sum += elements[l % elements.size]
                }

                sumSet.add(sum)
            }
        }

        return sumSet.size
    }

    private fun useSlidingWindow(elements: IntArray): Int {
        val n = elements.size
        val sums = mutableSetOf<Int>()
        val extends = elements + elements

        for(len in 1 .. n) {
            var windowSum = extends.take(len).sum()
            sums.add(windowSum)

            for(i in len until n + len) {
                windowSum += (extends[i] - extends[i - len])
                sums.add(windowSum)
            }
        }

        return sums.size
    }
}