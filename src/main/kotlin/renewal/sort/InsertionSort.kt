package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class InsertionSort {

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
     *
     *  ```
     *  [5, 4, 10, 2, 8, 6] : origin
     * 	[4, 5, 10, 2, 8, 6] : 1회전
     * 	[4, 5, 10, 2, 8, 6] : 2회전
     * 	[2, 4, 5, 10, 8, 6] : 3회전
     * 	[2, 4, 5, 8, 10, 6] : 4회전
     * 	[2, 4, 5, 6, 8, 10] : 5회전
     *```
     * - 배열을 왼쪽부터 차례로 순회하며, 각 원소를 앞쪽 정렬된 부분 배열에 **“삽입”**해나가는 방식의 정렬.
     * - 앞쪽은 항상 정렬되어 있다는 가정 하에, 현재 원소가 들어갈 위치를 찾아 끼워넣음.
     * - 정렬된 영역을 유지하면서 점진적으로 전체 배열을 정렬시킴.
     */
    @Test
    fun sort() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()



        for (i in 1 until array.size) {
            val temp = array[i]
            var j = i - 1
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = temp
        }

        assertArrayEquals(expected, array)
    }
}