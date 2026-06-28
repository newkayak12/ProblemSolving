package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `비밀 코드 해독` {

    /**
     * nCr을 한 것을 각 케이스에 대조한다.
     * 대조 했을 때 불일치하는 것들을 탈락 시키며 진행
     * 마지막에 남는 것을 카운트
     *
     *
     *
     */


    @Test
    fun solution1() {
        val n = 10
        val q = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(6, 7, 8, 9, 10),
            intArrayOf(3, 7, 8, 9, 10),
            intArrayOf(2, 5, 7, 9, 10),
            intArrayOf(3, 4, 5, 6, 7),
        )
        val answer = intArrayOf(2, 3, 4, 3, 3)
        val result = 3

        assertEquals(result, solution(n, q, answer))
    }

    @Test
    fun solution2() {
        val n = 15
        val q = arrayOf(
            intArrayOf(2, 3, 9, 12, 13),
            intArrayOf(1, 4, 6, 7, 9),
            intArrayOf(1, 2, 8, 10, 12),
            intArrayOf(6, 7, 11, 13, 15),
            intArrayOf(1, 4, 10, 11, 14),
        )
        val answer = intArrayOf(2, 1, 3, 0, 1)
        val result = 5

        assertEquals(result, solution(n, q, answer))
    }


    fun solution(n: Int, q: Array<IntArray>, ans: IntArray): Int {
        val array = (1..n).toList();
        val numberOfCases = getNumberOfCases(array, 0, 0, IntArray(5), mutableListOf<IntArray>()).toList()
        val filteredContainer = filterNumberOfCases(numberOfCases, q, ans)
        return filteredContainer.size
    }


    private fun getNumberOfCases(
        array: List<Int>,
        currentArrayIndex: Int,
        maxNumber: Int,
        case: IntArray,
        cases: MutableList<IntArray>
    ): MutableList<IntArray> {
        if (currentArrayIndex > 4) {
            cases.add(case.copyOf())
            return cases
        }


        for ((_, value) in array.withIndex()) {
            if (maxNumber >= value) continue

            case[currentArrayIndex] = value
            getNumberOfCases(array, currentArrayIndex + 1, value, case, cases)
            case[currentArrayIndex] = 0
        }

        return cases
    }

    private fun filterNumberOfCases(
        numberOfCase: List<IntArray>,
        queue: Array<IntArray>,
        answer: IntArray
    ): List<IntArray> {


        val copyOfNumberOfCases = numberOfCase.toMutableList()
        for((index, value) in queue.withIndex()) {
            copyOfNumberOfCases.removeIf {
                it.count { value.contains(it) } != answer[index]
            }
        }

        return copyOfNumberOfCases.toList()
    }
}