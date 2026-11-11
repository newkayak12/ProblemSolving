package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `지게차와 크레인` {


    @Test
    fun solution1() {
        val storage = arrayOf("AZWQY", "CAABX", "BBDDA", "ACACA")
        val requests = arrayOf("A", "BB", "A")
        val result = 11

        assertEquals(result, calculate(storage, requests))
    }

    @Test
    fun solution2() {
        val storage = arrayOf("HAH", "HBH", "HHH", "HAH", "HBH")
        val requests = arrayOf("C", "B", "B", "B", "B", "H")
        val result = 4

        assertEquals(result, calculate(storage, requests))
    }

    @Test
    fun solution3() {
        val storage = arrayOf("AAAA", "AAAA", "AAAA", "AAAA")
        val requests = arrayOf("A", "B", "C", "D", "E", "F")
        val result = 4

        assertEquals(result, calculate(storage, requests))
    }


    private fun prepareArray(storage: Array<String>) =
        Array(storage.size + 2) { Array(storage.first().length + 2) { "" } }
            .mapIndexed { index, array ->
                when {
                    index == 0 || index == storage.size + 1 -> array
                    else -> storage[index - 1].split("").toTypedArray()
                }
            }.toTypedArray()


    private fun erase(array: Array<Array<String>>, isSingle: Boolean, letter: String) {

        when (isSingle) {
            true -> {
                //0, 0 -> DFS로
                val stack = ArrayDeque<Pair<Int, Int>>()
                val visited = array.map { BooleanArray(it.size){ false } }
                stack.addLast(0 to 0)
                val yIndices = array.indices
                val xIndices = array[0].indices
                while(stack.isNotEmpty()) {
                    val (y, x) = stack.removeLast()
                    val capture = array[y][x]
                    if(visited[y][x]) continue
                    else visited[y][x] = true

                    if(array[y][x] == letter) { array[y][x] = "" }

                    if(y + 1 in yIndices && capture.isBlank() && (array[y + 1][x].isBlank() || array[y + 1][x] == letter)) stack.addLast(y + 1 to x)
                    if(y - 1 in yIndices && capture.isBlank() && (array[y - 1][x].isBlank() || array[y - 1][x] == letter)) stack.addLast(y - 1 to x)
                    if(x + 1 in xIndices && capture.isBlank() && (array[y][x + 1].isBlank() || array[y][x + 1] == letter)) stack.addLast(y to x + 1)
                    if(x - 1 in xIndices && capture.isBlank() && (array[y][x - 1].isBlank() || array[y][x - 1] == letter)) stack.addLast(y to x - 1)

                }

            }
            else -> {
                for ((outIndex, outerArray) in array.withIndex()) {
                    for ((innerIndex, innerArrayValue) in outerArray.withIndex()) {
                        if (innerArrayValue == letter) array[outIndex][innerIndex] = ""
                    }
                }
            }
        }
    }

    fun calculate(storage: Array<String>, requests: Array<String>): Int {
        val copy = prepareArray(storage)

        for (request in requests) {
            val isSingle = request.length == 1
            val letter = request[0].toString()

            erase(copy, isSingle, letter)
        }

        return copy.flatten().count { it != "" }
    }
}