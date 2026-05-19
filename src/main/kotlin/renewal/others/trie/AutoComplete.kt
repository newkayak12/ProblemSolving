package renewal.others.trie

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class AutoComplete {
    /**
     * 🌳 트라이 기본 문제 1번: "자동완성"
     * 문제:
     * 포털 다음에서 검색어 자동완성 기능을 만든다고 가정해봅시다.
     * 사용자가 typing한 문자열에 대해서, 원래 입력하려던 단어까지 총 몇 번의 문자를 입력해야 하는지 계산해주세요.
     * 조건:
     *
     * 학습된 단어들과 검색어가 주어집니다
     * 단어는 2글자 이상 100글자 이하입니다
     * 사용자는 자동완성되는 부분에서 바로 엔터를 칩니다
     * 다른 단어의 접두사인 경우에도 해당 시점에서 자동완성을 선택할 수 있습니다
     *
     * 입출력 예:
     * kotlinwords = arrayOf("go", "gone", "guild")
     * queries = arrayOf("go", "gon", "gui")
     * // 결과: [2, 3, 2]
     * 설명:
     *
     * "go": 'g'만 쳐도 'o' 입력 전에 이미 자동완성으로 "go"를 선택할 수 있음 → 1번
     * "gon": 'g'만 쳐도 자동완성으로 "gone"를 선택할 수 있음 → 1번
     * "gui": "go", "gone"과 구분되려면 "gui"까지 모두 입력해야 함 → 3번
     */

    @Test
    fun solution() {
        val kotlinWords = arrayOf("go", "gone", "guild")
        val queries = arrayOf("go", "gon", "gui")
        val result = arrayOf(2, 3, 2)

        assertArrayEquals(result, useTrie(kotlinWords, queries))
    }

    private fun useTrie(kotlinWords: Array<String>, queries: Array<String>): Array<Int> {
        val trie = Trie()
        val array = Array(queries.size){-1}
        for(element in kotlinWords){
            trie.insert(element)
        }


        for((index, value) in queries.withIndex()) {
            var word = ""

            for((charIndex, charValue) in value.withIndex()) {
                word += charValue

                val list = trie.findWordsWithPrefix(word)

                if( list.size ==  1 || (list.contains(word) && word == value)) {
                    array[index] = charIndex + 1
                    break
                }
            }
        }


        return array
    }

    private inner class TrieNode {
        val children = mutableMapOf<Char, TrieNode>()
        var isEndOfWord = false
    }

    private inner class Trie {
        val root = TrieNode()

        fun insert(word: String) {
            var current = root
            for (char in word) {
                if (!current.children.containsKey(char)) {
                    current.children.getOrPut(char) { TrieNode() }
                }

                current = current.children[char]!!
            }

            current.isEndOfWord = true
        }

        fun search(word: String): Boolean {
            var current = root
            for (char in word) {
                if (!current.children.containsKey(char)) {
                    return false
                }

                current = current.children[char]!!
            }

            return current.isEndOfWord
        }

        fun startWith(prefix: String): Boolean {
            var current = root
            for (char in prefix) {
                if (!current.children.containsKey(char)) {
                    return false
                }

                current = current.children[char]!!
            }

            return true
        }

        fun delete(word: String) {
            deleteHelper(root, word, 0)
        }

        private fun deleteHelper(node: TrieNode, word: String, index: Int): Boolean {
            if (index == word.length) {
                if (!node.isEndOfWord) return false
                node.isEndOfWord = false
                return node.children.isEmpty()
            }

            val char = word[index]
            val childNode = node.children[char] ?: return false
            val shouldDeleteChild = deleteHelper(childNode, word, index + 1)

            if (shouldDeleteChild) {
                node.children.remove(char)
                return !node.isEndOfWord && childNode.children.isEmpty()
            }

            return false
        }

        fun findWordsWithPrefix(prefix: String): List<String> {
            val result = mutableListOf<String>()
            var current = root

            for (char in prefix) {
                if (!current.children.containsKey(char)) {
                    return emptyList()
                }

                current = current.children[char]!!
            }
            dfs(current, prefix, result)

            return result
        }

        private fun dfs(node: TrieNode, currentWord: String, result: MutableList<String>) {
            if (node.isEndOfWord) {
                result.add(currentWord)
            }

            for ((char, childNode) in node.children) {
                dfs(childNode, currentWord + char, result)
            }
        }
    }
}