package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class CharacterArrangement {
    /**
     * 문제: 문자 배치 문제 (Character Arrangement)
     *
     * 문자들이 주어집니다.
     * 서로 다른 문자가 인접하지 않도록 배치할 수 있는지 확인하고,
     * 가능하면 그 중 하나의 배치 결과를 출력하세요.
     * 불가능하면 “불가능”을 출력하세요.
     *
     * ⸻
     *
     * 입력
     * 	•	한 줄에 문자열이 주어집니다. (알파벳 소문자, 길이 최대 1000)
     * ⸻
     *
     * 출력
     * 	•	가능한 배치 문자열 또는 “불가능”
     * ⸻
     *
     * 예시
     * | 입력  | 출력  |
     * | aabb | abab |
     * | aaab | 불가능 |
     *
     */

    @Test
    fun solution() {
        val string = "aabb"
        val expected = "abab"

        assertEquals(expected, greedy(string))
    }

    fun greedy(string: String): String {
        val queue = PriorityQueue<Pair<Char, Int>>(compareByDescending { it.second })
        val frequencyMap = string.fold(mutableMapOf<Char, Int>()) { acc, c ->
            acc[c] = acc.getOrDefault(c, 0) + 1
            acc
        }
        for ((key, value) in frequencyMap) {
            queue.offer(Pair(key, value))
        }

        val builder = StringBuilder()
        var prev: Pair<Char, Int>? = null

        while (queue.isNotEmpty()) {
            val (ch, freq) = queue.poll()
            builder.append(ch)

            if (prev != null && prev.second > 0) {
                queue.offer(prev)
            }

            prev = if(freq - 1 > 0) ch to freq - 1 else null

        }


        return if (builder.length != string.length) "불가능" else builder.toString()
    }
}