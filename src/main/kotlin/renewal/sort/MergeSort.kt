package renewal.sort

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMe
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.Stack

class MergeSort {
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
     * 	[5,4,10] | [2,8,6] : 분할
     * 	[5,4] [10] | [2,8] [6] : 분할
     * 	[4,5] [10] | [2,8] [6] : 병합1
     * 	[4,5,10] | [2,6,8] : 병합2
     * 	[2,4,5,6,8,10] : 병합3
     *```
     */

    data class Container(
        val start: Int,
        val end: Int,
        val needToMerge: Boolean
    ) {

    }

    @Test
    fun sortUseStack() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()
        val stack = Stack<Container>()
        stack.push(Container(0, array.size - 1, false))


        while (stack.isNotEmpty()) {
            val (start, end, needToMerge) = stack.pop()

            if (needToMerge) {
                val mid = (start + end) / 2
                val tempArray = mutableListOf<Int>()
                var i = start
                var j = mid + 1

                //	•	array[start..mid]와 array[mid+1..end]는 이미 정렬된 상태
                //	•	이 두 구간을 하나의 정렬된 구간 [start..end]로 만들기 위함
                while (i <= mid && j <= end) {
                    if (array[i] <= array[j]) tempArray.add(array[i++])
                    else tempArray.add(array[j++])

                    //	•	왼쪽 포인터 i는 start부터, 오른쪽 포인터 j는 mid+1부터 시작
                    //	•	두 값을 비교해 더 작은 값을 temp 리스트에 추가
                    //	•	추가한 쪽의 포인터를 하나 증가시킴
                    //	•	이 과정을 두 포인터 중 하나가 끝날 때까지 반복
                }
                while (i <= mid) tempArray.add(array[i++])
                //	왼쪽 구간에 값이 남은 경우, 남은 값을 전부 temp에 추가
                while (j <= end) tempArray.add(array[j++])
                //오른쪽 구간에 값이 남은 경우, 남은 값을 전부 temp에 추가

                for ((key, value) in tempArray.withIndex()) {
                    array[start + key] = value
                }

                continue
            }
            if (start >= end) continue

            val mid = (start + end) / 2
            //분할을 위한 모집
            stack.push(Container(start, end, true))

            //다음 분할을 위해서 저장
            //결국은 위의 스택에 들어가면서 정렬할 것
            stack.push(Container(mid + 1, end, false))
            stack.push(Container(start, mid, false))
        }



        assertArrayEquals(expected, array)
    }

    @Test
    fun sortUseCallStack() {
        val list = giveMeArray()
        val expected = list.sorted().toIntArray()
        val array = list.toIntArray()

        mergeSort(array, 0, array.size - 1)
        assertArrayEquals(expected, array)
    }

    fun merge(array: IntArray, start: Int, mid: Int, end: Int): Unit {
        var tempArray = mutableListOf<Int>()
        var i = start
        var j = mid + 1
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) tempArray.add(array[i++])
            else tempArray.add(array[j++])
        }
        while (i <= mid) tempArray.add(array[i++])
        while (j <= end) tempArray.add(array[j++])
        for ((key, value) in tempArray.withIndex()) {
            array[start + key] = value
        }
    }

    fun mergeSort(array: IntArray, start: Int, end: Int): Unit {
        if (start < end) {
            val mid = (start + end) / 2
            mergeSort(array, start, mid)
            mergeSort(array, mid + 1, end)

            merge(array, start, mid, end)
        }
    }
}