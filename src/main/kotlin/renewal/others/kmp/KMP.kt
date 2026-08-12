package renewal.others.kmp

class KMP {

    fun computePrefix(pattern: String): IntArray {
        val n = pattern.length
        val prefix = IntArray(n)
        var j = 0 //현재까지 일치한 접두사 길이

        for(i in 1 until n) { // 접미사 끝 위치

            //불일치 시 이전 일치점으로 되돌아가기
            while(j > 0 && pattern[i] != pattern[j]) {
                j = prefix[j - 1] //되돌아갈 위치
            }

            //현재 문자가 일치하면 길이 증가
            if(pattern[i] == pattern[j]) {
                j ++ //길이 일치 ++
            }

            prefix[i] = j // i번째까지의 부분문자열에서 최대 일치 길이
        }

        return prefix
    }

    fun kmp(text: String, pattern: String): List<Int> {
        val prefix = computePrefix(pattern)
        val result = mutableListOf<Int>()

        var j = 0 //  패턴 포인터

        for( i in text.indices) {//텍스트 포인터
            //불일치 시 패턴 포인터 조정
            while(j > 0 && text[i] != pattern[j]) {
                j = prefix[j - 1]
            }

            // 일치하면 패턴 포인터 증가
            if(text[i]== pattern[j]) {
                j ++
            }
            // 패턴 완전 매칭 시
            if (j == pattern.length) {
                result.add(i - pattern.length + 1)  // 시작 위치 저장
                j = prefix[j - 1]  // 다음 패턴 찾기 위해 조정
            }
        }

        return result
    }
}