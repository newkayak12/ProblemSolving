package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NQueen {

    /**
     * 📌 문제 설명
     *
     * N × N 크기의 체스판 위에 N개의 퀸을 서로 공격하지 않게 놓으려고 합니다.
     * 여기서 퀸은 같은 행, 열, 대각선 상에 있으면 서로 공격할 수 있습니다.
     * 목표:
     * N개의 퀸을 올바르게 놓을 수 있는 모든 경우의 수를 구하세요.
     * ⸻
     * 📥 입력
     * 하나의 정수 N (4 ≤ N ≤ 15)
     * ⸻
     * 📤 출력
     * 조건을 만족하는 퀸 배치의 총 경우의 수 (정수)
     *
     *
     *  1. 각 행에 Q는 하나
     *  2. 각 열에 Q는 아나
     *  3. 대각선으로도 Q는 하나
     *  4. 모든 경우의 수
     *
     *  n이 4라면 아래 두 개
     *  | |Q| | |
     *  | | | |Q|
     *  |Q| | | |
     *  | | |Q| |
     *
     *  | | |Q| |
     *  |Q| | | |
     *  | | | |Q|
     *  | |Q| | |
     *
     *
     * -> 구조
     *  {
     *
     *      만약 현재 row, col이 조건에 부합하지 않으면 종료
     *      만약 현재 row가 n이면 가능하다고 체크
     *
     *
     *      for(i 0 until n)
     *          row, i에 둔다.
     *          재귀 호출(row + 1)
     *          row, i에 둔 것을 제거한다.
     *  }
     *
     *
     *  문제점
     * 	•	“어떻게 2차원적인 대각선 관계를 1차원 배열로 추상화하지?” → 여기서 막힘.
     * 	•	“배열 크기는 또 얼마나 필요하지?” → 계산식 정리가 안 되면 못 씀.  -> 2*n - 1
     * 	[
     * 	    [(0,0),(1,0),(2,0),(3,0)],
     * 	    [(0,1),(1,1),(2,1),(3,1)],
     * 	    [(0,2),(1,2),(2,2),(3,2)],
     * 	    [(0,3),(1,3),(2,3),(3,3)]
     * 	]
     *
     *  left-bottom  (↘) : row + col
     *  right-bottom (↙) : row - col + (n-1)
     *
     *
     * 왜 left-bottom이 /가 아닌 \ 인가?
     * y ↑
     * 7 |
     * 6 | *
     * 5 |   *
     * 4 |     *
     * 3 |       *
     * 2 |         *
     * 1 |           *
     * 0 |____________> x
     *     0 1 2 3 4 5 6
     */



    fun useBacktracking( bottom: BooleanArray, leftBottom: BooleanArray, rightBottom: BooleanArray, row: Int, n: Int): Int {

        if(row == n) return  1

        var success = 0;
        for(col in 0 until n) {

            val leftBottomIndex = row + col
            val rightBottomIndex = row - col + n - 1
            if(bottom[col] or leftBottom[leftBottomIndex] or rightBottom[rightBottomIndex]) continue

            bottom[col] = true
            leftBottom[leftBottomIndex] = true
            rightBottom[rightBottomIndex] = true

            success += useBacktracking(bottom, leftBottom, rightBottom, row + 1, n)

            bottom[col] = false
            leftBottom[leftBottomIndex] = false
            rightBottom[rightBottomIndex] = false
        }

        return success;
}


    @Test
    fun backtracking4() {
        val n = 4
        val expected = 2
        val bottom = BooleanArray(n)
        val leftBottom = BooleanArray(2 * n - 1)
        val rightBottom = BooleanArray(2 * n - 1)
        val result = useBacktracking(bottom , leftBottom, rightBottom, 0, n)

        assertEquals(expected, result)
    }

    @Test
    fun backtracking5() {
        val n = 5
        val expected = 10
        val bottom = BooleanArray(n)
        val leftBottom = BooleanArray(2 * n - 1)
        val rightBottom = BooleanArray(2 * n - 1)
        val result = useBacktracking(bottom , leftBottom, rightBottom, 0, n)

        assertEquals(expected, result)
    }

    @Test
    fun backtracking8() {
        val n = 8
        val expected = 92
        val bottom = BooleanArray(n)
        val leftBottom = BooleanArray(2 * n - 1)
        val rightBottom = BooleanArray(2 * n - 1)
        val result = useBacktracking(bottom , leftBottom, rightBottom, 0, n)

        assertEquals(expected, result)
    }
}