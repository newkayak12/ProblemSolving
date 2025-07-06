package renewal.mathmetics

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals

class PrimeNumber {
    /**
     * 📘 소수 판별 – 기초 문제
     *
     * ⸻
     *
     * ✅ 문제: 소수 판별 (BOJ 1978 변형)
     *
     * 문제 설명
     * N개의 자연수가 주어졌을 때, 그 중에서 소수가 몇 개인지 판별하세요.
     *
     * 입력 형식
     * - 첫째 줄에 자연수 N이 주어집니다. (1 ≤ N ≤ 100)
     * - 둘째 줄에 N개의 자연수가 공백으로 구분되어 주어집니다.
     * - 각 수는 1 이상 1,000 이하입니다.
     *
     * 출력 형식
     * - 입력된 수 중에서 소수의 개수를 출력합니다.
     *
     * 예제 입력
     * 4
     * 1 3 5 7
     *
     * 예제 출력
     * 3
     */

    @Test
    fun solution(){
        val targets = listOf(1,3,5,7)
        val expected = 3
        assertEquals(expected, targets.count{isPrimeNumber(it)})
        assertEquals(expected, sieveOfEratosthenes(targets.max()).count{targets.contains(it)})
    }

    private fun isPrimeNumber(number: Int): Boolean {
        if( number <= 1) return false
        for(i in 2..sqrt(number.toDouble()).toInt()) {
            if(number % i == 0) return false
        }


        return true
    }
    private fun sieveOfEratosthenes(number: Int): List<Int> {
        if(number < 2) return emptyList()
        val prime = BooleanArray(number + 1) {true}
        prime[0] = false
        prime[1] = false


        for(i in 2 .. sqrt(number.toDouble()).toInt()) {
            if(prime[i]) {
                for(j in (i * i) .. number step i){
                    prime[j] = false
                }
            }
        }

        return (2..number).filter { prime[it] }
    }

}