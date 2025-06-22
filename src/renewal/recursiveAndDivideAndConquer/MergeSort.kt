package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class MergeSort {

    @Test
    fun mergeSort() {
        val array = intArrayOf(38, 27, 43, 3, 9, 82, 10)
        val expected = intArrayOf(3, 9, 10, 27, 38, 43, 82)

        divide(array, 0, array.size - 1)

        assertArrayEquals(expected, array)
    }

    private fun divide(array: IntArray, start: Int, end: Int) {

        if (start < end) {
            val mid = (start + end) / 2
            divide(array, start, mid)
            divide(array, mid + 1, end)
            conquer(array, start, mid, end)
        }
    }

    private fun conquer(array: IntArray, start: Int, mid: Int, end: Int) {
        val mutableArray = mutableListOf<Int>()
        var i = start
        var j = mid + 1

        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) mutableArray.add(array[i++])
            else mutableArray.add(array[j++])
        }

        while (i <= mid) mutableArray.add(array[i++])
        while (j <= end) mutableArray.add(array[j++])

        for ( index in mutableArray.indices) {
            array[start + index] = mutableArray[index]
        }
    }

}