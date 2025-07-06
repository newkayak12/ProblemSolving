package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class RadixSort {
    companion object {
        private lateinit var fixtureMonkey: FixtureMonkey;

        @JvmStatic
        @BeforeAll
        fun init() {
            fixtureMonkey = FixtureMonkey.builder()
                .plugin(KotlinPlugin())
                .plugin(SimpleValueJqwikPlugin().minNumberValue(1L).maxNumberValue(1000L)).build()
        }
    }

    private fun giveMeArray(): List<Int> {
        return fixtureMonkey.giveMe(10)
    }

    /**
     * ```
     *  [5, 4, 10, 2, 8, 6] : origin
     *  [10, 2, 4, 5, 6, 8] : 1의자리 기준
     *  [2, 4, 5, 6, 8, 10] : 10의자리 기준
     * ```
     */
    @Test
    fun sort() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()
        val max = array.maxOrNull() ?: return
        var exp = 1;

        while( max / exp > 0) {
            countingSortByDigit(array, exp)
            exp *= 10
        }

        assertArrayEquals(expected, array)
    }

    private fun countingSortByDigit(array: IntArray, exp: Int) {
        val output = IntArray(array.size)
        val count = IntArray(10)
        //0~10까지 count

        for(i in array.indices) {
            val digit = (array[i]/exp) % 10
            count[digit] ++
        }
        //count를 센다.

        for(i in 1 until 10) {
            count[i] += count[i - 1]
        }
        //누적합을 구한다.
        //[2,1,3]이면
        //[2,3,6]이 된다. -> 좌표가 된다.
        for (i in array.indices.reversed()) {
            val digit = (array[i] / exp) % 10
            output[count[digit] - 1] = array[i]
            count[digit]--
        }
        for(i in array.indices) {
            array[i] = output[i]
        }
    }
}