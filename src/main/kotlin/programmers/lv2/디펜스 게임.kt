package programmers.lv2

import java.util.PriorityQueue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `디펜스 게임` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/142085
     */

    @Test
    fun solution1() {
        val n = 7
        val k = 3
        val enemy = intArrayOf(4, 2, 4, 5, 3, 3, 1)
        val expected = 5
        assertEquals(expected, calculate1(n, k, enemy))
        assertEquals(expected, calculate2(n, k, enemy))
    }

    @Test
    fun solution2() {
        val n = 2
        val k = 4
        val enemy = intArrayOf(3, 3, 3, 3)
        val expected = 4
        assertEquals(expected, calculate1(n, k, enemy))
        assertEquals(expected, calculate2(n, k, enemy))
    }

    @Test
    fun solution3() {
        val n = 10
        val k = 1
        val enemy = intArrayOf(5, 1, 1, 1, 1, 1, 1)
        val expected = 7
        assertEquals(expected, calculate1(n, k, enemy))
        assertEquals(expected, calculate2(n, k, enemy))
    }

    @Test
    fun solution4() {
        val n = 100
        val k = 0
        val enemy = intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
        val expected = 10
        assertEquals(expected, calculate1(n, k, enemy))
        assertEquals(expected, calculate2(n, k, enemy))
    }

    @Test
    fun solution5() {
        val n = 100
        val k = 0
        val enemy = intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1)
        val expected = 10
        assertEquals(expected, calculate1(n, k, enemy))
        assertEquals(expected, calculate2(n, k, enemy))
    }

    fun calculate1(n: Int, k: Int, enemy: IntArray): Int {
        var number = n
        var pass = k
        var count = 0
        val pq = PriorityQueue<Int> { o1, o2 -> o1 - o2 }

        //1. number를 무조건 0으로 만들 수 있는 경우
        //2. pass는 element > number 일 때만 쓰는 것이 아님
        for (element in enemy) {
            if (pass > 0) {
                pq.add(element)
                pass--
            }
            else if(pq.isNotEmpty()) {
                val pqEnemy = pq.peek()
                if (number < pqEnemy && number < element) break

                if (pqEnemy > element) {
                    number -= element
                } else {
                    val prev = pq.poll()
                    pq.add(element)
                    number -= prev
                }
            }
            else {
                if(number < element) break
                number -= element
            }
            count++
        }

        return count
    }

    fun calculate2(n: Int, k: Int, enemy: IntArray): Int {
        if (k >= enemy.size) return enemy.size
        if (enemy.sumOf { it.toLong() } <= n.toLong()) return enemy.size

        var left = 0
        var right = enemy.size

        while (left < right) {
            val mid = (left + right + 1) / 2
            val isReachable = canReach(n, k, enemy, 0, mid)
            if(isReachable) {
                left = mid
            }
            else {
                right = mid - 1
            }


        }

        return left
    }

    private fun canReach(n: Int, k: Int, enemy: IntArray, start: Int, end: Int): Boolean {
        val sumOfSortedList = enemy
            .slice(start until end)
            .sortedDescending()
            .filterIndexed { index, _ -> index >= minOf(k, end - start) }
            .sumOf { it.toLong() }

        return sumOfSortedList <= n
    }
}