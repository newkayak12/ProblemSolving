package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BitMaskWithSumOfSubset {
    /**
     * 🧪 예제 문제 5 – 비트마스크로 최소 부분집합 합 구하기
     *
     * 📘 문제 설명
     *
     * 정수 배열 arr와 정수 target이 주어졌을 때,
     * 합이 target 이상이 되는 부분집합 중 가장 작은 합을 구하세요.
     *
     * ⸻
     *
     * ✅ 입력 예시
     * arr = [5, 1, 3, 4]
     * target = 8
     * ✅ 출력 예시
     * 8
     */
    private fun bitMask(arr: List<Int>, target: Int): Int {
        var number = Int.MAX_VALUE

        for (i in 0 until (1 shl arr.size)) {
            var sum = 0

            for (j in arr.indices) {
                if (i and (1 shl j) > 0) {
                    sum += arr[j]
                }
            }


            if (sum >= target) {
                number = minOf(number, sum)
            }
        }

        return number
    }
    @Test
    fun solution() {
        val arr = listOf(5, 1, 3, 4)
        val target = 8
        val expected = 8

        assertEquals(expected, bitMask(arr, target))
    }


}