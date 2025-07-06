package renewal.memoization

class PlusOneTwoThree {
    /**
     * 🧪 문제 이름
     *
     * 1, 2, 3 더하기
     * ⸻
     * 📄 문제 설명
     * 정수 n이 주어졌을 때, 정수 1, 2, 3을 사용하여 n을 만드는 방법의 수를 구하세요.
     * 단, 숫자의 순서가 다르면 다른 경우로 칩니다.
     * 예:
     * 	•	4 = 1+1+1+1 = 1+1+2 = 1+2+1 = 2+1+1 = 2+2 = 1+3 = 3+1
     * → 총 7가지
     * ⸻
     * 📥 입력
     * 	•	정수 n (1 ≤ n ≤ 20)
     * ⸻
     * 📤 출력
     * 	•	n을 만드는 총 경우의 수 (1, 2, 3을 더해서)
     * ⸻
     * 🧪 예제
     * 입력 출력
     *  3   4
     *  4   7
     *  5   13
     *
     * 1 -> 1
     * (1)
     * 2 -> 2
     * (1+1), (2)
     * 3 -> 4
     * (1+1+1), (1+2), (2+1), (3)
     * 4 -> 7
     * (1+1+1+1), (1+1+2), (1+2+1), (2+1+1), (2+2), (1+3), (3+1)
     * 5 -> 13
     *
     * p(n) = p(n-3) + p(n-2) + p(n-1)로 점화식을 짤 수 있다.
     *
     *
     * pseudo -
     *
     * fun oneTwoThree number:Int, map: MutableMap
     *      when number
     *        1 -> 1
     *        2 -> 2
     *        3 -> 4
     *        else -> map[number] ?: run {
     *           val result = oneTwoThree(number-3,map) + oneTwoThree(number-2,map) + oneTwoThree(number-1, map)
     *           map[number] = result
     *
     *           return result
     *        }
     */
}