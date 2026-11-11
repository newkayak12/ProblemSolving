package programmers.lv2

import org.junit.jupiter.api.Test
import java.lang.StrictMath.pow
import kotlin.test.assertEquals

class `예상 대진표` {
    /**
     * n -> a, b
     *
     * 8 -> 4, 7
     * 4 -> 2, 4
     * 2 -> 1, 2
     */

    @Test
    fun solution1() {
        val N = 8
        val A = 4
        val B = 7
        val expected = 3

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution2() {
        val N = 256
        val A = 3
        val B = 4
        val expected = 1

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution3() {
        val N = pow(2.0, 8.0).toInt()
        val A = 1
        val B = pow(2.0, 8.0).toInt()
        val expected = 8

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution4() {
        val N = 2
        val A = 1
        val B = 2
        val expected = 1

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution5() {
        val N = pow(2.0, 10.0).toInt()
        val A = 1
        val B = pow(2.0, 10.0).toInt()
        val expected = 10

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution6() {
        val N = pow(2.0, 10.0).toInt()
        val A = pow(2.0, 10.0).toInt()
        val B = 1
        val expected = 10

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution7() {
        val N = pow(2.0, 10.0).toInt()
        val A = pow(2.0, 10.0).toInt()
        val B = 2
        val expected = 10

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }

    @Test
    fun solution8() {
        val N = 16
        val A = 16
        val B = 3
        val expected = 4

        assertEquals(expected, calculate(N, A, B))
        assertEquals(expected, completeBinaryTreeParent(N, A, B))
//        assertEquals(expected, completeBinaryTreeDepth(N, A, B))
        assertEquals(expected, completeBinaryTreeBit(N, A, B))
    }


//    1 2 3 4

    private fun calculate(n: Int, a: Int, b: Int): Int { //complete binary tree check siblings
        var round = 1
        var playerCount = n
        var playerA = minOf(a, b)
        var playerB = maxOf(a, b)

        while(playerCount > 2 && (playerB - playerA != 1 || playerB % 2 != 0)) {
            round ++
            playerCount /= 2

            if(playerA % 2 != 0) {
                playerA = (playerA + 1) / 2
            }
            else playerA /= 2

            if(playerB % 2 != 0) {
                playerB = (playerB + 1) / 2
            }
            else playerB /= 2
        }

        return round
    }

    private fun completeBinaryTreeParent(n: Int, a: Int, b: Int): Int {
        var playerA = a
        var playerB = b
        var round = 1

        while((playerA + 1) / 2 != (playerB + 1) / 2 ) {
            playerA = (playerA + 1) / 2
            playerB = (playerB + 1) / 2
            round++
        }

        return round
    }

    private fun completeBinaryTreeDepth(n: Int, a: Int, b: Int): Int { //일반적 LCA 만 동시에 올라가는게 아니라 더 많은 수가 구해져서 적합하지 않다.
        var playerA = a
        var playerB = b
        var round = 1

        while(playerB != playerA) {
            if(playerA > playerB)  playerA = (playerA + 1) / 2
            else playerB = (playerB + 1) / 2

            round ++
        }

        return round
    }

    private fun completeBinaryTreeBit(n: Int, a: Int, b: Int): Int {
        return (a - 1 xor b - 1).toString(2).length
    }

}