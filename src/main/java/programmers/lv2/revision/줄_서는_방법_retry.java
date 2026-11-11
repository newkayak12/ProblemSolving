package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class 줄_서는_방법_retry {

    /**
     * 20!이면 충분히 크다. 앞 자리가 정해졌다고 하고, 뒷 자리수를 factorial 처리하면 앞 자리 고정 당 경우의 수가 나온다.
     * 팩토리얼을 매 번 계산할 수 없으므로 기록
     * 사용하는 수도 기록 + 사용하면 삭제
     *
     */
    public int[] solution(int n, long k) {

        long size = k - 1;

        List<Integer> numberTable = new ArrayList<>();
        long[] factorialTable = new long[n + 1];
        factorialTable[0] = 1;

        for (int i = 1; i <= n; i++) {
            numberTable.add(i);
            factorialTable[i] = factorialTable[i - 1] * i;
        }


        int[] answer = new int[numberTable.size()];
        for (int i = 1; i <= n; i++) {
            long factorialValue = factorialTable[n - i];
            long div = size / factorialValue;
            size %= factorialValue;

            answer[i - 1] = numberTable.remove((int) div);
        }

        return answer;
    }

    public int[] solutionWithRecursive2(int n, long k) {
        List<int[]> result = new ArrayList<>();

        useRecursive2(0, n, new boolean[n], new int[n], result);

        result.forEach(a -> System.out.println(Arrays.toString(a)));

        return result.get((int) k);
    }

    void useRecursive2(int position, int n, boolean[] visit, int[] array, List<int[]> list) {
        if (position >= n) {
            list.add(array.clone());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            array[position] = i + 1;
            useRecursive2(position + 1, n, visit, array, list);
            visit[i] = false;
        }
    }

    public int[] solutionWithRecursive1(int n, long k) {
        List<int[]> result = new ArrayList<>();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        useRecursion1(0, n, array, result);
        return result.get((int) k);
    }

    void useRecursion1(int position, int n, int[] array, List<int[]> list) {
        if (position >= n) {
            list.add(array.clone());
            return;
        }

        for (int i = position; i < n; i++) {
            swap(array, i, position);
            useRecursion1(position + 1, n, array, list);
            swap(array, position, i);
        }

    }

    void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    @Test
    void test1() {
        int n = 3;
        int k = 5;
        int[] result = {3, 1, 2};

        assertArrayEquals(result, solution(n, k));
    }
}
