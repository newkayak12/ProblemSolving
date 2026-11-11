package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `시소 짝꿍` {

    @Test
    fun solution() {
        val weights = intArrayOf(100, 180, 360, 100, 270)
        val expected = 4L

        assertEquals(expected, calculate(weights))
    }


    fun calculate(weights: IntArray): Long {
        weights.sort()
        val ratio = listOf(1.0 / 1.0, 3.0 / 2.0, 4.0 / 2.0, 4.0 / 3.0)
        val map = mutableMapOf<Double, Long>()
        var count = 0L;

        for (element in weights) {
            count += (map[element * 1.0] ?: 0L)

            for(r in ratio){
                map[r * element] = (map[r * element] ?: 0) + 1
            }
        }

        return count
    }

    //2-for + LCM
    //timeout
    fun calculateLCM(weights: IntArray): Long {
        val distance = listOf(2, 3, 4)
        val set = mutableSetOf<Pair<Int, Int>>()

        for (i in 0 until weights.size - 1) {
            val left = weights[i]
            for (j in i + 1 until weights.size) {
                val right = weights[j]
                val lcmValue = lcm(maxOf(left, right), minOf(left, right))

                val leftRatio = lcmValue / left
                val rightRatio = lcmValue / right

                for (dRight in distance) {
                    for (dLeft in distance) {
                        if (leftRatio * dRight == rightRatio * dLeft) {
                            set.add(maxOf(left, right) to minOf(left, right))
                        }
                    }
                }

            }
        }



        return set.size.toLong()
    }

    private fun lcm(a: Int, b: Int): Int {
        return (a * b) / gcd(a, b)
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}