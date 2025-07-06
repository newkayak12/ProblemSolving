package renewal.tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DistanceDFS {
    /**
     * 🧪 실습 문제 제시
     *
     * 🎯 [문제] 노드 간 거리 계산
     *
     * 트리가 주어진다. 이후 Q개의 쿼리가 주어진다.
     * 각 쿼리는 두 정점 u, v로 주어지며, 두 정점 사이의 거리를 출력하라.
     *
     * 🔸 입력
     * 	•	첫 줄: N (노드 수), Q (쿼리 수)
     * 	•	이후 N-1줄: 간선 정보 → u v w (u와 v 사이 간선, 가중치 w)
     * 	•	이후 Q줄: u v (거리 구할 노드 쌍)
     *
     * 🔸 출력
     * 	•	각 쿼리마다 u ~ v 거리 출력
     *
     * 예제 입력:
     * 5 3
     *
     * 1 2 3
     * 1 3 2
     * 3 4 4
     * 3 5 6
     *
     * ```text
     *      1
     * (3)/   \(2)
     *   2     3
     *    (4)/  \(6)
     *      4    5
     * ```
     *
     * 2 4
     * 4 5
     * 2 5
     * 예제 출력:
     * 9
     * 10
     * 11
     */

    @Test
    fun solution1() {
        val countOfNode = 5
        val table = arrayOf(
            Triple(1, 2, 3),
            Triple(1, 3, 2),
            Triple(3, 4, 4),
            Triple(3, 5, 6),
        )
        val start = 2
        val end = 4
        val score = 9

        assertEquals(score, distanceDFSUseRecursive(countOfNode, start, end, table))
        assertEquals(score, distanceDFSUseStack(countOfNode, start, end, table))
    }

    @Test
    fun solution2() {
        val countOfNode = 5
        val table = arrayOf(
            Triple(1, 2, 3),
            Triple(1, 3, 2),
            Triple(3, 4, 4),
            Triple(3, 5, 6),
        )
        val start = 4
        val end = 5
        val score = 10

        assertEquals(score, distanceDFSUseRecursive(countOfNode, start, end, table))
        assertEquals(score, distanceDFSUseStack(countOfNode, start, end, table))
    }

    @Test
    fun solution3() {
        val countOfNode = 5
        val table = arrayOf(
            Triple(1, 2, 3),
            Triple(1, 3, 2),
            Triple(3, 4, 4),
            Triple(3, 5, 6),
        )
        val start = 2
        val end = 5
        val score = 11

        assertEquals(score, distanceDFSUseRecursive(countOfNode, start, end, table))
        assertEquals(score, distanceDFSUseStack(countOfNode, start, end, table))
    }

    data class Node(
        val node: Int,
        val score: Int
    )

    private fun distanceDFSUseRecursive(
        countOfNode: Int,
        start: Int,
        end: Int,
        array: Array<Triple<Int, Int, Int>>
    ): Int {
        val graph = Array<MutableList<Node>>(countOfNode + 1) { mutableListOf() }

        for ((from, to, score) in array) {
            graph[from].add(Node(to, score))
            graph[to].add(Node(from, score))
        }

        val (_, score, isReached) = recursive(start, end, graph, 0, BooleanArray(countOfNode + 1) { it == start })
        return if (isReached) score else -1
    }

    private fun recursive(
        start: Int,
        end: Int,
        graph: Array<MutableList<Node>>,
        score: Int,
        visited: BooleanArray
    ): Triple<Int, Int, Boolean> {
        if (start == end) return Triple(start, score, true)
        val child = graph[start]
        var index = start
        var totalScore = score
        var isReached = false

        for (element in child) {
            if (visited[element.node]) continue
            visited[element.node] = true
            val (nextIndex, nextScore) = recursive(element.node, end, graph, score + element.score, visited)
            if (nextIndex == end) {
                index = nextIndex
                totalScore = nextScore
                isReached = true

                break
            }
        }

        return Triple(index, totalScore, isReached)
    }


    private fun distanceDFSUseStack(countOfNode: Int, start: Int, end: Int, array: Array<Triple<Int, Int, Int>>): Int {
        val graph = Array<MutableList<Node>>(countOfNode + 1) { mutableListOf() }

        for ((from, to, score) in array) {
            graph[from].add(Node(to, score))
            graph[to].add(Node(from, score))
        }

        val (score, isReached) = stack(start, end, graph, 0, BooleanArray(countOfNode + 1) { it == start })
        return if (isReached) score else -1
    }

    private fun stack(
        start: Int,
        end: Int,
        graph: Array<MutableList<Node>>,
        score: Int,
        visited: BooleanArray
    ): Pair<Int, Boolean> {
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addLast(start to score)

        var index = start
        var totalScore = score
        var isReached = false

        while (stack.isNotEmpty()) {
            val (currentNode, currentScore) = stack.removeLast()

            if (currentNode == end) {
                index = currentNode
                totalScore = currentScore
                isReached = true
                break
            }

            for (element in graph[currentNode]) {
                if (visited[element.node]) continue
                visited[element.node] = true
                stack.addLast(element.node to currentScore + element.score)
            }
        }

        return totalScore to isReached
    }
}