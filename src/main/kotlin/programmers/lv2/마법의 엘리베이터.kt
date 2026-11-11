package programmers.lv2

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.test.assertEquals

class `마법의 엘리베이터` {


    @Test
    fun solution1() {
        val storey = 16
        val expected = 6
        assertEquals(expected, calculate(storey))
    }

    @Test
    fun solution2() {
        val storey = 2554
        val expected = 16
        assertEquals(expected, calculate(storey))
    }

    @Test
    fun solution3() {
        val storey = 3
        val expected = 3

        assertEquals(expected, calculate(storey))
    }

    @Test
    fun solution4() {
        val storey = 6
        val expected = 5

        assertEquals(expected, calculate(storey))
    }

    @Test
    fun solution5() {
        val storey = 36
        val expected = 8

        assertEquals(expected, calculate(storey))
    }

    @Test
    fun solution6() {
        val storey = 35
        val expected = 8

        assertEquals(expected, calculate(storey))
    }


    @Test
    fun solution7() {
        val storey = 55555555

        val expected = 33

        assertEquals(expected, calculate(storey))
    }

    fun calculate(storey: Int): Int {
        var number = storey
        var count = 0
        for (i in 0..8) {
            val num = number / 10.0.pow(i).toInt() % 10

            if (num == 0) continue
            if (num < 5) {
                number -= num * 10.0.pow(i).toInt()
                count += num
            }
            else if(num > 5) {
                number += (10 - num) * 10.0.pow(i).toInt()
                count += (10 - num)
            }
            else {
                //5는 경계 값
                //바로 윗자리가 4보다 작으면 빼주고 6보다 크면 더해주는게 이득
                //바로 윗자리가 5이면 그 윗자리의 윗자리를 확인해야 한다.
                var index = i + 1
                var topNumber = 5
                while(index in 0 until 8){
                    val num = number / 10.0.pow(index).toInt() % 10

                    if(num == 5){
                        index ++
                    }
                    else {
                        topNumber = num
                        break
                    }
                }


                if(topNumber > 5){
                    number += (10 - num) * 10.0.pow(i).toInt()
                    count += (10 - num)
                }
                else if(topNumber < 5) {
                    number -= num * 10.0.pow(i).toInt()
                    count += num
                }
                else {
                    number += 5
                    count += 5
                }
            }
        }
        return count
    }
}