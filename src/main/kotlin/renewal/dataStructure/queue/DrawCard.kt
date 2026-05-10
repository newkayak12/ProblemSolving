package renewal.dataStructure.queue

import org.junit.jupiter.api.Test

class DrawCard {
    /**
     * 📘 실습 문제 – 카드 뽑기 시뮬레이션 (BOJ 2161 변형)
     *
     * ⸻
     *
     * 🧾 문제 설명
     *
     * 1부터 N까지 번호가 적힌 카드가 1번이 제일 위, N번이 제일 아래로 놓여 있습니다.
     * 다음 과정을 반복합니다:
     * 	1.	제일 위에 있는 카드를 버린다
     * 	2.	그 다음 위에 있는 카드를 맨 아래로 옮긴다
     *
     * 이 과정을 반복해 카드가 한 장 남을 때까지 진행했을 때,
     * 버린 카드들을 순서대로 출력하고, 마지막 남은 카드도 출력하세요.
     *
     * ⸻
     *
     * 📥 입력 형식
     * 	•	첫째 줄에 카드의 개수 N이 주어집니다. (1 ≤ N ≤ 1000)
     *
     * 예:
     * 7
     *
     * 📤 출력 형식
     * 	•	첫째 줄: 버린 카드 번호를 순서대로 출력 (공백 구분)
     * 	•	둘째 줄: 마지막에 남은 카드 번호 출력
     *
     * 예:
     * 1 3 5 7 4 2
     * 6
     */


    @Test
    fun solution() {
        val size = 7
        val expectedDiscarded = listOf(1,3,5,7,4,2)
        val expectedResult = 6

    }
}