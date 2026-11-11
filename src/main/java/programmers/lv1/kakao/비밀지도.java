package programmers.lv1.kakao;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class 비밀지도 {

    @Test
    public void solution1(){
        int n = 5;
        int[] arr1 = new int[]{9, 20, 28, 18, 11};
        int[] arr2 = new int[]{30, 1, 21, 17, 28};

        String[] expected = new String[]{"#####","# # #", "### #", "# ##", "#####"};
        assertArrayEquals(expected, calculate(n, arr1, arr2));
    }

    @Test
    public void solution2(){
        int n = 6;
        int[] arr1 = new int[]{46, 33, 33 ,22, 31, 50};
        int[] arr2 = new int[]{27 ,56, 19, 14, 14, 10};

        String[] expected = new String[]{"######", "### #", "## ##", " #### ", " #####", "### # "};
        assertArrayEquals(expected, calculate(n, arr1, arr2));
    }

    private String[] calculate(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i = 0; i < n; i ++) {
            answer[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
            answer[i] = String.format("%"+n+"s", answer[i]);
            answer[i] = answer[i]
                .replaceAll("1", "#")
                .replaceAll("0", " ");
        }

        return answer;
    }
}
