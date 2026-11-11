
package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `시소 짝꿍` {


    @Test
    fun test1() {
        val weights = intArrayOf(100, 180, 360, 100, 270)
        val result = 4L

        assertEquals(result, solution(weights))
    }

    fun solution(weights: IntArray): Long {
        val ratio = doubleArrayOf(1.0 / 1.0, 1.0 / 2.0, 2.0 / 3.0, 3.0 / 4.0)
        val map = mutableMapOf<Double, Long>()
        var count = 0L

        for (i in weights.sortedDescending()) {
            count += map[i.toDouble()] ?: 0

            for(r in ratio) { map.merge(i * r, 1L, Long::plus) }
        }

        return count
    }

    fun solutionTimeout(weights: IntArray): Long {

        val ratio: MutableMap<Double, Long> = arrayOf(
            1.0 / 1.0,
            1.0 / 2.0,
            2.0 / 3.0,
            3.0 / 4.0
        )
            .associateWith { 0L }
            .toMutableMap()

        for (i in weights.indices) {
            for (j in i + 1 until weights.size) {
                val max = maxOf(weights[i], weights[j])
                val min = minOf(weights[i], weights[j])
                val value = min.toDouble() / max.toDouble()

                if (ratio[value] != null) {
                    ratio.merge(value, 1, Long::plus)
                }
            }
        }

        return ratio.values.sum()
    }
}