package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CombinationSum {
    /**
     * 🌟 문제 이름
     *
     * 숫자 조합으로 목표값 만들기 (Combination Sum)
     * ⸻
     * ✅ 문제 설명
     * 정수 배열 candidates와 목표 정수 target이 주어질 때,
     * candidates의 숫자들을 중복해서 골라 합이 target이 되도록 하는 모든 조합을 구하시오.
     *
     * 단, 각 숫자는 무한히 사용 가능하며, 순서만 다르고 내용이 같은 조합은 중복으로 보지 않습니다.
     * ⸻
     * ✅ 입력
     * 	•	정수 배열 candidates (ex: [2, 3, 6, 7])
     * 	•	정수 target (ex: 7)
     * ⸻
     * ✅ 출력
     * 	•	합이 target이 되는 조합들을 리스트로 모두 출력
     * ⸻
     *
     * ✅ 조건 요약
     * 항목         | 제한
     * 배열 길이     | 1 ≤ nums.length ≤ 30
     * 원소 범위     | 1 ≤ nums[i] ≤ 200
     * target 범위  | 1 ≤ target ≤ 500
     * 중복 제거 방식 | 숫자 조합은 정렬된 상태, 순열로 치지 않음
     *
     * ⸻
     *
     * ✅ 예제
     * 입력
     * candidates = [2,3,6,7]
     * target = 7
     *
     * 출력
     * [ [2,2,3], [7] ]
     */

    @Test
    fun solution() {
        val candidates = intArrayOf(2, 3, 6, 7)
        val target = 7
        val expected = setOf(listOf(2, 2, 3), listOf(7))
        val result = mutableSetOf<List<Int>>()

        useBacktracking(candidates, target, 0, 0, mutableListOf(), result)


        assertEquals(expected, result)
    }

    fun useBacktracking(
        candidates: IntArray,
        target: Int, sum: Int,
        startIndex: Int,
        index: MutableList<Int>,
        result: MutableSet<List<Int>>
    ) {

        if (target < sum) return

        if (target == sum) {
            result.add(index.map { candidates[it] }.sorted())
            return
        }

        for (i in startIndex until candidates.size) {
            index.add(i)
            useBacktracking(candidates, target, sum + candidates[i], i, index, result)
            index.removeLast()
        }
    }
}