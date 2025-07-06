package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ShellSort {
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
     *  ✅ Gap = 3
     * 	•	그룹1: [5, 2] → 정렬 후 [2, 4, 10, 5, 8, 6]
     * 	•	그룹2: [4, 8] → 정렬 후 [2, 4, 10, 5, 8, 6] (이미 정렬됨)
     * 	•	그룹3: [10, 6] → 정렬 후 [2, 4, 6, 5, 8, 10]
     *  → ✅ 결과 after gap=3: [2, 4, 6, 5, 8, 10]
     *
     *  ✅ Gap = 1 (마지막 삽입 정렬 단계)
     * 	•	삽입 정렬처럼 한 칸씩 비교하면서 정렬
     *  → [2, 4, 6, 5, 8, 10]
     *  → [2, 4, 5, 6, 8, 10]
     *
     *  ✅ 최종 결과
     *  [2, 4, 5, 6, 8, 10]
     *  ```
     */
    @Test
    fun sort() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()
        var gap = array.size / 2

        while (gap > 0) {
            for( i in gap until array.size) {
                val temp = array[i]
                var j = i - gap
                while(j >= 0 && array[j] > temp) {
                    array[j + gap] = array[j]
                    j -= gap
                }
                array[j + gap] = temp
            }
            gap /= 2
        }

        assertArrayEquals(expected, array)
    }
}