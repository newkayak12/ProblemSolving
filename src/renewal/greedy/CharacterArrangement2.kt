package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class CharacterArrangement2 {
    /**
     * 문제: 문자 배치 문제 (Character Arrangement)
     *
     * 서로 다른 문자가 인접하지 않도록 문자열을 재배치하세요.
     * 	•	입력: 소문자 알파벳으로 이루어진 문자열 (최대 길이 1000)
     * 	•	출력: 가능한 재배치 문자열 중 하나 또는 “불가능” (불가능한 경우)
     *
     * ⸻
     *
     * 예시 1
     *
     * 입력
     * aaabbc
     * 출력
     * 가능
     *
     * 예시 2
     *
     * 입력
     * aaaabc
     * 출력
     * 불가능
     */

    @Test
    fun solution1() {
        val chars = "aaabbc"
        val expected = "가능"
        assertEquals(expected, greedy(chars))
    }

    @Test
    fun solution2() {
        val chars = "aaaabc"
        val expected = "불가능"
        assertEquals(expected, greedy(chars))
    }

    private fun greedy(chars: String): String {
        val frequencyMap = chars.fold(mutableMapOf()) { acc: MutableMap<Char, Int>, c: Char ->
            acc[c] = acc.getOrDefault(c, 0) + 1
            acc
        }
        val queue = PriorityQueue<Pair<Char, Int>>(compareByDescending { it.second }, )
        for ((char, frequency) in frequencyMap) {
            queue.add(Pair(char, frequency))
        }

        val builder = StringBuilder()
        var element: Pair<Char, Int>? = null
        while (queue.isNotEmpty()) {
            val (char, count) = queue.poll()

            builder.append(char)

            if(element != null && element.second > 0) {
                queue.offer(element)
            }

            element = if( count - 1 > 0) Pair(char, count - 1) else null
        }


        return if(builder.length == chars.length) "가능" else "불가능"
    }
}