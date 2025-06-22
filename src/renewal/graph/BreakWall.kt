package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.LinkedBlockingQueue

class BreakWall {
    /**
     * 🧩 문제: 벽 부수고 이동하기 (BOJ 2206)
     *
     * 📝 문제 설명
     * 	•	N×M의 행렬로 표현되는 맵이 있다.
     * 	•	고슴도치(혹은 사람)는 (1, 1)에서 출발하여 (N, M)으로 이동해야 한다.
     * 	•	0은 이동할 수 있는 곳, 1은 벽.
     * 	•	단 벽을 최대 1개까지 부술 수 있다.
     * 	•	이동은 상하좌우 인접한 칸만 가능.
     * 	•	최단 거리로 도착할 수 있는 최소 이동 횟수를 출력하라.
     * 	•	도달할 수 없다면 -1을 출력하라.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	첫째 줄: N M (1 ≤ N, M ≤ 1,000)
     * 	•	다음 N줄: 길이 M의 0과 1로 이루어진 문자열
     *
     * ⸻
     *
     * 📤 출력 형식
     * 	•	목적지까지 최단 거리 출력
     * 	•	도달할 수 없으면 -1
     *
     * ⸻
     *
     * 🧪 예제 입력
     * 6 4
     * 0100
     * 1110
     * 1000
     * 0000
     * 0111
     * 0000
     *
     * ✅예제 출력
     * 15
     */

    @Test
    fun solution() {
        val destination = 6 to 4
        val table = arrayOf(
            intArrayOf(0, 1, 0, 0),
            intArrayOf(1, 1, 1, 0),
            intArrayOf(1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1),
            intArrayOf(0, 0, 0, 0),
        )
        val expected = 15

        assertEquals(expected, bfs(destination, table))
    }

    private fun bfs(destination: Pair<Int, Int>, table: Array<IntArray>): Int {
        val (startY, startX, wallBreak) = Triple(0, 0, 0)
        val (destinationY, destinationX) = destination

        val visited = Array(table.size) {
            Array(table.first().size) {
                IntArray(2) { -1 }
            }
        }
        val queue = LinkedBlockingQueue<Triple<Int, Int, Int>>()
        queue.add(Triple(0, 0, 0))
        visited[startY][startX][wallBreak] = 1

        val direction = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        while (queue.isNotEmpty()) {
            val (y, x, wb) = queue.poll()

            for ((moveY, moveX) in direction) {
                val nextX = moveX + x
                val nextY = moveY + y

                if(
                    nextY in table.indices &&
                    nextX in table.first().indices
                ){

                    if( table[nextY][nextX] == 1 && wb == 0 && visited[nextY][nextX][1] == -1) {
                        visited[nextY][nextX][1] = visited[y][x][0] + 1
                        queue.add(Triple(nextY, nextX, 1))
                    }

                    if( table[nextY][nextX] != 1 && visited[nextY][nextX][wb] == -1) {
                        visited[nextY][nextX][wb] = visited[y][x][wb] + 1
                        queue.add(Triple(nextY, nextX, wb))
                    }

                }
            }
        }

        val response0 = visited[destinationY - 1][destinationX - 1][0]
        val response1 = visited[destinationY - 1][destinationX - 1][1]
        return when {
            response0 == -1 && response1 == -1 -> -1
            response0 == -1 && response1 != -1 -> response1
            response0 != -1 && response1 == -1 -> response0
            else -> minOf(response0, response1)
        }
    }
}