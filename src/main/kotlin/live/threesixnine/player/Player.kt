package live.threesixnine.player

import live.threesixnine.rule.RuleOf369

class Player(
    private val name: String,
    private val clapSound: String = DEFAULT_CLAP,
    private val rule: RuleOf369
) {

    fun do369(number: Int): Int {
        val parsedNumber = number.toString()

        return if(rule.isClapped(parsedNumber)) doClap(parsedNumber)
        else {
            speakNumber(parsedNumber)
            0
        }
    }

    private fun doClap(parsedNumber: String): Int {
        val count = rule.howToTimes(parsedNumber)
        val message = clapSound.repeat(count)
        speak(message)
        return count
    }

    private fun speakNumber(number: String) = speak(number)

    private fun speak(sentence: String) = println("$name: $sentence")

    companion object {
        private const val DEFAULT_CLAP = "clap"
    }
}