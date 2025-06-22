package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class HeapSort {
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

    /**
     * ```
     *  [5, 4, 10, 2, 8, 6] : origin
     *	[10, 8, 5, 2, 4, 6] : 1. MaxHeap:
     *  [8, 6, 5, 2, 4] + [10] : 2. Swap & Heapify
     *  [6, 4, 5, 2] + [8, 10] : 3. Swap & Heapify
     *  [2, 4, 5, 6, 8, 10] : 4. 계속 진행 → 최종
     *```
     */
    @Test
    fun sort() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()

        val size = array.size
        for(i in size / 2 - 1 downTo 0) {
            heapify(array, size, i)
        }
        for(i in size - 1 downTo 1) {
            array[0] = array[i].also { array[i] = array[0] }
            heapify(array, i, 0)
        }

        assertArrayEquals(expected, array)
    }

    fun heapify(array: IntArray, heapSize: Int, root: Int) {
        var largest = root
        val left = 2 * root + 1
        val right = 2 * root + 2


        if(left < heapSize && array[left] > array[largest]) largest = left
        if(right < heapSize && array[right] > array[largest]) largest = right

        if(largest != root) {
            array[root] = array[largest].also { array[largest] = array[root] }
            heapify(array, heapSize, largest)
        }
    }
}