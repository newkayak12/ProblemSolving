package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `무인도 여행` {

    @Test
    fun test1() {
        val maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
        val result = intArrayOf(1, 1, 27)

        assertArrayEquals(result, solution(maps))
    }

    @Test
    fun test2() {
        val maps = arrayOf("XXX", "XXX", "XXX")
        val result = intArrayOf(-1)

        assertArrayEquals(result, solution(maps))
    }

    private fun traverse(
        maps: Array<CharArray>,
        visited: Array<BooleanArray>,
        rowIndex: Int,
        columnIndex: Int,
        direction: List<Pair<Int, Int>>,
        score: Int
    ): Int {

        if(visited[rowIndex][columnIndex]) return score

        val column = maps[rowIndex][columnIndex]
        visited[rowIndex][columnIndex] = true
        if (column == 'X') return score


        var nowScore = score + column.digitToInt()


        for ((y, x) in direction) {
            val newY = rowIndex + y
            val newX = columnIndex + x

            if (newY !in maps.indices || newX !in maps.first().indices) continue
            nowScore = traverse(maps, visited, newY, newX, direction, nowScore)
        }

        return nowScore
    }

    fun solution(maps: Array<String>): IntArray {
        val table = maps.map { it.toCharArray() }.toTypedArray()
        val visited = maps.map { it.map { false }.toBooleanArray() }.toTypedArray()
        val scores = mutableListOf<Int>()
        val direction = listOf(
            1 to 0,
            -1 to 0,
            0 to 1,
            0 to -1
        )


        for ((rowIndex, row) in table.withIndex()) {
            for ((columnIndex, _) in row.withIndex()) {
                val score = traverse(table, visited, rowIndex, columnIndex, direction, 0)
                if (score > 0) scores.add(score)
            }
        }


        if (scores.isEmpty()) return intArrayOf(-1)
        return scores.sorted().toIntArray()
    }
}