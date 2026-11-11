package renewal.others.trie

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TrieExample {


    @Test
    fun testTrie() {
        val trie = Trie()
        trie.insert("apple")
        assertEquals(true, trie.search("apple"))
        assertEquals(false, trie.search("app"))
        assertEquals(true, trie.startsWith("app"))

        trie.insert("app")
        assertEquals(true, trie.search("app"))
    }

    class TrieNode {
        val children = mutableMapOf<Char, TrieNode>()
        var isEndOfWord = false
    }

    class Trie {
        private val root = TrieNode()

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

        fun startsWith(prefix: String): Boolean {
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

            if (shouldDeleteChild){
                node.children.remove(char)
                return !node.isEndOfWord && childNode.children.isEmpty()
            }

            return false
        }

        fun findWordsWithPrefix(prefix: String): List<String> {
            val result = mutableListOf<String>()
            var currrent = root

            for(char in prefix) {
                if(!currrent.children.containsKey(char)){
                    return emptyList()
                }
                currrent = currrent.children[char]!!
            }
            dfs(currrent, prefix, result)

            return result
        }

        private fun dfs(node: TrieNode, currentWord: String, result: MutableList<String>) {
            if(node.isEndOfWord){
                result.add(currentWord)
            }
            for ((char, childNode) in node.children) {
                dfs(childNode, currentWord + char, result)
            }
        }
    }


}

