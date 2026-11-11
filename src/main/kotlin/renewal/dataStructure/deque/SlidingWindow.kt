package renewal.dataStructure.deque

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class SlidingWindow {
    /**
     * 📘 실전 문제 – 슬라이딩 윈도우 최대값 (BOJ 11003 변형)
     *
     * 🧾 문제 설명
     *
     * N개의 수 A[1], A[2], …, A[N]이 주어집니다.
     * 이 수열에서 크기가 L인 슬라이딩 윈도우를 오른쪽으로 하나씩 이동시키며,
     * 각 구간의 최솟값을 출력하세요.
     *
     * 덱(Deque)을 사용해서 시간복잡도 O(N)에 해결해야 합니다.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	정수 N, L (1 ≤ L ≤ N ≤ 5,000,000)
     * 	•	수열 A₁ A₂ … Aₙ (각 원소는 절댓값 1,000,000 이하)
     *
     * 📤 출력 형식
     * 	•	한 줄로, 각 슬라이딩 윈도우의 최솟값을 출력 (공백 구분)
     *
     * 	예시
     * 	12 3
     *  1 5 2 3 6 2 3 7 3 5 2 6
     *
     *  출력
     *  1 2 2 2 2 2 3 3 2 2 2 2
     */

    @Test
    fun solution(){
        val N = 12 //arraySize
        val L = 3  //slidingSize
        val array = intArrayOf(1,5,2,3,6,2,3,7,3,5,2,6)
        val expected = intArrayOf(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 2, 2)


        assertArrayEquals(expected, slidingWindowMin(N, L, array))
   }

    /**
     * 	1.	덱은 인덱스를 저장한다.
     * 	2.	덱에는 항상 값의 ‘오름차순’만 유지되도록 조정한다.
     * 	3.	새 값이 들어올 때, 자기보다 큰 값은 덱에서 모두 제거한다.
     * → “나보다 큰 값은 내 뒤에 있으면 의미 없다”
     * 	4.	덱의 첫 번째 요소는 항상 현재 윈도우의 최솟값 인덱스이다.
     * 	5.	윈도우 범위를 벗어난 인덱스는 deque에서 제거한다.
     *
     */
    private fun slidingWindowMin(arraySize: Int, windowSize: Int, array: IntArray): IntArray {
        val result = IntArray(arraySize)
        val deque = ArrayDeque<Int>()

        for (i in 0 until arraySize) {
            // 1. 범위를 벗어난 인덱스 제거 (왼쪽)
            if (deque.isNotEmpty() && deque.first() <= i - windowSize) {
                deque.removeFirst()
            }

            // 2. 현재 값보다 큰 값들은 뒤에서 제거 (오른쪽)
            while (deque.isNotEmpty() && array[deque.last()] >= array[i]) {
                deque.removeLast()
            }

            // 3. 현재 인덱스를 덱에 추가
            deque.addLast(i)

            // 4. 결과에 덱의 front가 가리키는 최솟값 추가
            result[i] = array[deque.first()]
        }

        return result
    }

}