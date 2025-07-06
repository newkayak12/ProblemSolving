package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.Queue
import java.util.concurrent.LinkedBlockingQueue

class `EscapeFAILURE!!!!!` {
    /**
     *
     * 📘 문제 3. 탈출 (BOJ 3055)
     *
     * 설명
     *
     * 고슴도치가 비버 굴로 도망가야 합니다. 하지만 매 분마다 물이 차오르고 있습니다.
     * 지도 상에는 고슴도치(S), 비버 굴(D), 돌(X), 빈 공간(.)이 존재합니다.
     * 물(*)은 매 턴마다 상하좌우로 퍼지고, 고슴도치는 물이 퍼지기 전에 이동합니다.
     * 고슴도치는 물이 찬 칸으로 이동할 수 없고, 물도 비버 굴에는 퍼지지 않습니다.
     *
     * 입력
     * 	•	첫 줄에 R(행), C(열) (1 ≤ R, C ≤ 50)
     * 	•	둘째 줄부터 R줄에 걸쳐 C개의 문자로 지도 상태가 주어짐
     *
     * 출력
     * 	•	고슴도치가 비버 굴로 도망갈 수 있는 최소 시간 출력
     * 	•	도달할 수 없다면 "KAKTUS" 출력
     *
     * 예제 입력
     * 3 3
     *
     * | D | . | * |
     * | . | . | . |
     * | . | . | S |
     *
     * 예제 출력
     * 3
     *
     * BFS
     * 1. 물
     * 2. 비버
     *
     * 고려할 점
     * 물이 흐르는 것
     * 비버가 최단거리로 움직이는 것
     */

    @Test
    fun solution() {
        val table = arrayOf(
            arrayOf("D", ".", "*"),
            arrayOf(".", ".", "."),
            arrayOf(".", ".", "S")
        )
        val expected = "3"

        assertEquals(expected, bfsFUCKGPT(arrayOf(
            charArrayOf('D', '.', '*'),
            charArrayOf('.', '.', '.'),
            charArrayOf('.', '.', 'S')
        )))
    }

    private fun bfs(table: Array<Array<String>>): String {
        val waterQueue = LinkedBlockingQueue<Pair<Int, Int>>()
        val beaverQueue = LinkedBlockingQueue<Pair<Int, Int>>()
        val visited = Array(table.size) { IntArray(table.first().size) { -1 } }

        var destination: Pair<Int, Int>? = null
        var start: Pair<Int, Int>? = null

        for ((rowIndex, row) in table.withIndex()) {
            for ((cellIndex, cell) in row.withIndex()) {
                if (cell == "D") destination = Pair(rowIndex, cellIndex)
                if (cell == "*") waterQueue.add(rowIndex to cellIndex)
                if (cell == "S") start = rowIndex to cellIndex
            }
        }

        if (destination == null || start == null) return "KAKTUS"

        beaverQueue.add(start)
        visited[start.first][start.second] = 0

        val direction = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        while (beaverQueue.isNotEmpty()) {
            val waterSize = waterQueue.size
            repeat(waterSize) {
                val (waterY, waterX) = waterQueue.poll()
                for ((moveY, moveX) in direction) {
                    val nextY = waterY + moveY
                    val nextX = waterX + moveX

                    if (nextY in table.indices && nextX in table.first().indices && table[nextY][nextX] == ".") {
                        table[nextY][nextX] = "*"
                        waterQueue.add((nextY) to (nextX))
                    }
                }
            }


            val beaverSize = beaverQueue.size
            repeat(beaverSize) {
                val (y, x) = beaverQueue.poll()
                if (y == destination.first && x == destination.second) {
                    return "${visited[y][x]}"
                }


                for ((moveY, moveX) in direction) {
                    val nextY = moveY + y
                    val nextX = moveX + x
                    if (
                        (nextY) in table.indices &&
                        (nextX) in table.first().indices &&
                        visited[nextY][nextX] == -1 &&
                        (table[nextY][nextX] == "." || table[nextY][nextX] == "D")
                    ) {
                        beaverQueue.add(nextY to nextX)
                        visited[nextY][nextX] = visited[y][x] + 1
                    }
                }
            }


        }

        return "KAKTUS"
    }

    private fun bfsFUCKGPT(table: Array<CharArray>): String {
        val r = table.size
        val c = table[0].size

        val visited = Array(r) { IntArray(c) { -1 } }
        val waterQ: Queue<Pair<Int, Int>> = LinkedList()
        val moveQ: Queue<Pair<Int, Int>> = LinkedList()

        for (i in 0 until r) {
            for (j in 0 until c) {
                when (table[i][j]) {
                    '*' -> waterQ.add(i to j)
                    'S' -> {
                        moveQ.add(i to j)
                        visited[i][j] = 0
                    }
                }
            }
        }

        val dx = intArrayOf(0, 0, 1, -1)
        val dy = intArrayOf(1, -1, 0, 0)

        while (moveQ.isNotEmpty()) {
            // 먼저 물 확산
            repeat(waterQ.size) {
                val (y, x) = waterQ.poll()
                for (d in 0..3) {
                    val ny = y + dy[d]
                    val nx = x + dx[d]

                    if (ny !in 0 until r || nx !in 0 until c) continue
                    if (table[ny][nx] == '.') {
                        table[ny][nx] = '*'
                        waterQ.add(ny to nx)
                    }
                }
            }

            // 고슴도치 이동
            repeat(moveQ.size) {
                val (y, x) = moveQ.poll()
                for (d in 0..3) {
                    val ny = y + dy[d]
                    val nx = x + dx[d]

                    if (ny !in 0 until r || nx !in 0 until c) continue
                    if (visited[ny][nx] != -1) continue

                    if (table[ny][nx] == '.') {
                        visited[ny][nx] = visited[y][x] + 1
                        moveQ.add(ny to nx)
                    } else if (table[ny][nx] == 'D') {
                        return (visited[y][x] + 1).toString()
                    }
                }
            }
        }

        return "KAKTUS"
    }
}