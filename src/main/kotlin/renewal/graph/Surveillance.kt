package renewal.graph

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import renewal.graph.Surveillance.Direction.DOWN
import renewal.graph.Surveillance.Direction.LEFT
import renewal.graph.Surveillance.Direction.RIGHT
import renewal.graph.Surveillance.Direction.UP

class Surveillance {
    /**
     * 🧩 문제: 감시 (코딩 테스트 변형)
     * (기반: BOJ 15683)
     * ⸻
     * 📝 문제 설명
     *
     * 사무실은 N×M 크기의 격자판이다. 각 칸에는 다음 중 하나가 존재한다.
     * 	•	0: 빈 칸
     * 	•	6: 벽
     * 	•	1~5: CCTV (총 5종류, 감시 방향 다름)
     *
     * CCTV는 자신의 종류에 따라 한 방향 또는 여러 방향을 감시할 수 있다.
     * 감시 방향에 있는 모든 빈 칸은 감시 가능하다. 벽이 있으면 감시가 그 칸에서 막힌다.
     *
     * 당신은 모든 CCTV에 대해 감시 방향을 하나씩 정할 수 있다.
     *
     * 모든 CCTV의 방향을 적절히 정했을 때, 감시되지 않는 빈 칸(0)의 최소 개수는?
     * ⸻
     * 📥 입력 형식
     * 	•	첫째 줄에 N, M이 주어진다. (1 ≤ N, M ≤ 8)
     * 	•	둘째 줄부터 N개의 줄에 사무실 정보가 주어진다.
     * 	•	사무실에는 CCTV가 최소 1개 이상 존재한다.
     * ⸻
     * 📤 출력 형식
     * 	•	감시되지 않는 최소 빈 칸 수를 출력하시오.
     * ⸻
     * 💡 CCTV 종류 및 감시 방향
     * |  번호   |  감시 가능 방향 (회전 가능)            |
     * |  1     |  → or ↓ or ← or ↑ (1방향)         |
     * |  2     |  ↔ or ↕ (2방향)                   |
     * |  3     |  →↑ or ↑← or ←↓ or ↓→ (직각 2방향) |
     * |  4     |  →↑← or ↑←↓ or ←↓→ or ↓→↑ (3방향) |
     * |  5     |  →↓←↑ (모든 방향, 고정)             |
     *
     *
     * 🧪 예제 입력
     * 4 6
     * 0 0 0 0 0 0
     * 0 1 0 0 6 0
     * 0 0 0 0 0 0
     * 0 0 0 0 0 5
     *
     * ✅ 예제 출력
     * 11
     *
     *
     * 0 0 0 0 0 #
     * 0 1 # # 6 #
     * 0 0 0 0 0 #
     * # # # # # 5
     */

    @Test
    fun solution() {
        val table = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 6, 0),
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 5),
        )
        val expected = 11

        assertEquals(expected, dfsUseRecursive(table))
        assertEquals(expected, dfsUseStack(table))
    }

    private fun dfsUseRecursive(table: Array<IntArray>): Int {

        val camera = mutableListOf<Pair<Int, Int>>()
        for ((rowIndex, row) in table.withIndex()) {
            for ((cellIndex, cell) in row.withIndex()) {
                if (cell in 1..5) camera.add(rowIndex to cellIndex)
            }
        }
        return recursive(0, camera, table, table.sumOf { it.count { cell -> cell == 0 } })
    }

    /**
     * ```
     * fun 재귀호출 (현재 카메라 인덱스, 총 카메라 리스트, 테이블, 0의 개수) {
     *
     *      if(현재 카메라 카운트 < 0 || 현재 카메라 카운트 > 카메라 사이즈 ) return 지금까지 0의 개수
     *      for( 방향 in 현재 카메라의 시각들 ) {
     *           val 이번 턴에 한 방향 마킹 기억 = mutableList()
     *
     *           for( 방향 in 방향 ) {
     *               방향 마킹처리
     *               이번 턴에 한 방향 마킹 기억에 기록
     *           }
     *
     *           val 현재 마킹까지 0의 개수 = table.sumOf{it.count{element == 0}}
     *           재귀 호출 (현재 카메라 인덱스 + 1, 총 카메라 리스트, 테이블 ,현재 마킹까지 0의 개수)
     *
     *           for (복원 요소 in 이번 턴에 한 방향 마킹 기억) {
     *              복원
     *           }
     *      }
     * }
     * ```
     */
    private fun recursive(
        index: Int,
        camera: MutableList<Pair<Int, Int>>,
        table: Array<IntArray>,
        count: Int
    ): Int {
        if (index !in 0 until camera.size) return count

        var min = count
        val directions: Map<Int, List<List<Direction>>> = mapOf(
            1 to listOf(listOf(RIGHT), listOf(DOWN), listOf(LEFT), listOf(UP)),       // → ↓ ← ↑
            2 to listOf(listOf(RIGHT, LEFT), listOf(DOWN, UP)),                       // ↔ ↕
            3 to listOf(listOf(RIGHT, UP), listOf(UP, LEFT), listOf(LEFT, DOWN), listOf(DOWN, RIGHT)), // 직각
            4 to listOf(
                listOf(RIGHT, UP, LEFT),
                listOf(UP, LEFT, DOWN),
                listOf(LEFT, DOWN, RIGHT),
                listOf(DOWN, RIGHT, UP)
            ), // 3방향
            5 to listOf(listOf(RIGHT, DOWN, LEFT, UP))                                // 전 방향
        )

        /**
         * for 방향
         *    visited에 각 상황 마킹
         *    visitied에서 0 세고 min에 minOf 업데이트
         *        만약 업데이트라면 visited 두고
         *      아니면 돌려놓기
         */


        val (y, x) = camera[index]
        val cameraNumber: Int = table[y][x]

        for( direction in directions[cameraNumber]!!) {
            val memorize = mutableListOf<Triple<Int, Int, Int>>()
            for( dir in direction) {
               when (dir) {
                   UP -> {
                       for( i in y downTo 0) {
                           if(table[i][x] == 6 ) break
                           if(table[i][x] == 0) {
                               memorize.add(Triple(i, x, table[i][x]))
                               table[i][x] = 7
                           }
                       }
                   }
                   LEFT -> {
                       for( i in x downTo 0) {
                           if(table[y][i] == 6) break
                           if(table[y][i] == 0) {
                               memorize.add(Triple(y, i, table[y][i]))
                               table[y][i] = 7
                           }
                       }
                   }
                   RIGHT -> {
                       for( i in x until  table.first().size) {
                           if(table[y][i] == 6) break
                           if(table[y][i] == 0) {
                               memorize.add(Triple(y, i, table[y][i]))
                               table[y][i] = 7
                           }
                       }
                   }
                   DOWN -> {
                       for( i in y until  table.size) {
                           if(table[i][x] == 6) break
                           if(table[i][x] == 0) {
                               memorize.add(Triple(i, x, table[i][x]))
                               table[i][x] = 7
                           }
                       }
                   }
               }

            }

            val count = table.sumOf { it.count { cell -> cell == 0 } }
            min = minOf(min, recursive(index + 1, camera, table, count))

            for((y, x, value) in memorize) {
                table[y][x] = value
            }

        }

        return min
    }


    /**
     *```
     *  val {현재 카메라, 현재 방향, 이전까지 테이블} = stack.pop()
     *  val copyTable = 이전까지 테이블 카피
     *
     *  for( 방향 in 현재 방향) {
     *      방향 마킹 -> copyTable
     *  }
     *
     *  if(cameraIndex 가 넘지 않았다면) {
     *      val 다음 카메라 = 카메라 세트[현재 카메라 인덱스 + 1]
     *      for(다음 카메라 방향 in 다음 카메라 방향들 ) {
     *        stack.push(현재 카메라 인덱스 + 1, 다음 카메라 방향, copyTable )
     *      }
     *  }
     *  else {
     *      집계
     *  }
     *```
     */
    private fun dfsUseStack(table: Array<IntArray>): Int {
        val camera = mutableListOf<Pair<Int, Int>>()
        val directions: Map<Int, List<List<Direction>>> = mapOf(
            1 to listOf(listOf(RIGHT), listOf(DOWN), listOf(LEFT), listOf(UP)),       // → ↓ ← ↑
            2 to listOf(listOf(RIGHT, LEFT), listOf(DOWN, UP)),                       // ↔ ↕
            3 to listOf(listOf(RIGHT, UP), listOf(UP, LEFT), listOf(LEFT, DOWN), listOf(DOWN, RIGHT)), // 직각
            4 to listOf(
                listOf(RIGHT, UP, LEFT),
                listOf(UP, LEFT, DOWN),
                listOf(LEFT, DOWN, RIGHT),
                listOf(DOWN, RIGHT, UP)
            ), // 3방향
            5 to listOf(listOf(RIGHT, DOWN, LEFT, UP))                                // 전 방향
        )

        for ((rowIndex, row) in table.withIndex()) {
            for ((cellIndex, cell) in row.withIndex()) {
                if (cell in 1..5) camera.add(rowIndex to cellIndex)
            }
        }

        val stack = ArrayDeque<Triple<Int, Array<IntArray>, List<Direction>>>()
        var minOf = Int.MAX_VALUE

        // 첫 카메라 방향별로 push
        val firstY = camera.first().first
        val firstX = camera.first().second
        val first = table[firstY][firstX]
        for (dirs in directions[first]!!) {
            // table deep copy
            val copy = Array(table.size) { table[it].clone() }
            mark(copy, firstY, firstX, dirs)
            stack.addLast(Triple(0, copy, dirs))
        }

        while (stack.isNotEmpty()) {
            val (index, curTable, dirs) = stack.removeLast()

            if (index + 1 < camera.size) {
                val (nextY, nextX) = camera[index + 1]
                val camType = table[nextY][nextX]
                for (nextDirs in directions[camType]!!) {
                    // 다음 분기용 table deep copy
                    val nextTable = Array(curTable.size) { curTable[it].clone() }
                    mark(nextTable, nextY, nextX, nextDirs)
                    stack.addLast(Triple(index + 1, nextTable, nextDirs))
                }
            } else {
                // 리프에서 0 개수 세기
                val min = curTable.sumOf { it.count { v -> v == 0 } }
                minOf = minOf(minOf, min)
            }
        }
        return minOf
    }

    // table에 dirs 방향 마킹 함수
    private fun mark(table: Array<IntArray>, y: Int, x: Int, dirs: List<Direction>) {
        for (dir in dirs) {
            when (dir) {
                UP -> {
                    for (i in y - 1 downTo 0) {
                        if (table[i][x] == 6) break
                        if (table[i][x] == 0) table[i][x] = 7
                    }
                }
                DOWN -> {
                    for (i in y + 1 until table.size) {
                        if (table[i][x] == 6) break
                        if (table[i][x] == 0) table[i][x] = 7
                    }
                }
                LEFT -> {
                    for (i in x - 1 downTo 0) {
                        if (table[y][i] == 6) break
                        if (table[y][i] == 0) table[y][i] = 7
                    }
                }
                RIGHT -> {
                    for (i in x + 1 until table[0].size) {
                        if (table[y][i] == 6) break
                        if (table[y][i] == 0) table[y][i] = 7
                    }
                }
            }
        }
    }

    enum class Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

}