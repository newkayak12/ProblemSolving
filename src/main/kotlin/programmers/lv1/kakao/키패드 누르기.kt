package programmers.lv1.kakao

import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class `키패드 누르기` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/67256?language=kotlin
     */

    @Test
    fun solution1() {
        val numbers = intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5)
        val hand = "right"

        val expected = "LRLLLRLLRRL"

        assertEquals(expected, calculate(numbers, hand))
    }

    @Test
    fun solution2() {
        val numbers = intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2)
        val hand = "left"

        val expected = "LRLLRRLLLRR"

        assertEquals(expected, calculate(numbers, hand))
    }

    @Test
    fun solution3() {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
        val hand = "right"

        val expected = "LLRLLRLLRL"

        assertEquals(expected, calculate(numbers, hand))
    }


    fun calculate(numbers: IntArray, hand: String): String {
        val keypad = mapOf(
            "*" to (0 to 0),
            "0" to (0 to 1),
            "#" to (0 to 2),
            "7" to (1 to 0),
            "8" to (1 to 1),
            "9" to (1 to 2),
            "4" to (2 to 0),
            "5" to (2 to 1),
            "6" to (2 to 2),
            "1" to (3 to 0),
            "2" to (3 to 1),
            "3" to (3 to 2),
        )
        val rightArea = listOf("3", "6", "9", "#")

        val right = "right"
        var leftHand = "*"
        var rightHand = "#"
        val answer = StringBuilder()

        for (number in numbers) {
            val stringNumber = "$number"

            when (stringNumber) {
                "1", "4", "7" -> {
                    leftHand = stringNumber
                    answer.append("L")
                }

                "3", "6", "9" -> {
                    answer.append("R")

                }

                else -> {
                    val destinationPoint = keypad[stringNumber]!!
                    val leftPoint = keypad[leftHand]!!
                    val rightPoint = keypad[rightHand]!!

                    val leftToDestinationDistance =
                        abs(destinationPoint.first - leftPoint.first) + abs(destinationPoint.second - leftPoint.second)
                    val rightToDestinationDistance =
                        abs(destinationPoint.first - rightPoint.first) + abs(destinationPoint.second - rightPoint.second)

                    if (leftToDestinationDistance == rightToDestinationDistance) {
                        if (hand == right) {
                            answer.append("R")
                            rightHand = stringNumber
                        } else {
                            answer.append("L")
                            leftHand = stringNumber
                        }
                        continue
                    }

                    if (leftToDestinationDistance > rightToDestinationDistance) {
                        answer.append("R")
                        rightHand = stringNumber
                    } else {
                        answer.append("L")
                        leftHand = stringNumber
                    }
                }
            }


        }

        return answer.toString()
    }
}