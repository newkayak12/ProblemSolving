package renewal.bottomup

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OnTheWayToSchool {
    /**
     * 🧪 문제 2. 등굣길
     * ⸻
     * 📄 문제 설명
     * m × n 격자판이 있다.
     * 왼쪽 위에서 시작해 오른쪽 아래로 이동하는 경우의 수를 구하시오.
     * 오른쪽, 아래로만 이동할 수 있고, 물웅덩이는 피해야 한다.
     * ⸻
     * 📥 입력
     * 	•	정수 m, n (1 ≤ m, n ≤ 100)
     * 	•	물웅덩이 좌표 리스트 [[x1, y1], [x2, y2], ...]
     * ⸻
     * 📤 출력
     * 	•	가능한 모든 이동 경로의 수
     * ⸻
     */

    @Test
    fun solution(){

        val expected = 70
        val result = useBottomUp(5,5, Array(0){IntArray(0)})

        assertEquals(expected, result)
    }

    private fun useBottomUp(m: Int, n:Int, spot: Array<IntArray>): Int {
        val table = Array(m + 1) { IntArray(n + 1) }
        table[1][1] = 1

        for(i in 0 until  spot.size) {
            val (x,y) = spot[i]
            table[x][y] = -1
        }

        for(i in 1 .. m) {
            for(j in 1 .. n) {
                if ( table[i][j] == -1) {
                    table[i][j] = 0
                    continue
                }

                if(table[i - 1][j] != -1) table[i][j] += table[i - 1][j]
                if(table[i][j - 1] != -1) table[i][j] += table[i][j - 1]
            }
        }


        return table[m][n]
    }
}