package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class 영어_끝말잇기 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12981?language=java [번호, 차례]
     */

    @Test
    public void solution1() {

        int n = 3;
        String[] words = new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] expected = new int[]{3, 3};

        assertArrayEquals(expected, calculate(n, words));
    }

    @Test
    public void solution2() {

        int n = 5;
        String[] words = new String[]{
            "hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure",
            "establish", "hang", "gather", "refer", "reference", "estimate", "executive"
        };
        int[] expected = new int[]{0, 0};

        assertArrayEquals(expected, calculate(n, words));
    }

    @Test
    public void solution3() {

        int n = 2;
        String[] words = new String[]{"hello", "one", "even", "never", "now", "world", "draw"};
        int[] expected = new int[]{1, 3};

        assertArrayEquals(expected, calculate(n, words));
    }

    private int[] calculate(int n, String[] words) {
        int[] result = new int[2];
        Set<String> dictionary = new HashSet<>();
        String word = "";
        dictionary.add(words[0]);

        for (int i = 0; i < words.length; i++) {
            int people = i % n + 1;
            int turn = (int) Math.ceil((i * 1.0 + 1) /  n);
            if (!word.isEmpty()) {
                String start = words[i].substring(0, 1);
                if (!word.endsWith(start) || !dictionary.add(words[i])) {
                    result[0] = people;
                    result[1] = turn;
                    break;
                }
            }
            word = words[i];
        }

        return result;
    }

}
