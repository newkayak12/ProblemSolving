package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import org.junit.jupiter.api.Test;

public class 올바른_괄호 {

    @Test
    public void solution1() {

        String s = "()()";
        Boolean expected = true;

        assertEquals(expected, calculate(s));
    }

    @Test
    public void solution2() {

        String s = "(())()";
        Boolean expected = true;

        assertEquals(expected, calculate(s));
    }

    @Test
    public void solution3() {

        String s = ")()(";
        Boolean expected = false;

        assertEquals(expected, calculate(s));
    }

    @Test
    public void solution4() {

        String s = "(()(";
        Boolean expected = false;

        assertEquals(expected, calculate(s));
    }

    public boolean calculate( String s ) {
        ArrayDeque<Character> arrayDeque = new ArrayDeque<>();

        for(Character i : s.toCharArray()){
            if(i == '(') {
                arrayDeque.addLast(i);
            }
            else {
                Character last = arrayDeque.peekLast();
                if(last == null) return false;
                else arrayDeque.removeLast();
            }
        }

        if(!arrayDeque.isEmpty()) return false;
        else return true;
    }
}
