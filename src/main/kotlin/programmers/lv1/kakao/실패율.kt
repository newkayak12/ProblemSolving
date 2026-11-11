package programmers.lv1.kakao

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class 실패율 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42889?language=kotlin
     *
     * 실패율 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
     * N : 스테이지
     * stages[] : 각 사용자 현재 스테이지 현황 - 도전 중 (N + 1: 클리어)
     * 1 ~ N까지의 stages: 실패율
     */

    @Test
    fun solution1() {
        val N = 5
        val stages = intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)
        val expected = intArrayOf(3, 4, 2, 1, 5)

        assertArrayEquals(expected, try1(N, stages))
        assertArrayEquals(expected, try2(N, stages))
    }

    @Test
    fun solution2() {
        val N = 4
        val stages = intArrayOf(4, 4, 4, 4, 4)
        val expected = intArrayOf(4, 1, 2, 3)

        assertArrayEquals(expected, try1(N, stages))
        assertArrayEquals(expected, try2(N, stages))
    }

    private fun try1(N: Int, stages: IntArray): IntArray {
        var total = stages.size.toDouble()
        //TIMEOUT! stages.associateBy
        val clear = (1..N).associateBy({ it: Int -> it }) { number -> stages.count { it -> it == number } }
        val result = Array(N) { i: Int ->
            val count = clear[i + 1] ?: 0
            val result = if (count == 0) 0.0 else count / total
            total -= count

            i to result
        }


        return result.sortedByDescending { it.second }
            .map { it.first + 1 }
            .toIntArray()
    }

    private fun try2(N: Int, stages: IntArray): IntArray {
        var stagesCount = stages.size.toDouble()
        val map = mutableMapOf<Int, Int>()

        for (i in stages) {
            map[i] = (map[i] ?: 0) + 1
        }

        val map2 = mutableMapOf<Int, Double>()
        for (i in 1..N) {
            val count = map[i]
            if (count == null) {
                map2[i] = 0.0
                continue
            }

            map2[i] = count / stagesCount
            stagesCount -= count
        }

        return map2.keys.sortedByDescending { map2[it] ?: 0.0 }.toIntArray()
    }
}