package programmers.lv1

class `행렬의 덧셈` {

    fun calculate(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val answer = Array(arr1.size) { IntArray(arr1.first().size) { 0 } }

        return answer.mapIndexed { row, array ->
            array.mapIndexed { col, _ -> arr1[row][col] + arr2[row][col] }.toIntArray()
        }.toTypedArray()
    }
}