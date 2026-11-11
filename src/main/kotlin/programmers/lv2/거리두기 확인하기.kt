package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `거리두기 확인하기` {
    /**
     * P 찾고
     * - 상하좌우 2칸(중간에 O라면)
     * - 대각선 1칸
     *
     */


    @Test
    fun solution() {
        val places = arrayOf(
            arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
            arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
            arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
        )

//        00010
        val result = intArrayOf(1, 0, 1, 1, 1)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution1() {
        val places = arrayOf(
            arrayOf(
                "POOOP",
                "OXXOX",
                "OPXPX",
                "OOXOX",
                "POXXP"),
        )


        val result = intArrayOf(1)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution2() {
        val places = arrayOf(
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        )


        val result = intArrayOf(0)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution3() {
        val places = arrayOf(
            arrayOf(
                "PXOPX",
                "OXOXP",
                "OXPOX",
                "OXXOP",
                "PXPOX"
            ),
        )


        val result = intArrayOf(1)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution4() {
        val places = arrayOf(
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        )


        val result = intArrayOf(1)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution5() {
        val places = arrayOf(
            arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
        )


        val result = intArrayOf(1)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution6() {
        val places = arrayOf(
            arrayOf(
                "POOOO",
                "OPOOO",
                "OOOOO",
                "OOOOO",
                "OOOOO",
            )
        )


        val result = intArrayOf(0)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution7() {
        val places = arrayOf(
            arrayOf(
                "PPOOO",
                "OOOOO",
                "OOOOO",
                "OOOOO",
                "OOOOO",
            )
        )


        val result = intArrayOf(0)
        assertArrayEquals(result, calculate(places))
    }

    @Test
    fun solution8() {
        val places = arrayOf(
            arrayOf(
                "PXPOO",
                "OOOOO",
                "OOOOO",
                "OOOOO",
                "OOOOO",
            )
        )


        val result = intArrayOf(0)
        assertArrayEquals(result, calculate(places))
    }

    fun calculate(places: Array<Array<String>>): IntArray = places.map { if (useBFS(it)) 0 else 1 }.toIntArray()


    fun useBFS(places: Array<String>): Boolean {
        val deque = ArrayDeque<Pair<Pair<Int, Int>, Int>>()
        val visit = places.map { it.toCharArray().map { false }.toMutableList() }.toMutableList()
        val person = 'P'
        val barrier = 'X'

        for (outIndex in places.indices) {
            for (innerIndex in places[outIndex].indices) {
                val y = outIndex
                val x = innerIndex

                if (visit[y][x]) continue
                if (places[y][x] != person) continue

                deque.addLast(y to x to 0)
                visit[y][x] = true

                while (deque.isNotEmpty()) {
                    val (coordination, distance) = deque.removeFirst()
                    val (currentY, currentX) = coordination

                    if(distance > 2) continue
                    if (places[currentY][currentX] == person && distance != 0) {
                        return true
                    }


                    if (
                        currentY - 1 in places.indices &&
                        !visit[currentY - 1][currentX] &&
                        places[currentY - 1][currentX] != barrier
                    ) {
                        deque.addLast(currentY - 1 to currentX to distance + 1)
                    }
                    if (
                        currentY + 1 in places.indices &&
                        !visit[currentY + 1][currentX] &&
                        places[currentY + 1][currentX] != barrier
                    ) {
                        deque.addLast(currentY + 1 to currentX to distance + 1)
                    }
                    if (
                        currentX - 1 in places.indices &&
                        !visit[currentY][currentX - 1] &&
                        places[currentY][currentX - 1] != barrier
                    ) {
                        deque.addLast(currentY to currentX - 1 to distance + 1)
                    }
                    if (
                        currentX + 1 in places.indices &&
                        !visit[currentY][currentX + 1] &&
                        places[currentY][currentX + 1] != barrier
                    ) {
                        deque.addLast(currentY to currentX + 1 to distance + 1)
                    }
                }
            }
        }

        return false
    }


    fun calculateSimulation(places: Array<Array<String>>): IntArray = places.map {
        if (traverse(it)) 0 else 1
    }.toIntArray()

    fun getOrNull(array: Array<String>, r: Int, c: Int): Char? {
        return if (r in array.indices && c in array.first().indices) array[r][c] else null
    }


    private fun traverse(array: Array<String>): Boolean {
        val people = 'P'
        val barrier = 'X'

        for (outerIndex in array.indices) {
            for (innerIndex in array[outerIndex].indices) {
                if (people != array[outerIndex][innerIndex]) continue

                val topValue = getOrNull(array, outerIndex - 1, innerIndex)
                val bottomValue = getOrNull(array, outerIndex + 1, innerIndex)
                val leftValue = getOrNull(array, outerIndex, innerIndex - 1)
                val rightValue = getOrNull(array, outerIndex, innerIndex + 1)

                if (topValue == people) return true
                if (bottomValue == people) return true
                if (leftValue == people) return true
                if (rightValue == people) return true

                val topLeftInValue = getOrNull(array, outerIndex - 1, innerIndex - 1)
                val topRightInValue = getOrNull(array, outerIndex - 1, innerIndex + 1)
                val bottomLeftInValue = getOrNull(array, outerIndex + 1, innerIndex - 1)
                val bottomRightInValue = getOrNull(array, outerIndex + 1, innerIndex + 1)

                //diagonal
                if (
                    topValue != null && topValue != barrier &&
                    leftValue != null && leftValue != barrier &&
                    topLeftInValue == people
                ) return true

                if (
                    topValue != null && topValue != barrier &&
                    rightValue != null && rightValue != barrier &&
                    topRightInValue == people
                ) return true

                if (
                    bottomValue != null && bottomValue != barrier &&
                    leftValue != null && leftValue != barrier &&
                    bottomLeftInValue == people
                ) return true

                if (
                    bottomValue != null && bottomValue != barrier &&
                    rightValue != null && rightValue != barrier &&
                    bottomRightInValue == people
                ) return true


                val topInDoubleRangeValue = getOrNull(array, outerIndex - 2, innerIndex)
                val bottomInDoubleRangeValue = getOrNull(array, outerIndex + 2, innerIndex)
                val leftInDoubleRangeValue = getOrNull(array, outerIndex, innerIndex - 2)
                val rightInDoubleRangeValue = getOrNull(array, outerIndex, innerIndex + 2)

                if (topValue != null && topValue != barrier && topInDoubleRangeValue == people) return true
                if (bottomValue != null && bottomValue != barrier && bottomInDoubleRangeValue == people) return true
                if (leftValue != null && leftValue != barrier && leftInDoubleRangeValue == people) return true
                if (rightValue != null && rightValue != barrier && rightInDoubleRangeValue == people) return true


            }
        }

        return false;
    }
}