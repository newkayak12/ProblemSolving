package renewal.dataStructure.deque

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `0-1BFS` {
    /**
     * 0-1 BFS는 간선 가중치가 0 또는 1인 그래프에서 최단 거리를 구할 때 사용하는
     * 특수한 BFS 알고리즘입니다.
     *
     * 일반적인 BFS와 Dijkstra 알고리즘의 중간 형태이며,
     * 시간 복잡도는 Dijkstra보다 빠르고 BFS만큼 간단한 구조를 가집니다.
     *
     * ✅ 개념 정리
     *
     * |항목| 설명|
     * |:---:|:---:|
     * |목적| 간선 가중치가 0 또는 1인 그래프에서 최단 거리 계산|
     * |행심 아이디어|간선 가중치가 0이면 앞, 1이면 뒤에 넣는 방식|
     * |자료구조|Deque(Double-ended queue)|
     * |시간 복잡도|O(V+E)|
     *
     * ## 일반 BFS는 왜 안되는가?
     * - BFS는 모든 간선의 가중치가 동일일 때만 유효하다.
     * - 가중치 0 또는 1일 경우, 일반 BFS는 0 가중치 간선을 더 빨리 반영하지 못함
     * - Dijkstra는 우선순위 큐를 사용해서 정확하지만, 복잡하고 느리다.
     * - 0-1 BFS는 두 단점을 보완
     *
     *
     * ## Pseudo
     *
     * ```text
     * 시작 정점에서 Deque 초기화
     * 방문 배열을 모두 초기화
     *
     * 큐가 빌 때까지:
     *      현재 노드를 꺼낸다.
     *      인접한 노드를 순회
     *          가중치가 0이면 -> Deque의 앞에 삽입
     *          가중치가 1이면 -> Deque의 뒤에 삽입
     *
     *
     * ```
     * ### 1과 0에 대한 처리
     * - 더 빠르게 도착할 수 있는 경로부터 탐색한다.
     * - 가중치로 우선순위를 나눈다.
     *
     *
     * ## 예시 문제
     *
     * | 문제 이름 | 설명 |
     * |:----:|:----:|
     * |벽 부수고 이동| 벽을 1개까지 부술 수 있는 상태 확장형|
     * |특정 위치까지 최소 시간| 순간이동(0초) + 걷기(1초) 등의 조합|
     * |가중치 0/1 도로로 된 그래프의 최소 경로 찾기| 도로의 종류에 따라 우선순위 다르게 적용|
     */

    data class State(val x: Int, val y: Int, val wallBreakCount: Int, val dist: Int)

    @Nested
    inner class BreakWall {
        /**
         * 🧱 문제: 벽 부수고 탈출하기 (0-1 BFS 변형)
         *
         * 📘 문제 설명
         *
         * 당신은 N x M 크기의 미로에 갇혔습니다.
         * 당신은 (1,1) 위치에서 출발해 (N,M) 위치로 이동하려고 합니다.
         * 이 미로에는 **빈 칸(0)**과 **벽(1)**이 존재하며, 당신은 벽을 여러 개 부술 수 있습니다.
         * 하지만, 부술 수 있는 벽의 최대 개수는 K개입니다.
         *
         * 이동할 수 있는 방향은 상하좌우이며, 한 칸을 이동할 때는 1초가 걸립니다.
         * 가능한 가장 빠른 시간에 탈출할 수 있도록 하세요.
         * 탈출할 수 없다면 -1을 출력합니다.
         *
         * ⸻
         *
         * 📥 입력 형식
         * N M K
         * <미로 정보> (N줄)
         *
         * 📤 출력 형식
         * 	•	최단 시간 (이동 횟수)을 출력
         * 	•	탈출이 불가능한 경우 -1 출력
         *
         *
         * 💡 예제 입력
         * 6 6 2
         * 010000
         * 111000
         * 100000
         * 000001
         * 011111
         * 000000
         *
         * 💡 예제 출력
         * 10
         */


        @Test
        fun solution() {
            val N = 6
            val M = 6
            val K = 2
            val miro = arrayOf(
                intArrayOf(0, 1, 0, 0, 0, 0),
                intArrayOf(1, 1, 1, 0, 0, 0),
                intArrayOf(1, 0, 0, 0, 0, 0),
                intArrayOf(0, 0, 0, 0, 0, 1),
                intArrayOf(0, 1, 1, 1, 1, 1),
                intArrayOf(0, 0, 0, 0, 0, 0),
            )
            val expected = 10


            assertEquals(expected, useBFS(N, M, K, miro))
        }


        private fun useBFS(N: Int, M: Int, K: Int, miro: Array<IntArray>): Int {
            val visit = Array(N) { Array(M) { Array(K + 1) { false } } }
            val deque = ArrayDeque<State>()
            val endPointX = N - 1
            val endPointY = M - 1
            val direction = listOf(
                Pair(-1, 0),
                Pair(1, 0),
                Pair(0, -1),
                Pair(0, 1)
            )

            deque.addLast(State(0, 0, 0, 0))

            while (deque.isNotEmpty()) {
                val state = deque.removeFirst()
                if (state.x == endPointX && state.y == endPointY) return state.dist

                for ((nextX, nextY) in direction) {
                    val moveX = nextX + state.x
                    val moveY = nextY + state.y

                    if (moveY !in miro.indices) continue
                    if (moveX !in miro.first().indices) continue

                    val isWall = miro[moveY][moveX] == 1
                    val wallBreakCount = state.wallBreakCount + if (isWall) 1 else 0

                    if (wallBreakCount > K) continue
                    if (visit[moveY][moveX][wallBreakCount]) continue

                    visit[moveY][moveX][wallBreakCount] = true
                    deque.addLast(State(moveX, moveY, wallBreakCount, state.dist + 1))
                }
            }

            return -1
        }

    }


    @Nested
    inner class HideAndSeek {
        /**
         * 🧭 문제: 숨바꼭질 3 (BOJ 13549 변형)
         *
         * ⸻
         *
         * 📘 문제 설명
         *
         * 수빈이는 현재 위치 N에 있고, 동생은 K에 있습니다.
         * 수빈이는 다음 세 가지 방법으로 이동할 수 있습니다:
         * 	1.	X - 1 (걷기) → 1초 소요
         * 	2.	X + 1 (걷기) → 1초 소요
         * 	3.	2 * X (순간이동) → 0초 소요
         *
         * 수빈이는 동생을 찾기 위해 가장 빠른 시간에 도착하고자 합니다.
         * 최소 시간을 구하세요.
         *
         * ⸻
         *
         * 📥 입력 형식
         * N K
         *
         * 	•	0 ≤ N, K ≤ 100,000
         *
         * ⸻
         *
         * 📤 출력 형식
         * 	•	수빈이가 동생에게 도달하는 데 걸리는 최소 시간 출력
         *
         * ⸻
         *
         * 💡 예제 입력
         * 5 17
         *
         * 💡 예제 출력
         * 2
         *
         * > 경로: 5 → 10 (순간이동, 0초) → 9 → 18 -> 17 (1초 + 1초)
         */

        @Test
        fun solution() {
            val N = 5
            val K = 17
            val expected = 2

            assertEquals(expected, useBFS(N, K))
        }

        private fun useBFS(N: Int, K: Int): Int {
            val deque = ArrayDeque<Pair<Int, Int>>()
            val visit = BooleanArray(100_001)
            val direction = listOf<Pair<(Int) -> Int, (Int) -> Int>>(
                ({ i: Int -> i + 1 } to { i: Int -> i + 1 }),
                ({ i: Int -> i - 1 } to { i: Int -> i + 1 }),
                ({ i: Int -> i * 2 } to { i: Int -> i + 0 })

            )
            deque.addLast(N to 0)

            while (deque.isNotEmpty()) {
                val (position, time) = deque.removeFirst()
                if (position == K) return time

                for ((move, spend) in direction) {
                    val next = move(position)
                    val spendedTime = spend(time)

                    if (next !in 0..100_000) continue
                    if (visit[next]) continue
                    visit[next] = true

                    if (spendedTime == time) {
                        deque.addFirst(next to spendedTime)
                    } else {
                        deque.addLast(next to spendedTime)
                    }
                }

            }

            return -1
        }
    }

    @Nested
    inner class `0-1Graph` {
        /**
         * 🧭 문제: 알고스팟 (BOJ 1261 변형)
         *
         * ⸻
         *
         * 📘 문제 설명
         *
         * 당신은 N x M 크기의 미로의 (0,0)에서 (N-1, M-1)로 이동하려고 합니다.
         * 이 미로는 **빈 칸(0)**과 **벽(1)**으로 구성되어 있으며,
         * 빈 칸은 비용 0, 벽은 비용 1입니다.
         *
         * 이동은 상하좌우로만 가능하며,
         * 도착 지점까지 도달할 때 **부순 벽의 수의 최소값(=총 비용)**을 출력하세요.
         *
         * ⸻
         *
         * 📥 입력 형식
         * M N
         * <미로 정보> (N줄, 각 줄 M개의 0 또는 1)
         * 	•	1 ≤ N, M ≤ 100
         * 	•	0: 빈 칸 → 이동 가능 (비용 0)
         * 	•	1: 벽 → 부수고 이동 가능 (비용 1)
         * 📤 출력 형식
         *  	•	(0,0) → (N-1,M-1) 까지 도달하는 최소 비용 (부순 벽 개수)
         *
         *  ⸻
         *
         *  💡 예제 입력
         * 3 3
         * 011
         * 111
         * 110
         *
         * 💡 예제 출력
         * 3
         */

        @Test
        fun solution() {
            val N = 3
            val M = 3
            val miro = arrayOf(
                intArrayOf(0, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 0),
            )
            val expected = 3

            assertEquals(expected, useBFS(N, M, miro))
        }

        private fun useBFS(N: Int, M: Int, miro: Array<IntArray>): Int {
            val deque = ArrayDeque<State>()
            val endPointX = N - 1
            val endPointY = M - 1
            val visited = Array(N){IntArray(M){Int.MAX_VALUE} }
            val direction = listOf(
                1 to 0,
                -1 to 0,
                0 to 1,
                0 to -1
            )
            deque.addLast(State(0, 0, 0, 0))



            while(deque.isNotEmpty()){
                val state = deque.removeFirst()
                if(state.x == endPointX && state.y == endPointY) return state.wallBreakCount
                for((x, y) in direction) {
                    val nextX = x + state.x
                    val nextY = y + state.y

                    if(nextX !in 0 until N) continue
                    if(nextY !in 0 until M) continue
                    val nextStep = miro[nextY][nextX]
                    val cost = state.wallBreakCount + nextStep

                    if(visited[nextY][nextX] <= cost) continue

                    val nextState = State(nextX, nextY, cost, 0)
                    visited[nextY][nextX] = cost
                    if(nextStep == 1) {
                        deque.addLast(nextState)
                    }
                    else {
                        deque.addFirst(nextState)
                    }
                }
            }

            return -1
        }
    }
}