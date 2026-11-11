package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.w3c.dom.ranges.Range

class `연속된 부분 수열의 합` {

    @Test
    fun test1() {
        val sequence = intArrayOf(1, 2, 3, 4, 5)
        val k = 7
        val result = intArrayOf(2, 3)


        assertArrayEquals(result, solution(sequence, k))
    }

    @Test
    fun test2() {
        val sequence = intArrayOf(1, 1, 1, 2, 3, 4, 5)
        val k = 5
        val result = intArrayOf(6, 6)


        assertArrayEquals(result, solution(sequence, k))
    }

    @Test
    fun test3() {
        val sequence = intArrayOf(2, 2, 2, 2, 2)
        val k = 6
        val result = intArrayOf(0, 2)


        assertArrayEquals(result, solution(sequence, k))
    }

    /**
     * start_index = 0
     * end_index = 0
     *
     * sum = (0..0).sum()
     * array = set()
     *
     * while end_index < sequence.size && start_index <= end_index
     *
     *
     *      if sum > k
     *         sum -= sequence[start_index]
     *         start_index += 1
     *
     *      else if sum < k
     *         if end_index + 1!in sequence.indices
     *            break;
     *
     *         end_index += 1
     *         sum += sequence[end_index]
     *
     *      else sum == k
     *         array.add(start_index .. end_index)
     *         sum -= sequence[start_index]
     *         start_index += 1
     *
     */
    fun solution(sequence: IntArray, k: Int): IntArray {
        var answer: Pair<Int, Int> = 0 to sequence.size - 1
        var startIndex = 0
        var endIndex = 0
        var sum = sequence[0]

        while (endIndex < sequence.size && startIndex <= endIndex) {
            if (sum > k) {
                sum -= sequence[startIndex]
                startIndex++
            } else if (sum < k) {
                if (endIndex + 1 !in sequence.indices) break
                endIndex++
                sum += sequence[endIndex]
            } else {
                if(endIndex - startIndex < answer.second - answer.first){

                    answer = startIndex to endIndex
                }

                sum -= sequence[startIndex]
                startIndex++
            }
        }

        return intArrayOf(answer.first, answer.second)
    }
}