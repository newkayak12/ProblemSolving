package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class BubbleSort {

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
     * 5, 4, 10, 2, 8, 6 : origin
     * 4, 5, 2, 8, 6, 10 : 1회전
     * 4, 2, 5, 6, 8, 10 : 2회전
     * 2, 4, 5, 6, 8, 10 : 3회전
     */
    @Test
    fun `bubbleSort`() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()

        for (i in 0 until array.size - 1) {
            for (j in 0 until array.size - 1 - i) {
                if (array[j] > array[j + 1]) {
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
        println("before: ${expected.contentToString()}")
        println("after: ${array.contentToString()}")
        assertArrayEquals(expected, array)
    }

}