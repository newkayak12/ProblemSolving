package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UnboundedBackpack {
    /**
     * 🧪 예시 문제 (Unbounded)
     *
     * 당신은 무게 제한 W가 있는 배낭을 가지고 있고,
     * N개의 물건을 가지고 있다.
     * 각 물건은 무게와 가치가 있고, 무제한으로 담을 수 있다.
     * 최대 가치를 구하시오.
     * ```kotlin
     * val items = arrayOf(
     *     intArrayOf(2, 3),
     *     intArrayOf(3, 4),
     *     intArrayOf(4, 5)
     * )
     * val W = 7
     * ```
     */

    @Test
    fun solution(){
        val items = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 5)
        )
        val W = 7
        val expected = 10

            assertEquals(expected, unboundedTwoDimension(W, items))
            assertEquals(expected, unboundedOneDimension(W, items))

    }

    private fun unboundedTwoDimension(W: Int, items: Array<IntArray>): Int {
        val table = Array(items.size + 1) {IntArray(W + 1)}

        for(i in 1 .. items.size) {
            val (weight, value) = items[i - 1]

            for (j in 0 .. W) {
                table[i][j] = if(j < weight)  table[i - 1][j]
                else {
                    maxOf(table[i - 1][j], table[i][j - weight] + value)
                }
            }
        }

        return table[items.size][W]
    }

    private fun unboundedOneDimension(W: Int, items: Array<IntArray>): Int {
        val table = IntArray(W + 1)

        for(i in 1 .. items.size) {
            val (weight, value) = items[i - 1]
            for (j in weight..W) {
                table[j] = maxOf(table[j], table[j - weight] + value)
            }
        }

        return table.maxOrNull() ?: 0
    }
}