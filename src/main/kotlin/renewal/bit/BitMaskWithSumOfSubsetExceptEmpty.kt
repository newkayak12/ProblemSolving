package renewal.bit

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BitMaskWithSumOfSubsetExceptEmpty {
    /**
     * 🧪 실습 문제 7 – 공집합 제외한 부분집합의 총합 구하기
     *
     * 📘 문제 설명
     *
     * 정수 배열 arr가 주어졌을 때,
     * 공집합을 제외한 모든 부분집합의 원소 합의 총합을 구하세요.
     *
     * ⸻
     *
     * ✅ 입력 예시
     * arr = [1, 2, 3]
     * ✅ 출력 예시
     * 24
     */

    @Test
    fun solution(){
        val arr = listOf(1,2,3)
        val expected = 24

        assertEquals(expected, bitmask(arr))
    }

    private fun bitmask(array: List<Int>): Int {
        var result = 0

        for( i in 0 until (1 shl array.size)) {
            var subsetResult = 0

            for( j in array.indices) {
                if(i and (1 shl j) > 0) {
                    subsetResult += array[j]
                }
            }

            result += subsetResult
        }


        return result
    }
}