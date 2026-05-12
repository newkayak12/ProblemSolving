package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `2개 이하로 다른 비트` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/77885?language=kotlin
     *
     *  xor -> bitCount <= 2 -> minOf()
     *  shl 2 최대
     */

    @Test
    fun solution() {
        val numbers = longArrayOf(2, 7)
        val expected = longArrayOf(3, 11)
        assertArrayEquals(expected, calculate(numbers))
    }

    private fun calculate(numbers: LongArray): LongArray {
        var answer = LongArray(numbers.size)

        for ((index, number) in numbers.withIndex()) {

            if(number % 2 == 0L) answer[index] = number + 1
            else {
                val radix2 = number.toString(2)
                val oneBitCount = number.countOneBits()
                if( radix2.length == oneBitCount) {
                    answer[index] = number + (1).shl(radix2.length - 1)
                }
                else {
                    val rightmostZero = number.inv() and (number + 1)
                    number + rightmostZero - (rightmostZero shr 1)
                }
            }

        }

        return answer
    }
}