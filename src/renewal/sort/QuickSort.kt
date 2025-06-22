package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.Stack

class QuickSort {
    companion object {
        private lateinit var fixtureMonkey: FixtureMonkey;

        @JvmStatic
        @BeforeAll
        fun init() {
            fixtureMonkey = FixtureMonkey.builder()
                .plugin(KotlinPlugin())
                .plugin(SimpleValueJqwikPlugin().minNumberValue(1L).maxNumberValue(10L)).build()
        }
    }

    private fun giveMeArray(): List<Int> {
        return fixtureMonkey.giveMe(10)
    }

    data class Container (
        val left: Int,
        val right: Int,
    ){}
    /**
     * ```
     *  [5, 4, 10, 2, 8, 6] : origin
     *	[5, 4, 6, 2, 8, 10] : 1회전 (pivot = 10, index: (left: 0, right: 5, pivot: (0 + 6) / 2) )
     * 	[5, 4, 2, 6, 8, 10] : 2회전 (왼쪽 부분, pivot = 6, index: ( left: 0, right: 4, pivot : (0 + 4) / 2) )
     * 	[2, 4, 5, 6, 8, 10] : 3회전 (왼쪽 부분, pivot = 4, index: ( left: 0, right: 2, pivot : (0 + 2) / 2) )
     *```
     */
    @Test
    fun sortUseStack() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()
        val stack = Stack<Container>()
        stack.push(Container(0, array.size - 1))

        while(stack.isNotEmpty()) {
            val (left, right) = stack.pop();
            var leftPoint = left
            var rightPoint = right
            var pivotValue = array[(leftPoint + rightPoint) / 2]

            do {
                while (pivotValue > array[leftPoint]) leftPoint += 1;
                while (pivotValue < array[rightPoint]) rightPoint -= 1;

                if (leftPoint <= rightPoint) {
                    val temp = array[leftPoint]
                    array[leftPoint] = array[rightPoint]
                    array[rightPoint] = temp
                    leftPoint += 1
                    rightPoint -= 1
                }

            } while (leftPoint <= rightPoint)

            if(left < rightPoint) {
                stack.push(Container(left, rightPoint))
            }
            if(right > leftPoint) {
                stack.push(Container(leftPoint, right))
            }

        }

        assertArrayEquals(expected, array)
    }


    private fun quickSort(array: IntArray, left: Int, right: Int) {
        var leftPoint = left
        var rightPoint = right
        var pivotValue = array[(leftPoint + rightPoint) / 2]

        do {
            while (pivotValue > array[leftPoint]) leftPoint += 1;
            while (pivotValue < array[rightPoint]) rightPoint -= 1;

            if (leftPoint <= rightPoint) {
                array[leftPoint] = array[rightPoint].also {
                    array[rightPoint] = array[leftPoint]
                }
                leftPoint += 1
                rightPoint -= 1
            }

        } while (leftPoint <= rightPoint)

        if(left < rightPoint) {
            quickSort(array, left, rightPoint)
        }
        if(right > leftPoint) {
            quickSort(array, leftPoint, right)
        }
    }

    @Test
    fun sortUseCallStack() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()

        quickSort(array, 0, array.size - 1)


        assertArrayEquals(expected, array)
    }
}