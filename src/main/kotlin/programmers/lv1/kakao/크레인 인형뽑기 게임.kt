package programmers.lv1.kakao

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `크레인 인형뽑기 게임` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/64061?language=kotlin
     */

    @Test
    fun solution1() {
        val board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 3),
            intArrayOf(0, 2, 5, 0, 1),
            intArrayOf(4, 2, 4, 4, 2),
            intArrayOf(3, 5, 1, 3, 1)
        )

        val moves = intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
        val result = 4

        assertEquals(result, calculate(board, moves))
    }


    private fun calculate(board: Array<IntArray>, moves: IntArray): Int {
        val list = ArrayDeque<Int>()
        var answer = 0
        for (value in moves) {
            var i = 0

            while (i < board.first().size) {
                val pick = board[i][value - 1]
                if (pick != 0) {

                    val pickLast = list.lastOrNull()
                    if(pickLast != null && pickLast == pick) {
                        list.removeLast()
                        answer += 2
                    }
                    else {
                        list.addLast(pick)
                    }
                    board[i][value - 1] = 0
                    break
                }

                i ++
            }

        }

        // 4, 3, 1, 1, 3, 2, x, 4
        return answer
    }
}