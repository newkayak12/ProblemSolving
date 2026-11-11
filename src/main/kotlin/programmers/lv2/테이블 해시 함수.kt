package programmers.lv2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `테이블 해시 함수` {
    @Test
    fun solution1() {
        val data = arrayOf(
            intArrayOf(2, 2, 6),
            intArrayOf(1, 5, 10),
            intArrayOf(4, 2, 9),
            intArrayOf(3, 8, 3)
        )
        val col = 2
        val rowBegin = 2
        val rowEnd = 3
        val result = 4


        assertEquals(result, calculate(data, col, rowBegin, rowEnd))
    }

    fun calculate(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {

        val sortedData = data.sortedWith(compareBy<IntArray> { it[col - 1] }.thenByDescending { it[0] })
        var result = 0


        for (i in row_begin .. row_end) {
            var sum = 0
            for ((_, value) in sortedData[i - 1].withIndex()) {
                sum += (value % i)
            }

            result = result xor sum
//            println("$i -> $sum :: $result")
        }

        return result
    }
}