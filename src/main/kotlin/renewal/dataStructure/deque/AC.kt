package renewal.dataStructure.deque

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AC {
    /**
     * 📘 문제: AC (BOJ 5430 변형)
     *
     * 🧾 문제 설명
     * R(뒤집기)과 D(버리기)로 이루어진 명령어 문자열과, 정수 배열이 주어집니다.
     * 명령어를 앞에서부터 순서대로 수행하면서 배열에 변화를 적용하세요.
     * 단, 배열이 비어있을 때 D 명령어가 들어오면 에러가 발생합니다.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	첫 줄에 테스트 케이스 T (1 ≤ T ≤ 100)
     * 	•	각 테스트 케이스마다:
     * 	•	명령어 문자열 p (길이 1 ≤ p ≤ 100,000)
     * 	•	정수 n (0 ≤ n ≤ 100,000)
     * 	•	배열: [x₁,x₂,…,xₙ] 형식의 문자열
     *
     * ⸻
     *
     * 📤 출력 형식
     * 	•	명령어 수행 결과 배열을 문자열로 출력
     * 	•	에러 발생 시 "error" 출력
     *
     * ⸻
     *
     * ✅ 예제
     *
     * 입력:
     * 4
     * --- 예제 1
     * 명령어: RDD
     * 배열 길이: 4
     * 배열: [1,2,3,4]
     *
     * --- 예제 2
     * 명령어: DD
     * 배열 길이: 1
     * 배열: [42]
     *
     * --- 예제 3
     * 명령어: RRD
     * 배열 길이: 6
     * 배열: [1,1,2,3,5,8]
     *
     * --- 예제 4
     * 명령어: D
     * 배열 길이: 0
     * 배열: []
     *
     * 출력:
     * --- 출력 1
     * [2,1]
     *
     * --- 출력 2
     * error
     *
     * --- 출력 3
     * [1,2,3,5,8]
     *
     * --- 출력 4
     * error
     */

    @Test
    fun solution1() {
        val command = "RDD"
        val arraySize = 4
        val array = intArrayOf(1,2,3,4)
        val expected = intArrayOf(2,1).contentToString()
        assertEquals(expected, useDeque(command, array))
    }

    @Test
    fun solution2() {
        val command = "DD"
        val arraySize = 1
        val array = intArrayOf(42)
        val expected = "error"
        assertEquals(expected, useDeque(command, array))
    }

    @Test
    fun solution3() {
        val command = "D"
        val arraySize = 6
        val array = intArrayOf(1,1,2,3,5,8)
        val expected = intArrayOf(1,2,3,5,8).contentToString()
        assertEquals(expected, useDeque(command, array))
    }

    @Test
    fun solution4() {
        val command = "RDD"
        val arraySize = 0
        val array = intArrayOf()
        val expected = "error"
        assertEquals(expected, useDeque(command, array))
    }

    private fun useDeque(command: String, array: IntArray): String {
        val deque = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        var leftToRight = true
        val reverse = 'R'
        val delete = 'D'

        for(element in array) {
            deque.addLast(element)
        }

        for(element in command) {
            when(element) {
                reverse -> leftToRight = !leftToRight
                delete -> {
                    if(deque.isEmpty()) return "error"

                    if(leftToRight){
                        deque.removeFirst()
                    }
                    else {
                        deque.removeLast()
                    }
                }
            }
        }

        while(deque.isNotEmpty()) {
            if(leftToRight) {
                result.add(deque.removeFirst())
            }
            else {
                result.add(deque.removeLast())
            }
        }


        return result.toIntArray().contentToString()
    }
}