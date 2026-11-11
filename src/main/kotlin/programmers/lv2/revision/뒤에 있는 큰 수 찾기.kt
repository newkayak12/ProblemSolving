package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `뒤에 있는 큰 수 찾기` {

    /**
     * 정수로 이루어진 배열 numbers가 있습니다.
     *
     * 배열 의 각 원소들에 대해 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수를 뒷 큰수라고 합니다.
     * 정수 배열 numbers가 매개변수로 주어질 때,
     * 모든 원소에 대한 뒷 큰수들을 차례로 담은 배열을 return 하도록 solution 함수를 완성해주세요.
     *
     * 단, 뒷 큰수가 존재하지 않는 원소는 -1을 담습니다.
     *
     *
     * val arrayDequeue = ArrayDeque()
     *
     *
     * 1. 현재 값이 다음 값보다 작다면 => 현재 값은 다음 값으로 변경해야 한다.
     * 2. 현재 값이 다음 값과 같거나 크면 => 현재 인덱스를 ArrayDeque 에 집어 넣고 기다린다.
     *
     */


    fun calculate(numbers: IntArray): IntArray {
        val arrayDeque = ArrayDeque<Int>()
        val copyOf = numbers.copyOf()


        for( i in 0 until numbers.size) {
            while(arrayDeque.isNotEmpty() && numbers[arrayDeque.last()] < numbers[i]) {
                val index = arrayDeque.removeLast()
                copyOf[index] = copyOf[i]
            }

            arrayDeque.addLast(i)
        }

        while(arrayDeque.isNotEmpty()) {
            val index = arrayDeque.removeLast()
            copyOf[index] = -1
        }
        return copyOf
    }


    @Test
    fun solution1() {
        val numbers = intArrayOf(2, 3, 3, 5)
        val result = intArrayOf(3, 5, 5, -1)

        assertArrayEquals(result, calculate(numbers))
    }


    @Test
    fun solution2() {
        val numbers = intArrayOf(9, 1, 5, 3, 6, 2)
        val result = intArrayOf(-1, 5, 6, 6, -1, -1)

        assertArrayEquals(result, calculate(numbers))
    }



}