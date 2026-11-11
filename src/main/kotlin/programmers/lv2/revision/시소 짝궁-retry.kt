package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `시소 짝궁-retry` {

    fun solution(weights: IntArray): Long {
        var count = 0L
        val map = weights.associate { it.toDouble() to  0L }.toMutableMap()
        val ratio = listOf<Double>(
            (1.0 / 1.0),
            (2.0 / 4.0),
            (2.0 / 3.0),
            (3.0 / 4.0),
        )

        for (i in weights.sortedDescending()) {
            count += map.getOrDefault(i.toDouble(), 0L)
            for (r in ratio) {
                map.computeIfPresent(i * r) { k, v -> v + 1 }
            }
        }


        return count
    }


    @Test
    fun test1() {
        val weights = intArrayOf(100, 180, 360, 100, 270)
        val result = 4L
        assertEquals(result, solution(weights))
    }
}