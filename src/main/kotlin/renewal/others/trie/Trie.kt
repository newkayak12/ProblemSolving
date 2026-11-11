package renewal.others.trie



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

    //삭제가 복잡한 이유는?
    /**
     * CARD 저장
     * CAR 삭제 -> 이 경우 CAR를 그냥 지울 수 없음
     */
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
            return !node.isEndOfWord && node.children.isEmpty()
        }

        return false
    }

    fun findWordsWithPrefix(prefix: String): List<String> { //자동완성?
        val result = mutableListOf<String>()
        var current = root

        for(char in prefix) {
            if(!current.children.containsKey(char)) {
                return emptyList()
            }
            current = current.children[char]!!
        }
        dfs(current, prefix, result)
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

fun main() {
    val trie = Trie()

    // 단어 삽입
    trie.insert("car")
    trie.insert("card")
    trie.insert("care")
    trie.insert("cat")

    // 검색
    println(trie.search("car"))     // true
    println(trie.search("card"))    // true
    println(trie.search("ca"))      // false

    // 접두사 확인
    println(trie.startsWith("ca"))  // true
    println(trie.startsWith("dog")) // false

    // 접두사로 시작하는 모든 단어
    println(trie.findWordsWithPrefix("car")) // [car, card, care]
}