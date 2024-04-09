package programmers.lv2.kakaoLv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSort {
    //https://school.programmers.co.kr/learn/courses/30/lessons/17686
    /**
     *<pre>
     * 파일명 정렬
     * 세 차례의 코딩 테스트와 두 차례의 면접이라는 기나긴 블라인드 공채를 무사히 통과해
     * 카카오에 입사한 무지는 파일 저장소 서버 관리를 맡게 되었다.
     *
     * 저장소 서버에는 프로그램의 과거 버전을 모두 담고 있어,
     * 이름 순으로 정렬된 파일 목록은 보기가 불편했다.
     * 파일을 이름 순으로 정렬하면 나중에 만들어진
     * ver-10.zip이
     * ver-9.zip보다 먼저 표시되기 때문이다.
     *
     * 버전 번호 외에도 숫자가 포함된 파일 목록은 여러 면에서 관리하기 불편했다.
     * 예컨대 파일 목록이 ["img12.png", "img10.png", "img2.png", "img1.png"]일 경우,
     * 일반적인 정렬은 ["img1.png", "img10.png", "img12.png", "img2.png"] 순이 되지만,
     * 숫자 순으로 정렬된 ["img1.png", "img2.png", "img10.png", img12.png"] 순이 훨씬 자연스럽다.
     *
     * 무지는 단순한 문자 코드 순이 아닌,
     * 파일명에 포함된 숫자를 반영한 정렬 기능을 저장소 관리 프로그램에 구현하기로 했다.
     * 소스 파일 저장소에 저장된 파일명은
     * 100 글자 이내로, 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-")만으로 이루어져 있다.
     * 파일명은 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.
     * 파일명은 크게 HEAD, NUMBER, TAIL의 세 부분으로 구성된다.
     *
     *  - HEAD는 숫자가 아닌 문자로 이루어져 있으며, 최소한 한 글자 이상이다.
     *  - NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자로 이루어져 있으며,
     *    앞쪽에 0이 올 수 있다. 0부터 99999 사이의 숫자로, 00000이나 0101 등도 가능하다.
     *  - TAIL은 그 나머지 부분으로, 여기에는 숫자가 다시 나타날 수도 있으며,
     *    아무 글자도 없을 수 있다.
     *
     *
     * 파일명    	          HEAD	NUMBER	TAIL
     * foo9.txt	          foo	9	    .txt
     * foo010bar020.zip	  foo	010	    bar020.zip
     * F-15	              F-	15	    (빈 문자열)
     *
     *
     * 파일명을 세 부분으로 나눈 후, 다음 기준에 따라 파일명을 정렬한다.
     * 파일명은 우선 HEAD 부분을 기준으로 사전 순으로 정렬한다.
     * 이때, 문자열 비교 시 대소문자 구분을 하지 않는다.
     * MUZI와 muzi, MuZi는 정렬 시에 같은 순서로 취급된다.
     *
     * 1. 파일명의 HEAD 부분이 대소문자 차이 외에는 같을 경우,
     * NUMBER의 숫자 순으로 정렬한다. 9 < 10 < 0011 < 012 < 13 < 014 순으로 정렬된다.
     *
     * 2. 숫자 앞의 0은 무시되며, 012와 12는 정렬 시에 같은 같은 값으로 처리된다.
     * 두 파일의 HEAD 부분과,
     *
     * 3. NUMBER의 숫자도 같을 경우,
     * 원래 입력에 주어진 순서를 유지한다.
     * MUZI01.zip과 muzi1.png가 입력으로 들어오면,
     * 정렬 후에도 입력 시 주어진 두 파일의 순서가 바뀌어서는 안 된다.
     *
     *
     * 무지를 도와 파일명 정렬 프로그램을 구현하라.
     * </pre>
     */

    @Nested
    public class TestCases {

        @ParameterizedTest
        @ValueSource(strings = {"f1","foo9.txt", "foo010bar020.zip", "F-15", "F-0002500 Freedom 002Fighter"})
        public void splitTest(String file) {

            int start = -1;
            int end = -1;
            char[] arr = file.toCharArray();
            for( int i = 0; i <  arr.length; i ++ ){
                char check = arr[i];
                if( start == -1 && (check >= 48 && check <= 57)) start = i;
                if( start != -1 && (check < 48 || check > 57)) {
                    end = i;
                    break;
                }
            }


            if( end == -1) end = file.length();
            if( end - start >= 5) end = start + 5;
            String number = file.substring(start, end);


            String[] split = file.split(number);
            String head = split[0];
            String tail = split.length < 2 ? "" : split[1];

            System.out.println(head);
            System.out.println(number);
            System.out.println(tail);
        }

        @ParameterizedTest
        @ValueSource(strings = {"f1","foo9.txt", "foo010bar020.zip", "F-15", "F-0002500 Freedom 002Fighter"})
        public void splitTest2(String s) {

            int startNumberIdx = -1;
            boolean first = true;
            int endNumberIdx = -1;
            int size = 0;

            for(int j = 0; j < s.length(); j++){
                if(Character.isDigit(s.charAt(j))){
                    if(first) {
                        startNumberIdx = j;
                        first = false;
                    }
                    size++;
                }else{
                    if(!first) break;
                }
            }

            endNumberIdx = startNumberIdx + size - 1;

            String head = s.substring(0, startNumberIdx);
            String number = s.substring(startNumberIdx, endNumberIdx+1);
            String tail = s.substring(endNumberIdx+1);

            System.out.println(head);
            System.out.println(number);
            System.out.println(tail);
        }
        @Test
        public void case1 () {
            String[] input = new String[] { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" };
            String[] output = new String[] {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"};

            Assertions.assertArrayEquals(output, solution(input));
        }

        @Test
        public void case2 () {
            String[] input = new String[] { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };
            String[] output = new String[] {"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"};

            Assertions.assertArrayEquals(output, solution(input));
        }

        @Test
        public void case3 () {
            String[] input = new String[] { "Muzi01.png", "mUzi01.png", "MuZi01.png", "Muzi1.png", "mUzi01.pdf", "MuZi01.dmg", };
            String[] output = new String[] {"Muzi01.png", "mUzi01.png", "MuZi01.png", "Muzi1.png", "mUzi01.pdf", "MuZi01.dmg", };

            Assertions.assertArrayEquals(output, solution(input));
        }

        @Test
        public void case4 () {
            String[] input = new String[] { "Muzi000001.png", "mUzi000003.png", "MuZi000002.png" };
            String[] output = new String[] {"Muzi000001.png", "mUzi000003.png", "MuZi000002.png"  };

            Assertions.assertArrayEquals(output, solution(input));
        }

        @Test
        public void case6 () {
            String[] input = new String[] { "F-0002500 Freedom 002Fighter", "F-0002510 Freedom 002Fighter" };
            String[] output = new String[] { "F-0002500 Freedom 002Fighter", "F-0002510 Freedom 002Fighter" };

            Assertions.assertArrayEquals(output, solution(input));
        }
    }

    public String[] solution(String[] files) {
        String[][] container = new String[files.length][3];

        for( int j = 0; j < files.length; j ++  ) {
            String file = files[j];
//13, 20에서 FAIL 원인
//            char[] arr = file.toCharArray();
//            String number = "";
//            for (int i = 0; i < arr.length; i++) {
//                char check = arr[i];
//                if (number.length() == 5) break;
//                if (number.length() <= 0 && !Character.isDigit(check)) continue;
//                if (Character.isDigit(check)) number += check;
//                else break;
//            }
//
//            String[] split = file.split(number);
//            String head = split[0];
//            String tail = split.length < 2 ? "" : split[1];
//
//            container[j][0] = head;
//            container[j][1] = number;
//            container[j][2] = tail;
            int start = -1;
            int end = -1;
            char[] arr = file.toCharArray();
            for( int i = 0; i <  arr.length; i ++ ){
                char check = arr[i];
                if( start == -1 && Character.isDigit(check)) start = i;
                if( start != -1 && !Character.isDigit(check)) {
                    end = i;
                    break;
                }
            }


            if( end == -1) end = file.length();
            if( end - start > 5) end = start + 5;

            container[j][0] = file.substring(0, start);
            container[j][1] = file.substring(start, end);
            container[j][2] = file.substring(end);

    }



        Arrays.sort(container,  (o1, o2) -> {
            int headerResult = o1[0].compareToIgnoreCase(o2[0]);
            if( headerResult != 0 ) return headerResult;

            int numberResult = Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
            if( numberResult != 0) return numberResult;

            return 0;
        });
        String[] answer = new String[files.length];
        for( int i = 0; i < answer.length; i ++) {
            answer[i] = container[i][0] +container[i][1] + container[i][2];
        }

        return answer;
    }


    public String[] solutionList(String[] files) {

        String HEADER =  "header";
        String NUMBER =  "number";
        String TAIL =  "tail";

        List<Map<String, String>> listMap = new LinkedList<>();
        for( String file : files ) {
//            int start = -1;
//            int end = -1;
//            char[] arr = file.toCharArray();
//            for( int i = 0; i <  arr.length; i ++ ){
//                char check = arr[i];
//                if( start == -1 && (check >= 48 && check <= 57)) start = i;
//                if( start != -1 && (check < 48 || check > 57)) {
//                    end = i;
//                    break;
//                }
//            }


//            if( end == -1) end = file.length();
//            if( end - start > 5) end = start + 5;
//            String number = file.substring(start, end);

            char[] arr = file.toCharArray();
            String number = "";
            for( int i = 0; i <  arr.length; i ++ ){
                char check = arr[i];
                if( number.length() == 5) break;
                if( number.length() <= 0 && !Character.isDigit(check)) continue;
                if( Character.isDigit(check) ) number += check;
                else break;
            }




            String[] split = file.split(number);
            String head = split[0];
            String tail = split.length < 2 ? null : split[1];

            listMap.add(Map.of(
                    HEADER, head,
                    NUMBER, number,
                    TAIL, tail
            ));
        }

        Collections.sort(listMap, (o1, o2) -> {


            int headerResult = o1.get(HEADER).compareToIgnoreCase(o2.get(HEADER));
            if( headerResult != 0 ) return headerResult;

            int numberResult = Integer.parseInt(o1.get(NUMBER)) - Integer.parseInt(o2.get(NUMBER));
            if( numberResult != 0) return numberResult;


            return 0;
        });



        return listMap.stream().map(map -> {
            String result = map.get(HEADER)+map.get(NUMBER);
            if(Objects.nonNull(map.get(TAIL))) result+= map.get(TAIL);
            return result;
        }).toArray(String[]::new);
    }

}
