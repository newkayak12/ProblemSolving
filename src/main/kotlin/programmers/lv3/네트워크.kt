package programmers.lv3

import com.navercorp.fixturemonkey.kotlin.expression.root
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 네트워크 {


    @Test
    fun test1() {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 1),
        )
        val result = 2

        assertEquals(result, solution(n, computers))
        assertEquals(result, solution2(n, computers))
    }

    @Test
    fun test2() {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1),
            intArrayOf(0, 1, 1),
        )
        val result = 1

        assertEquals(result, solution(n, computers))
        assertEquals(result, solution2(n, computers))
    }

    fun solution(n: Int, computers: Array<IntArray>): Int {

        val visit = BooleanArray(computers.size)
        var count = 0
        for (computerIndex in computers.indices) {
            if (visit[computerIndex]) continue
            count++
            dfs(computers, visit, computerIndex)
        }
        return count
    }

    private fun dfs(computers: Array<IntArray>, visit: BooleanArray, index: Int) {

        val stack = ArrayDeque<Int>()
        stack.addLast(index)

        while (stack.isNotEmpty()) {
            val idx = stack.removeLast()
            if (visit[idx]) continue

            visit[idx] = true
            val connections: IntArray = computers[idx]

            for (connectIndex in connections.indices) {
                if (
                    connectIndex == idx ||
                    computers[idx][connectIndex] == 0 ||
                    visit[connectIndex]
                ) continue


                stack.addLast(connectIndex)
            }
        }
    }

    fun solution2(n: Int, computers: Array<IntArray>): Int {
        val dsu = DisjointUnionSet(n)
        var count = n

        for((index, computer) in computers.withIndex()) {
            for((innerIndex, connected) in computer.withIndex()) {
                if(innerIndex == index) continue
                if(connected == 1 && dsu.union(index, innerIndex)) count --
            }

        }

        return count
    }

    class DisjointUnionSet(size: Int) {
        val parent = IntArray(size) { it }
        val rank = IntArray(size) { 0 }

        fun find(x: Int): Int {
            if(parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }


        fun union(x: Int, y: Int): Boolean {
            val rootA = find(x)
            val rootB = find(y)

            if(rootA == rootB) return false

            when {
                rank[rootA] < rank[rootB] -> parent[rootA] = rootB
                rank[rootA] > rank[rootB] -> parent[rootB] = rootA
                else -> {
                    parent[rootB] = rootA
                    rank[rootA] ++
                }
            }

            return true
        }

    }
}