package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class 뉴스_클러스터링 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/17677?language=javascript
     *
     * 숫자, 특수문자, 공백 무시
     * 대소문자 무시
     * 정답은 *= 65536 , 소수점 아래 버림
     *
     * 중복원소를 허용
     * {1,1,2,2,3}, {1,2,2,4,5}
     * min(3,5) -> 교집합 {1,2,2}
     * max(3,5) -> 합집합 {1,1,2,2,3,4,5}
     */

    @Test
    public void solution1(){
        String str1 = "FRANCE";
        String str2 = "french";
        int expected = 16384;

        assertEquals(expected, calculate(str1, str2));

    }
    @Test
    public void solution2(){
        String str1 = "handshake";
        String str2 = "shake hands";
        int expected = 65536;

        assertEquals(expected, calculate(str1, str2));
    }
    @Test
    public void solution3(){
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";
        int expected = 43690;

        assertEquals(expected, calculate(str1, str2));
    }
    @Test
    public void solution4(){
        String str1 = "E=M*C^2";
        String str2 = "e=m*c^2";
        int expected = 65536;

        assertEquals(expected, calculate(str1, str2));
    }

    private int calculate(String str1, String str2) {

        Map<String, Integer> str1Set = toMap(group(str1));
        Map<String, Integer> str2Set = toMap(group(str2));

        Map<String , Integer> union = new HashMap<>();
        Map<String , Integer> intersect = new HashMap<>();

        Set<String> keys = new LinkedHashSet<>();
        keys.addAll(str1Set.keySet());
        keys.addAll((str2Set.keySet()));

        for(String key : keys) {
            if(str1Set.containsKey(key) && str2Set.containsKey(key)) {
                int str1SetSize = str1Set.get(key);
                int str2SetSize = str2Set.get(key);

                intersect.put(key, Math.min(str1SetSize, str2SetSize));
            }

            if(str1Set.containsKey(key)) {
                int unionSize = union.getOrDefault(key, 0);
                int str1SetSize = str1Set.get(key);

                union.put(key, Math.max(unionSize, str1SetSize));
            }

            if(str2Set.containsKey(key)) {
                int unionSize = union.getOrDefault(key, 0);
                int str2SetSize = str2Set.get(key);

                union.put(key, Math.max(unionSize, str2SetSize));
            }
        }


        double intersectSize = intersect.values().stream().reduce(0, (integer, integer2) -> integer + integer2);
        double unionSize = union.values().stream().reduce(0, (integer, integer2) -> integer + integer2);



        if(intersectSize == 0 && unionSize == 0) return 65536;
        return (int) Math.floor(intersectSize / unionSize * 65536);
    }

    private Map<String ,Integer> toMap(List<String> list) {
        return list.stream().collect(Collectors.toMap(
            Function.identity(),
            it -> 1,
            (integer, integer2) -> integer + integer2
        ));
    }
    private List<String> group(String str) {
        List<String> set = new ArrayList<>();
        char[] charArray = str.toCharArray();

        for(int i = 0; i < charArray.length - 1; i++) {
            char first = charArray[i];
            char second = charArray[i + 1];

            if(!Character.isAlphabetic(first) || !Character.isAlphabetic(second)) {
                continue;
            }

            String result = ""+Character.toLowerCase(first)+Character.toLowerCase(second);
            set.add(result);
        }

        return set;
    }
}
