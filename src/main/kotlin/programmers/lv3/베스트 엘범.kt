package programmers.lv3

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `베스트 엘범` {

    fun solution(genres: Array<String>, plays: IntArray) =  genres
            .indices
            .groupBy{genres[it]}
            .entries
            .sortedByDescending { (_, value) ->  value.sumOf { plays[it] }}
            .map { (_, value) -> value.sortedByDescending { plays[it] }.take(2) }
            .flatten()
            .toIntArray()


    fun solutionNotOptimized(genres: Array<String>, plays: IntArray): IntArray {
        val genresGroup: Map<String, List<Int>> = genres
            .withIndex()
            .groupBy { it -> it.value }
            .mapValues { it -> it.value.map { it.index } }

        val playsMap: Map<Int, Int> = plays.withIndex().associate { it.index to it.value }
        val genrePlayTime: Map<String, Int> = genresGroup.mapValues { it.value.sumOf { index -> playsMap[index] ?: 0 } }


        return  genresGroup
            .entries
            .sortedByDescending { (key, _) -> genrePlayTime[key] ?: 0 }
            .map { it.value.sortedByDescending { playsMap[it] ?:0 }.take(2) }
            .flatten()
            .toIntArray()
    }


    @Test
    fun test1() {
        val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
        val plays = intArrayOf(500, 600, 150, 800, 2500)
        val result = intArrayOf(4, 1, 3, 0)

        assertArrayEquals(result, solution(genres, plays))
    }


    @Test
    fun test2() {
        val genres = arrayOf("classic", "pop", "classic", "classic", "kpop")
        val plays = intArrayOf(500, 600, 150, 800, 2500)
        val result = intArrayOf(4, 3, 0, 1)
        /**
         * kpop 2500
         * classic 1450
         * pop 600
         *
         * 4 3 0 1
         */

        assertArrayEquals(result, solution(genres, plays))
    }
}