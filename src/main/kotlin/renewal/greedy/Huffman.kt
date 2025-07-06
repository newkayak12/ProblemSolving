package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class Huffman {
    /**
     * <pre>
     * 허프만 코딩 문제
     *
     * 문자들이 주어지고, 각 문자의 등장 빈도가 있습니다.
     * 이 빈도를 이용해 허프만 트리를 만들어 최소한의 인코딩 비용을 계산하려고 합니다.
     * 두 개의 가장 작은 빈도수를 가진 노드를 합치는 과정을 반복하여, 모든 문자를 포함하는 하나의 트리를 만듭니다.
     * 이때, 합치는 과정에서 발생하는 비용은 두 빈도수의 합입니다.
     * 전체 과정에서 발생하는 비용의 총합을 구하세요.
     * ⸻
     * 입력
     * 	•	빈도수를 담은 정수 배열 frequencies[] (1 ≤ 배열 길이 ≤ 1000, 각 빈도는 자연수)
     * ⸻
     * 출력
     * 	•	최소 인코딩 비용의 총합 (정수)
     * ⸻
     * 예제
     *
     * 입력: [3, 1, 2, 4]
     * 출력: 19
     *
     *  > ## 1. 허프만 트리
     *  > 데이터 압축에 사용되는 이진 트리 구조
     *  > 자주 등장하는 문자일수록 짧은 비트 수로, 덜 등장하는 문자는 긴 비트수로 인코딩해서 전체 데이터 크기를 최소화 하는 방식
     *  >
     *  > ## 2. 허프만 트리를 만드는 방법
     *  > 1. 모든 문자를 각각 하나의 노드로 취급하여 우선순위 큐에 넣는다.
     *  > 2. 최소 빈도 두 개를 꺼내서 새로운 부모 노드를 만들고, 두 노드의 빈도 합을 부모 노드의 빈도 합으로 설정한다.
     *  > 3. 새로 만든 부모 노드를 다시 큐에 넣는다.
     *  > 4. 큐에 노드가 하나 남을 때까지 2 ~ 3 반복
     *
     * 	 문자 빈도
     * 	•	a: 5
     * 	•	b: 2
     * 	•	c: 2
     * 	•	d: 1
     * 	•	e: 1
     * 	•	f: 1
     * ⸻
     * 허프만 트리 단계별 생성
     * 	1.	d(1) + e(1) = de(2)
     * 	2.	f(1) + b(2) = fb(3)
     * 	3.	c(2) + de(2) = cde(4)
     * 	4.	fb(3) + cde(4) = fbcde(7)
     * 	5.	a(5) + fbcde(7) = root(12)
     * ⸻
     * 최종 트리 구조
     *             (12)
     *           /     \
     *         (7)       a(5)
     *      /      \
     *    (3)       (4)
     *   /   \     /   \
     * f(1) b(2) c(2) (2)
     *               /   \
     *            d(1)   e(1)
     *
     * 풀이
     * 1 2 3 4
     *
     * 1 + 2 = 3
     * 3 + 3 = 6
     * 4 + 6 = 10
     * </pre>
     * */

    @Test
    fun solution() {
        val input = intArrayOf(3, 1, 2, 4)
        val expected = 19

        assertEquals(expected, greedy(input))
    }

    private fun greedy(input: IntArray): Int {
        input.sort()
        val queue = PriorityQueue<Int>(compareBy { it })
        input.forEach { queue.add(it) }
        var result = 0

        while(queue.size > 1) {
            val sum = queue.poll() + queue.poll()
            result  += sum
            queue.offer(sum)
        }

        return result
    }
}