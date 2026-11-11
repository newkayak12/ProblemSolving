package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class 최고의_집합 {

    public int[] solution(int n, int s) {

        /**
         * a + b = 9
         * a * b 가 최대
         *
         * (s/n)(s/n + 1)
         */
        int[] answer = new int[n];
        int i = 0;
        int l = n - i;
        int number = s;

        while (i < answer.length - 1) {
            int divLeft = (int) Math.ceil(number / l);
            if (divLeft == 0) {
                return new int[]{-1};
            }
            answer[i++] = divLeft;
            number -= divLeft;
            l = n - i;
        }

        answer[i] = number;

        return answer;
    }


    public int[] solutionBak(int n, int s) {
        List<int[]> listUp = new ArrayList<>();
        useRecursive(0, 1, new int[n], s, listUp);

        listUp.sort(
            Comparator.comparing((int[] a) -> Arrays.stream(a)
                    .mapToLong(it -> (long) it)
                    .sorted()
                    .reduce(1, (left, right) -> left * right)
                )
                .reversed()
        );

        listUp.forEach(i -> System.out.println(Arrays.toString(i)));
        return listUp.stream().findFirst().orElse(new int[]{-1});
    }

    void useRecursive(int index, int start, int[] set, int s, List<int[]> result) {

        if (index == set.length) {
            if (s == 0) {
                result.add(set.clone());
            }
            return;
        }

        int remain = set.length - index;
        for (int v = start; (long) v * remain <= s; v++) {
            set[index] = v;
            useRecursive(index + 1, v, set, s - v, result);
        }
    }


    @Test
    void test1() {
        int n = 2;
        int s = 9;
        int[] result = {4, 5};

        assertArrayEquals(result, solution(n, s));
    }

    @Test
    void test2() {
        int n = 2;
        int s = 1;
        int[] result = {-1};

        assertArrayEquals(result, solution(n, s));
    }

    @Test
    void test3() {
        int n = 2;
        int s = 8;
        int[] result = {4, 4};

        assertArrayEquals(result, solution(n, s));
    }

}
