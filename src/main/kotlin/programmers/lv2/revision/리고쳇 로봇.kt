package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `리고쳇 로봇` {


    @Test
    fun test1() {
        val board = arrayOf("...D..R", ".D.G...", "....D.D", "D....D.", "..D....")
        val result = 7

        assertEquals(result, solution(board))
    }

    @Test
    fun test2() {
        val board = arrayOf(".D.R", "....", ".G..", "...D")
        val result = -1

        assertEquals(result, solution(board))
    }


    private fun findPoint(table: List<List<Char>>, target: Char): Pair<Int, Int> {
        for ((rowIndex, rowValue) in table.withIndex()) {
            for ((cellIndex, cellValue) in rowValue.withIndex()) {
                if (cellValue == target) return rowIndex to cellIndex
            }
        }
        return -1 to -1
    }

    private fun slide(table: List<List<Char>>, position: Pair<Int, Int>, deltaX: Int, deltaY: Int): Pair<Int, Int> {
        var (positionY, positionX) = position
        val WALL = 'D'

        while (
            positionY + deltaY in table.indices &&
            positionX + deltaX in table[positionY].indices &&
            WALL != table[positionY + deltaY][positionX + deltaX]
        ) {
            positionY += deltaY
            positionX += deltaX
        }

        return positionY to positionX
    }

    fun solution(board: Array<String>): Int {

        val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>()// y, x, count
        val table = board.map { it.toCharArray().asList() }
        val visit = table.map { row -> BooleanArray(row.size) { false } }.toTypedArray()
        val (startPositionY, startPositionX) = findPoint(table, 'R')
        val (endPositionY, endPositionX) = findPoint(table, 'G')


        queue.addLast((startPositionY to startPositionX) to 0)

        while (queue.isNotEmpty()) {
            val (position, count) = queue.removeFirst()
            val (y, x) = position

            if (visit[y][x]) continue
            else visit[y][x] = true

            if (endPositionY == y && endPositionX == x) return count


            val up = slide(table, position, 0, 1)
            val down = slide(table, position, 0, -1)
            val right = slide(table, position, 1, 0)
            val left = slide(table, position, -1, 0)

            if (position != up) queue.addLast(up to count + 1)
            if (position != down) queue.addLast(down to count + 1)
            if (position != right) queue.addLast(right to count + 1)
            if (position != left) queue.addLast(left to count + 1)
        }

        return -1
    }
}