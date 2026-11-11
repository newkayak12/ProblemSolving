package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.test.assertEquals

class `광물캐기-retry` {
    /**
     * 다이아로는 무조건 다 1
     * 철로는 다이아만 5 나머지 다 1
     * 돌은 25, 5, 1
     *
     * 한 번 집으면 쭉 써야 함
     * 광물 순서는 못 바꿈
     * 곡괭이 휘두를 수 있는 횟수는 5번
     *
     *
     *
     *
     * 1. 결과적으로 광물 배치를 보고 곡괭이 배치를 정해야 한다.
     *
     *
     * 피로도 계산임
     *
     */


    fun solution(picks: IntArray, minerals: Array<String>) = minerals.map { mineral ->
        when (mineral) {
            "stone" -> 1
            "iron" -> 5
            "diamond" -> 25
            else -> 0
        }
    }

        .chunked(5)
        .take(picks.sum())
        .map { it to it.sum() }
        .sortedByDescending { it.second }
        .map {
            val diamondPickaxeCount = picks[0]
            val ironPickaxeCount = picks[1]
            val stonePickaxeCount = picks[2]
            val subList = it.first

            when {
                diamondPickaxeCount > 0 -> {
                    picks[0] -= 1
                    subList.fold(0) { prev, next -> prev + ceil(next.toDouble() / 25).toInt() }
                }

                ironPickaxeCount > 0 -> {
                    picks[1] -= 1
                    subList.fold(0) { prev, next -> prev + ceil(next.toDouble() / 5).toInt() }
                }

                stonePickaxeCount > 0 -> {
                    picks[2] -= 1
                    subList.fold(0) { prev, next -> prev + next }
                }

                else -> 0
            }
        }
        .sum()


    @Test
    fun test1() {
        val picks = intArrayOf(1, 3, 2)
        val minerals = arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
        val result = 12
        assertEquals(result, solution(picks, minerals))
    }


    @Test
    fun test2() {
        val picks = intArrayOf(0, 1, 1)
        val minerals = arrayOf(
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "iron",
            "iron",
            "iron",
            "iron",
            "iron",
            "diamond"
        )
        val result = 50
        assertEquals(result, solution(picks, minerals))
    }
}