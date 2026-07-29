package live.threesixnine.rule


class SeoulRuleOf369(
    val rule: List<Char> = THREE_SIX_NINE
): RuleOf369 {

    override fun isClapped(parsedNumber: String) = parsedNumber.any{ rule.contains(it) }

    override fun howToTimes(parsedNumber: String) = DEFAULT_CLAP_COUNT

    companion object {
        private val THREE_SIX_NINE = listOf<Char>('3', '6', '9')
        private const val DEFAULT_CLAP_COUNT = 1
    }
}