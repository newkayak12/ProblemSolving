package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.LinkedBlockingQueue

class `Escape Easy` {

    /**
     * ⸻
     *
     * ✅ 난이도 하향 문제: 장애물을 피해 최단 거리로 이동하기
     *
     * 문제 설명
     * N×M 크기의 지도에서 고슴도치 ‘S’는 비버의 굴 ‘D’까지 이동하려고 합니다.
     * 지도에는 돌(‘X’)이 있어 이동이 불가능한 칸이 있으며, 고슴도치는 인접한 네 방향(상하좌우)으로 이동할 수 있습니다.
     * 고슴도치는 돌을 피해서 목적지 ‘D’까지 최단 거리로 이동해야 합니다.
     *
     * 지도 정보
     * 	•	S: 시작 지점 (고슴도치)
     * 	•	D: 도착 지점 (비버의 굴)
     * 	•	.: 빈 공간
     * 	•	X: 돌 (지나갈 수 없음)
     *
     * ⸻
     *
     * ✨ 입력 예시
     * 5 4
     * D . . .
     * . X X .
     * . . . .
     * X X . X
     * S . . .
     * ✅ 출력 예시
     * 8
     */

    @Test
    fun solution() {
        val table = arrayOf(
            arrayOf("D", ".", ".", "."),
            arrayOf(".", "X", "X", "."),
            arrayOf(".", ".", ".", "."),
            arrayOf("X", "X", ".", "X"),
            arrayOf("S", ".", ".", "."),
        )
        val expected = 8

        assertEquals(expected, bfs(table))
    }

    private fun bfs(table: Array<Array<String>>): Int {
        var startPoint: Pair<Int, Int>? = null
        var endPoint: Pair<Int, Int>? = null

        for ((rowIndex, row) in table.withIndex()) {
            for ((cellIndex, cell) in row.withIndex()) {
                if (cell == "S") startPoint = rowIndex to cellIndex
                if (cell == "D") endPoint = rowIndex to cellIndex
            }
        }


        if(startPoint == null || endPoint == null) return  - 1

        val queue = LinkedBlockingQueue<Pair<Int, Int>>()
        val visited = Array(table.size) { IntArray(table.first().size){ -1 } }

        queue.add(startPoint)
        visited[startPoint.first][startPoint.second] = 0

        while (queue.isNotEmpty()) {
            val (y, x) = queue.poll()
            if(y == endPoint.first && x == endPoint.second ) break

            val direction = mutableListOf<Pair<Int, Int>>()
            if (y - 1 >= 0) direction.add(y - 1 to x)
            if (y + 1 < table.size) direction.add(y + 1 to x)
            if (x - 1 >= 0) direction.add(y to x - 1)
            if (x + 1 < table.first().size) direction.add(y to x + 1)



            for ((nextY, nextX) in direction) {
                if ((table[nextY][nextX] == "." || table[nextY][nextX] == "D") && visited[nextY][nextX] == -1) {
                    queue.add(nextY to nextX)
                    visited[nextY][nextX] = visited[y][x] + 1
                }
            }
        }
        return visited[endPoint?.first ?: 0][endPoint?. second?: 0]
    }
}