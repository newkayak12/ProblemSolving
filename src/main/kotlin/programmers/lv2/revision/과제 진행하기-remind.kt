package programmers.lv2.revision

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `과제 진행하기-remind` {

    /**
     * 새로운 과제를 시작할 시각이 되었을 때, 기존 진행 중이던 과제가 있다면(안끝났다면) 과제를 멈추고 새로운 과제를 시작
     * -> 기존 과제를 어디에 저장한다.
     *
     * 진행 중이던 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춰둔 과제를 이어서 진행합니다.
     * 과제를 끝낸 시각에 새로 시작해야 하는 과제와 잠시 멈춰둔 과제가 모두 있다면 새로 시작해야 하는 과제부터 진행합니다
     * -> 기존 종료 시간, 스킵한 과제 있고, 시작 시간이 같으면 새로운 것 진행
     * -> 멈춰둔거, 새 것 부터 -> 스택
     *
     * 제한
     * 3 ~ 1000 개 plans
     * plans = [name, start, playtime]
     * 시작 시간, 기간
     *
     * name은 2 ~ 10 (길지 않음) + 중복 없음 + 소문자 -> 별 의미 없어보임
     * start hh:mm 형태 -> 파싱이 필요할지도 + 모든 과제 시작은 겹치지 않음 시작 시간은 00:00 ~ 23:59 -> 다음 날 개념이 있을 수 있음 음... 종료 시간을 계산한다면 int로 처리하면 (시 * 60 + 분)
     * playtime 1 ~ 100 (0분 없음)
     *
     * 배열 시간 순 정리 X -> 정렬 필요
     *
     *
     * 1. 시간 파싱
     * 2. 시간 정렬
     * 3. stack 생성
     *   1. 뭘 저장할 것인가?
     *   2. 4-3 아... Index와 elapsedPlayTime이면 낫지 않을까?
     * 4. 리소스
     *   1. 시간이 흐른다.
     *   2. 현재 진행 중인 작업의 playTime 만큼 흐른다(그대로 끝난다면)
     *   3. 그대로 안끝나면 기존 작업은 playtime - (다음 작업 시작 - 현재 작업 시작) 만큼 남는다.
     * 5. while 처리
     *   1. 현재 시간, 다음 시간 비교해야 한다.
     *   2. 그럼 인덱스를 관리 별도로 해야 할 것 같다.
     *   3. 차라리 for로 돌리는게 나을지도 모른다
     * 6. 이러면 차라리 todo.addLast를 for 안에서 하는게 흐름에 자연스럽다.
     *   1. 근데 처음에 바로 덱에 넣는가?
     *   2. 어... 이러면 차라리 유지하는게 나을 수도 있는데 변수로 따로 뺴서?
     *   3. 그래야 다음이랑 비교할 수 있을거 같은데 아 덱에 넣으면 다음에 이전 것 꺼내 볼거고
     *      1. 꺼내서 시간 지나면 버리면 되고
     *      2. 시간 안지났으면 냅두고 다음 진행 것 넣으면 될거고
     *      3. 그럼 덱의 last는 현재가 될거 같은데?
     *
     *  1-try 중간에 뒤집히는 경우가 있다
     *      1. korean, english, math -> korean(700, 30), english(730, 20)
     *      2. science, music, history, computer -> music(740, 40 = 780), computer(750, 100, 850), science(760, 50, 810), history(840, 30, 870)
     *          순서를 생각해보자
     *          1t -> TOP: music
     *          2t -> TOP: music(30) | computer -> while에 걸려서 그냥 소모할 떄까지 돈다.
     *
     *  2-try break 처리를 해서 while로 그냥 소모하는걸 방지
     *      1. music(740, 40 = 780), computer(750, 100, 850), science(760, 50, 810), history(840, 30, 870)
     *      > 1 [(0, 40, music)]
     *      > 2 [(0, 30, music), (1, 100, computer)]
     *      > 3 [(0, 30, music), (1, 90, computer), (2, 50, science)]
     *      > 3 [(0, 30, music), (1, 90, computer)] -> science out + history가 처리되어야 하는데... 여기서 그냥 diff 처버리면 무조건 이전 것들이 처리되는데 -> 보정처리 해야함.. 그러니까 흐르는 시간 처리를 해야할듯? 아... 처리 시간 만큼 더할까? diff?
     *      > 3 [(0, 30)]
     *  ! 흐르는 시간 처리가 관건이다. 이건..
     *  ! 그러니까 2-try의 2번 예시 같이 줄줄이 끝나는걸 막으려면..
     *  ! while 안에서 처리를 해야 맞는거 같은데? 왜냐하면 갱신하면서 시간이 흐를테니까 아.... previous 랑 현재랑 비교해서 preivous 가 끝나면 그냥 playtime 만큼 시간이 지난거고 그게 아니면 diff 만큼 같은데?
     *       > 0 [] -> 0분
     *       > 1 [(0, 40, music)] -> diff 잴 수 있는거 아님 이 타이밍? -> 10분
     *       > 2 [(0, 30, music), (1, 100, computer)] -> 10분 지났지 -> 20분
     *       > 3 [(0, 30, music), (1, 90, computer), (2, 50, science)] 그럼 사이언스는; 830에 끝나네? -> 사이언스 플레이타임까지 정하면 70분 -> 920분 걸리네 컴퓨터 히스토리 정리하고
     *       > diff 처리로 증분 처리하고 시작 시간 보정하는 수밖에 없어 보임
     *       > 음... 그냥 시간이 흘렀다고 생각하면 될 것 같음
     *       > test2는 통과임 나머지 flush까지
     *       > 문제는 test3이 ccc, bbb, aaa
     * try-3
     *  aaa(720, 20, 740), bbb(730, 30, 760), ccc(760, 10, 770)
     *       > 1 [(0, 10)] 10
     *       > 2 [(0, 10), (1, 10)] 30 -> bbb 졸업 근데.. 왜... ccc 부터 시작일까?
     *
     *
     * ! 아 아니다 이게 시간 개념이 앞쪽부터 보정으로 하면 뒤쪽 시간은 앞쪽 계산분까지 다 포함되어 이 뒤랑 비교하니까 문제가 있네
     * ! 가까운 시간이라면, 시간이 적고 인덱스가 멀어질 때마다 커져야 하네
     * ! 그러면 현재 vs. 다음 비교할 때 적고 현재가 그 앞으로 당겨질 때마다 더해져야 하네?
     * ! 단조 증가하면 되는게 아니네 시간이
     *
     * try -3 예시는 전부 성공
     * !그래서 현재와 이전들 간의 차이를 누적으로 비교해야 하고
     * !아 단순히 시작과 끝을 빼면 안되는거 같은데? -> flowTime += (currentTime - previousTime) 이게 문제야
     * previousTime보댜 오래 걸리는 친구들이 있을 수 있는데 그걸 무시한 듯?
     *
     * 120(100), 130(100), 240(50) -> 130, 120, 240
     * 120(100) |  [130(100), 240] -> 110차이
     * 120(100 + 110), 240(50) -> 130, 240, 120이 되어 버림 -> 이거 정상으로 계산하면 330이 아니라 230, 240 간 비교가 되어야 함  그러면... 이전의 종료 시간 - 현재 시작 시간 사이 차이?
     *
     * 120(100), 130(100), 140(100), 260(50) ->  140(20), 130(30), 120 누적이 10씩이니까
     *
     *
     * 아.. flowtime 계산이 어렵네
     *
     * 그냥 리소스로 보면 되네
     *
     */

    private fun parse(time: String) = time.split(":").let { (it[0].toInt() * 60 + it[1].toInt()) }

    fun solution(plans: Array<Array<String>>): Array<String> {
        val todo = ArrayDeque<Pair<Int, Int>>()
        val result = mutableListOf<String>()
        val parsedPlans = plans.map {
            val (subject, time, playtime) = it
            Triple(subject, parse(time), playtime.toInt())
        }
            .sortedBy { it.second }

        for ((currentIndex, value) in parsedPlans.withIndex()) {
            val (_, currentStartTime, currentPlayTime) = value

            var resource = if (todo.isEmpty()) 0 else currentStartTime - parsedPlans[currentIndex - 1].second

            while (todo.isNotEmpty() &&  resource > 0) { // 내부 소비 다하면 끝이고
                val (previousIndex, previousRunningTime) = todo.removeLast() // 뺴버리자


                if (previousRunningTime <= resource) {
                    result.addLast(parsedPlans[previousIndex].first) //종료
                    resource -= previousRunningTime
                } else {
                    todo.addLast(previousIndex to previousRunningTime - resource)
                    resource = 0
                }
            }


            todo.addLast(currentIndex to currentPlayTime)
        }

        while (todo.isNotEmpty()) {
            val (index, _) = todo.removeLast()
            result.add(parsedPlans[index].first)
        }

        return result.toTypedArray()
    }


    @Test
    fun test1() {
        val plans = arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40")
        )
        val result = arrayOf("korean", "english", "math")

        assertArrayEquals(result, solution(plans))
    }

    @Test
    fun test2() {
        val plans = arrayOf(
            arrayOf("science", "12:40", "50"),
            arrayOf("music", "12:20", "40"),
            arrayOf("history", "14:00", "30"),
            arrayOf("computer", "12:30", "100"),
        )
        val result = arrayOf("science", "history", "computer", "music")

        assertArrayEquals(result, solution(plans))
    }

    @Test
    fun test3() {
        val plans = arrayOf(
            arrayOf("aaa", "12:00", "20"),
            arrayOf("bbb", "12:10", "30"),
            arrayOf("ccc", "12:40", "10"),
        )
        val result = arrayOf("bbb", "ccc", "aaa")

        assertArrayEquals(result, solution(plans))
    }


}