package programmers.lv1.kakao

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `신고 결과 받기` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/92334?language=kotlin
     */


    @Test
    fun solution1() {
        val id_list = arrayOf("muzi", "frodo", "apeach", "neo")
        val report = arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi")
        val k = 2

        val expected = intArrayOf(2, 1, 1, 0)

        assertArrayEquals(expected, calculate1(id_list, report, k))
        assertArrayEquals(expected, calculate2(id_list, report, k))
        assertArrayEquals(expected, calculate3(id_list, report, k))


    }

    @Test
    fun solution2() {
        val id_list = arrayOf("con", "ryan")
        val report = arrayOf("ryan con", "ryan con", "ryan con", "ryan con")
        val k = 3

        val expected = intArrayOf(0, 0)

        assertArrayEquals(expected, calculate1(id_list, report, k))
        assertArrayEquals(expected, calculate3(id_list, report, k))


    }

    private fun calculate1(id_list: Array<String>, report: Array<String>, k: Int): IntArray {

        val reportMap: Map<String, MutableSet<String>> = id_list.associateWith { mutableSetOf() }
        val reportCount = id_list.associateWith { 0 }.toMutableMap()
        val list = mutableListOf<String>()



        for (target in report) {
            val split = target.split(" ")
            if (reportMap[split[1]]!!.contains(split[0])) continue
            reportMap[split[1]]!!.add(split[0])
            reportCount.computeIfPresent(split[1]) { _, value -> value + 1 }
        }

        for ((key, value) in reportCount) {
            if (value >= k) list.addAll(reportMap[key] ?: emptySet())
        }



        return id_list.map { key -> list.count { it == key } }
            .toIntArray()
    }

    private fun calculate2(id_list: Array<String>, report: Array<String>, k: Int): IntArray {

        val reportMap = mutableMapOf<String, MutableSet<String>>()

        for (target in report) {
            val (reporter, reported) = target.split(" ")
            reportMap.getOrPut(reported) { mutableSetOf() }.add(reporter)
        }

        val result = reportMap
            .filter { it.value.size >= k }
            .flatMap { it.value }


        return id_list.map { user -> result.count { it == user } }.toIntArray()
    }

    private fun calculate3(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val userIndexMap = id_list.withIndex().associate { it.value to it.index }
        val reportSet = mutableSetOf<String>()
        val reportCount = mutableMapOf<String, Int>()

        // 중복 제거하며 카운팅
        for (target in report) {
            if (reportSet.add(target)) {
                val reported = target.substringAfter(" ")
                reportCount[reported] = (reportCount[reported] ?: 0) + 1
            }
        }

        val result = IntArray(id_list.size)

        for (target in reportSet) {
            val (reporter, reported) = target.split(" ")
            if ((reportCount[reported] ?: 0) >= k) {
                userIndexMap[reporter]?.let { result[it]++ }
            }
        }

        return result

    }
}