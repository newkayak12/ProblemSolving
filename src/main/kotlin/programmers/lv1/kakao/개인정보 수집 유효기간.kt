package programmers.lv1.kakao

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class `개인정보 수집 유효기간` {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/150370?language=kotlin
     *
     * 모든 달은 28일까지
     */

    @Test
    fun solution1() {
        val today = "2022.05.19"
        val terms = arrayOf("A 6", "B 12", "C 3")
        val privacies = arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")

        val expected = intArrayOf(1, 3)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution2() {
        val today = "2020.01.01"
        val terms = arrayOf("Z 3", "D 5")
        val privacies = arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z")

        val expected = intArrayOf(1, 4, 5)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution3(){
        val today = "2028.04.28"
        val terms = arrayOf("A 100")
        val privacies = arrayOf("2020.01.01 A")

        val expected = intArrayOf()
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution4(){
        val today = "2020.12.01"
        val terms = arrayOf("A 12")
        val privacies = arrayOf("2019.12.1 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution5(){
        val today = "2021.01.01"
        val terms = arrayOf("A 12")
        val privacies = arrayOf("2020.01.01 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution6(){
        val today = "2020.01.10"
        val terms = arrayOf("A 12")
        val privacies = arrayOf("2019.01.10 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution7(){
        val today = "2020.12.27"
        val terms = arrayOf("A 24")
        val privacies = arrayOf("2018.12.28 A")

        val expected = intArrayOf()
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution8(){
        val today = "2020.01.01"
        val terms = arrayOf("A 1")
        val privacies = arrayOf("2019.12.28 A")

        val expected = intArrayOf()
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution9(){
        val today = "2025.01.01"
        val terms = arrayOf("A 60")
        val privacies = arrayOf("2020.01.01 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution10(){
        val today = "2030.01.01"
        val terms = arrayOf("A 100")
        val privacies = arrayOf("2020.01.01 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution11(){
        val today = "2021.01.01"
        val terms = arrayOf("A 12")
        val privacies = arrayOf("2020.01.01 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution12(){
        val today = "2020.01.01"
        val terms = arrayOf("A 1")
        val privacies = arrayOf("2019.01.01 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    @Test
    fun solution13(){
        val today = "2021.01.01"
        val terms = arrayOf("A 12")
        val privacies = arrayOf("2019.12.01 A")

        val expected = intArrayOf(1)
        assertArrayEquals(expected, calculate1(today, terms, privacies))
        assertArrayEquals(expected, calculate2(today, terms, privacies))
    }

    private fun calculate1(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val termMap = terms.associate {
            val split = it.split(" ")
            split[0] to (split[1]).toInt()
        }

        val todaySplit= today.split(".")
        val todayYear = todaySplit[0].toInt() * 12 * 28
        val todayMonth = todaySplit[1].toInt() * 28
        val todayDay = todaySplit[2].toInt()

        val todayTotal = todayYear + todayMonth + todayDay


        return privacies.mapIndexed { index, value ->
            val splitPrivacy = value.split(" ")
            val policyMonth = termMap[splitPrivacy[1]]!!
            val fromDate = splitPrivacy[0]
            val dateSplit = fromDate.split(".")
            var year = (dateSplit[0]).toInt() * 12 * 28
            var month = ((dateSplit[1]).toInt() + policyMonth)* 28
            var date = (dateSplit[2]).toInt() - 1


            val total = year + month + date
            if ( total < todayTotal) index + 1
            else 0
        }
            .filter { it != 0 }
            .toIntArray()
    }


    private fun calculate2(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val termMap = terms.associate {
            val split = it.split(" ")
            split[0] to (split[1]).toInt()
        }


        return privacies.mapIndexed { index, value ->
            val splitPrivacy = value.split(" ")
            val policyMonth = termMap[splitPrivacy[1]]!!
            val fromDate = splitPrivacy[0]
            val dateSplit = fromDate.split(".")
            var year = (dateSplit[0]).toInt()
            var month = (dateSplit[1]).toInt()
            var date = (dateSplit[2]).toInt() - 1


            val total = (month + policyMonth) * 28 + date
            month = total / 28
            date = total % 28


            if (date == 0) {
                month -= 1
                date = 28
            }

            if (month > 12) {
                year += month / 12
                month %= 12
            }

            if( month == 0) {
                year -= 1
                month = 12
            }


            val dateToString = StringBuilder("$year.")

            if(month < 10) dateToString.append("0$month.")
            else dateToString.append("$month.")

            if(date < 10) dateToString.append("0$date")
            else dateToString.append("$date")



            println(dateToString)
            if ( dateToString.toString() < today) index + 1
            else 0
        }
            .filter { it != 0 }
            .toIntArray()
    }
}