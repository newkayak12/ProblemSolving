package renewal.dataStructure.deque

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class BasicSlidingWindow {
    /**
     * 📘 문제: 슬라이딩 윈도우의 최솟값 (정석, 일반적 스타일)
     *
     * 🧾 문제 설명
     *
     * 정수 배열 nums와 정수 k가 주어집니다.
     * 길이 k의 슬라이딩 윈도우를 오른쪽으로 한 칸씩 이동시키면서,
     * 각 윈도우에서 최솟값을 구하여 리스트로 반환하세요.
     *
     * ⸻
     *
     * 📥 입력
     * 	•	정수 배열 nums: 길이 N (1 ≤ N ≤ 10⁶)
     * 	•	윈도우 크기 k: 1 ≤ k ≤ N
     *
     * ⸻
     *
     * 📤 출력
     * 	•	각 슬라이딩 윈도우의 최솟값을 순서대로 담은 정수 리스트
     *
     * ⸻
     *
     * ✅ 예제 1
     *
     * 입력:
     * nums = [1, 3, -1, -3, 5, 3, 6, 7]
     * k = 3
     * 출력:
     * [-1, -3, -3, -3, 3, 3]
     */


    @Test
    fun solution(){
        val numbs = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7)
        val windowSize = 3
        val expected = intArrayOf(-1, -3, -3, -3, 3, 3)

        assertArrayEquals(expected, useSlidingWindow(numbs, windowSize))
    }


    private fun useSlidingWindow(number: IntArray, windowSize: Int): IntArray {
        val resultList = mutableListOf<Int>()
        val deque = ArrayDeque<Int>()

        for(i in number.indices) {
            while(deque.isNotEmpty() && deque.first() <= i - windowSize) {
                deque.removeFirst()
            }
            while(deque.isNotEmpty() && number[deque.last()] >= number[i]){
                deque.removeLast()
            }

            deque.addLast(i)
            if(i - windowSize + 1 >= 0){
                resultList.add(number[deque.first()])
            }

        }



        return resultList.toIntArray()
    }
}