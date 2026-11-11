package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `멀쩡한 사각형` {
    /**
     * 8 * 12의 대각선을 그으면 총 16개가 나온다.
     * w, h는 1억 이하의 자연수다  -> long
     *
     * 2 * 3
     * 결과적으로 대각선을 긋고 찾아야 하는데, 대각선을 긋는 패턴을 잡아야 할 것같음
     *
     *  2 * 3
     * 🟦⬜
     * 🟦🟦
     * ⬜🟦
     *
     *  2 * 4
     * 🟦⬜
     * 🟦⬜
     * ⬜🟦
     * ⬜🟦
     *
     *  3 * 4
     * 🟦⬜⬜
     * ⬜🟦⬜
     * ⬜🟦⬜
     * ⬜⬜🟦
     *
     *
     *  w/h가 기울기네
     *
     *  최대 공약수 구해서 비율 잡고,
     *  x추 한 칸 * 기울기의 ceil로 칸 수 잡고
     *  칸 수 * gcd가 대각선 차지 칸수
     *
     *  2*3 이면 세로 선 1 개 가로 선 2개를 지난다.
     *
     *  val result = 1  //첫 칸
     *      + W - 1 // 가로로 넘어갔을 때 추가 되는 칸
     *      + H - 1 // 세로로 넘어갔을 때 추가되는 칸
     *      + (gcd - 1) // 각 수의 최대 공약수 - 1로 꼭짓점을 넘어 가로, 세로 동시 계산 방지
     */

        @Test
    fun test() {
        val W= 8
        val H = 12
        val result = 80L

        assertEquals(result, solution(W, H))

    }

    fun solution(w: Int, h: Int): Long
    = w.toLong() * h.toLong() - (1 + (w.toLong() - 1) + (h.toLong() - 1) - (gcd(w.toLong(), h.toLong()) - 1))


        private fun gcd(a: Long, b: Long): Long {
            val larger = if(a > b) a else b
            val smaller = if(a > b) b else a
            if(smaller == 0L) return larger
            return gcd(smaller, larger % smaller)
        }
}