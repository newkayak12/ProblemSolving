package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SelectionSort {

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
     *  [5, 4, 10, 2, 8, 6] : origin
     * 	[2, 4, 10, 5, 8, 6] : 1회전
     * 	[2, 4, 10, 5, 8, 6] : 2회전
     * 	[2, 4, 5, 10, 8, 6] : 3회전
     * 	[2, 4, 5, 6, 8, 10] : 4회전
     * 	[2, 4, 5, 6, 8, 10] : 5회전
     */
    @Test
    fun sort() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()

        for( i in 0 until array.size - 1) {
            var minimumIndex = i
            for(j in i + 1  until array.size) {
                if(array[minimumIndex] > array[j]) {
                    minimumIndex = j
                }
            }



            val swap = array[i]
            array[i] = array[minimumIndex]
            array[minimumIndex] = swap
        }

        assertArrayEquals(expected, array)
    }
}