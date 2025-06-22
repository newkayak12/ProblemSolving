package renewal.search

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class BinarySearch {

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


    @Test
    fun `이진탐색`() {
        val list: List<Int> = giveMeArray()
        val find = 10
        val array: IntArray = list.sorted().toIntArray()
        var start = 0
        var end = array.size - 1
        var mid = 0

        do {
            mid = (start + end) / 2

            if (find == array[mid]) break
            else if (find > array[mid]) start = mid + 1
            else end = mid - 1
        } while (start < end)

        if (start == mid) mid = -1
        val expected = if (array.contains(find)) array.indexOf(find) else -1

        println(array.contentToString())
        assertEquals(expected, mid)
    }

    @Test
    fun `lowerBound`() {
        /**
         *  찾고자 하는 값 이상이 처음으로 나타나는 위치
         */

        val array: IntArray = giveMeArray().sorted().toIntArray()
        val find = 7
        var start = 0
        var end = array.size

        while (start < end) {
            val mid = (start + end) / 2
            if (array[mid] < find) {
                start = mid + 1
            } else {
                end = mid
            }
        }

        // Lower Bound는 find 이상인 첫 번째 위치
        val lowerBoundIndex = start

        // Expected는 배열에서 find 이상인 값이 나오는 첫 번째 위치
        val expected = array.indexOfFirst { it >= find }
            .takeIf { it != -1 } ?: array.size

        println("array = ${array.contentToString()}")
        println("lowerBound = $lowerBoundIndex, expected = $expected")
        assertEquals(expected, lowerBoundIndex)
    }

    @Test
    fun `upperBound`() {
        /**
         *  찾고자 하는 값보다 큰 값이 처음으로 나타나는 위치
         */

        val array: IntArray = giveMeArray().sorted().toIntArray()
        val find = 7
        var start = 0
        var end = array.size

        while (start < end) {
            val mid = (start + end) / 2
            if (array[mid] <= find) {
                start = mid + 1
            } else {
                end = mid
            }
        }

        // Upper Bound는 find 초과인 첫 번째 위치
        val upperBoundIndex = start

        // Expected는 배열에서 find 초과인 값이 나오는 첫 번째 위치
        val expected = array.indexOfFirst { it > find }
            .takeIf { it != -1 } ?: array.size

        println("array = ${array.contentToString()}")
        println("upperBound = $upperBoundIndex, expected = $expected")
        assertEquals(expected, upperBoundIndex)
    }
}