package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `연속된 부분 수열의 합` {


    @Test
    fun solution1() {
        val sequence = intArrayOf(1, 2, 3, 4, 5)
        val k = 7
        val expected = intArrayOf(2, 3)

        assertArrayEquals(expected, calculate(sequence, k))
    }

    @Test
    fun solution2() {
        val sequence = intArrayOf(1, 1, 1, 2, 3, 4, 5)
        val k = 5
        val expected = intArrayOf(6, 6)

        assertArrayEquals(expected, calculate(sequence, k))
    }

    @Test
    fun solution3() {
        val sequence = intArrayOf(2, 2, 2, 2, 2)
        val k = 6
        val expected = intArrayOf(0, 2)

        assertArrayEquals(expected, calculate(sequence, k))
    }

    fun calculate(sequence: IntArray, k: Int): IntArray {
        val listUp = mutableListOf<Pair<Int, Int>>()

        var a = 0
        var b = 0
        var sum = sequence[a]

        while (
            a <= b &&
            b in sequence.indices
        ) {

            when{
                sum == k -> {
                    listUp.add(a to b)
                    b++
                    if(b in sequence.indices) sum += sequence[b]
                }
                sum < k -> {
                    b++
                    if(b in sequence.indices) sum += sequence[b]
                }
                else -> {
                    sum -= sequence[a]
                    a++
                }
            }
        }

        val compare = Comparator<Pair<Int, Int>> { o1: Pair<Int, Int>, o2: Pair<Int, Int> ->
                (o1.second - o1.first).compareTo(o2.second - o2.first)
            }
            .thenComparing { o1: Pair<Int, Int>, o2: Pair<Int, Int> ->
                o1.first.compareTo(o2.first)
            }
        listUp.sortWith(compare)

        return listUp.first().toList().toIntArray()
    }
}