package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class 압축 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/17684?language=java
     */


    @Test
    public void solution1() {
        String msg = "KAKAO";
        int[] expected = new int[]{11, 1, 27, 15};

        assertArrayEquals(expected, calculate(msg));
    }


    @Test
    public void solution2() {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] expected = new int[]{20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34};

        assertArrayEquals(expected, calculate(msg));
    }


    @Test
    public void solution3() {
        String msg = "ABABABABABABABAB";
        int[] expected = new int[]{1, 2, 27, 29, 28, 31, 30};

        assertArrayEquals(expected, calculate(msg));


    }

    private int[] calculate(String msg) {
        List<String> dictionary = IntStream.rangeClosed('A', 'Z')
            .mapToObj(it -> String.valueOf((char) it))
            .collect(Collectors.toList());

        List<Integer> result = new ArrayList<>();

        char[] charArray = msg.toCharArray();

        int found = -1;
        StringBuilder temporary = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            //이전거 버퍼링이 필요할텐데

            temporary.append(charArray[i]);

            if(dictionary.contains(temporary.toString())) {
                found = dictionary.indexOf(temporary.toString()) + 1;
            }
            else {
                result.add(found);
                dictionary.add(temporary.toString());
                temporary.delete(0, temporary.length());
                i--;
            }

        }

        if(temporary.length()!=0) {
            result.add(dictionary.indexOf(temporary.toString()) + 1);
        }


        return result.stream().mapToInt(it -> it).toArray();
    }
}
