package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `이모티콘 할인행사` {
    /**
     * 1. 이모티콘 플러스 최대화
     * 2. 이모티콘 판매액을 늘림
     *
     * 나는 순서대로 '이모티콘 판매액 늘리기 위해서 계산' -> 특정 조건에 따라 이모티콘 플러스로 최대한 넘긴다.
     *
     *
     *   ( 할인율, 상한 가격 ) = user
     *
     *   할인율 40, 30, 20, 10
     *   -> 가격 당 할인율 계산 미리 해서 넣고
     *   -> DP로 진행
     */

    @Test
    fun test1() {
        val users = arrayOf(intArrayOf(40, 10000), intArrayOf(25, 10000))
        val emoticons = intArrayOf(7000, 9000)
        val result = intArrayOf(1, 5400)

        assertArrayEquals(result, solution(users, emoticons))
    }

    @Test
    fun test2() {
        val users = arrayOf(
            intArrayOf(40, 2900),
            intArrayOf(23, 10000),
            intArrayOf(11, 5200),
            intArrayOf(5, 5900),
            intArrayOf(40, 3100),
            intArrayOf(27, 9200),
            intArrayOf(32, 6900),
        )
        val emoticons = intArrayOf(1300, 1500, 1600, 4900)
        val result = intArrayOf(4, 13860)

        assertArrayEquals(result, solution(users, emoticons))
    }


    fun solution(users: Array<IntArray>, emoticons: IntArray) = dfs(emoticons.map { 0 to it }.toTypedArray(), 0, users)


    private fun dfs(emoji: Array<Pair<Int, Int>>, index: Int, users: Array<IntArray>): IntArray {
        if (emoji.size <= index) return calculate(emoji, users)

        var answer = intArrayOf(0, 0)

        for (ratio in 10..40 step 10) {
            val (_, originalPrice) = emoji[index]
            emoji[index] = ratio to originalPrice
            val result = dfs(emoji, index + 1, users)
            emoji[index] = 0 to originalPrice

            when {
                (result[0] > answer[0]) -> answer = result
                (result[0] == answer[0] && result[1] > answer[1]) -> answer = result
            }
        }



        return answer
    }

    private fun calculate(emoji: Array<Pair<Int, Int>>, users: Array<IntArray>): IntArray {

        var array = intArrayOf(0, 0)

        for (user in users) {
            val userRatioLimit = user[0]
            val userPriceLimit = user[1]

            val sumOfPrice = emoji.sumOf {
                val emojiRatio = it.first
                val emojiOriginalPrice = it.second

                if (emojiRatio < userRatioLimit) 0
                else (emojiOriginalPrice * (100 - emojiRatio)) / 100
            }
            if (sumOfPrice >= userPriceLimit) array[0]++
            else array[1] += sumOfPrice
        }

        return array
    }

}