package programmers.lv1.kakaoLv1.recommendId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecommendId {
    //https://school.programmers.co.kr/learn/courses/30/lessons/72410
    /**
     * 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을 때,
     * 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램을 개발하는 것입니다.
     *
     *
     *
     * 아이디의 길이는 3자 이상 15자 이하여야 합니다.
     * 아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
     * 단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.
     *
     *
     * 신규 유저가 입력한 아이디가 new_id 라고 한다면,
     * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
     * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
     * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
     * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
     * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
     * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
     * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
     */


    /**
     * 예를 들어, new_id 값이 "...!@BaT#*..y.abcdefghijklm" 라면, 위 7단계를 거치고 나면 new_id는 아래와 같이 변경됩니다.
     *
     * 1단계 대문자 'B'와 'T'가 소문자 'b'와 't'로 바뀌었습니다.
     * "...!@BaT#*..y.abcdefghijklm" → "...!@bat#*..y.abcdefghijklm"
     *
     * 2단계 '!', '@', '#', '*' 문자가 제거되었습니다.
     * "...!@bat#*..y.abcdefghijklm" → "...bat..y.abcdefghijklm"
     *
     * 3단계 '...'와 '..' 가 '.'로 바뀌었습니다.
     * "...bat..y.abcdefghijklm" → ".bat.y.abcdefghijklm"
     *
     * 4단계 아이디의 처음에 위치한 '.'가 제거되었습니다.
     * ".bat.y.abcdefghijklm" → "bat.y.abcdefghijklm"
     *
     * 5단계 아이디가 빈 문자열이 아니므로 변화가 없습니다.
     * "bat.y.abcdefghijklm" → "bat.y.abcdefghijklm"
     *
     * 6단계 아이디의 길이가 16자 이상이므로, 처음 15자를 제외한 나머지 문자들이 제거되었습니다.
     * "bat.y.abcdefghijklm" → "bat.y.abcdefghi"
     *
     * 7단계 아이디의 길이가 2자 이하가 아니므로 변화가 없습니다.
     * "bat.y.abcdefghi" → "bat.y.abcdefghi"
     *
     * 따라서 신규 유저가 입력한 new_id가 "...!@BaT#*..y.abcdefghijklm"일 때,
     * 네오의 프로그램이 추천하는 새로운 아이디는 "bat.y.abcdefghi" 입니다
     */


    public static String solution (String newId) {
        String reference = new String(newId);
        String change = new String(newId);

        change = toLowerCaseFilter(change);
        System.out.println("1 :: "+ change);
        change = removeSpecialCases(change);
        System.out.println("2 :: "+ change);
        change = replaceContinuousDot(change);
        System.out.println("3 :: "+ change);
        change = removeFirstOrLastDot(change);
        System.out.println("4 :: "+ change);
        change = paddingAToEmptyString(change);
        System.out.println("5 :: "+ change);
        change = cutTextIfOverSixteen(change);
        System.out.println("6 :: "+ change);
        change = addLastCharIfLengthUnderThree(change);
        System.out.println("7 :: "+ change);


        if( reference.equals(change) ) return reference;
        else return solution(change);
    }

    private static String toLowerCaseFilter( String input ) {
        return input.toLowerCase();
    }

    private static String removeSpecialCases ( String input ) {
        int alphabetAcode = 97;
        int alphabetZcode = 122;
        int numberZero = 48;
        int numberNine = 57;
        int hyphen = 45;
        int dot = 46;
        int underScore = 95;

        char[] charArray = input.toCharArray();
        List<Character> characterList = new ArrayList<>();

        for ( char piece : charArray ){

            if (
                    (piece >= numberZero && piece <= numberNine) ||
                    (piece >= alphabetAcode && piece <= alphabetZcode) ||
                    (piece == hyphen) ||
                    (piece == dot) ||
                    (piece == underScore)
            ) {
                characterList.add(piece);
            }
        }

        return characterList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static String replaceContinuousDot ( String input ) {
        return input.replaceAll("(\\.){2,}", ".");
    }

    private static String removeFirstOrLastDot ( String input ) {
        String change = input;

        if(Objects.isNull(input) || input.length() == 0) return change;

        if ( input.charAt(0) == '.') change = change.substring(1);
        if ( input.charAt(input.length() - 1) == '.')  change = change.substring(0, input.length() - 1);

        return change;
    }

    private static String paddingAToEmptyString ( String input ) {
        if( "".equals(input) ) return "a";
        else return input;
    }

    private static String cutTextIfOverSixteen ( String input ) {
        if ( input.length() >= 16 ) return input.substring(0, 15);
        else return input;
    }

    private static String addLastCharIfLengthUnderThree ( String input ) {
        return addLast(input);
    }

    private static String addLast(String input ) {
        if ( input.length() <= 2 ) return addLast(input+input.charAt(input.length() - 1));
        else return input;
    }

    @Nested
    class TestCases {
        @Test
        public void case1() {
            String input = "...!@BaT#*..y.abcdefghijklm";
            String expect = "bat.y.abcdefghi";
            Assertions.assertEquals(solution(input), expect);
        }

        @Test
        public void case2() {
            String input = "z-+.^.";
            String expect = "z--";
            Assertions.assertEquals(solution(input), expect);
        }

        @Test
        public void case3() {
            String input = "=.=";
            String expect = "aaa";
            Assertions.assertEquals(solution(input), expect);
        }

        @Test
        public void case4() {
            String input = "123_.def";
            String expect = "123_.def";
            Assertions.assertEquals(solution(input), expect);
        }

        @Test
        public void case5() {
            String input = "abcdefghijklmn.p";
            String expect = "abcdefghijklmn";
            Assertions.assertEquals(solution(input), expect);

        }
    }

}
