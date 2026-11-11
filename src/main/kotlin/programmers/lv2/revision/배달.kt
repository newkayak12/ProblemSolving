package programmers.lv2.revision

import java.util.PriorityQueue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class 배달 {
    /**
     * BFS
     *
     *  val pq = PriorityQueue<Pair<Int, Int>>({ score가 작은 순으로 정렬 })
     *  val roadMap = MutableMap<Int, MutableList<Pair<Int, Int>>()
     *  val visit = mutableSetOf()
     *
     *  for( roadSpec in road )
     *    val start = road[0]
     *    val end = road[1]
     *    val score = road[2]
     *
     *    roadMap[start] = end to score
     *    roadMap[end] = start to score
     *
     *
     *   pq.add (1 to 0)
     *
     *   while( pq.isNotEmpty )
     *
     *     val (nextNode, score) = pq의 첫 번째
     *     if(visit에 있거나 score가 k보다 크면) continue
     *
     *
     *     visit에 node 추가
     *
     *     for( roadElement : roadMap[node])
     *       val (nextNode, nextScore) = roadElement
     *       if(nextScore + score가 k보다 크면 ) continue
     *       pq에 roadElement 추가
     *
     *
     */
    fun solution2(N: Int, road: Array<IntArray>, k: Int): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second })) // node to score
        val roadMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        val distance = IntArray(N) { Int.MAX_VALUE }

        for (roadSpec in road) {
            val firstNode = roadSpec[0]
            val secondNode = roadSpec[1]
            val score = roadSpec[2]
            roadMap.merge(firstNode, mutableListOf(secondNode to score), { a, b ->
                a.addAll(b)
                a
            })

            roadMap.merge(secondNode, mutableListOf(firstNode to score), { a, b ->
                a.addAll(b)
                a
            })
        }

        pq.add(1 to 0)

        while (pq.isNotEmpty()) {
            val (node, score) = pq.poll()
            if (distance[node - 1] < score) continue


            for (roadElement in roadMap[node]!!) {
                val (nextNode, nextScore) = roadElement
                val newScore = score + nextScore

                if(newScore < distance[nextNode - 1]) {
                    distance[nextNode - 1] = newScore
                    pq.add(nextNode to newScore)
                }
            }
        }

        return distance.count { it != Int.MAX_VALUE && it <= k}
    }


    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second })) // node to score
        val roadMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        val visit = mutableSetOf<Int>()

        for (roadSpec in road) {
            val firstNode = roadSpec[0]
            val secondNode = roadSpec[1]
            val score = roadSpec[2]
            roadMap.merge(firstNode, mutableListOf(secondNode to score), { a, b ->
                a.addAll(b)
                a
            })

            roadMap.merge(secondNode, mutableListOf(firstNode to score), { a, b ->
                a.addAll(b)
                a
            })
        }

        pq.add(1 to 0)

        while (pq.isNotEmpty()) {
            val (node, score) = pq.poll()
            if (node in visit || score > k) continue

            visit.add(node)

            for (roadElement in roadMap[node]!!) {
                val (nextNode, nextScore) = roadElement

                if (nextScore + score > k) continue
                pq.add(nextNode to (score + nextScore))
            }
        }

        return visit.size
    }


    @Test
    fun test1() {
        val N = 5
        val road = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 3, 3),
            intArrayOf(5, 2, 2),
            intArrayOf(1, 4, 2),
            intArrayOf(5, 3, 1),
            intArrayOf(5, 4, 2)
        )
        val K = 3
        val result = 4

        assertEquals(result, solution(N, road, K))
        assertEquals(result, solution2(N, road, K))
    }

    @Test
    fun test2() {
        val N = 6
        val road = arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 2),
            intArrayOf(3, 4, 3),
            intArrayOf(3, 5, 2),
            intArrayOf(3, 5, 3),
            intArrayOf(5, 6, 1)
        )
        val K = 4
        val result = 4

        assertEquals(result, solution(N, road, K))
        assertEquals(result, solution2(N, road, K))
    }

}