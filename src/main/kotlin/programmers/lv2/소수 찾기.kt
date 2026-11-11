package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals

class `소수 찾기` {

    @Test
    fun solution1() {
        val numbers = "17"
        val expected = 3

        assertEquals(expected, calculateUseRecursive(numbers))
        assertEquals(expected, calculateUseStack(numbers))

    }

    @Test
    fun solution2() {
        val numbers = "011"
        val expected = 2

        assertEquals(expected, calculateUseRecursive(numbers))
        assertEquals(expected, calculateUseStack(numbers))

    }

    private fun calculateUseRecursive(numbers: String): Int {
        val array = numbers.split("")
        val set = mutableSetOf<Int>()

        recursive(array, BooleanArray(array.size), "", set)

        return set
            .count { isPrime(it) }
    }

    private fun recursive(arr: List<String>, visit: BooleanArray, str: String, set: MutableSet<Int>): String {

        if (visit.reduce { acc: Boolean, b: Boolean -> acc && b }) return str

        for ((i, v) in arr.withIndex()) {
            if (visit[i]) continue
            visit[i] = true
            if(str.isNotBlank()){
                set.add((str+v).toInt())
            }
            recursive(arr, visit, str + v, set)
            visit[i] = false
        }

        return str
    }

    private fun calculateUseStack(numbers: String): Int {
        val array = numbers.split("")

        return stack(array)
    }

    private fun stack(arr:List<String>): Int{
        val stack = ArrayDeque<Pair<String, BooleanArray>>()
        val set = mutableSetOf<String>()
        stack.addLast("" to BooleanArray(arr.size))

        while(stack.isNotEmpty()) {
            val (str, booleanArray) = stack.removeLast()
            set.add(str)

            for((i,v) in arr.withIndex()) {
                if(booleanArray[i]) continue
                val copiedBooleanArray = booleanArray.copyOf()
                copiedBooleanArray[i] = true
                stack.addLast(str+v to copiedBooleanArray)
            }
        }

        return set
            .filter { it.isNotBlank() }
            .map { it.toInt() }
            .distinct()
            .count { isPrime(it) }
    }



    private fun isPrime(number: Int): Boolean {
        if(number <= 1) return false
        for(i in 2 .. sqrt(number.toDouble()).toInt()) {
            if(number % i == 0) return false
        }

        return true
    }

}