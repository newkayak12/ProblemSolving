package renewal.others.`bellman-ford`

class BellmanFord {

    fun bellmanFord(
        V: Int, //vertex
        edges: Array<IntArray>, //edges
        start: Int
    ): IntArray? {
        //1. initialize
        val dist = IntArray(V + 1) {
            if (it == start) 0
            else Int.MAX_VALUE
        }

        //repeat (v-1)
        for (index in 0..<V-1) {
            for (edge in edges) {
                val (from, to , score) = edge
                if(
                    dist[from] != Int.MAX_VALUE &&
                    dist[from] + score < dist[to]
                ) {
                    dist[to] = dist[from] + score
                }
            }
        }

        // minus cycle
        for (edge in edges) {
            val (from, to , score) = edge
            if(
                dist[from] != Int.MAX_VALUE &&
                dist[from] + score < dist[to]
            ){
                return null
            }
        }

        return  dist
    }


}