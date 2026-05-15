package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `2개 이하로 다른 비트` {


    @Test
    fun test1() {
        val numbers = longArrayOf(2, 7)
        val result = longArrayOf(3, 11)

        assertArrayEquals(result, solution(numbers))
        assertArrayEquals(result, solutionPureBitOperations(numbers))
    }

    /**
     * 02 = 00010 -> 03
     * 03 = 00011 -> 05 ✅
     * 04 = 00100 -> 05
     * 05 = 00101 -> 06
     * 06 = 00110 -> 07
     * 07 = 00111 -> 11 ✅
     * 08 = 01000 -> 09
     * 09 = 01001 -> 10
     * 10 = 01010 -> 11
     * 11 = 01011 -> 13 ✅
     * 12 = 01100 -> 13
     * 13 = 01101 -> 14
     * 14 = 01110 -> 15
     * 15 = 01111 -> 23 ✅
     * 16 = 10000 -> 17
     * 17 = 10001 -> 18
     * 18 = 10010 -> 19
     * 19 = 10011 -> 21
     * 20 = 10100 -> 21
     * 21 = 10101 -> 22
     * 22 = 10110 -> 23
     * 23 = 10111 -> 27 ✅
     * 24 = 11000
     * 25 = 11001
     * 26 = 11010
     * 27 = 11011
     * 28 = 11100
     * 29 = 11101
     * 30 = 11110
     *
     * - 끝이 1이 아닌 경우
     *   -  + 1
     * - 끝이 1인 경우
     *   - 모두 1로 이뤄진 경우
     *   - 0이 포함된 경우
     *
     */


    fun solutionPureBitOperations(numbers: LongArray): LongArray {
        val ONE = 1L
        val answer = LongArray(numbers.size)
        for ((index, number) in numbers.withIndex()) {
            if (number and ONE != 1L) {
                answer[index] = number + 1
                continue
            }

            var mask = 1L

            repeat(number.countOneBits()) {
                (mask shl 1) or 1
            }

            val add = number.inv() and (number + 1)
            val minus = add shr 1

            answer[index] = (number xor minus) or add
        }

        return answer
    }

    fun solution(numbers: LongArray): LongArray {

        val answer = LongArray(numbers.size)

        for ((index, number) in numbers.withIndex()) {
            var bit = number.toString(2)
            if (bit.endsWith("0")) {
                answer[index] = number + 1
            } else {
                if (bit.length == number.countOneBits()) {
                    bit = "0$bit"
                }
                val lastZeroIndex = bit.lastIndexOf('0')
                val char = bit.toCharArray()
                char[lastZeroIndex] = '1'
                char[lastZeroIndex + 1] = '0'

                val newBit = String(char)
                answer[index] = newBit.toLong(2)
            }

        }

        return answer
    }
}