package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.test.assertEquals

class `서버 증설 횟수` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/389479
     */

    @Test
    fun solution1() {
        val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
        val m = 3
        val k = 5
        val expected = 7

        assertEquals(expected, calculate(players, m, k))
    }

    @Test
    fun solution2() {
        val players = intArrayOf(0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0)
        val m = 5
        val k = 1
        val expected = 11

        assertEquals(expected, calculate(players, m, k))
    }

    @Test
    fun solution3() {
        val players = intArrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1)
        val m = 1
        val k = 1
        val expected = 12

        assertEquals(expected, calculate(players, m, k))
    }

    @Test
    fun solution4() {
        val players = intArrayOf(0,2,3,4,1,6,0,0,0,0,4,2,0,6,5,6,13,3,5,10,3,0,1)
        val m = 3
        val k = 5
        val expected = 7

        assertEquals(expected, calculate(players, m, k))
    }

    //시뮬레이션?
    fun calculate(players: IntArray, m: Int, k: Int): Int {
        var scaleOutCount = 0
        var accumulatedScaleOutCount = 0
        val resourceQueue = ArrayDeque<Pair<Int, Int>>()

        //scale-out 시점
        //현재까지 누적 scale-out 수
        //scale-out 한 리소스 생성 시점 -> + k시간 반환

        for ((time, player) in players.withIndex()) {

            var scCount = 0
            var maximumLessThan = (scaleOutCount + 1) * m




            while (resourceQueue.isNotEmpty()) {
                val (whenCreated, server) = resourceQueue.first()
                if (time - whenCreated >= k) {
                    resourceQueue.removeFirst()
                    scaleOutCount -= server //scaleOutCount 컨트롤
                    maximumLessThan = (scaleOutCount + 1) * m
                } else break
            }



            while (player >= maximumLessThan) {
                scCount++
                maximumLessThan = (scaleOutCount + scCount + 1) * m
            }

            if (scCount > 0) {
                scaleOutCount += scCount
                accumulatedScaleOutCount += scCount
                resourceQueue.addLast(time to scCount)
            }

        }


        return accumulatedScaleOutCount
    }
}