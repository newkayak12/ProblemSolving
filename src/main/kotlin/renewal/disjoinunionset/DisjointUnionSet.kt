package renewal.disjoinunionset

class DisjointUnionSet(size: Int) {
    private val parents = IntArray(size) { it }
    private val rank = IntArray(size) { 0 }


    fun find(x: Int): Int {
        if (parents[x] != x) {
            parents[x] = find(parents[x])
        }

        return parents[x]
    }

    fun union(a: Int, b: Int): Boolean {
        val rootA = find(a)
        val rootB = find(b)

        if (rootA == rootB) return false

        when {
            rank[rootA] < rank[rootB] -> parents[rootA] = rootB
            rank[rootA] > rank[rootB] -> parents[rootB] = rootA
            else -> {
                parents[rootB] = rootA
                rank[rootA] ++
            }
        }

        return true
    }
}