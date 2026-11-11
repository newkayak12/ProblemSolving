package programmers.lv2.revision

import java.util.Comparator.comparing
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `테이블 해시 함수` {

    @Test
    fun test1() {
        val data = arrayOf(intArrayOf(2, 2, 6), intArrayOf(1, 5, 10), intArrayOf(4, 2, 9), intArrayOf(3, 8, 3))
        val col = 2
        val row_begin = 2
        val row_end = 3
        val result = 4


        assertEquals(result, solution(data, col, row_begin, row_end))
    }


    private fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int =
        data.sortedWith ( compareBy<IntArray> { it[col - 1] }.thenByDescending { it[0] } )
            .mapIndexed { index, value -> value.map { inner -> inner % (index + 1)}.reduce { acc, next -> acc + next  } }
            .filterIndexed{ index, _ -> index in row_begin - 1..row_end - 1 }
            .reduce{ acc, next -> acc xor  next }
}