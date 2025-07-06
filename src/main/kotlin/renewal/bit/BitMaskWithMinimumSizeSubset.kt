package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BitMaskWithMinimumSizeSubset {
    /**
     * 🧪 실습 문제 6 – 최소 크기 부분집합 (비트마스크 + 조건 필터)
     *
     * 📘 문제 설명
     *
     * 정수 배열 arr와 정수 target이 주어집니다.
     * 합이 target 이상인 부분집합 중에서 원소 개수가 가장 작은 경우의 크기를 출력하세요.
     *
     * ⸻
     *
     * ✅ 입력 예시
     * arr = [5, 1, 3, 4]
     * target = 8
     *
     * ✅ 출력 예시
     * 2
     */

    @Test
    fun solution() {
        val arr = listOf(5,1,3,4)
        val target = 8
        val expected = 2

        assertEquals(expected, bitMask(arr, target))
    }

    private fun bitMask(arr: List<Int>, target: Int): Int {
        var minimum = Int.MAX_VALUE

        for( i in 0 until  (1 shl arr.size)) {
            var count = 0
            var sum = 0
            for(j in arr.indices) {
                if(i and (1 shl j) > 0) {
                    count ++
                    sum += arr[j]
                }
            }

            if(sum >= target) {
                minimum = minOf(minimum, count)
            }
        }

        return minimum
    }
}