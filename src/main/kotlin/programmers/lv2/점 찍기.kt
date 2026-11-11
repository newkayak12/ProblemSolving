package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.test.assertEquals

class `점 찍기` {

    @Test
    fun solution1() {
        val k = 2
        val d = 4
        val result = 6L

        assertEquals(result, calculate(k, d))
    }


    @Test
    fun solution2() {
        val k = 1
        val d = 5
        val result = 26L

        assertEquals(result, calculate(k, d))
    }


    fun calculate(k: Int, d: Int): Long {
        //x를 고정하고 y를 구한다
        // (k * a)^2 + (k * b)^2 = d^2
        // k^2 * (a^2 + b^2) = d^2

        var accumulate = 0L
        val dPow2 = d.toDouble().pow(2)
        for(x in 0 .. d step k) {
            //x = k*a
            //y = k*b
            val bMax = (sqrt(dPow2 - x.toDouble().pow(2)) / k).toInt() + 1
            accumulate +=  bMax
        }

        return accumulate
    }

    fun calculateDFS(k: Int, d: Int): Long {
        val arrayDeque = ArrayDeque<Pair<Int, Int>>()
        val visited = mutableMapOf<Pair<Int, Int>, Boolean>()
        val set = mutableSetOf<Pair<Int, Int>>()
        val dPow = d.toDouble().pow(2)


        arrayDeque.addLast(0 to 0)
        while (arrayDeque.isNotEmpty()) {
            val (x, y) = arrayDeque.removeFirst()

            if(visited[x to y]?: false) continue

            val distance = (k*x).toDouble().pow(2) + (k*y).toDouble().pow(2)
            if(distance > dPow) continue

            set.add(x to y)
            visited[x to y] = true

            arrayDeque.addLast(x + 1 to y)
            arrayDeque.addLast(x to y + 1)


        }

        return set.size.toLong()
    }
}