package programmers.greedy

import java.util.PriorityQueue
import org.junit.jupiter.api.Test
import kotlin.math.sign
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
        val reserveStudents = reserve.toMutableList()
        val lostStudents = lost.toMutableList()

        reserveStudents.removeAll(lost.toList())
        lostStudents.removeAll(reserve.toList())

        var count = n - lostStudents.size


        for (lostStudent in lostStudents) {

            when {
                reserveStudents.contains(lostStudent - 1) -> {
                    reserveStudents.remove(lostStudent - 1)
//                    lostStudents.remove(lostStudent)
                    count++
                }

                reserveStudents.contains(lostStudent + 1) -> {
                    reserveStudents.remove(lostStudent + 1)
//                    lostStudents.remove(lostStudent)
                    count++
                }
            }
        }


        println(
            """
            reserveStudent ${reserveStudents}
            lostStudents ${lostStudents}
        """.trimIndent()
        )
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

    @Test
    fun test2() {
        val n = 5
        val lost = intArrayOf(1, 2)
        val reserve = intArrayOf(1, 3)

        val result = 5

        assertEquals(result, solution(n, lost, reserve))
    }
}

class `2-1 보상 카드` { //Job Sequencing


    fun solution(cards: Array<IntArray>): Int {
        val maxDates = cards.maxOf { it[0] }
        val pq = cards.sortedByDescending { it[1] }.toMutableList()

        val slots = IntArray(maxDates) { 0 }
        val visit = BooleanArray(maxDates) { false }



        while (pq.isNotEmpty()) {
            val element = pq.removeFirst()
            val date = element[0]
            val score = element[1]

            for (i in date - 1 downTo 0) {
                if (visit[i]) continue

                slots[i] = score
                visit[i] = true
                break
            }
        }


        return slots.sum()
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

    @Test
    fun test2() {
        val cards = arrayOf(
            intArrayOf(1, 100),
            intArrayOf(2, 10),
            intArrayOf(2, 1),
        )
        val result = 110


        assertEquals(result, solution(cards))
    }

    @Test
    fun test3() {
        val cards = arrayOf(
            intArrayOf(3, 100),
            intArrayOf(1, 50),
            intArrayOf(1, 40),
        )
        val result = 150


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

class `2-4 작업 스케쥴링` { //Moore-Hodgson


    fun solution(jobs: Array<IntArray>): Int {
        val sortedJob = jobs.sortedBy { it[1] }
        val queue = PriorityQueue<IntArray>(compareByDescending { it[0] })
        var totalDuration = 0

        for (job in sortedJob) {
            val duration = job[0]
            val endTime = job[1]
            if (queue.isEmpty()) {
                queue.add(job)
                totalDuration += duration
            } else {
                when {
                    totalDuration + duration <= endTime -> {
                        queue.add(job)
                        totalDuration += duration
                    }

                    else -> {
                        val poll = queue.peek()
                        val stackedDuration = poll[0]

                        if (stackedDuration > duration) {
                            queue.poll()
                            totalDuration -= stackedDuration
                            queue.add(job)
                            totalDuration += duration
                        }


                    }
                }
            }

            queue.forEach { println(it.contentToString()) }
            println()
        }


        return queue.size
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

    @Test
    fun test2() {
        val jobs = arrayOf(
            intArrayOf(3, 3),
            intArrayOf(2, 2),
        )

        val result = 1

        assertEquals(result, solution(jobs))
    }

    @Test
    fun test3() {
        val jobs = arrayOf(

            intArrayOf(5, 5),
            intArrayOf(1, 6),
            intArrayOf(1, 6),
            intArrayOf(1, 6),
            intArrayOf(1, 6),
            intArrayOf(1, 6),
        )

        val result = 5

        assertEquals(result, solution(jobs))
    }

    @Test
    fun test4() {
        val jobs = arrayOf(

            intArrayOf(5, 5),
            intArrayOf(4, 5),
            intArrayOf(4, 5),
        )

        val result = 1

        assertEquals(result, solution(jobs))
    }
}

class `3-1 잔돈 거스름돈` {
    fun solution(coins: IntArray, amount: Int): Int {
        var total = amount
        var count = 0

        for (coin in coins) {

            while (total - coin >= 0) {
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

class `3-2 분할 가능 배낭` {


    fun solution(items: Array<IntArray>, capacity: Int): Double {
        val sortedItems = items
        sortedItems.sortWith(compareByDescending { it[1].toDouble() / it[0].toDouble() })

        var totalWeight = capacity.toDouble()
        var totalValue = 0.0

        for (item in sortedItems) {
            val weight = item[0]
            val value = item[1]



            if (weight <= totalWeight) {
                totalValue += value
                totalWeight -= weight
            } else {
                val part = totalWeight / weight
                totalValue += value * part
                totalWeight = 0.0
            }
        }

        return totalValue
    }

    @Test
    fun test1() {
        val items = arrayOf(
            intArrayOf(10, 60),
            intArrayOf(20, 100),
            intArrayOf(30, 120),
        )
        val capacity = 50
        val result = 240.0

        assertEquals(result, solution(items, capacity))
    }

    @Test
    fun test2() {
        val items = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(2, 100),
        )
        val capacity = 2
        val result = 100.0

        assertEquals(result, solution(items, capacity))
    }

    @Test
    fun test3() {
        val items = arrayOf(
            intArrayOf(4, 40),
            intArrayOf(3, 30),
            intArrayOf(2, 20),
        )
        val capacity = 5
        val result = 50.0

        assertEquals(result, solution(items, capacity))
    }
}

class `3-3 최소 주유 횟수` {

    fun solution(stations: Array<IntArray>, startFuel: Int, dist: Int): Int {
        val sortedStations = stations.sortedWith(compareBy<IntArray> { it[0] })
        val pq = PriorityQueue<IntArray>(compareByDescending { it[1] })

        var count = 0
        var fuel = startFuel

        for (station in sortedStations) {
            val (position, _) = station
            if (fuel >= dist) return count


            while (pq.isNotEmpty() && fuel < position ) {
                val (_, additionalFuel) =  pq.poll()
                fuel += additionalFuel
                count ++
            }

            if (fuel < position) return -1
            pq.add(station)
        }

        while (fuel < dist) {
            if (pq.isEmpty()) return -1
            val (_, additionalFuel) = pq.poll()
            fuel += additionalFuel
            count++
        }


        return count
    }


    @Test
    fun test1() {

        val stations = arrayOf(
            intArrayOf(10, 60),
            intArrayOf(20, 30),
            intArrayOf(30, 30),
            intArrayOf(60, 40),
        )

        val start_fuel = 40
        val dist = 100
        val result = 1

        assertEquals(result, solution(stations, start_fuel, dist))
    }

    @Test
    fun test2() {

        val stations = arrayOf(
            intArrayOf(3, 10),
            intArrayOf(15, 3),
        )

        val start_fuel = 5
        val dist = 20
        val result = -1

        assertEquals(result, solution(stations, start_fuel, dist))
    }

    @Test
    fun test3() {

        val stations = arrayOf(
            intArrayOf(5, 3),
            intArrayOf(6, 3),
            intArrayOf(9, 60),
        )

        val start_fuel = 5
        val dist = 15
        val result = 3

        assertEquals(result, solution(stations, start_fuel, dist))
    }

    @Test
    fun test4() {

        val stations = arrayOf(
            intArrayOf(3, 1),
            intArrayOf(10, 100),
        )

        val start_fuel = 5
        val dist = 10
        val result = -1

        assertEquals(result, solution(stations, start_fuel, dist))
    }
}

class `4-1 활동 선택` {
    fun solution(activities: Array<IntArray>): Int {

        val sortedActivities = activities.sortedBy { it[1] }
        var lastEnd = 0
        var count = 0

        for (activity in sortedActivities) {
            val start = activity[0]
            val end = activity[1]

            if (lastEnd <= start) {
                lastEnd = end
                count++
            }
        }


        return count
    }

    @Test
    fun test1() {
        val activities = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4),
            intArrayOf(0, 6),
            intArrayOf(5, 7),
            intArrayOf(8, 9),
            intArrayOf(5, 9),
        )
        val result = 4
        assertEquals(result, solution(activities))
    }


}

class `4-2 최소 회의실 개수` {
    fun solution(meetings: Array<IntArray>): Int {
        val room = PriorityQueue<Int>()
        val sortedMeetings = meetings
        sortedMeetings.sortWith(compareBy({ it[1] }, { it[0] }))

        for (meeting in sortedMeetings) {
            val start = meeting[0]
            val end = meeting[1]

            if (room.isEmpty()) room.add(end)
            else {
                val queuedRoom = room.poll()
                if (queuedRoom < start) {
                    room.add(end)
                } else {
                    room.add(queuedRoom)
                    room.add(end)
                }
            }
        }


        return room.size
    }

    @Test
    fun test1() {
        val meetings = arrayOf(
            intArrayOf(0, 30),
            intArrayOf(5, 10),
            intArrayOf(15, 20),
        )
        val result = 2

        assertEquals(result, solution(meetings))
    }
}

class `4-3 정렬 기준 비교` {

}