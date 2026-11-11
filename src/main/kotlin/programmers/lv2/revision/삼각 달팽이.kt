package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `삼각 달팽이` {
    /**
     * 직접 배열을 만들고 시뮬레이션
     *
     *
     * ⬇️ row index를 늘려나가면서 채번
     * ➡️ cell Index를 늘려나가면서 채번
     * ↖️ row index - 1, cell index - 1 하면서 채번, 해당 인덱스에 숫자가 있으면 종료
     */

    @Test
    fun test1() {
        val n = 4
        val result = intArrayOf(1, 2, 9, 3, 10, 8, 4, 5, 6, 7)
        assertArrayEquals(result, solution(n))
    }


    @Test
    fun test2() {
        val n = 5
        val result = intArrayOf(1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9)
        assertArrayEquals(result, solution(n))
    }


    @Test
    fun test3() {
        val n = 6
        val result = intArrayOf(1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11)
        assertArrayEquals(result, solution(n))
    }


    fun solution(n: Int): IntArray {
        val array = Array(n) { IntArray(n) { 0 } }
        var number = 1
        var rowIndex = 0
        var columIndex = 0

        while (true) {
            if(array[rowIndex][columIndex] != 0) break

            var nowRowIndex = rowIndex
            var nowColumIndex = columIndex


            while(nowRowIndex in rowIndex until n && array[nowRowIndex][nowColumIndex] == 0) {
                array[nowRowIndex][nowColumIndex] = number ++
                if(nowRowIndex + 1 in rowIndex until n && array[nowRowIndex + 1][nowColumIndex] == 0) {
                    nowRowIndex++
                }
                else break
            }


            nowColumIndex++

            while(nowColumIndex in columIndex until  n&& array[nowRowIndex][nowColumIndex] == 0) {
                array[nowRowIndex][nowColumIndex] = number++

                if(nowColumIndex + 1 in columIndex until n && array[nowRowIndex][nowColumIndex + 1] == 0) {
                    nowColumIndex++
                }
                else break
            }


            nowColumIndex--
            nowRowIndex--

            while(
                nowColumIndex in columIndex until  n &&
                nowRowIndex in rowIndex until  n &&
                array[nowRowIndex][nowColumIndex] == 0
            ) {
                array[nowRowIndex][nowColumIndex] = number++

                if(
                    nowRowIndex - 1 in rowIndex until n &&
                    nowColumIndex - 1 in columIndex until n &&
                    array[nowRowIndex - 1][nowColumIndex - 1] == 0
                ) {
                    nowRowIndex --
                    nowColumIndex --
                }
                else break
            }

            nowRowIndex++

            rowIndex = nowRowIndex
            columIndex = nowColumIndex
        }


        return array.flatMap { it.filterNot { it == 0 } }.toIntArray()
    }

}