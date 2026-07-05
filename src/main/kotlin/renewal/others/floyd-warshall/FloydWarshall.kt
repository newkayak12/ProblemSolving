package renewal.others.`floyd-warshall`

class FloydWarshall {

    fun floydWarshall(n: Int, edges: Array<IntArray>): Array<IntArray> {
        //1. 거리 테이블 & 자기 자신은 0으로 초기화
        val dist = Array(n) { outer -> IntArray(n) { inner -> if (outer == inner) 0 else Int.MAX_VALUE } }


        //2.초기 간선 정보 입력
        for (edge in edges) {
            val (a, b, cost) = edge
            dist[a][b] = cost
            //양방향이면 dist[b][a] = cost
        }

        //3. floyd-warshall
        for (k in 0 until n) { //경유
            for (i in 0 until n) { //시작
                for (j in 0 until n) { //끝

                    //중간에 가본 적이 있으면
                    if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE) {
                        dist[i][j] = minOf(dist[i][j], dist[i][k] + dist[k][j]) //비교해서 변경
                    }

                }
            }
        }

        return dist
    }
}