package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `메뉴 리뉴얼` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/72411
     */


    @Test
    fun solution1() {
        val orders = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH")
        val course = intArrayOf(2, 3, 4)
        val result = arrayOf("AC", "ACDE", "BCFG", "CDE")

        assertArrayEquals(result, calculate(orders, course))
    }

    @Test
    fun solution2() {
        val orders = arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD")
        val course = intArrayOf(2, 3, 5)
        val result = arrayOf("ACD", "AD", "ADE", "CD", "XYZ")

        assertArrayEquals(result, calculate(orders, course))
    }

    @Test
    fun solution3() {
        val orders = arrayOf("XYZ", "XWY", "WXA")
        val course = intArrayOf(2, 3, 4)
        val result = arrayOf("WX", "XY")

        assertArrayEquals(result, calculate(orders, course))
    }

    @Test
    fun solution4() {
        val orders = arrayOf("ABC", "ABC", "AC", "AC", "BCD", "BCE")
        val course = intArrayOf(2)
        val result = arrayOf("AC", "BC")

        assertArrayEquals(result, calculate(orders, course))
    }

    private fun calculate(orders: Array<String>, course: IntArray): Array<String> {
        val table = course.associateWith { it }
            .mapValues { mutableMapOf<String, Int>() }


        for (order in orders) {
            recursive(
                order.toCharArray().sortedArray(),
                course,
                "",
                table,
                0
            )
        }

        return course.flatMap {
            val target = table[it]?: emptyMap<String, Int>()
            val max = target.values.maxOrNull() ?: 0
            target.filter { it.value == max && it.value > 1 }.keys
        }
        .sorted()
        .toTypedArray()
    }

    private fun recursive(
        order: CharArray,
        course: IntArray,
        combination: String,
        memorize: Map<Int, MutableMap<String, Int>>,
        index: Int
    ) {

        if(course.contains(combination.length)) {
            memorize[combination.length]?.merge(combination, 1, Int::plus)
        }

        for (idx in index until order.size) {
            val element = order[idx]
            recursive(order, course, (combination + element), memorize, idx + 1)
        }
    }
}