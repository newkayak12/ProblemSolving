package renewal.greedy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Kruskal {

    /**
     * <pre>
     * 예시 기초 문제
     * 문제: 최소 신장 트리 (크루스칼 알고리즘)
     *
     * 정점의 개수 n과 간선들의 정보가 주어집니다.
     * 이 그래프는 무방향 그래프이며, 각 간선은 두 정점과 가중치를 포함합니다.
     *
     * 모든 정점을 연결하는 최소 신장 트리(MST)를 만들 때, MST에 포함되는 간선들의 가중치 합을 구하세요.
     * ⸻
     *
     * 입력
     * 	•	첫째 줄에 정점의 개수 n과 간선의 개수 m이 주어집니다. (1 ≤ n ≤ 1000, 1 ≤ m ≤ 10000)
     * 	•	다음 m개의 줄에는 간선 정보 u v w가 주어집니다.
     * 	•	u와 v는 간선이 연결하는 두 정점 (0 ≤ u, v < n)
     * 	•	w는 가중치 (1 ≤ w ≤ 10000)
     * ⸻
     *
     * 출력
     * 	•	MST에 포함된 간선 가중치 합을 출력하세요.
     * ⸻
     *
     * 예시 입력
     * vertex: 4
     * edge: 5
     * 0 1 1
     * 0 2 4
     * 1 2 2
     * 1 3 6
     * 2 3 3
     *
     * 예시 출력
     * 6
     * </pre>
     */
    @Test
    fun solution() {
        val vertex = 4
        val edge = 5
        val connection = arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 2, 4),
            intArrayOf(1, 2, 2),
            intArrayOf(1, 3, 6),
            intArrayOf(2, 3, 3),
        )
        val expected = 6
        /**
         * Q.
         * 1. 어떤 노드에서 시작하면 좋을까?
         *    -> 가중치로 sort하고 낮은 곳부터 시작
         *    -> 위 예시로는 0이나 1
         *      -> 다음 노드의 시작점을 확인해서 이어지는 쪽 반대쪽에서 시작하면 될 것 같은데?
         *         -> 0 -> 1 -> 2 -> 3 (1 + 2 + 3)
         *         => A.시작점은 상관 없다. 연결만 확인한다.
         *      -> 그럼 다음 것 체크했을 때 안 이어져 있으면??
         *      -> 그 다음 element로 넘어가나?
         *          -> 끝까지 안되면?
         *          => 연결 그래프라는 전제하에 시작한다.
         *    -> 그럼 사이클 체크는?
         *      -> 다음 것 찾을 때마다 진행하는가?
         * 2. 노드를 지날 때마다 vertex를 줄여서 모두 순회했는지 체크
         * 3. vertex + 1 = edge
         *
         * Pseudo
         *     connection으로 sort
         *     var result = 0
         *     for( i in connection )
         *      resutl += if( DSU에 union ) i[2] else 0
         */


        assertEquals(expected, kruskal(vertex, connection))
    }

    private fun kruskal(vertex: Int, connection: Array<IntArray>): Int {
        val dsu = DisjointSetUnion(vertex)
        connection.sortBy { it[2] }
        var result = 0

        for (i in connection) {
            result += if (dsu.union(i[0], i[1])) i[2] else 0
        }


        return result
    }
}


/**
 * <pre>
 *     ## 최소 신장 트리
 *
 *  - 정의
 *  가중치가 있는 연결 그래프에서 모든 정점(vertex)를 포함하면서
 *  사이클이 없고(트리) 간선(edge)들의 가중치 합이 최고가 되는 부분 그래프
 *
 *  > 가중치 : 그래프에서 간선에 붙는 숫자 값. 두 노드를 연결하는 비용, 거리, 시간, 용량 등 문제에 따라 여러 의미를 가질 수 있다.
 *  > 정점: 그래프에서 하나의 점이나 노드
 *  > 간선: 두 정점을 연결하는 선
 *  > 연결 그래프: 그래프 내의 모든 정점이 서로 연결되어 있어서 어느 한 정점에서 다른 모든 정점으로 경로가 존재하는 그래프
 *  > 사이클 : 그래프에서 한 정점에서 출발해서 다른 정점들을 거쳐서 다시 원래 정점으로 돌아오는 경로가 존재하는 경우
 *  > 트리 : 사이클이 없는 연결 그래프
 *
 * 예)
 * A -- B -- C
 *      |
 *      D
 *
 *  - 주요 특징
 *  1. 모든 정점을 연결한다.
 *  2. 사이클이 없어야 한다.
 *  3. 간선 가중치 합 최소
 *  4. 무방향 그래프다.
 * </pre>
 *
 * ---
 *
 * <pre>
 *  ## 크루스칼 알고리즘
 *  - 주어진 연결 그래프에서 최소 신장 트리(MST)를 찾는 것
 *  - 가장 가중치가 작은 간선부터 차례대로 선택하되, 사이클을 만들지 않는 간선만 포함
 *  - 주요 절차
 *      1. 모든 간선을 가중치 오름차순으로 정렬
 *      2. 순서대로 간선 선택
 *      3. 선택한 간선이 사이클을 만들면 제외
 *      4. 모든 정점이 연결될 때까지 반복
 *  - 사이클
 *      1. 유니온 파인드(Disjoint Set Union, DSU) 자료구조를 사용해서 빠르게 판별
 *      2. 두 정점이 이미 같은 집합이면 사이클 발생
 * </pre>
 * > ## DSU: 서로소 집합 자료 구조?
 * > 1. 여러 개의 원소가 있을 때,
 * > 2. 각 원소가 속한 집합을 관리하고
 * > 3. 두 집합을 합치거나(Union)
 * > 4. 특정 원소가 어떤 집합에 속하는지 찾는 연산을 빠르게 처리하는 자료구조
 * > ## 주요 연산
 * > 1. find(x)
 * >   - 원소 x가 속한 집합의 대표자를 찾는다.
 * >   - 같은 집합에 속한 원소들은 같은 대표자를 가진다.
 * > 2. Union(a,b)
 * >   - 원소 a가 속한 집합과 원소 b가 속한 집합을 합친다.
 * >   - 두 원소가 같은 집합이면 합치지 않고 사이클 발생을 막는다.
 */
class DisjointSetUnion(size: Int) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size) { 0 }

    private fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(a: Int, b: Int): Boolean {
        val rootA = find(a)
        val rootB = find(b)

        if (rootA == rootB) return false

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA
        } else {
            parent[rootB] = rootA
            rank[rootA]++
        }

        return true
    }
}