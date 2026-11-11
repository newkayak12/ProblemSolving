package renewal.others.kmp

class StringArray {

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

    /**
     * abababab
     *
     * i = 0
     * j = 0
     * prefix[0] = 0
     * a
     * ----------------
     *
     *
     * i = 1
     * j = 0
     * prefix[1] = 0
     * ab
     *
     * i = 2
     * j = 1
     * prefix[2] = 1
     * aba
     *
     * i = 3
     * j = 2
     * prefix[3] = 2
     * abab
     *
     * ...
     *
     * 가나가다가나
     * i = 0
     * j = 0
     * prefix[0] = 0
     * 가
     * ----
     *
     * i = 1
     * j = 0
     * prefix[1] = 0
     * 가나
     *
     * i = 2
     * j = 1
     * prefix[2] = 1
     * 가나가
     *
     *
     * i = 3
     * j = 0
     * prefix[3] = 0
     * 가나가다
     *
     * i = 4
     * j = 1
     * prefix[4] = 1
     * 가나가다가
     *
     * i = 5
     * j = 2
     * prefix[5] = 2
     * 가나가다가나
     */


}