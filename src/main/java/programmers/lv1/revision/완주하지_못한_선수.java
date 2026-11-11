package programmers.lv1.revision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class 완주하지_못한_선수 {

    @Test
    void test1() {

        String[] participant = new String[]{"leo", "kiki", "eden"};
        String[] completion = new String[]{"eden", "kiki"};

        String result = "leo";

        assertEquals(result, solution(participant, completion));
    }

    @Test
    void test2() {
        String[] participant = new String[]{"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion = new String[]{"josipa", "filipa", "marina", "nikola"};

        String result = "vinko";

        assertEquals(result, solution(participant, completion));
    }

    @Test
    void test3() {
        String[] participant = new String[]{"mislav", "stanko", "mislav", "ana"};
        String[] completion = new String[]{"stanko", "ana", "mislav"};

        String result = "mislav";

        assertEquals(result, solution(participant, completion));
    }


    String solution(String[] participant, String[] completion) {

        Map<String, Integer> completionMap = Arrays.stream(completion)
            .collect(Collectors.toMap(Function.identity(), s -> 1, Integer::sum));

        for (String people : participant) {
            int peopleCount = completionMap.getOrDefault(people, 0);

            if (peopleCount <= 0) return people;
            else completionMap.computeIfPresent(people, (key, value) -> value - 1);

        }

        return "";
    }
}
