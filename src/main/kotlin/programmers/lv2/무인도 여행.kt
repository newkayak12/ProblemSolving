package programmers.lv2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `무인도 여행` {

    @Test
    fun solution1() {
        val maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
        val result = intArrayOf(1, 1, 27)

        assertArrayEquals(result, calculate(maps))
    }

    @Test
    fun solution2() {
        val maps = arrayOf("XXX", "XXX", "XXX")
        val result = intArrayOf(-1)

        assertArrayEquals(result, calculate(maps))
    }

    private fun traversal(map: List<List<String>>, visit: Array<BooleanArray>, y: Int, x: Int, score: Int): Int {
        val list = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        var nextScore = score

        for ((yMovement, xMovement) in list) {

            val newY = y + yMovement
            val newX = x + xMovement

            if (newY !in map.indices) {
                continue
            }
            if (newX !in map[0].indices) {
                continue
            }
            if (visit[newY][newX]) {
                continue
            }

            visit[newY][newX] = true

            if ("X".equals(map[newY][newX])) {
                continue
            }


            nextScore = traversal(map, visit, newY, newX, nextScore + (map[newY][newX]).toInt())
        }

        return nextScore
    }

    fun calculate(maps: Array<String>): IntArray {
        val map = maps.map { it.split("").filterNot { string -> string.isEmpty() } }
        val visit = Array(map.size) { BooleanArray(map[0].size) { false } }
        val result = mutableListOf<Int>()

        for ((rowIndex, rowValue) in map.withIndex()) {
            for ((cellIndex, cellValue) in rowValue.withIndex()) {
                if (visit[rowIndex][cellIndex]) continue
                visit[rowIndex][cellIndex] = true

                if ("X".equals(cellValue)) continue

                val value = traversal(map, visit, rowIndex, cellIndex, cellValue.toInt())
                if(value > 0) {
                    result.add(value)
                }

            }
        }

        return if(result.isEmpty()) intArrayOf(-1) else result.sorted().toIntArray()
    }
}