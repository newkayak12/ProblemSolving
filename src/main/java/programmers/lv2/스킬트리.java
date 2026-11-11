package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class 스킬트리 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/49993?language=java
     */


    @Test
    public void solution() {
        String skill = "CBD";
        String[] skill_trees = new String[]{
            "BACDE", "CBADF", "AECB", "BDA"
        };
        int expected = 2;

        assertEquals(expected, calculate(skill, skill_trees));
    }


    private int calculate(String skill, String[] skill_trees) {
        int index = 0;
        int count = 0;
        boolean result = true;

        /**
         * Queue로
         * SkillTrees의 Char가 이 Queue에 포함되면 Queue의 단어를 뽑고 맞는지 검증
         */

        for (String s : skill_trees) {
            index = 0;
            result = true;

            for (String e : s.split("")) {
                if (skill.length() <= index) {
                    break;
                }
                if (!skill.contains(e)) {
                    continue;
                }

                if (skill.contains(e) && !e.equals(skill.charAt(index) + "")) {
                    result = false;
                    break;
                }

                index++;
            }

            if (result) {
                count++;
            }
        }

        return count;
    }
}
