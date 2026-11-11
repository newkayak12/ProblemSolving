package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class 기능개발 {

    @Test
    fun solution1() {
        val progress = intArrayOf(93, 30, 55)
        val speeds = intArrayOf(1, 30, 5)
        val expected = intArrayOf(2, 1)

        assertArrayEquals(expected, calculate(progress, speeds))
    }


    @Test
    fun solution2() {
        val progress = intArrayOf(95, 90, 99, 99, 80, 99)
        val speeds = intArrayOf(1, 1, 1, 1, 1, 1)
        val expected = intArrayOf(1, 3, 2)

        assertArrayEquals(expected, calculate(progress, speeds))
    }

    fun calculate(progresses: IntArray, speeds: IntArray): IntArray {
        val queue = ArrayDeque<Int>()
        val list = mutableListOf<Int>()
        for (index in progresses.indices) {
            queue.add(index)
        }

        while (queue.isNotEmpty()) {
            var count = 0

            for ((index, progress) in progresses.withIndex()) {
                progresses[index] = progress + speeds[index]
            }

            while (queue.isNotEmpty()) {
                val idx = queue.first()
                if (progresses[idx] < 100) {
                    break
                }

                count++
                queue.removeFirst()
            }

            if (count != 0) {
                list.add(count)
            }
        }


        return list.toIntArray()
    }
}