package live.threesixnine.rule


class BusanRuleOf369(
    val rule: List<Char> = THREE_SIX_NINE
): RuleOf369 {

    override fun isClapped(parsedNumber: String) = parsedNumber.any{ rule.contains(it) }

    override fun howToTimes(parsedNumber: String) = parsedNumber.count { rule.contains(it) }

    companion object {
        private val THREE_SIX_NINE = listOf<Char>('3', '6', '9')
    }
}