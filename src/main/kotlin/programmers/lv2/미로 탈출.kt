package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `미로 탈출` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/159993
     *
     *
     * //BFS를 두 번 사용한다.
     * 1. 시작점에서 레버가 있는 곳으로
     * 2. 레버에서 탈출지점으로
     *
     */

    @Test
    fun solution1() {
        val maps = arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE")
        val expected = 16

        assertEquals(expected, calculate(maps))
    }


    @Test
    fun solution2() {
        val maps = arrayOf("LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO")
        val expected = -1

        assertEquals(expected, calculate(maps))
    }


    private fun calculate(maps: Array<String>): Int {
        val charMaps = maps.map { it.toCharArray() }
        var startPoint: Pair<Int, Int> = 0 to 0
        var leverPoint: Pair<Int, Int> = 0 to 0

        for((rowIndex, rowValue) in charMaps.withIndex()) {
            for((columnIndex, columnValue) in rowValue.withIndex()) {
                if(columnValue == 'S') startPoint = rowIndex to columnIndex
                if(columnValue == 'L') leverPoint = rowIndex to columnIndex
            }
        }


        val leverCount = useStack(charMaps, Array(charMaps.size){BooleanArray(charMaps.first().size)}, startPoint, 'L')
        if(leverCount == -1) return -1

        val endCount = useStack(charMaps, Array(charMaps.size){BooleanArray(charMaps.first().size)}, leverPoint, 'E')
        if(endCount == -1) return  -1

        return leverCount + endCount - 2
    }

    private fun useStack(maps: List<CharArray>, visit: Array<BooleanArray>, start: Pair<Int, Int>, end: Char): Int {
        val queue = ArrayDeque<Triple<Array<BooleanArray>, Pair<Int, Int>, Int>>()
        queue.add(Triple(visit, start, 0))


        while (queue.isNotEmpty()) {
            val (visitMap, coordinate, count) = queue.removeFirst()
            val (y, x) = coordinate

            if (y !in maps.indices || x !in maps.first().indices) continue
            if (visitMap[y][x]) continue
            val copyOfVisitMap = visitMap.copyOf()
            copyOfVisitMap[y][x] = true


            if (maps[y][x] == end) {
                return count + 1
            }


            if (y + 1 in maps.indices && 'X' != maps[y+1][x]) queue.add(Triple(copyOfVisitMap, y + 1 to x, count + 1))
            if (y - 1 in maps.indices && 'X' != maps[y-1][x]) queue.add(Triple(copyOfVisitMap, y - 1 to x, count + 1))
            if (x + 1 in maps.first().indices && 'X' != maps[y][x+1]) queue.add(Triple(copyOfVisitMap, y to x + 1, count + 1))
            if (x - 1 in maps.first().indices && 'X' != maps[y][x-1]) queue.add(Triple(copyOfVisitMap, y to x - 1, count + 1))

        }


        return -1
    }
}