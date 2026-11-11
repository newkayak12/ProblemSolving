package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `서버 증설 횟수` {

    /**
     * var count = 0
     * val queue = ArrayDeque
     * for (index, 플레이어) in players.withIndices
     *
     *   var n = queue.size + 1
     *   val preserve = 플레이어 in (n * m) .. ((n+1) * m)
     *
     *   while( !preserve && 플레이어 > (n * m) )
     *      queue.add (index)
     *      count ++
     *      n ++
     *      preserve = 플레이어 in (n * m) .. ((n+1) * m)
     *
     *
     *   while( queue.isNotEmpty && !preserve && 플레이어 < (n+1) * m && (index - queue.first >= k))
     *      queue.removeFirst
     *       n --
     *       preserve = 플레이어 in (n * m) .. ((n+1) * m)
     *
     */

    private fun isEnough(player: Int, serverSize: Int, capacity: Int) =
        player in (serverSize * capacity) until (serverSize + 1) * capacity

    fun solution(players: IntArray, m: Int, k: Int): Int {
        var count = 0
        val queue = ArrayDeque<Int>()
        var serverSize = 0
        for ((time, player) in players.withIndex()) {


            while (queue.isNotEmpty() &&  time == queue.first() + k) {
                queue.removeFirst()
                serverSize -= 1
            }


            while (!isEnough(player, serverSize, m) && player > (serverSize * m)) {
                queue.add(time)
                count += 1
                serverSize += 1
            }

        }
        return count
    }


    @Test
    fun test1() {
        val players = intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5)
        val m = 3
        val k = 5
        val result = 7

        assertEquals(result, solution(players, m, k))
    }

    @Test
    fun test2() {
        val players = intArrayOf(0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0)
        val m = 5
        val k = 1
        val result = 11

        assertEquals(result, solution(players, m, k))
    }

    @Test
    fun test3() {
        val players = intArrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1)
        val m = 1
        val k = 1
        val result = 12

        assertEquals(result, solution(players, m, k))
    }


}