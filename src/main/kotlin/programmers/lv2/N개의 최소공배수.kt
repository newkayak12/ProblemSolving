package programmers.lv2

import org.junit.jupiter.api.Test
import java.util.PriorityQueue
import kotlin.test.assertEquals

class `N개의 최소공배수` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12953?language=kotlin
     */

    @Test
    fun solution1() {
        val arr = intArrayOf(2, 6, 8, 14)
        val expected = 168

        assertEquals(expected, calculate(arr))
    }

    @Test
    fun solution2() {
        val arr = intArrayOf(1, 2, 3)
        val expected = 6

        assertEquals(expected, calculate(arr))
    }

    private fun calculate(arr: IntArray): Int {
        val sortedArray = arr.sortedArray()
        val pq = ArrayDeque<Int>()
        pq.addAll(sortedArray.toList())

        while (pq.size > 1) {
            val first = pq.removeFirst()
            val second = pq.removeFirst()

            pq.addFirst((first * second) / gcd(second, first))
        }

        return pq.last()
    }

    private fun gcd(larger: Int, smaller: Int): Int {
        return if (smaller == 0) larger
        else gcd(smaller, larger % smaller)
    }

    /**
     * 전체 GCD 방식:
     *
     * 모든 수의 공통 약수만 추출
     * 각 수의 고유 소인수 관계 무시
     * 단순 곱셈으로는 중복 제거 불가
     *
     * 순차 LCM 방식:
     *
     * 매 단계마다 두 수의 관계를 정확히 계산
     * 소인수 최대 지수가 자동으로 누적
     * lcm(a,b) = a×b/gcd(a,b) 공식이 중복 제거
     */

}