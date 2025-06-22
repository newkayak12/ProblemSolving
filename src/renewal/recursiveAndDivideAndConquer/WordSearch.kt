package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.StringBuilder

class WordSearch {
    /**
     * ✅ 문제 설명
     * 2D 문자 격자(board)와 하나의 단어(word)가 주어질 때,
     * 격자 내 인접한 셀을 따라 해당 단어가 존재하는지 판별하시오.
     * 	•	인접: 상하좌우
     * 	•	같은 셀을 두 번 이상 사용할 수 없음
     *
     *
     * ✅ 입력 예
     * 	board = [
     *     ['A','B','C','E'],
     *     ['S','F','C','S'],
     *     ['A','D','E','E']
     * ]
     *
     * word = "ABCCED"
     *
     * ✅ 출력
     * true
     *
     * ✅ 조건 요약
     * 항목          |제한
     * board 크기    | 1 ≤ m, n ≤ 6
     * word 길이     | 1 ≤ word.length ≤ 15
     * 중복 방문 금지  |동일 좌표 두 번 사용 불가
     *
     */

    @Test
    fun solution() {
        val board = arrayOf(
            arrayOf("A", "B", "C", "E"),
            arrayOf("S", "F", "C", "S"),
            arrayOf("A", "D", "E", "E")
        )
        val visit = Array<BooleanArray>(board.size) {
            BooleanArray(4)
        }

        val word = "ABCCED"
        val expected = true
        val result = StringBuilder()
        useBacktrackingV2(board, visit, 0, word, result, 0, 0)

        print(result)
        assertEquals(expected, result.toString()==word)
    }

    fun useBacktrackingV1(
        board: Array<Array<String>>, visit: Array<BooleanArray>, count: Int,
        find: String, word: StringBuilder,
        row: Int, col: Int,
    ) {

        if(board[row][col] != "${find[count]}") return

        word.append(board[row][col])
        if (count == find.length - 1) {
            return
        }


        if(row - 1 >= 0 && !visit[row - 1][col]) {
            visit[row - 1][col] = true
            useBacktrackingV1(board, visit, count + 1, find, word, row - 1, col)
            visit[row - 1][col] = false
        }

        if(col - 1 >= 0 && !visit[row][col - 1]) {
            visit[row][col -1] = true
            useBacktrackingV1(board, visit, count + 1, find, word, row, col-1)
            visit[row][col -1] = false
        }

        if(row + 1 < board.size && !visit[row + 1][col]) {
            visit[row + 1][col] = true
            useBacktrackingV1(board, visit, count + 1, find, word, row + 1, col)
            visit[row + 1][col] = false
        }

        if(col + 1 < board[0].size && !visit[row][col + 1]) {
            visit[row][col + 1] = true
            useBacktrackingV1(board, visit, count + 1, find, word, row, col+1)
            visit[row][col + 1] = false
        }
    }


    fun useBacktrackingV2(
        board: Array<Array<String>>, visit: Array<BooleanArray>, count: Int,
        find: String, word: StringBuilder,
        row: Int, col: Int,
    ) {

        if(board[row][col] != "${find[count]}") return
        word.append(board[row][col])
        visit[row][col] = true
        if (count == find.length - 1) {
            return
        }

        val direction = listOf(
            Pair(-1, 0), Pair(1, 0),
            Pair(0, -1), Pair(0, 1)
        )

        for((dr, dc) in direction) {
            val nr = row + dr
            val nc = col + dc

            if (
                nr in board.indices &&
                nc in board[0].indices &&
                !visit[nr][nc]
            ) {
                useBacktrackingV2(board, visit, count + 1, find, word, nr, nc)
            }
        }

        visit[row][col] = false
    }
}
