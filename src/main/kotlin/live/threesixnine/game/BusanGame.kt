package live.threesixnine.game

import live.threesixnine.accumulator.ClapCountAccumulator
import live.threesixnine.player.Player
import live.threesixnine.rule.BusanRuleOf369

class BusanGame(
    totalCount: Int,
    playerName: Array<String>,
    clapCountAccumulator: ClapCountAccumulator
) : Game(
    count = totalCount,
    rule = BusanRuleOf369(),
    clapCountAccumulator = clapCountAccumulator
) {

    private var turn = 0
    private val players: Array<Player>

    init {
        players = playerName
            .map { Player(name = "$LOCATION-$it",  rule = rule) }
            .toTypedArray()
    }

    override fun play() {
        for (number in 1..count) {
            val currentPlayer = players[turn % players.size]
            val clappedCount = currentPlayer.do369(number)
            repeat(clappedCount) { clapCountAccumulator.increase() }
            turn++
        }
    }

    override fun run() = play()

    companion object {
        private const val LOCATION = "BUSAN"
    }
}