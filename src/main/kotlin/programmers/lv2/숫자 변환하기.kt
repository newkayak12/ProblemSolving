package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `숫자 변환하기` {
    //BFS
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/154538?language=kotlin
     *
     * x += n
     * x *= 2
     * x *= 3
     */

    @Test
    fun solution1() {
        val x = 10
        val y = 40
        val n = 5
        val expected = 2
        assertEquals(expected, calculate(x, y, n))
        assertEquals(expected, useDP(x, y, n))
    }

    @Test
    fun solution2() {
        val x = 10
        val y = 40
        val n = 30
        val expected = 1
        assertEquals(expected, calculate(x, y, n))
        assertEquals(expected, useDP(x, y, n))
    }

    @Test
    fun solution3() {
        val x = 2
        val y = 5
        val n = 4
        val expected = -1
        assertEquals(expected, calculate(x, y, n))
        assertEquals(expected, useDP(x, y, n))
    }

    private fun calculate(x: Int, y: Int, n: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visit = mutableMapOf<Int, Int>()
        queue.add(x to 0)
        var result = -1

        while (queue.isNotEmpty()) {
            val (element, count) = queue.removeFirst()

            if (element == y) {
                result = count
                break
            }

            if (element + n <= y && visit[element + n] ?: Int.MAX_VALUE > count + 1) {
                queue.add(element + n to count + 1)
                visit[element + n] = count + 1
            }
            if (element * 2 <= y && visit[element * 2] ?: Int.MAX_VALUE > count + 1) {
                queue.add(element * 2 to count + 1)
                visit[element * 2] = count + 1
            }
            if (element * 3 <= y && visit[element * 3] ?: Int.MAX_VALUE > count + 1) {
                queue.add(element * 3 to count + 1)
                visit[element * 3] = count + 1
            }
        }

        return result
    }

    private fun useDP(x: Int, y: Int, n: Int): Int {
        val array = IntArray(y + 1) { Int.MAX_VALUE }
        array[x] = 0

        for(i in x until  y) {
            if(array[i] == Int.MAX_VALUE) continue
            val count = array[i]

            if(i + n <= y)  array[i + n] = minOf(array[i + n], count + 1)
            if(i * 2 <= y)  array[i * 2] = minOf(array[i * 2], count + 1)
            if(i * 3 <= y)  array[i * 3] = minOf(array[i * 3], count + 1)
        }


        return if(array[y] == Int.MAX_VALUE) -1 else array[y]
    }
}