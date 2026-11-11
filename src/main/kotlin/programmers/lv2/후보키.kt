package programmers.lv2

import java.util.concurrent.CopyOnWriteArrayList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 후보키 {

    @Test
    fun solution1() {
        val relation = arrayOf(
            arrayOf("100", "ryan", "music", "2"),
            arrayOf("200", "apeach", "math", "2"),
            arrayOf("300", "tube", "computer", "3"),
            arrayOf("400", "con", "computer", "4"),
            arrayOf("500", "muzi", "music", "3"),
            arrayOf("600", "apeach", "music", "2"),
        )

        val result = 2

        assertEquals(result, calculate(relation))
    }

    @Test
    fun solution2() {
        val relation = arrayOf(
            arrayOf("100", "1ryan", "music", "2"),
            arrayOf("200", "2apeach", "math", "2"),
            arrayOf("300", "3tube", "computer", "3"),
            arrayOf("400", "4con", "computer", "4"),
            arrayOf("500", "5muzi", "music", "3"),
            arrayOf("600", "6apeach", "music", "2"),
        )

        val result = 2

        assertEquals(result, calculate(relation))
    }




    private fun isDuplicated(relation: Array<Array<String>>, indices: List<Int>): Boolean {
        val concat = relation.map { row -> indices.map { index -> row[index] }.joinToString() }

        return concat.size != concat.toSet().size
    }

    fun calculate(relation: Array<Array<String>>): Int {
        var answer = 0
        val queue = ArrayDeque<List<Int>>()
        val list = mutableListOf<List<Int>>()

        for (index in relation.first().indices) {
            queue.add(listOf(index))
        }


        while (queue.isNotEmpty()) {
            val indexSet = queue.removeFirst()
            if (list.any { indexSet.containsAll(it) }) continue
            if (!isDuplicated(relation, indexSet)) {
                list.add(indexSet)
                answer ++
                continue
            }

            println(indexSet)

            val lastIndex = indexSet.last()

            for (i in lastIndex + 1 until relation.first().size) {
                val copy = indexSet.toMutableList()
                copy.add(i)
                queue.addLast(copy)
            }
        }


        return answer
    }
}