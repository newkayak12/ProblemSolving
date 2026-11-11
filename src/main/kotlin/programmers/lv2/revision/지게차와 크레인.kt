package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.collections.withIndex
import kotlin.test.assertEquals
import kotlin.text.withIndex

class `지게차와 크레인` {


    @Test
    fun solution1() {
        val storage = arrayOf("AZWQY", "CAABX", "BBDDA", "ACACA")
        val requests = arrayOf("A", "BB", "A")
        val result = 11

        assertEquals(result, solution(storage, requests))
    }


    @Test
    fun solution2() {
        val storage = arrayOf("HAH", "HBH", "HHH", "HAH", "HBH")
        val requests = arrayOf("C", "B", "B", "B", "B", "H")
        val result = 4

        assertEquals(result, solution(storage, requests))
    }


    fun solution(storage: Array<String>, requests: Array<String>): Int {
        val emptyChar = '-'
        var copyOfStorage = createOutline(storage, emptyChar)


        for (request in requests) {
            val (useCrane, target) = planning(request)

            when (useCrane) {
                true -> useCrane(copyOfStorage, emptyChar, target)
                false -> useForklift(copyOfStorage, emptyChar, target)
            }
        }


        return copyOfStorage.sumOf { it.count { innerIt -> innerIt != '-' } }
    }

    fun planning(request: String) = (request.length > 1) to request[0]

    fun createOutline(storage: Array<String>, emptyChar: Char): Array<Array<Char>> {
        val copyOfStorage =
            Array<Array<Char>>(storage.size + 2) { Array<Char>(storage.first().length + 2) { emptyChar } }


        for ((outerIndex, cargo) in storage.withIndex()) {
            for ((innerIndex, element) in cargo.toCharArray().withIndex()) {
                copyOfStorage[outerIndex + 1][innerIndex + 1] = element
            }
        }

        return copyOfStorage
    }

    fun useCrane(copyOfStorage: Array<Array<Char>>, emptyChar: Char, targetChar: Char) {
        for ((outerIndex, cargo) in copyOfStorage.withIndex()) {
            for ((innerIndex, element) in cargo.withIndex()) {
                if (element != targetChar) continue
                copyOfStorage[outerIndex][innerIndex] = emptyChar
            }
        }
    }

    fun useForklift(copyOfStorage: Array<Array<Char>>, emptyChar: Char, targetChar: Char) {
        val visited = copyOfStorage.map { it.map { false }.toBooleanArray() }.toTypedArray()
        val direction = arrayOf<Pair<Int, Int>>(
            1 to 0,
            -1 to 0,
            0 to 1,
            0 to -1
        )
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.add(0 to 0)

        while (stack.isNotEmpty()) {
            val (y, x) = stack.removeLast()
            if (copyOfStorage[y][x] != emptyChar) continue

            for ((deltaY, deltaX) in direction) {
                val nextY = deltaY + y
                val nextX = deltaX + x
                if (nextY !in copyOfStorage.indices || nextX !in copyOfStorage.first().indices) continue
                if (visited[nextY][nextX]) continue
                visited[nextY][nextX] = true

                if (copyOfStorage[nextY][nextX] == targetChar) {
                    copyOfStorage[nextY][nextX] = emptyChar
                } else {
                    stack.addLast(nextY to nextX)
                }
            }

        }
    }


}

