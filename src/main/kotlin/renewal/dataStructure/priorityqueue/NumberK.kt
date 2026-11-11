package renewal.dataStructure.priorityqueue

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class NumberK {
    /**
     * K번째 수 (프로그래머스)
     * 문제 설명
     * 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
     * commands 배열: [i, j, k] 형태의 명령들이 여러 개
     * 입출력 예
     * array: [1, 5, 2, 6, 3, 7, 4]
     * commands: [[2, 5, 3], [4, 4, 1], [1, 7, 3]]
     * return: [5, 6, 3]
     *
     * 과정:
     * 1. [2,5,3]: 2~5번째 → [5,2,6,3] → 정렬 [2,3,5,6] → 3번째 = 5
     * 2. [4,4,1]: 4번째 → [6] → 정렬 [6] → 1번째 = 6
     * 3. [1,7,3]: 1~7번째 → [1,5,2,6,3,7,4] → 정렬 [1,2,3,4,5,6,7] → 3번째 = 3
     */


    @Test
    fun solution() {
        val array = intArrayOf(1, 5, 2, 6, 3, 7, 4)
        val commands = arrayOf(
            intArrayOf(2, 5, 3),
            intArrayOf(4, 4, 1),
            intArrayOf(1, 7, 3),
        )

        val expected = intArrayOf(5, 6, 3)

        assertArrayEquals(expected, pq(array, commands))
    }

    private fun pq(array: IntArray, commands: Array<IntArray>): IntArray {
        val result = IntArray(commands.size)

        for ((index, command) in commands.withIndex()) {
            val start = command[0]
            val end = command[1]
            val point = command[2]
            var idx = 0
            val pq = PriorityQueue<Int>()


            for(element in array.slice(start - 1..<end)) {
                pq.offer(element)
            }

            while(pq.isNotEmpty()) {
                idx ++
                val poll = pq.poll()
                if(point == idx) {
                    result[index] = poll
                    break
                }
            }
        }

        return result
    }
}