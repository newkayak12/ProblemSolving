package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VowelDictionary {
    /**
     * 사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는,
     * 길이 5 이하의 모든 단어가 수록되어 있습니다.
     *
     * 사전에서 첫 번째 단어는 "A"이고,
     * 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
     *
     * 단어 하나 word가 매개변수로 주어질 때,
     * 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     *  - word의 길이는 1 이상 5 이하입니다.
     *  - word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String word = "AAAAE";
            int result = 6;
            Assertions.assertEquals(result, solution(word));
        }

        @Test
        public void case2 () {
            String word = "AAAE";
            int result = 10;
            Assertions.assertEquals(result, solution(word));
        }

        @Test
        public void case3 () {
            String word = "I";
            int result = 1563;
            Assertions.assertEquals(result, solution(word));
        }

        @Test
        public void case4 () {
            String word = "EIO";
            int result = 1189;
            Assertions.assertEquals(result, solution(word));
        }
    }

    public int solution(String word) {

        List<String> array = IntStream.rangeClosed(1, 55555)
                                      .mapToObj(String::valueOf)
                                      .filter(num -> {
                                          if(
                                            num.contains("0") ||
                                            num.contains("6") ||
                                            num.contains("7") ||
                                            num.contains("8") ||
                                            num.contains("9")
                                          ) return false;
                                          else return true;
                                      })
                                      .map(num -> {
                                         num = num.replaceAll("1", "A");
                                         num = num.replaceAll("2", "E");
                                         num = num.replaceAll("3", "I");
                                         num = num.replaceAll("4", "O");
                                         num = num.replaceAll("5", "U");

                                         return num;
                                     })
                                      .sorted()
                                      .collect(Collectors.toList());

        return array.indexOf(word) + 1;
    }



    @Nested
    class Solution { //otherSolution >> binarySearch/ Dfs

        @BeforeEach
        public void before () {
            arr = new ArrayList<>();
        }

        @Test
        public void case1 () {
            String word = "AAAAE";
            int result = 6;
            Assertions.assertEquals(result, this.solution(word));
        }
        @Test
        public void case2 () {
            String word = "AAAE";
            int result = 10;
            Assertions.assertEquals(result, this.solution(word));
        }
        @Test
        public void case3 () {
            String word = "I";
            int result = 1563;
            Assertions.assertEquals(result, this.solution(word));
        }
        @Test
        public void case4 () {
            String word = "EIO";
            int result = 1189;
            Assertions.assertEquals(result, this.solution(word));
        }

        static ArrayList<String> arr = new ArrayList<>();
        static char[] alphabet = { 'A', 'E', 'I', 'O', 'U' };
        public int solution(String word) {
            dfs(0, new StringBuilder());
            Collections.sort(arr);
            return binarySearch(word);
        }
        private int binarySearch(String word) {
            int left = 0, right = arr.size() - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (arr.get(mid).compareTo(word) < 0)
                    left = mid + 1;
                else if (arr.get(mid).compareTo(word) > 0)
                    right = mid - 1;
                else
                    return mid + 1;
            }
            return mid + 1;
        }
        private void dfs(int count, StringBuilder sb) {
            if (1 <= count && count <= 5) {
                arr.add(sb.toString());
                if (count == 5)
                    return;
            }
            for (int index = 0; index < 5; index++) {
                sb.append(alphabet[index]);
                dfs(count + 1, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }
}
