package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.test.assertEquals

class `광물 캐기` {


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


    fun solutionFailed1(picks: IntArray, minerals: Array<String>): Int {

        /**
         *  상위 광물 곡괭이는 동등 혹은 하위 광물 cost 1
         *  철은 다이아 5
         *  돌은 다이아 25, 철 5
         *
         *  곡괭이 사용 순서 X
         *  곡괭이 사용 가능 횟수 5 /  한 번 선택하면 다써야 한다.
         *
         *  광물 순서는 변경 X
         *
         *  종료 조건은 모두 캐거나 더 이상 못 쓰거나
         *
         *  피로도 리턴
         *
         *  광물 찾는 gap 5 개
         *      이 사이 값에 대해서 다이아, 철, 돌 최솟 값 확인
         *          단 각 곡괭이가 있을 때
         *
         *          최솟 값으로 선정된 곡괭이 사용 처리 및 피로도 증가
         *
         */
        var diamondPickaxeCount = picks[0]
        var ironPickaxeCount = picks[1]
        var stonePickaxeCount = picks[2]

        val diamond = "diamond"
        val iron = "iron"

        var totalFatigue = 0


        var index = 0
        while (
            index < minerals.size &&
            (diamondPickaxeCount != 0 || stonePickaxeCount != 0 || ironPickaxeCount != 0)
        ) {

            val end = if (index + 5 in minerals.indices) index + 5 else minerals.size

            var diamondFatigue = 0
            var ironFatigue = 0
            var stoneFatigue = 0

            for (i in index until end) {
                diamondFatigue += 1
                ironFatigue += if (minerals[i] == diamond) 5 else 1
                stoneFatigue += if (minerals[i] == diamond) 25 else if (minerals[i] == iron) 5 else 1
            }


            index += 5

            if (diamondPickaxeCount > 0) {
                diamondPickaxeCount--
                totalFatigue += diamondFatigue
            } else if (ironPickaxeCount > 0) {
                ironPickaxeCount--
                totalFatigue += ironFatigue
            } else if (stonePickaxeCount > 0) {
                stonePickaxeCount--
                totalFatigue += stoneFatigue
            }

            return totalFatigue
        }

        return totalFatigue
    }

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        /**
         * 그리디?
         */

        var diamondPickaxeCount = picks[0]
        var ironPickaxeCount = picks[1]
        var stonePickaxeCount = picks[2]


        return minerals
            .map {
                when (it) {
                    "diamond" -> 25
                    "iron" -> 5
                    else -> 1
                }
            }
            .chunked(5)
            .take(diamondPickaxeCount + ironPickaxeCount + stonePickaxeCount)
            .map { it.sum() to it }
            .sortedByDescending { it.first }
            .sumOf {
                val (sum, array) = it
                when {

                    diamondPickaxeCount > 0 -> {
                        diamondPickaxeCount--
                        array.size
                    }

                    ironPickaxeCount > 0 -> {
                        ironPickaxeCount--
                        array.sumOf { item -> ceil(item / 5.0).toInt() }
                    }

                    else -> {
                        stonePickaxeCount--
                        sum
                    }
                }
            }
    }
}