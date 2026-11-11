package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import org.junit.jupiter.api.Test;

public class 짝지어_제거하기 {

    @Test
    public void solution1(){
        String s = "baabaa";
        int expected = 1;

        assertEquals(expected, calculate(s));
    }

    @Test
    public void solution2(){
        String s = "cdcd";
        int expected = 0;

        assertEquals(expected, calculate(s));
    }

    private int calculate(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(Character character : s.toCharArray()){
            if(stack.peekLast() == null) {
                stack.add(character);
                continue;
            }


            if(stack.peekLast() == character) {
                stack.removeLast();
            }
            else {
                stack.addLast(character);
            }
        }

        System.out.println(stack);
        int bit = 0;
        if(stack.isEmpty()) {
            bit = 1;
        }

        return bit;
    }
}
