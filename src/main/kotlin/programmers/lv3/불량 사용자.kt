package programmers.lv3

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `불량 사용자` {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val result = mutableListOf<IntArray>()

        useRecursive(
            user_id,
            BooleanArray(user_id.size),
            banned_id.map { it.replace('*', '.').toRegex() }.toTypedArray(),
            0,
            IntArray(banned_id.size),
            result
        )


        return result
            .map { it.sorted() }
            .distinct()
            .size
    }


    fun useRecursive(
        user_id: Array<String>,
        visited: BooleanArray,
        regExps: Array<Regex>,
        containerIndex: Int,
        container: IntArray,
        result: MutableList<IntArray>
    ) {
        if (containerIndex >= container.size) {
            result.add(container.copyOf())
            return
        }

        val regExp = regExps[containerIndex]

        for ((index, value) in user_id.withIndex()) {
            if (visited[index] || !regExp.matches(value)) continue

            visited[index] = true
            container[containerIndex] = index
            useRecursive(user_id, visited, regExps, containerIndex + 1, container, result)
            visited[index] = false
        }
    }

    private fun solutionBackup(user_id: Array<String>, banned_id: Array<String>): Int {
        // 정규 표현식이 겹치는 경우의 수를 확인
        // 정규 표현식에 해당하는 user_id를 모음
        // 각 모음 당 n개를 뽑을 경우의 수를 구한다.


        val regExpToCount = banned_id.groupingBy { it }.eachCount()
        val regExpToMatchedUsers = banned_id.groupBy { it }.mapValues {
            val regExp = it.key.replace('*', '.').toRegex()
            user_id.filter { it.matches(regExp) }
        }

        var size = 0
        for (key in regExpToMatchedUsers.keys) {
            for (innerKey in regExpToMatchedUsers.keys) {
                if (key == innerKey) {
                    continue
                }

                val list = regExpToMatchedUsers.getValue(key).toMutableList()
                val inner: Set<String> = regExpToMatchedUsers.getValue(innerKey).toSet()
                list.retainAll(inner)

                size += list.size
            }
        }

        println(size)

        return regExpToCount.entries.map { (regExp, count) ->
            val list = regExpToMatchedUsers[regExp] ?: emptyList()
            factorial(list.size) / factorial(count)
        }
            .fold(1) { acc, n -> acc * n }
    }

    private fun factorial(number: Int) = (number downTo 1).reduce { acc, it -> acc * it }

    /**
     * 응모자 계정 중 banndedId의 마스킹 처리된 내용과 패턴이 유사한 계정을 뽑고,
     * 그 경우의 수를 구하라
     *
     * 사실 바로 떠오르는건 정규표현식이 있음
     *
     * 같은 정규 표현식에 대한 처리가 필요해보임
     *
     * Hash로 묶고 숫자 계산
     * DFS?
     */
    @Test
    fun test1() {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("fr*d*", "abc1**")
        val result = 2

        assertEquals(result, solution(user_id, banned_id))
    }

    @Test
    fun test2() {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("*rodo", "*rodo", "******")
        val result = 2

        assertEquals(result, solution(user_id, banned_id))
    }

    @Test
    fun test3() {
        val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
        val banned_id = arrayOf("fr*d*", "*rodo", "******", "******")
        val result = 3

        assertEquals(result, solution(user_id, banned_id))
    }

}