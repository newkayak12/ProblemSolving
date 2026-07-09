package programmers.greedy

import java.util.PriorityQueue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `1-1 회의실 배정` {

    fun solution(meetings: Array<IntArray>): Int {
        val sortedMeetings = meetings.sortedBy { it[1] }
        var count = 0
        var lastEndTime = 0

        for (meeting in sortedMeetings) {
            val start = meeting[0]
            val end = meeting[1]

            if (lastEndTime <= start) {
                count++
                lastEndTime = end
            }
        }

        return count
    }

    @Test
    fun test1() {
        val meetings = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(1, 4),
            intArrayOf(3, 5),
            intArrayOf(0, 6),
            intArrayOf(5, 7),
            intArrayOf(3, 9),
            intArrayOf(5, 9),
            intArrayOf(6, 10),
            intArrayOf(8, 11),
            intArrayOf(8, 12),
            intArrayOf(2, 14),
            intArrayOf(12, 16),
        )
        val result = 5

        assertEquals(result, solution(meetings))
    }
}

class `1-2 보상 카드` {

    fun solution(cards: Array<IntArray>, start: Int): Int {
        val sortedCards = cards.sortedBy { it[0] }
        var sum = start
        for (card in sortedCards) {
            val start = card[0]
            val score = card[1]

            if (sum >= start) sum += score
        }

        return sum
    }

    @Test
    fun test1() {
        val cards = arrayOf(
            intArrayOf(1, 10),
            intArrayOf(3, 3),
            intArrayOf(20, 100),
            intArrayOf(2, 5),
        )
        val start = 1
        val result = 19

        assertEquals(result, solution(cards, start))
    }

}

class `1-3 체육복` {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val reserveStudent = reserve.toMutableList()
        val lostStudents = lost.toMutableList()

        reserveStudent.removeAll(lostStudents)
        lostStudents.removeAll(reserveStudent)

        var count = n - lostStudents.size


        for (lostStudent in lostStudents) {
            when {
                reserveStudent.contains(lostStudent - 1) -> {
                    reserveStudent.remove(lostStudent - 1)
                    count++
                }

                reserveStudent.contains(lostStudent + 1) -> {
                    reserveStudent.remove(lostStudent + 1)
                    count++
                }
            }
        }


        return count
    }

    @Test
    fun test1() {
        val n = 5
        val lost = intArrayOf(3, 4)
        val reserve = intArrayOf(2, 5)

        val result = 5

        assertEquals(result, solution(n, lost, reserve))
    }
}

class `2-1 보상 카드` {


    fun solution(cards: Array<IntArray>): Int {
        val pq = PriorityQueue<IntArray>(compareByDescending { it[0] })

        for (card in cards) {
            pq.add(card)
        }

        var due = 1
        var totalScore = 0

        while (pq.isNotEmpty()) {
            val element = pq.poll()
            val limit = element[0]
            val score = element[1]

            if (due <= limit) totalScore += score

            due++
        }

        return totalScore
    }

    @Test
    fun test1() {
        val cards = arrayOf(
            intArrayOf(2, 100),
            intArrayOf(1, 19),
            intArrayOf(2, 27),
            intArrayOf(1, 25),
            intArrayOf(1, 15),
        )
        val result = 127


        assertEquals(result, solution(cards))
    }


}

class `2-3 최소 비용 막대 연결` {


    fun solution(sticks: IntArray): Long {
        val pq = PriorityQueue<Int>(compareBy { it })
        for (stick in sticks) {
            pq.add(stick)
        }

        var count = 0L

        while (pq.isNotEmpty() && pq.size > 1) {
            val element1 = pq.poll()
            val element2 = pq.poll()

            val sum = element1 + element2
            count += sum

            pq.add(sum)
        }

        return count
    }

    @Test
    fun test1() {
        val sticks = intArrayOf(4, 3, 2, 6)
        val result = 29L

        assertEquals(result, solution(sticks))
    }
}

class `2-4 작업 스케쥴링` {



    fun solution(jobs: Array<IntArray>): Int {
        val pq = PriorityQueue<IntArray>(compareBy( { it[1] }, { it[0] }))
        var time = 0

        for(job in jobs) { pq.add(job) }

        while(pq.isNotEmpty()) {
            val element = pq.poll()
            val runningTime = element[0]
            val limit = element[1]

            if((runningTime + time) <= limit) {
                time += runningTime
            }
        }

        return time
    }

    @Test
    fun test1() {
        val jobs = arrayOf(
            intArrayOf(2, 2),
            intArrayOf(1, 1),
            intArrayOf(3, 3),
            intArrayOf(1, 2),
        )

        val result = 2

        assertEquals(result, solution(jobs))
    }
}

class `3-1 잔돈 거스름돈`{
    fun solution(coins: IntArray, amount: Int): Int {
        var total = amount
        var count = 0

        for(coin in coins) {

            while(total - coin >= 0) {
                total -= coin
                count++
            }
        }

        return count
    }

    @Test
    fun test1() {
        val coins = intArrayOf(500, 100, 50, 10)
        val amount = 1260

        val result = 6

        assertEquals(result, solution(coins, amount))
    }
}