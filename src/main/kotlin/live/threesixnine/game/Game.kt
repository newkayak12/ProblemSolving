package live.threesixnine.game

import live.threesixnine.accumulator.ClapCountAccumulator
import live.threesixnine.rule.RuleOf369

abstract class Game(
    protected val count: Int,
    protected val rule: RuleOf369,
    protected val clapCountAccumulator: ClapCountAccumulator
): Runnable {

    abstract fun play()


}