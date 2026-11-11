package programmers.lv2

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class 튜플 {


    @Test
    fun solution1() {
        val s = "{{2},{2,1},{2,1,3},{2,1,3,4}}"
        val expected = intArrayOf(2, 1, 3, 4)

        assertArrayEquals(expected, calculate(s))
    }

    @Test
    fun solution2() {
        val s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"
        val expected = intArrayOf(2, 1, 3, 4)

        assertArrayEquals(expected, calculate(s))
    }

    @Test
    fun solution3() {
        val s = "{{20,111},{111}}"
        val expected = intArrayOf(111, 20)

        assertArrayEquals(expected, calculate(s))
    }

    @Test
    fun solution4() {
        val s = "{{123}}"
        val expected = intArrayOf(123)

        assertArrayEquals(expected, calculate(s))
    }

    @Test
    fun solution5() {
        val s = "{{4,2,3},{3},{2,3,4,1},{2,3}}"
        val expected = intArrayOf(3, 2, 4, 1)

        assertArrayEquals(expected, calculate(s))
    }

    fun calculate(s: String): IntArray {
        val set = s
            .replace("{{", "")
            .replace("}}", "")
            .split("},{")
            .sortedBy { it.length }
        val result = linkedSetOf<Int>()  // 핵심 2: 순서 보장

        set.forEach{
             it.split(",").forEach{ iterate ->
                result.add(iterate.toInt())
            }
        }

        return result.toIntArray()
    }

}