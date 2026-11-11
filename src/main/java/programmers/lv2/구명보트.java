package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 구명보트 {

    @Test
    public void solution1() {
        int[] people = new int[]{70, 50, 80, 50};
        int limit = 100;
        int expected = 3;

        assertEquals(expected, calculate(people, limit));
    }

    @Test
    public void solution2() {
        int[] people = new int[]{70, 80, 50};
        int limit = 100;
        int expected = 3;

        assertEquals(expected, calculate(people, limit));
    }

    private int calculate(int[] people, int limit) {
        Arrays.sort(people);
        int frontIndex = people.length - 1;
        int backIndex = 0;
        int count = 0;

        for (; frontIndex >= backIndex; frontIndex --) {
            int sum = people[frontIndex] + people[backIndex];

            if(sum <= limit) {
                backIndex ++;
            }

            count++;
        }

        return count;
    }
}
