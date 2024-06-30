package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Bracket {
    //retry
    //https://school.programmers.co.kr/learn/courses/30/lessons/12909
    /**
     * <pre>
     *   괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')'
     *   문자로 닫혀야 한다는 뜻입니다.
     *
     * 예를 들어
     *   "()()" 또는 "(())()" 는 올바른 괄호입니다.
     *   ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
     *   '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때,
     *
     * 문자열 s가 올바른 괄호이면 true를 return 하고,
     * 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.
     *
     * 제한사항
     *  - 문자열 s의 길이 : 100,000 이하의 자연수
     *  - 문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String s = "()()";
            Boolean answer = Boolean.TRUE;
            Assertions.assertEquals(answer, solution(s));
        }
        @Test
        public void case2 () {
            String s = "(())()";
            Boolean answer = Boolean.TRUE;
            Assertions.assertEquals(answer, solution(s));
        }
        @Test
        public void case3 () {
            String s = ")()(";
            Boolean answer = Boolean.FALSE;
            Assertions.assertEquals(answer, solution(s));
        }
        @Test
        public void case4 () {
            String s = "(()(";
            Boolean answer = Boolean.FALSE;
            Assertions.assertEquals(answer, solution(s));
        }
        @Test
        public void case5 () {
            String s = "()())()";
            Boolean answer = Boolean.FALSE;
            Assertions.assertEquals(answer, solution(s));
        }
        @Test
        public void case6 () {
            String s = "( )((( )";
            Boolean answer = Boolean.FALSE;
            Assertions.assertEquals(answer, solution(s));
        }

    }

    public boolean solution( String s ) {
        Stack<Character> characters = new Stack<>();
        boolean result = true;
        char open = '(';
        char close = ')';

        for(int i = 0; i < s.length(); i ++ ) {
            char here = s.charAt(i);
            if( here == open ) {
                characters.add(here);
            }
            else {
                if( !characters.empty() ) characters.pop();
                else {
                    result = false;
                    break;
                }
            }
        }


        if( !characters.isEmpty() ) return false;
        else return result;
    }


    class Success {
        public Boolean solution( String s ) {
            int openCount = 0;
            int closeCount = 0;

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    openCount++;
                } else if (s.charAt(i) == ')') {
                    closeCount++;
                }
                if (openCount < closeCount) {
                    return false;
                }
            }
            if (openCount == closeCount) {
                return true;
            }
            return false;
        }

        class FailureCase { //timeout
            public Boolean solution1( String s ) {
                String regExp = "\\(\\)";
                Pattern pattern = Pattern.compile(regExp);
                Boolean exist = Boolean.FALSE;
                do {
                    exist = Boolean.FALSE;
                    Matcher m = pattern.matcher(s);
                    exist = m.find();

                    if (exist) s = s.replaceFirst(regExp,"");
                } while ( exist );

                if("".equals(s)) return Boolean.TRUE;
                else return Boolean.FALSE;
            }
            public Boolean solution2( String s ) {

                while(s.contains("()")){
                    s = s.replace("()","");
                }

                if("".equals(s)) return Boolean.TRUE;
                else return Boolean.FALSE;
            }
            public Boolean solution3( String s ) {
                String[] brackets = s.split("");
                Deque<String> deque = new LinkedBlockingDeque<>(Arrays.stream(brackets).collect(Collectors.toList()));
                Boolean result = Boolean.TRUE;


                if( brackets.length % 2 != 0) return Boolean.FALSE;
                if( brackets[0].equals(")") || brackets[brackets.length - 1].equals("(")) return Boolean.FALSE;

                /**
                 * ())()
                 */

                deque.pollFirst();
                loop: while (!deque.isEmpty()) {
                    if(")".equals(deque.getFirst()) ) {
                        deque.pollFirst();
                        deque.pollFirst();
                    }
                    else if(")".equals(deque.getLast()) ) deque.pollLast();
                    else {
                        result = Boolean.FALSE;
                        break loop;
                    }
                }

                return result;
            }

            //
            public Boolean solution4( String s ) {

                if( s.length() % 2 != 0) return Boolean.FALSE;
                if( s.charAt(0) == ')' || s.charAt(s.length() - 1) == '(') return Boolean.FALSE;

                Boolean sz = (s.indexOf("(", 1) + 1) % 2 == 0;

                if (
                        (s.indexOf("(", 1) + 1) % 2 == 0 && (s.lastIndexOf(")") + 1 % 2 !=0 ) ||
                                (s.indexOf("(", 1) ) % 2 != 0 && (s.lastIndexOf(")", s.length() - 2)  % 2 == 0 ) ||
                                (s.indexOf(")", 1) + 1) % 2 == 0 && (s.lastIndexOf("(") + 1 % 2 !=0 ) ||
                                (s.indexOf(")", 1) ) % 2 != 0 && (s.lastIndexOf("(", s.length() - 2)  % 2 == 0 )
                ) return Boolean.TRUE;
                else return Boolean.FALSE;
            }
        }

    }


}
