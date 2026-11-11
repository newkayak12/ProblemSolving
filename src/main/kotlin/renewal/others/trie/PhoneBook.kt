package renewal.others.trie

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PhoneBook {
    /**
     * 기본 문제 2: 전화번호 목록
     * 문제 설명
     * 전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
     * 전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두어입니다.
     *
     * 구조대: 119
     * 박준영: 97674223
     * 지영석: 1195524421
     *
     * 전화번호부에 적힌 전화번호를 담은 배열 phone_book이 매개변수로 주어질 때,
     * 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
     * 제한사항
     *
     * phone_book의 길이는 1 이상 1,000,000 이하입니다.
     * 각 전화번호의 길이는 1 이상 20 이하입니다.
     * 같은 전화번호가 중복해서 들어있지 않습니다.
     *
     * 입출력 예
     * phone_book: ["119","97674223","1195524421"]
     * return: false
     *
     * phone_book: ["123","456","789"]
     * return: true
     *
     * phone_book: ["12","123","1235","567","88"]
     * return: false
     */


    @Test
    fun solution1() {
        val phoneBook = arrayOf("119", "97674223", "1195524421")
        val expected = false
        assertEquals(expected, useTrie(phoneBook))
    }

    @Test
    fun solution2() {
        val phoneBook = arrayOf("123", "456", "789")
        val expected = true
        assertEquals(expected, useTrie(phoneBook))
    }

    @Test
    fun solution3() {
        val phoneBook = arrayOf("12", "123", "1235", "567", "88")
        val expected = false
        assertEquals(expected, useTrie(phoneBook))
    }

    private fun useTrie(phoneBook: Array<String>): Boolean {
        val trie = Trie()

        for (phone in phoneBook) {
            if(!trie.insert(phone)) return false
        }

        return true
    }

    private inner class Trie {
        private val root = TrieNode()

        //접두어 관계면 true 아니면 false
        fun insert(word: String): Boolean {
            var current = root

            for (char in word) {
                if (current.isEndOfWord) return false

                current = current.children.getOrPut(char) { TrieNode() }
            }

            if (current.children.isNotEmpty()) return false

            current.isEndOfWord = true
            return true
        }
    }

    private inner class TrieNode {
        val children = mutableMapOf<Char, TrieNode>()
        var isEndOfWord = false
    }

}