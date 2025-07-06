package renewal.tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiameterOfTree {
    /**
     * 🧪 실습 문제
     *
     * 🎯 [문제] 트리의 지름 구하기
     *
     * N개의 노드로 이루어진 트리가 있다.
     * 트리는 간선마다 가중치가 있고, 연결된 두 노드와 거리(가중치)가 주어진다.
     * 이 트리의 지름(가장 긴 경로의 거리)을 구하시오.
     *
     * 🔸 입력 형식
     * 	•	첫 줄: 노드의 수 N (2 ≤ N ≤ 10,000)
     * 	•	둘째 줄부터 N-1개 줄:
     *   u v w → 노드 u와 v가 거리가 w인 간선으로 연결됨
     *
     * 🔸 출력 형식
     * 	•	트리의 지름 (가장 먼 두 노드 사이의 거리)
     * 🔹 예제 입력
     * ```text
     * 5
     * 1 2 3
     * 1 3 2
     * 3 4 4
     * 3 5 6
     * ```
     *
     * 🔹 예제 출력
     * 11
     */

    @Test
    fun solution() {
        val numberOfNode = 5
        val nodes = listOf(
            Triple(1, 2, 3),
            Triple(1, 3, 2),
            Triple(3, 4, 4),
            Triple(3, 5, 6),
        )
        val expected = 11

        assertEquals(expected, diameterUseRecursive(5, nodes))
        assertEquals(expected, diameterUseStack(5, nodes))
    }

    private inner class Node(
        val node: Int,
        val score: Int
    )


    private fun diameterUseRecursive(numberOfNode: Int, table: List<Triple<Int, Int, Int>>): Int {
        val graph = Array<MutableList<Node>>(numberOfNode + 1) { mutableListOf() }

        for ((parent, child, score) in table) {
            graph[parent].add(Node(child, score))
            graph[child].add(Node(parent, score))
        }


        val (deepestIndex, _) = recursive(1, graph, BooleanArray(numberOfNode + 1) {
            it == 1
        }, 0)

        val (_, farthestScore) = recursive(deepestIndex, graph, BooleanArray(numberOfNode + 1) {
            it == deepestIndex
        }, 0)

        return farthestScore
    }

    private fun recursive(
        node: Int,
        graph: Array<MutableList<Node>>,
        visited: BooleanArray,
        score: Int
    ): Pair<Int, Int> {
        val now = graph[node]
        var index = node
        var maxScore = score

        for (element in now) {

            if (visited[element.node]) continue
            visited[element.node] = true
            val (nextIndex, nextScore) = recursive(element.node, graph, visited, score + element.score)
            if (maxScore < nextScore) {
                maxScore = nextScore
                index = nextIndex
            }

        }

        return index to maxScore
    }

    private fun diameterUseStack(numberOfNode: Int, table: List<Triple<Int, Int, Int>>): Int {
        val graph = Array<MutableList<Node>>(numberOfNode + 1) { mutableListOf() }

        for ((parent, child, score) in table) {
            graph[parent].add(Node(child, score))
            graph[child].add(Node(parent, score))
        }


        val (deepestIndex, _) = stack(numberOfNode, 1, graph, 0)

        val (_, farthestScore) = stack(numberOfNode, deepestIndex, graph, 0)

        return farthestScore
    }

    private fun stack(
        numberOfNode: Int,
        startNodeIndex: Int,
        graph: Array<MutableList<Node>>,
        score: Int
    ): Pair<Int, Int> {
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addLast(startNodeIndex to score)

        val visited = BooleanArray(numberOfNode + 1) { it == startNodeIndex }
        var deepestScore = score
        var deepestIndex = startNodeIndex

        while (stack.isNotEmpty()) {
            val (node, score) = stack.removeLast()

            for (next in graph[node]) {

                if (visited[next.node]) continue
                if (deepestScore < next.score + score) {
                    deepestScore = next.score + score
                    deepestIndex = next.node
                }
                stack.addLast(next.node to next.score + score)
                visited[next.node] = true

            }
        }


        return deepestIndex to deepestScore
    }
}