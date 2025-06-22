package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Maze {
    /**
     * 🔍 BFS 심화 문제 - 미로 탐색 (난이도: 중하 ~ 중)
     *
     * 문제 설명
     *
     * N × M 크기의 배열로 표현되는 미로가 있습니다.
     * 각 칸은 다음 중 하나입니다:
     * 	•	1: 이동할 수 있는 칸
     * 	•	0: 이동할 수 없는 칸
     *
     * 당신은 (1,1)에서 출발해 (N,M)으로 이동해야 합니다.
     * 이동 시, 상하좌우 인접한 칸으로만 움직일 수 있습니다.
     * 이때 최소 몇 칸을 지나가야 목적지에 도달할 수 있는지 구하세요.
     * 	•	출발점과 도착점도 포함하여 센다.
     *
     * ⸻
     *
     * 입력 형식
     * 	•	첫 줄에 N, M이 주어진다. (1 ≤ N, M ≤ 100)
     * 	•	다음 N줄에 M개의 숫자가 붙어서 주어진다. (예: 101111)
     *
     * ⸻
     *
     * 출력 형식
     * 	•	최소 이동 칸 수를 출력한다. 이동이 불가능하면 절대 없음.
     *
     * 	예시 입력
     * 	4 6
     * 101111
     * 101010
     * 101011
     * 111011
     *
     * 예시 출력
     * 15
     */

    @Test
    fun solution() {
        val destination = Pair(4, 6)
        val table = arrayOf(
            intArrayOf(1, 0, 1, 1, 1, 1),
            intArrayOf(1, 0, 1, 0, 1, 0),
            intArrayOf(1, 0, 1, 0, 1, 1),
            intArrayOf(1, 1, 1, 0, 1, 1),
        )

        val expected = 15

        assertEquals(expected, bfs(destination, table))
    }

    fun bfs( destination: Pair<Int, Int>,  table: Array<IntArray>): Int {
        val visited = Array(table.size){ IntArray(table.first().size){ - 1} }
        val direction = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

        val start = Pair(0, 0)
        val stack = ArrayDeque<Pair<Int, Int>>()
        stack.addLast(start)
        visited[start.first][start.second] = 1

        val (destinationY, destinationX) = destination

        while(stack.isNotEmpty()) {
            val (y, x) = stack.removeLast()


            for((moveX, moveY) in direction) {

                val nextX = moveX + x
                val nextY = moveY + y
                if(
                    nextY in table.indices &&
                    nextX in table.first().indices &&
                    table[nextY][nextX] == 1 &&
                    visited[nextY][nextX] == -1
                ) {
                  stack.addLast((nextY) to (nextX))
                 visited[(nextY)][(nextX)] = visited[y][x] + 1
                }
            }
        }

        return visited[destinationY - 1][destinationX - 1]
    }
}