package renewal.dataStructure.hash

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Marathon {
    /**
     * 기본 문제 1: 완주하지 못한 선수
     * 문제 설명
     * 수많은 마라톤 선수들이 마라톤에 참여했습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주했습니다.
     * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 매개변수로 주어질 때,
     * 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한사항
     *
     * 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
     * completion의 길이는 participant의 길이보다 1 작습니다.
     * 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
     * 참가자 중에는 동명이인이 있을 수 있습니다.
     *
     * 입출력 예
     * participant: ["leo", "kiki", "eden"]
     * completion: ["eden", "kiki"]
     * return: "leo"
     *
     * participant: ["marina", "josipa", "nikola", "vinko", "filipa"]
     * completion: ["josipa", "filipa", "marina", "nikola"]
     * return: "vinko"
     *
     * participant: ["mislav", "stanko", "mislav", "ana"]
     * completion: ["stanko", "ana", "mislav"]
     * return: "mislav"
     */


    @Test
    fun solution1() {
        val participant = arrayOf("leo", "kiki", "eden")
        val completion = arrayOf("eden", "kiki")
        val expected = "leo"

        assertEquals(expected, hashMap(participant, completion))
    }

    @Test
    fun solution2() {
        val participant = arrayOf("marina", "josipa", "nikola", "vinko", "filipa")
        val completion = arrayOf("josipa", "filipa", "marina", "nikola")
        val expected = "vinko"

        assertEquals(expected, hashMap(participant, completion))
    }

    @Test
    fun solution3() {
        val participant = arrayOf("mislav", "stanko", "mislav", "ana")
        val completion = arrayOf("stanko", "ana", "mislav")
        val expected = "mislav"

        assertEquals(expected, hashMap(participant, completion))
    }


    private fun hashMap(participant: Array<String>, completion: Array<String>): String {
        val participantMap: MutableMap<String, Int> = participant
            .groupBy { it }
            .mapValues { entry -> entry.value.size }
            .toMutableMap()

        for (complete in completion) {
            participantMap.computeIfPresent(complete) { _, value -> value - 1 }
        }

        return  participantMap.filterValues { it > 0 }.keys.first()
    }
}