package live.threesixnine

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import live.threesixnine.accumulator.ClapCountAccumulator
import live.threesixnine.game.BusanGame
import live.threesixnine.game.Game
import live.threesixnine.game.SeoulGame
import kotlin.test.assertEquals


fun main() {

    val executors = Executors.newWorkStealingPool()
    val totalCount = 100
    val expectedResult = 111L
    val playerNames = arrayOf(
        "광수",
        "재석",
        "석진",
        "하하"
    )
    val clapCountAccumulator = ClapCountAccumulator()
    val games: Array<Game> = arrayOf(
        SeoulGame(totalCount, playerNames, clapCountAccumulator),
        BusanGame(totalCount, playerNames, clapCountAccumulator),

    )

    val futures = games.map { executors.submit(it) }
    executors.shutdown()
    executors.awaitTermination(10, TimeUnit.SECONDS)

    println("Total clapped Count: ${clapCountAccumulator.get()}")
    assertEquals(expectedResult, clapCountAccumulator.get())
}