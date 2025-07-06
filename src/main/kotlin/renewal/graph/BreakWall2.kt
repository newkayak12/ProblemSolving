package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BreakWall2 {
    /**
     * 🧩 문제: 벽 부수고 이동하기 2 (BOJ 14442 변형, 코딩 테스트 스타일)
     *
     * ⸻
     *
     * 📝 문제 설명
     *
     * 당신은 N × M 크기의 평면 맵 위에 서 있습니다.
     * 이 맵은 0(빈칸)과 1(벽)로 구성되어 있고,
     * 당신은 시작점 (1, 1)에서 도착점 (N, M)으로 이동해야 합니다.
     *
     * 이때, 최대 K개의 벽을 부수고 지나갈 수 있습니다.
     * 단, 한 칸 이동은 상하좌우 인접한 칸으로만 가능합니다.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	첫째 줄: N M K (1 ≤ N, M ≤ 1,000, 1 ≤ K ≤ 10)
     * 	•	다음 N줄: 길이 M의 0 또는 1로만 이루어진 문자열
     *
     * ⸻
     *
     * 📤 출력 형식
     * 	•	도착점까지 이동할 수 있다면, 최소 이동 횟수를 출력
     * 	•	이동할 수 없다면 -1을 출력
     *
     * ⸻
     *
     * 💡 예제 입력
     * 6 4 1
     * 0100
     * 1110
     * 1000
     * 0000
     * 0111
     * 0000
     * ✅ 예제 출력
     * 15
     */

    @Test
    fun solution() {
        val destination = 6 to 4
        val wallBreakCount = 1
        val table = arrayOf(
            intArrayOf(0, 1, 0, 0),
            intArrayOf(1, 1, 1, 0),
            intArrayOf(1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1),
            intArrayOf(0, 0, 0, 0),
        )
        val expected = 15

        assertEquals(expected, bfs(destination, wallBreakCount, table))
    }

    fun bfs(destination: Pair<Int, Int>, wallBreakCount: Int, table: Array<IntArray>): Int {
        val startPoint = 0 to 0
        val (endPointY, endPointX) = destination.first - 1 to destination.second - 1

        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        val visited = Array(table.size) { Array(table.first().size) { IntArray(wallBreakCount + 1) { -1 } } }
        val direction = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        queue.addLast(Triple(startPoint.first, startPoint.second, 0))
        visited[startPoint.first][startPoint.second][0] = 1



        while (queue.isNotEmpty()) {
            val (y, x, wallBreak) = queue.removeLast()

            for ((moveY, moveX) in direction) {
                val nextY = moveY + y
                val nextX = moveX + x
                if (nextY !in table.indices || nextX !in table.first().indices) continue

                if (table[nextY][nextX] != 1 && visited[nextY][nextX][wallBreak] == -1) {
                    queue.addLast(Triple(nextY, nextX, wallBreak))
                    visited[nextY][nextX][wallBreak] = visited[y][x][wallBreak] + 1
                }
                else if (
                    table[nextY][nextX] == 1 &&
                    wallBreak < wallBreakCount &&
                    visited[nextY][nextX][wallBreak + 1] == -1
                ) {
                    queue.addLast(Triple(nextY, nextX, wallBreak + 1))
                    visited[nextY][nextX][wallBreak + 1] = visited[y][x][wallBreak] + 1
                }
            }
        }


        return visited[endPointY][endPointX].filter { it != -1 }.minOrNull() ?: -1
    }
}