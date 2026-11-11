package programmers.lv2.revision

import kotlin.math.ceil

class `예상 대진표` {
    fun calculate(n: Int, a: Int, b: Int): Int {
        var aPlayer = a.toDouble()
        var bPlayer = b.toDouble()

        for(i in 1..n / 2) {
            aPlayer = ceil(aPlayer / 2)
            bPlayer = ceil(bPlayer / 2)


            if(aPlayer == bPlayer) {
                return i
            }
        }

        return -1
    }
}