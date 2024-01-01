package programmers.kakaoLv1.mbti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Mbti {
    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
            int[] choices = {5, 3, 2, 7, 5};
            String expect = "TCMA";

            Assertions.assertEquals(expect, solution(survey, choices));
        }

        @Test
        public void case2 () {
            String[] survey = {"TR", "RT", "TR"};
            int[] choices = {7, 1, 3};
            String expect = "RCJA";

            Assertions.assertEquals(expect, solution(survey, choices));

        }
    }

    public int compareReference ( String key ) {
        switch (key) {
            case "R":
            case "T":
                return 4;
            case "C":
            case "F":
                return 3;
            case "J":
            case "M":
                return 2;
            case "A":
            case "N":
                return 1;
            default: return 0;
        }
    }
    public String solution( String[] survey, int[] choices ) {
        Map<String, Integer> resultSet = new HashMap<String, Integer>(){{
                put("R", 0);
                put("T", 0);
                put("C", 0);
                put("F", 0);
                put("J", 0);
                put("M", 0);
                put("A", 0);
                put("N", 0);
        }};

        StringBuilder builder = new StringBuilder();



        for ( int step = 0; step < survey.length; step ++ ) {

            String[] split = survey[step].split("");
            String first = split[0];
            String second = split[1];

            int score = choices[step] - 4;

            String selectedKey = null;
            if (score > 0 ) selectedKey = second;
            if (score < 0 ) selectedKey = first;
            if(Objects.nonNull(selectedKey)) resultSet.computeIfPresent(selectedKey, (k, v) -> v + Math.abs(score));
        }


        List<String> sortedKeySet =  resultSet.keySet().stream().collect(Collectors.toList());
        Collections.sort(sortedKeySet, (k1, k2) -> compareReference(k2) - compareReference(k1));


        for ( int index = 0; index < sortedKeySet.size(); index += 2 ) {
            String first = sortedKeySet.get(index);
            int firstScore = resultSet.getOrDefault(first, 0);
            String second = sortedKeySet.get(index + 1);
            int secondScore = resultSet.getOrDefault(second, 0);

            if( firstScore > secondScore ) builder.append(first);
            else if ( firstScore < secondScore ) builder.append(second);
            else builder.append(first);
        }

        return builder.toString();
    }




}
