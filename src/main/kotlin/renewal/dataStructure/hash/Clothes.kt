package renewal.dataStructure.hash

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Clothes {
    /**
     * 기본 문제 2: 의상 (코디 조합)
     * 문제 설명
     * 코니는 매일 다른 옷을 조합하여 입으려고 합니다.
     * 코니가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때, 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
     * 제약조건:
     *
     * 코니는 하루에 최소 한 개의 의상은 입습니다.
     * 같은 이름을 가진 의상은 존재하지 않습니다.
     *
     * 입출력 예
     * clothes: [["yellow_hat", "headgear"], ["blue_sunglasses", "eyewear"], ["green_turban", "headgear"]]
     * return: 5
     *
     * clothes: [["crow_mask", "face"], ["blue_sunglasses", "eyewear"], ["smoky_makeup", "face"], ["silver_bracelet", "accessory"]]
     * return: 11
     */

    @Test
    fun solution1() {
        val clothes = arrayOf(
            arrayOf("yellow_hat", "headgear"),
            arrayOf("blue_sunglasses", "eyewear"),
            arrayOf("green_turban", "headgear"),
        )
        val expected = 5

        assertEquals(expected, hash(clothes))
    }

    @Test
    fun solution2() {
        val clothes = arrayOf(
            arrayOf("crow_mask", "face"),
            arrayOf("blue_sunglasses", "eyewear"),
            arrayOf("smoky_makeup", "face"),
            arrayOf("silver_bracelet", "accessory"),
        )
        val expected = 11

        assertEquals(expected, hash(clothes))
    }

    private fun hash(clothes: Array<Array<String>>): Int {
        var count = 1
        val map = clothes.groupBy { it[1] }.mapValues { entry -> entry.value.map { it[0] } }
        map.forEach { entry -> count *= (entry.value.size + 1) }
        return count - 1
    }
}