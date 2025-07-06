package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertEquals

class BitMaskWithSubset {
    /**
     * 🧪 예제 문제 4 – 비트마스크로 부분 집합 만들기
     *
     * 📘 문제 설명
     *
     * 정수 배열 arr가 주어졌을 때,
     * 이 배열의 모든 부분 집합을 비트마스크를 이용하여 생성하세요.
     *
     * 각 인덱스를 하나의 비트로 보고,
     * 비트가 1이면 포함, 0이면 미포함으로 간주합니다.
     *
     * ⸻
     *
     * ✅ 예시
     * arr = [1, 2, 3]
     *
     * 출력
     * []
     * [1]
     * [2]
     * [1, 2]
     * [3]
     * [1, 3]
     * [2, 3]
     * [1, 2, 3]
     */

    @Test
    fun solution() {
        val arr = listOf(1, 2, 3)
        val expectedSubSets = """
            []
            [1]
            [2]
            [1, 2]
            [3]
            [1, 3]
            [2, 3]
            [1, 2, 3]
        """.trimIndent()

        assertEquals(expectedSubSets, subset(arr))
    }

    private fun subset(arr: List<Int>): String {
        val builder = StringBuilder();
        for (i in 0 until 2.0.pow(arr.size).toInt()) {
            val subSet = mutableListOf<Int>()

            for (num in arr.indices) {
                if (i and (1 shl num) > 0) {
                    subSet.add(arr[num])
                }
            }


            builder.append(subSet)
            builder.append("\n")
        }


        return builder.toString().trimIndent()
    }
}