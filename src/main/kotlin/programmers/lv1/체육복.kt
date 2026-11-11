package programmers.lv1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 체육복 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/42862?language=kotlin
     * 학생 번호 == 체격 순
     * 바로 앞/ 바로 뒷 번호에게만 빌려줄 수 있다.
     */

    @Test
    fun solution1() {
        val n = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(1, 3, 5)
        val expected = 5

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution2() {
        val n = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(3)
        val expected = 4

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution3() {
        val n = 3
        val lost = intArrayOf(3)
        val reserve = intArrayOf(1)
        val expected = 2

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution4() {
        val n = 3
        val lost = intArrayOf(1, 3)
        val reserve = intArrayOf(2)
        val expected = 2

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution5() {
        val n = 3
        val lost = intArrayOf(1, 2, 3)
        val reserve = intArrayOf(2)
        val expected = 1

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution6() {
        val n = 3
        val lost = intArrayOf(1, 2, 3)
        val reserve = intArrayOf(2)
        val expected = 1

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution7() {
        val n = 3
        val lost = intArrayOf(1)
        val reserve = intArrayOf(3, 3, 3)
        val expected = 2

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution8() {
        val n = 3
        val lost = intArrayOf(3)
        val reserve = intArrayOf(3, 3, 3)
        val expected = 3

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution9() {
        val n = 3
        val lost = intArrayOf(3)
        val reserve = intArrayOf(1)
        val expected = 2

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }


    @Test
    fun solution10() {
        val n = 3
        val lost = intArrayOf(2)
        val reserve = intArrayOf()
        val expected = 2

//        assertEquals(expected, calculate1(n, lost, reserve))
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution11() {
        // 여벌 체육복을 가진 학생이 도난당한 경우가 먼저 처리되어야 함
        val n = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(1, 2, 5)
        val expected = 5

        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution12() {
        // 수정된 케이스 - 실제로는 3명만 가능
        val n = 5
        val lost = intArrayOf(1, 3, 5)
        val reserve = intArrayOf(2, 4)
        val expected = 4  // 2->1, 4->3 또는 4->5, 어떻게 해도 3명만 가능

        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution13() {
        // 수정된 케이스 - 실제로는 5명 가능
        val n = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(3, 1)
        val expected = 5  // 1->2, 3->4로 모든 학생이 체육복 가능

        println("calculate2 result with [3,1]: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution14() {
        // 진짜 엣지 케이스 - 그리디 순서가 중요한 경우
        val n = 5
        val lost = intArrayOf(2, 3, 4)
        val reserve = intArrayOf(1, 5)
        val expected = 4  // 1->2, 5->4만 가능, 3번은 구제 불가

        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution15() {
        // 중간 학생이 양쪽을 연결하는 경우
        val n = 5
        val lost = intArrayOf(1, 4)
        val reserve = intArrayOf(2, 3, 5)
        val expected = 5  // 2->1, 3 또는 5가 4에게

        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution16() {
        // 이 케이스가 핵심 문제점을 보여줄 것
        val n = 5
        val lost = intArrayOf(1, 3)
        val reserve = intArrayOf(4, 2)  // 4가 먼저 처리되면 3에게 줌, 2는 1에게 주지만 앞뒤 확인 순서 문제
        val expected = 5

        println("calculate2 result: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution17() {
        // 간단한 디버깅 테스트
        val n = 5
        val lost = intArrayOf(1, 3)
        val reserve = intArrayOf(4, 2)

        println("n=$n, lost=${lost.contentToString()}, reserve=${reserve.contentToString()}")
        println("calculate2 result: ${calculate2(n, lost, reserve)}")
        println("Expected: 4")
    }

    @Test
    fun solution18() {
        // edgeCase3 디버깅
        val n = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(3, 1)

        println("n=$n, lost=${lost.contentToString()}, reserve=${reserve.contentToString()}")
        println("calculate2 result: ${calculate2(n, lost, reserve)}")
        println("Expected: 5")
    }

    @Test
    fun solution19() {
        // 그리디 실패 케이스 - 3번이 2번에게 주면 4번 구제 불가
        val n = 4
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(3)
        val expected = 3  // 3번이 2번 또는 4번 중 하나만 구제 가능

        println("possibleFailCase1: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution20() {
        // 순서에 따른 차이가 날 수 있는 케이스
        val n = 5
        val lost = intArrayOf(1, 3, 5)
        val reserve = intArrayOf(2, 4)
        val expected = 4

        println("possibleFailCase2: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution21() {
        // 프로그래머스 실패 추정 케이스 - 앞번호 우선이 비최적인 경우
        val n = 5
        val lost = intArrayOf(2, 4)
        val reserve = intArrayOf(1, 3, 5)
        // 1->2, 3->4, 5는 사용안함으로 모든 학생이 체육복 있음
        val expected = 5

        println("likelyFailureCase: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution22() {
        // 실제 문제가 될 수 있는 케이스 - 앞번호 우선 때문에 실패
        val n = 3
        val lost = intArrayOf(1, 3)
        val reserve = intArrayOf(2)
        // 2번이 1번 또는 3번 중 하나만 선택 가능, 어느 쪽을 선택해도 결과는 같음
        val expected = 2  // 2번이 1번 또는 3번 중 하나 구제, 총 2명

        println("actualProblemCase: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }

    @Test
    fun solution23() {
        val n = 4
        val lost = intArrayOf(2, 3)
        val reserve = intArrayOf(1, 4)
        val expected = 4

        println("actualProblemCase: ${calculate2(n, lost, reserve)}")
        assertEquals(expected, calculate2(n, lost, reserve))
        assertEquals(expected, calculate3(n, lost, reserve))
        assertEquals(expected, calculate4(n, lost, reserve))
    }


    private fun calculate1(n: Int, lost: IntArray, reserve: IntArray): Int {
        val table = reserve.filter { !lost.contains(it) }.associateWith { 1 }.toMutableMap()
        var answer = lost.size - (reserve.size - table.size)

        for (i in lost) {
            val before = table[i - 1] ?: -1
            if (before != -1) {
                table[i - 1] = -1
                answer -= 1
                continue
            }
            val after = table[i + 1] ?: -1
            if (after != -1) {
                table[i + 1] = -1
                answer -= 1
            }
        }
        return n - answer
    }

    private fun calculate2(n: Int, lost: IntArray, reserve: IntArray): Int {
        val map = (1..n).associateWith { true }.toMutableMap()

        for (l in lost.sortedArray()) {
            map[l] = false
        }

        for (r in reserve.sortedArray()) {
            val i = map[r]
            if (i != null && !i) {
                map[r] = true
                continue
            }

            val before = map[r - 1]
            if (before != null && !before) {
                map[r - 1] = true
                continue
            }

            val after = map[r + 1]
            if (after != null && !after) {
                map[r + 1] = true
            }
        }


        return map.count { entry -> entry.value }
    }

    private fun calculate3(n: Int, lost: IntArray, reserve: IntArray): Int {

        //최대한 많은
        val array = IntArray(n)
        for (l in lost.sortedArray()) {
            array[l - 1]--
        }
        for (r in reserve.sortedArray()) {
            array[r - 1]++
        }

        for (index in array.indices) {
            if (array[index] >= 0 || (index - 1 !in array.indices && index + 1 !in array.indices)) {
                continue
            }

            if (index != 0 && array[index] < 0 && array[index - 1] > 0) {
                array[index]++
                array[index - 1]--
                continue
            }

            if (index != array.lastIndex && array[index] < 0 && array[index + 1] > 0) {
                array[index]++
                array[index + 1]--
                continue
            }
        }

        return array.count { it >= 0 }
    }

    private fun calculate4(n: Int, lost: IntArray, reserve: IntArray): Int {
        val realLost = (lost.toSet() - reserve.toSet()).sorted().toMutableList()
        val realReserve = (reserve.toSet() - lost.toSet()).sorted().toMutableList()
        var result = n

        for (l in realLost) {
            when {
                l - 1 in realReserve -> realReserve.remove(l - 1)
                l + 1 in realReserve -> realReserve.remove(l + 1)
                else -> result -= 1
            }
        }

        return result
    }
}