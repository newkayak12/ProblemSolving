package programmers.lv2.revision

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class `다리를 지나는 트럭` {


    @Test
    fun test1() {

        val bridgeLength = 2
        val weight = 10
        val truckWeight = intArrayOf(7, 4, 5, 6)
        val result = 8

        assertEquals(result, solution(bridgeLength, weight, truckWeight))
    }

    @Test
    fun test2() {

        val bridgeLength = 100
        val weight = 100
        val truckWeight = intArrayOf(10)
        val result = 101

        assertEquals(result, solution(bridgeLength, weight, truckWeight))
    }

    @Test
    fun test3() {

        val bridgeLength = 100
        val weight = 100
        val truckWeight = intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
        val result = 110

        assertEquals(result, solution(bridgeLength, weight, truckWeight))
    }

    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        /**
         * val bridge = ArrayDequeue<Pair<Int, Int>>() //다리에 올라간 시간, 트럭
         * var weightLimit = weight
         * var bridgeLength = bridge_length
         * var passed = 0
         * var index = 0
         * var time = 0
         *
         * while passed < truck_weights.length
         *   time ++
         *   while 다리 위 트럭이 있을 때
         *     트럭이 탈출할 수 있는 조건인지 확인하고 탈출
         *     weightLimit 복원
         *     passed ++
         *
         *
         *   if weightLimit 비교해서 현재 차량이 올라올 수 있는가? && 다리 길이가 충분한가
         *     bridge에 올린다.
         *     index ++
         *
         *
         */

        val bridge = ArrayDeque<Pair<Int, Int>>()
        var weightLimit = weight
        var bridgeLength = bridge_length
        var passed = 0
        var index = 0
        var elapsedTime = 0


        while (passed < truck_weights.size) {

            println("\n$bridge $passed ${truck_weights.size} elapsed time: $elapsedTime")

            while (bridge.isNotEmpty()) {
                val (time, truckWeight) = bridge.first()
                if (elapsedTime - time < bridge_length)  break

                    bridge.removeFirst()
                    passed++
                    weightLimit += truckWeight
                    bridgeLength++
            }


            if ( bridgeLength > 0 && index in truck_weights.indices && weightLimit >= truck_weights[index]) {
                val truck = truck_weights[index++]
                bridge.addLast(elapsedTime to truck)
                weightLimit -= truck
                bridgeLength--
            }

            elapsedTime++
      }


        return elapsedTime
    }
}