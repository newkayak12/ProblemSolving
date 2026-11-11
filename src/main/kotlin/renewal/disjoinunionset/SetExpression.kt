package renewal.disjoinunionset

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class SetExpression {
    /**
     * 기본 문제 1: 집합의 표현
     * 문제 설명
     * 초기에 n+1개의 집합 {0}, {1}, {2}, ... , {n}이 있다. 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
     * 집합을 표현하는 프로그램을 작성하시오.
     * 입력
     *
     * 첫째 줄에 n(1 ≤ n ≤ 1,000,000)과 m(1 ≤ m ≤ 100,000)이 주어진다.
     * m개의 줄에는 각각의 연산이 주어진다.
     * 합집합 연산은 0 a b 의 형태로 입력된다. (a와 b를 같은 집합으로 합친다)
     * 같은 집합에 포함되어 있는지 확인하는 연산은 1 a b 의 형태로 입력된다.
     *
     * 출력
     * 1로 시작하는 입력에 대해서 a와 b가 같은 집합에 포함되어 있으면 "YES", 아니면 "NO"를 출력한다.
     * 예제
     * 입력:
     * 7 8
     * 0 1 3
     * 1 1 7
     * 0 7 6
     * 1 7 1
     * 0 3 7
     * 1 3 7
     * 0 4 2
     * 1 1 2
     *
     * 출력:
     * NO
     * NO
     * YES
     * NO
     */

    private inner class DisjointUnionSet(size: Int) {
        val parent = IntArray(size + 1) { it }
        val rank = IntArray(size + 1) { 0 }

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }

            return parent[x]
        }

        fun union(a: Int, b: Int): Boolean {
            val rootA = parent[a]
            val rootB = parent[b]

            if (rootA == rootB) false

            when {
                rank[rootA] > rank[rootB] -> parent[rootB] = rootA
                rank[rootA] < rank[rootB] -> parent[rootA] = rootB
                else -> {
                    parent[rootB] = rootA
                    rank[rootA]++
                }
            }


            return true
        }
    }


    @Test
    fun solution() {
        val n = 7
        val m = 8
        val action = arrayOf(
            intArrayOf(0, 1, 3),
            intArrayOf(1, 1, 7),
            intArrayOf(0, 7, 6),
            intArrayOf(1, 7, 1),
            intArrayOf(0, 3, 7),
            intArrayOf(1, 3, 7),
            intArrayOf(0, 4, 2),
            intArrayOf(1, 1, 2),
        )
        val expected = arrayOf(
            "NO",
            "NO",
            "YES",
            "NO",
        )

        assertArrayEquals(expected, useDisjointUnionSet(n, m, action))
    }

    private fun useDisjointUnionSet(n: Int, m: Int, action: Array<IntArray>): Array<String> {
        val unionSet = DisjointUnionSet(n)
        val result = mutableListOf<String>()

        for(element in action) {
            val (action, a, b) = element

            when {
                action == 0 -> {
                    unionSet.union(a, b)
                }
                else -> {
                    val aFind = unionSet.find(a)
                    val bFind = unionSet.find(b)

                    result.add(if(aFind == bFind) "YES" else "NO")
                }
            }
        }


        return result.toTypedArray()
    }
}