package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `리코쳇 로봇` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/169199?language=kotlin
     */


    @Test
    fun solution1() {
        val board = arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D....")
        val result = 7
        assertEquals(result, calculate(board))
    }

    @Test
    fun solution2() {
        val board = arrayOf(".D.R", "....", ".G..", "...D")
        val result = -1
        assertEquals(result, calculate(board))
    }

    private fun calculate(board: Array<String>): Int {
        /**
         * BFS
         * 이동을 D전, 끝나는 인덱스 전
         * 이동 인덱스에 visited를 체크
         */
        val map: List<CharArray> = board.map { it.toCharArray() }
        val visit = Array(map.size) { BooleanArray(map.first().size) }
        val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>()
        val startPoint = findStartPoint(board, 'R')
        val endPoint = findStartPoint(board, 'G')
        queue.add(startPoint to 0)

        while (queue.isNotEmpty()) {
            val (coordinate, count) = queue.removeFirst()
            val (y, x) = coordinate

            if (endPoint.first == y && endPoint.second == x) {
                return count
            }
            if (visit[y][x]) continue
            visit[y][x] = true

            val leftCoordinate = findCoordinates(map, coordinate, 'L')
            val rightCoordinate = findCoordinates(map, coordinate, 'R')
            val upCoordinate = findCoordinates(map, coordinate, 'U')
            val downCoordinate = findCoordinates(map, coordinate, 'D')

            if (!visit[leftCoordinate.first][leftCoordinate.second]) {
                queue.add(leftCoordinate to count + 1)
            }

            if (!visit[rightCoordinate.first][rightCoordinate.second]) {
                queue.add(rightCoordinate to count + 1)
            }

            if (!visit[upCoordinate.first][upCoordinate.second]) {
                queue.add(upCoordinate to count + 1)
            }

            if (!visit[downCoordinate.first][downCoordinate.second]) {
                queue.add(downCoordinate to count + 1)
            }

        }


        return -1
    }

    private fun findStartPoint(board: Array<String>, point: Char): Pair<Int, Int> {
        for (y in board.indices) {
            val x = board[y].indexOf(point)
            if (x != -1) {
                return y to x
            }
        }

        return 0 to 0
    }

    private fun findCoordinates(
        map: List<CharArray>,
        coordinate: Pair<Int, Int>,
        direction: Char
    ): Pair<Int, Int> {
        val dir = when (direction) {
            'U' -> -1 to 0
            'D' -> 1 to 0
            'L' -> 0 to -1
            'R' -> 0 to 1
            else -> 0 to 0
        }

        var position = coordinate
        while (true) {
            val y = position.first + dir.first
            val x = position.second + dir.second

            if (
                y !in map.indices ||
                x !in map.first().indices ||
                map[y][x] == 'D'
            ) {
                break
            }

            position = y to x
        }

        return position
    }
}