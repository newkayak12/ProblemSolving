package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `124 나라의 숫자` {
    /***
     *  3 * 0 + 0 -> 0
     *  3 * 0 + 1 -> 1
     *  3 * 0 + 2 -> 2
     *
     *  3 * 1 + 0 -> 4
     *  3 * 1 + 1 -> 11
     *  3 * 1 + 2 -> 12
     *
     *  3 * 2 + 0 -> 14
     *  3 * 2 + 1 -> 21
     *  3 * 2 + 2 -> 22
     *
     *  3 * 3 + 0 -> 24
     *  3 * 3 + 1 -> 41
     *  3 * 3 + 2 -> 42
     *
     *  3 * 4 + 0 -> 44
     *  3 * 4 + 1 -> 111
     *  3 * 4 + 2 -> 112

     *  3 * 5 + 0 -> 114
     *  3 * 5 + 1 -> 121
     *  3 * 5 + 2 -> 122

     *  3 * 6 + 0 -> 124
     *  3 * 6 + 1 -> 411
     *  3 * 6 + 2 -> 412
     *
     *  21 -> 414
     *
     *  3 * 7 + 1 -> 421
     *  3 * 7 + 2 -> 422
     *  24 -> 424
     *
     *  3 * 8 + 1 -> 441
     *  3 * 8 + 2 -> 442
     *  27 -> 444
     */

    fun solution(n: Int): String {
        val builder = StringBuilder()

        var number = n

        while (number != 0) {
//            val originNumber = 3 * (number / 3) + (number % 3)
            val div = number / 3
            val mod = number % 3

            when {
                (mod != 0) -> {
                    builder.append(mod)
                    number = div
                }

                else -> {
                    builder.append("4")
                    number = (div - 1)
                }
            }
        }


            return builder.reverse().toString()
        }

        @Test
        fun test1() {

            val n = 1
            val result = "1"
            assertEquals(result, solution(n))
        }

        @Test
        fun test2() {

            val n = 2
            val result = "2"
            assertEquals(result, solution(n))
        }

        @Test
        fun test3() {

            val n = 3
            val result = "4"
            assertEquals(result, solution(n))
        }

        @Test
        fun test4() {

            val n = 4
            val result = "11"
            assertEquals(result, solution(n))
        }


    }