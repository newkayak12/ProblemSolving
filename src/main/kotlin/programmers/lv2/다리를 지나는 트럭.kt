package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `다리를 지나는 트럭` {

    @Test
    fun solution1() {
        val brdige_length = 2
        val weight = 10
        val truck_weights = intArrayOf(7, 4, 5, 6)
        val expected = 8

        assertEquals(expected, calculate(brdige_length, weight, truck_weights))
    }


    @Test
    fun solution2() {
        val brdige_length = 100
        val weight = 100
        val truck_weights = intArrayOf(10)
        val expected = 101

        assertEquals(expected, calculate(brdige_length, weight, truck_weights))
    }

    @Test
    fun solution3() {
        val brdige_length = 100
        val weight = 100
        val truck_weights = intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
        val expected = 110

        assertEquals(expected, calculate(brdige_length, weight, truck_weights))
    }

    fun calculate(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var count = 0
        var index = 0
        var totalWeight = 0
        val queue = ArrayDeque<Pair<Int, Int>>()

        while (index < truck_weights.size || queue.isNotEmpty()) {
            count++

            if (queue.isNotEmpty() && count - queue.first().second >= bridge_length) {
                val (truck, _) = queue.removeFirst()
                totalWeight -= truck
            }

            if (index in truck_weights.indices && totalWeight + truck_weights[index] <= weight) {
                queue.addLast(truck_weights[index] to count)
                totalWeight += truck_weights[index]
                index++
            }
        }

        return count
    }
}