package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `이모티콘 할인 행사` {
    /**
     * 1. 서비스 가입자 최대화
     * 2. 판매액 최대화
     *
     * n명에 m개 할인 판매
     * 10, 20, 30, 40
     *
     *
     * 기준에 따라 일정 비율 이상 할인하는 이모티콘 모두 구매
     * 자신 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이면 이모티콘 모두 취소 -> 이모티콘 플러스 서비스 가입
     */

    @Test
    fun solution1() {
        val users = arrayOf(intArrayOf(40, 10000), intArrayOf(25, 10000))
        val emoticons = intArrayOf(7000, 9000)
        val results = intArrayOf(1, 5400)

        assertArrayEquals(results, calculate(users, emoticons))
    }

    @Test
    fun solution2() {

        // 비율, 가격
        val users = arrayOf(
            intArrayOf(40, 2900),
            intArrayOf(23, 10000),
            intArrayOf(11, 5200),
            intArrayOf(5, 5900),
            intArrayOf(40, 3100),
            intArrayOf(27, 9200),
            intArrayOf(32, 6900),
        )
        //가격
        val emoticons = intArrayOf(1300, 1500, 1600, 4900)
        //서비스, 매출
        val results = intArrayOf(4, 13860)

        assertArrayEquals(results, calculate(users, emoticons))
    }

    private fun returnUserStatus(emoticons: Array<Pair<Int, Int>>, users: Array<IntArray>): IntArray {
        val result = intArrayOf(0, 0)

        for (user in users) {
            val userWantedDiscountPercentage = user[0]
            val userBudgetLimit = user[1]

            val totalPrice = emoticons.map {
                val discountPercentage = it.first
                val originalPrice = it.second

                if (userWantedDiscountPercentage > discountPercentage) 0
                else originalPrice * (100 - discountPercentage) / 100
            }
                .sum()

            if (userBudgetLimit <= totalPrice) result[0]++
            else result[1] += totalPrice
        }

        return result
    }

    private fun calculatePrice(emoticons: Array<Pair<Int, Int>>, index: Int, users: Array<IntArray>): IntArray {
        if (emoticons.size <= index) return returnUserStatus(emoticons, users)

        var answer = intArrayOf(0, 0)

        for (i in 10..40 step 10) {
            emoticons[index] = i to emoticons[index].second
            val result = calculatePrice(emoticons, index + 1, users)
            emoticons[index] = 0 to emoticons[index].second


            when {
                answer[0] < result[0] -> answer = result
                answer[0] == result[0] && answer[1] <= result[1] -> answer = result
            }
        }
        return answer;
    }


    fun calculate(users: Array<IntArray>, emoticons: IntArray): IntArray {
        val manipulatedEmoticons = emoticons.map { 0 to it }.toTypedArray()
        return calculatePrice(manipulatedEmoticons, 0, users)
    }

}