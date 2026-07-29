package live.threesixnine.accumulator

import java.util.concurrent.atomic.AtomicLong

class ClapCountAccumulator {
    private val clapCounter = AtomicLong(0L)

    fun increase() = clapCounter.incrementAndGet()

    fun get(): Long = clapCounter.get()
}