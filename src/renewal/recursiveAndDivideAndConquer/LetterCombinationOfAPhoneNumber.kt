package renewal.recursiveAndDivideAndConquer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LetterCombinationOfAPhoneNumber {


    /**
     * ```
     * ⸻
     * ✅ 문제 이름
     * 숫자 패드 문자 조합 (Letter Combinations of a Phone Number)
     * ⸻
     * ✅ 문제 설명
     * 전화기 숫자 패드는 다음과 같이 문자에 매핑됩니다:
     * 2 → "abc"
     * 3 → "def"
     * 4 → "ghi"
     * 5 → "jkl"
     * 6 → "mno"
     * 7 → "pqrs"
     * 8 → "tuv"
     * 9 → "wxyz"
     * 숫자 문자열 digits가 주어졌을 때,
     * 해당 숫자에 매핑된 문자들을 이용해 만들 수 있는 모든 문자 조합을 출력하세요.
     * ⸻
     * ✅ 입력
     * 	•	숫자 문자열 digits (예: “23”)
     * 	•	길이: 1 ≤ digits.length ≤ 4
     * 	•	각 문자는 ‘2’~‘9’ 사이
     * ⸻
     * ✅ 출력
     * 	•	가능한 문자 조합 리스트 (예: [“ad”, “ae”, “af”, “bd”, …])
     *
     * 	입력
     * 	digits = "23"
     *
     * 	출력
     * 	["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
     *
     * 	```
     */
    @Test
    fun solution(){
        val inputNumber = 23
        val result = mutableListOf<String>()
        val expected =  listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")

        val map: Map<String, String>  = mapOf(
            Pair("2", "abc"),
            Pair("3", "def"),
            Pair("4", "ghi"),
            Pair("5", "jkl"),
            Pair("6", "mno"),
            Pair("7", "pqrs"),
            Pair("8", "tuv"),
            Pair("9", "wxyz"),
        )
        useBacktracking(inputNumber.toString(), 0,"", result, map)

        assertEquals(expected, result.sorted())
    }

    private fun useBacktracking(target: String, index: Int, combination: String,  list: MutableList<String>, map: Map<String, String>){


        if(target.length <= index){
            list.add(combination)
            return
        }

        val targetNumber: String = target[index].toString()
        val stringNumber: String = map[targetNumber]!!;
        for( j in 0 until stringNumber.length){
            val result = stringNumber[j]
            useBacktracking(target, index + 1, combination + result, list, map)
        }
    }
}