package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class 파일명_정렬 {

    @Test
    public void solution1() {
        String[] input = new String[]{
            "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"
        };
        String[] expected = new String[]{
            "img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"
        };

        assertArrayEquals(expected, calculate(input));
    }

    @Test
    public void solution2() {
        String[] input = new String[]{
            "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"
        };
        String[] expected = new String[]{
            "A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"
        };

        assertArrayEquals(expected, calculate(input));
    }


    private String[] calculate(String[] input) {
        List<String[]> divided = Arrays.stream(input).map(this::divider).collect(Collectors.toList());


        divided.sort(
            Comparator
                .comparing((String[] it) -> it[0], String.CASE_INSENSITIVE_ORDER)
                .thenComparingLong((String[] it) -> Long.parseLong(it[1]))
        );

        return divided.stream().map(it -> Arrays.stream(it).collect(Collectors.joining(""))).toArray(String[]::new);
    }

    /**
     * Head 숫자 아닌 문자, 최소 한 글자 이상
     * Number 1 ~ 5글자 0으로 시작 가능 0 ~ 99999
     * Tail -> 나머지 없을 수도 있음
     */
    private String[] divider(String element) {
        char[] charArray = element.toCharArray();
        int headEnd = -1;
        int numberEnd = -1;

        for (int i = 0; i < charArray.length; i++) {
            char charElement = charArray[i];

            if (Character.isDigit(charElement) && headEnd == -1) {
                headEnd = i;
            }

            if (!Character.isDigit(charElement) && headEnd != -1 && numberEnd == -1) {
                numberEnd = i;
                break;
            }
        }

        if (numberEnd == -1) numberEnd = element.length();

        return new String[]{
            element.substring(0, headEnd),
            element.substring(headEnd, numberEnd),
            element.substring(numberEnd)
        };
    }

}
