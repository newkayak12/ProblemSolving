package renewal.graph
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TopologicalExample {
    /**
     * 📘 문제: 줄 세우기 (BOJ 2252)
     *
     * ⸻
     *
     * ✅ 문제 설명
     *
     * N명의 학생들을 키 순서대로 일렬로 세우려고 한다.
     * M개의 키 비교 결과가 주어진다.
     * 각 비교는 A가 B보다 앞에 서야 한다는 의미다.
     *
     * 모든 조건을 만족하는 하나의 줄 세우기 결과를 출력하시오.
     *
     * ⸻
     *
     * ✅ 입력 형식
     * 	•	첫 줄: 정수 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)
     * 	•	다음 M줄: A B (A가 B보다 앞에 서야 한다는 의미)
     *
     * ⸻
     *
     * ✅ 출력 형식
     * 	•	조건을 만족하는 학생들의 번호를 순서대로 출력 (여러 답이 가능)
     *
     * ⸻
     *
     * 🧪 입력 예시
     * 3 2
     * 1 3
     * 2 3
     * 🧾 출력 예시
     * 1 2 3
     */
    @Test
    fun solution() {
        val n = 3
        val edges = listOf(
            1 to 2,
            2 to 3,
        )

        val expected = "1 2 3"
        assertEquals(expected, topologicalSort(n, edges))
    }

    fun topologicalSort(element: Int, edges: List<Pair<Int,Int>>):String {
        val inDegree = IntArray(element + 1) { 0 }
        val graph = Array(element + 1){ mutableListOf<Int>() }

        for((inElement, outElement) in edges) {
            inDegree[outElement] ++
            graph[inElement].add(outElement)
        }


        val queue = ArrayDeque<Int>()
        for( i in 1 .. element) {
            if(inDegree[i] == 0) queue.addLast(i)
        }

        val builder = mutableListOf<Int>()
        while(queue.isNotEmpty()) {
            val node = queue.removeLast()
            builder.add(node)

            for( next in graph[node]) {
                inDegree[next] --

                if(inDegree[next] == 0) {
                    queue.addLast(next)
                }

            }
        }

        return if (builder.size == element) {
            builder.joinToString(" ")
        } else {
            "사이클 발생"
        }
    }
}
