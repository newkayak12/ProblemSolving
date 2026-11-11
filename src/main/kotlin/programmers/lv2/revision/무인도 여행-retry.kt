package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `무인도 여행-retry` {
    fun solution(maps: Array<String>): IntArray {

        var answer: MutableList<Int> = mutableListOf()

        val path = listOf<Pair<Int, Int>>(
            1 to 0,
            -1 to 0,
            0 to 1,
            0 to -1
        )
        val restricted = 'X'
        val charMaps = maps.map { charMap -> charMap.toCharArray() }
        val visited = maps.map { charMaps -> BooleanArray(charMaps.length) { false } }
        val queue = ArrayDeque<Pair<Int, Int>>()


        for (rowIndex in charMaps.indices) {
            for (colIndex in charMaps[rowIndex].indices) {
                if (visited[rowIndex][colIndex]) {
                    continue
                }
                queue.add(rowIndex to colIndex)
                var sum = 0

                while (queue.isNotEmpty()) {
                    val (y, x) = queue.removeFirst()
                    val value = charMaps[y][x]

                    if (value == restricted || visited[y][x] || !value.isDigit()) {
                        continue
                    }

                    sum += value.toString().toInt()
                    visited[y][x] = true


                    for ((deltaY, deltaX) in path) {
                        val nextY = deltaY + y
                        val nextX = deltaX + x

                        if (nextY !in charMaps.indices || nextX !in charMaps.first().indices) {
                            continue
                        }
                        queue.add(nextY to nextX)
                    }

                }

                if (sum > 0) answer.add(sum)
            }
        }



        return if(answer.isNotEmpty()) answer.sorted().toIntArray() else intArrayOf(-1)
    }


    @Test
    fun test1() {

        val map = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
        val result = intArrayOf(1, 1, 27)

        assertArrayEquals(result, solution(map))
    }

    @Test
    fun test2() {

        val map = arrayOf("XXX", "XXX", "XXX")
        val result = intArrayOf(-1)

        assertArrayEquals(result, solution(map))
    }
}