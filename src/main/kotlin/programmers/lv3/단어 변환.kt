package programmers.lv3

import org.junit.jupiter.api.Test
import kotlin.collections.ArrayDeque
import kotlin.test.assertEquals

class `단어 변환` {

    /**
     * DFS가 맞을거 같고
     * hit -> hot -> dot -> dog -> cog
     * 앞 뒤 단어 중 한 글자만 달라야한다.
     */

    fun solution(begin: String, target: String, words: Array<String>): Int {
        val visited: MutableMap<String, Boolean> = words.associateWith { false }.toMutableMap()
        val queue = ArrayDeque<Pair<String, Int>>()
        queue.add(begin to 0)


        while (queue.isNotEmpty()) {
            val (current, times) = queue.removeFirst()
            if (current == target) return times
            visited[current] = true

            for (word in words) {
                if (visited[word] == true) continue
                var diff = 0

                for ((index, alphabet) in current.withIndex()) {
                    if (diff > 1) break
                    if (alphabet != word[index]) diff++
                }


                println("diff $diff word $word")
                if (diff == 1) queue.addLast(word to times + 1)
            }
        }

        return 0
    }


    @Test
    fun test1() {
        val begin = "hit"
        val target = "cog"
        val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")
        val result = 4

        assertEquals(result, solution(begin, target, words))
    }

    @Test
    fun test2() {
        val begin = "hit"
        val target = "cog"
        val words = arrayOf("hot", "dot", "dog", "lot", "log")
        val result = 0

        assertEquals(result, solution(begin, target, words))
    }

    @Test
    fun test3() {
        val begin = "hit"
        val target = "hit"
        val words = arrayOf("hot", "dot", "dog", "lot", "log")
        val result = 0

        assertEquals(result, solution(begin, target, words))
    }
}