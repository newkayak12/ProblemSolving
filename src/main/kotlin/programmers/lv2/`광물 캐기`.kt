package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.ceil
import kotlin.test.assertEquals

class `광물 캐기` {

    @Test
    fun solution1() {
        //diamond, iron, stone
        val picks = intArrayOf(1, 3, 2)
        val minerals = arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
        val result = 12


        assertEquals(result, calculate(picks, minerals))
    }

    @Test
    fun solution2() {
        //diamond, iron, stone
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

        assertEquals(result, calculate(picks, minerals))
    }

    @Test
    fun solution3() {
        //diamond, iron, stone
        val picks = intArrayOf(100, 100, 100)
        val minerals = arrayOf(
            "diamond",
            "diamond",
        )
        val result = 2

        assertEquals(result, calculate(picks, minerals))
    }

    @Test
    fun solution4() {
        //diamond, iron, stone
        val picks = intArrayOf(0, 0, 1)
        val minerals = arrayOf(
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "diamond",
        )
        val result = 125

        assertEquals(result, calculate(picks, minerals))
    }

    @Test
    fun solution5() {
        //diamond, iron, stone
        val picks = intArrayOf(0, 0, 0)
        val minerals = arrayOf(
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "diamond",
        )
        val result = 0

        assertEquals(result, calculate(picks, minerals))
    }

    @Test
    fun solution6() {
        //diamond, iron, stone
        val picks = intArrayOf(0, 1, 0)
        val minerals = arrayOf(
            "diamond",
            "iron",
            "iron",
            "diamond",
            "diamond",

            "diamond",
            "iron",
            "diamond",
            "diamond",
            "diamond",
        )
        val result = 21

        assertEquals(result, calculate(picks, minerals))
    }

    @Test
    fun solution7() {
        val picks = intArrayOf(1, 1, 0)
        val minerals = arrayOf(
            "diamond", "diamond", "diamond", "diamond", "diamond",
            "stone", "stone", "stone", "stone", "stone",
            "diamond", "diamond", "diamond", "diamond", "diamond",
        )
        val result = 10
        assertEquals(result, calculate(picks, minerals))
    }


    // Greedy이긴 한데;;
    /**
     * 5개씩 묶어서 광석을 캤을 때의 cost를 바탕으로
     * Greedy를 진행하면 될 것 같은데?
     */

    fun calculate(picks: IntArray, minerals: Array<String>): Int {
        var diamond = picks[0]
        var iron = picks[1]
        var stone = picks[2]

        return minerals.map {
            when (it) {
                "diamond" -> 25
                "iron" -> 5
                else -> 1
            }
        }
            .chunked(5)
//            .take(diamond + iron + stone)
            .map { Triple(it.sum(), it.size, it) }
            .sortedByDescending { it.first }
            .map {
                val (sum, count, arr) = it

                when {
                    diamond > 0 -> {
                        diamond--
                        count
                    }

                    iron > 0 -> {
                        iron--
                        arr.map { ceil(it / 5.0).toInt() } .sum()
                    }

                    stone > 0 -> {
                        stone--
                        sum
                    }

                    else -> {
                        0
                    }
                }
            }
            .sum()
    }
}