package programmers.lv1.kakao

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `가장 많이 받은 선물` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/258712?language=kotlin
     *
     *
     * rule: A -> B: 5, B -> A: 3? B -> A +1
     * 선물 지수: 준 선물 - 받은 선물
     * 선물 지수가 같으면 선물을 주고 받지 않는다.
     */

    @Test
    fun solution1() {
        val friends = arrayOf("muzi", "ryan", "frodo", "neo")
        val gifts = arrayOf(
            "muzi frodo",
            "muzi frodo",
            "ryan muzi",
            "ryan muzi",
            "ryan muzi",
            "frodo muzi",
            "frodo ryan",
            "neo muzi"
        )
        val expected = 2

        assertEquals(expected, calculate(friends, gifts))
    }

    @Test
    fun solution2() {
        val friends = arrayOf("joy", "brad", "alessandro", "conan", "david")
        val gifts =
            arrayOf("alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david")
        val expected = 4

        assertEquals(expected, calculate(friends, gifts))
    }

    @Test
    fun solution3() {
        val friends = arrayOf("a", "b", "c")
        val gifts = arrayOf("a b", "b a", "c a", "a c", "a c", "c a")
        val expected = 0

        assertEquals(expected, calculate(friends, gifts))
    }

    private fun calculate(friends: Array<String>, gifts: Array<String>): Int {
        val gifted = mutableMapOf<String, Int>()
        val status = mutableMapOf<String, MutableMap<String, Int>>()
        val resultMap = friends.associateWith { 0 }.toMutableMap()

        for (element in gifts) {
            val (gift, giftee) = element.split(" ")
            gifted[gift] = gifted.getOrPut(gift) { 0 } + 1
            gifted[giftee] = gifted.getOrPut(giftee) { 0 } - 1

            val statusMap = status.getOrPut(gift) { mutableMapOf() }
            statusMap[giftee] = statusMap.getOrPut(giftee) { 0 } + 1
        }


        for (i in friends.indices) {
            for (j in i + 1 until friends.size) {
                val friend = friends[i]
                val otherSide = friends[j]

                val aToB = status[friend]?.get(otherSide) ?: 0
                val bToA = status[otherSide]?.get(friend) ?: 0

                if (aToB > bToA)  resultMap.computeIfPresent(friend) { _, value -> value + 1 }
                else if(bToA > aToB) resultMap.computeIfPresent(otherSide) { _, value -> value + 1 }
                else {
                    val aToBGifted = gifted[friend]?:0
                    val bToAGifted = gifted[otherSide] ?: 0

                    if(aToBGifted > bToAGifted) resultMap.computeIfPresent(friend) { _, value -> value + 1 }
                    else if(bToAGifted > aToBGifted)resultMap.computeIfPresent(otherSide) { _, value -> value + 1 }
                }
            }
        }

        return resultMap.values.maxOf { it }
    }
}
