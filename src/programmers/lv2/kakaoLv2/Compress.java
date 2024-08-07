package programmers.lv2.kakaoLv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Compress {
    //https://school.programmers.co.kr/learn/courses/30/lessons/17684
    /**
     * <pre>
     *     압축
     * 신입사원 어피치는 카카오톡으로 전송되는 메시지를 압축하여 전송 효율을 높이는 업무를 맡게 되었다.
     * 메시지를 압축하더라도 전달되는 정보가 바뀌어서는 안 되므로,
     * 압축 전의 정보를 완벽하게 복원 가능한 무손실 압축 알고리즘을 구현하기로 했다.
     *
     * 어피치는 여러 압축 알고리즘 중에서 성능이 좋고 구현이 간단한 LZW(Lempel–Ziv–Welch)
     * 압축을 구현하기로 했다.
     * LZW 압축은 1983년 발표된 알고리즘으로,
     * 이미지 파일 포맷인 GIF 등 다양한 응용에서 사용되었다.
     * LZW 압축은 다음 과정을 거친다.
     *
     * 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
     * 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
     * 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
     * 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
     * 6. 단계 2로 돌아간다.
     *
     * 압축 알고리즘이 영문 대문자만 처리한다고 할 때,
     * 사전은 다음과 같이 초기화된다.
     * 사전의 색인 번호는 정수값으로 주어지며, 1부터 시작한다고 하자.
     *
     * 색인 번호	1	2	3	...	24	25	26
     * 단어	    A	B	C	...	X	Y	Z
     *
     * 예를 들어 입력으로 KAKAO가 들어온다고 하자.
     * 현재 사전에는 KAKAO의 첫 글자 K는 등록되어 있으나,
     * 두 번째 글자까지인 KA는 없으므로, 첫 글자 K에 해당하는 색인 번호 11을 출력하고,
     * 다음 글자인 A를 포함한 KA를 사전에 27 번째로 등록한다.
     * 두 번째 글자 A는 사전에 있으나, 세 번째 글자까지인 AK는 사전에 없으므로,
     * A의 색인 번호 1을 출력하고, AK를 사전에 28 번째로 등록한다.
     * 세 번째 글자에서 시작하는 KA가 사전에 있으므로, KA에 해당하는 색인 번호 27을 출력하고,
     * 다음 글자 O를 포함한 KAO를 29 번째로 등록한다.
     * 마지막으로 처리되지 않은 글자 O에 해당하는 색인 번호 15를 출력한다.
     * </pre>
     */

    @Nested
    class TestCases {

        @Test
        void case1 () {
            String msg = "KAKAO";
            int[] answer = new int[]{11,1,27,15};
            Assertions.assertArrayEquals(answer, solution(msg));
        }

        @Test
        void case2 () {
            String msg = "TOBEORNOTTOBEORTOBEORNOT";
            int[] answer = new int[]{20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34};
            Assertions.assertArrayEquals(answer, solution(msg));
        }

        @Test
        void case3 () {
            String msg = "ABABABABABABABAB";
            int[] answer = new int[]{1, 2, 27, 29, 28, 31, 30};
            Assertions.assertArrayEquals(answer, solution(msg));
        }
    }

   public int[] solution(String msg) {
        List<String> dictionary = IntStream.range(65, 65 + 26)
                                           .mapToObj(ascii -> ((char) ascii)+"")
                                           .collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();

        /**
         * K -> 11
         * A -> 1  (KA) -> 27 ==> A
         *
         */

        String tmp = "";
        for ( int i = 0; i < msg.length() - 1; i ++) {
            String now = String.valueOf(msg.charAt(i));
            String next = String.valueOf(msg.charAt(i + 1));

            if (!tmp.isEmpty()) now = tmp;

            int idx = dictionary.indexOf(now + next);
            if( idx <= -1 ) {
                list.add(dictionary.indexOf(now) + 1 );
                dictionary.add(now+next);
                tmp = "";
            } else {
                tmp = now+next;
            }

        }



        String result = String.valueOf(msg.charAt(msg.length() - 1));
        if( !tmp.isEmpty() ) result  = tmp;
        list.add(dictionary.indexOf(result) + 1);

        return list.stream().mapToInt(i -> i).toArray();
    }


}

/**
 * 해설
 *
 * 어피치는 여러 압축 알고리즘 중에서 성능이 좋고 구현이 간단한 LZW(Lempel–Ziv–Welch) 압축을 구현하기로 했다. LZW 압축은 1983년 발표된 알고리즘으로, 이미지 파일 포맷인 GIF 등 다양한 응용에서 사용되었다.
 *
 * LZW 압축은 다음 과정을 거친다.
 *
 * 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
 * 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
 * w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
 * 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
 * 단계 2로 돌아간다.
 */