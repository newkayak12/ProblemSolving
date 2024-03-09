package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RotatingParenthesis {
    //https://school.programmers.co.kr/learn/courses/30/lessons/76502
    /**
     * <pre>
     * 다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의합니다.
     *
     *  - (), [], {} 는 모두 올바른 괄호 문자열입니다.
     *
     *  - 만약 A가 올바른 괄호 문자열이라면, (A), [A], {A} 도 올바른 괄호 문자열입니다.
     *    예를 들어, [] 가 올바른 괄호 문자열이므로, ([]) 도 올바른 괄호 문자열입니다.
     *
     *  - 만약 A, B가 올바른 괄호 문자열이라면, AB 도 올바른 괄호 문자열입니다.
     *    예를 들어, {} 와 ([]) 가 올바른 괄호 문자열이므로, {}([]) 도 올바른 괄호 문자열입니다.
     *
     * 대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열 s가 매개변수로 주어집니다.
     * 이 s를 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가
     * 올바른 괄호 문자열이 되게 하는 x의 개수를 return 하도록 solution 함수를 완성해주세요.
     *
     * 제한사항
     *  - s의 길이는 1 이상 1,000 이하입니다.
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String s = "[](){}";
            int result = 3;

            Assertions.assertEquals(result, solution(s));
        }
        @Test
        public void case2 () {
            String s = "}]()[{";
            int result = 2;

            Assertions.assertEquals(result, solution(s));
        }
        @Test
        public void case3 () {
            String s = "[)(]";
            int result = 0;

            Assertions.assertEquals(result, solution(s));
        }
        @Test
        public void case4 () {
            String s = "}}}";
            int result = 0;

            Assertions.assertEquals(result, solution(s));
        }
        @Test
        public void case5 () {
            String s = "[](){}(";
            int result = 0;

            Assertions.assertEquals(result, solution(s));
        }
        @Test
        public void case6 () {
            String s = "([{)}]";
            int result = 0;

            Assertions.assertEquals(result, solution(s));
        }
    }



    public int solution( String s ) {
        int answer = 0;
        Queue<String> queue = new LinkedList<>();
        for(String parenthesis : s.split("")) queue.add(parenthesis);


        if (s.length() % 2 != 0) return 0;

        for( int i = 0; i < queue.size(); i ++ ) {
            if( check(queue) ) answer += 1;
            String piece = queue.poll();
            queue.add(piece);
        }

        return answer;
    }

    private boolean check( Queue<String>  s ) {
        Stack<String > stack = new Stack<>();
        String smOpen = "(";
        String smClose = ")";

        String mdOpen = "[";
        String mdClose = "]";

        String lgOpen = "{";
        String lgClose = "}";


        for ( String c : s ) {
            if (
                smClose.equals(c) ||
                mdClose.equals(c) ||
                lgClose.equals(c)
            ) {

                if (stack.isEmpty()) return false;
                String now = stack.pop();

                boolean process = false;
                if( c.equals(smClose) && smOpen.equals(now) ) process|= true;
                if( c.equals(mdClose) && mdOpen.equals(now) ) process|= true;
                if( c.equals(lgClose) && lgOpen.equals(now) ) process|= true;

                if (!process) return false;


            } else stack.push(c);
        }
        return true;
    }
}
