package renewal.search

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class LinearSearch  {

    companion object {
        lateinit var fixtureMonkey: FixtureMonkey;

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
    fun `순차 검색`() {
        val list: List<Int> = giveMeArray()
        val find = 10
        val array: IntArray = list.shuffled().toIntArray()
        val size = array.size
        var index = 0



        while( index < size && array[index] != find ) {
            index ++;
        }

        val found = index != array.size
        assertEquals(found, array.contains(find))
    }

    @Test
    fun `보초법`() {
        val list: List<Int> = giveMeArray()
        val find = 10
        val mutableList = list.shuffled().toMutableList()
        mutableList.add(find)
        val array: IntArray = mutableList.toIntArray()

        var index = 0

        while (array[index] != find) {
            index++
        }

        val found = index != array.size - 1
        assertEquals(found, list.contains(find))
    }
}