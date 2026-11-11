package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import org.junit.jupiter.api.Test;

public class 점프와_순간_이동 {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/12980?language=java
     * BFS
     */


    @Test
    public void solution1(){
        int N = 5;
        int expected = 2;

        assertEquals(expected, calculate(N));
        assertEquals(expected, noBFS(N));
    }

    @Test
    public void solution2(){
        int N = 6;
        int expected = 2;

        assertEquals(expected, calculate(N));
        assertEquals(expected, noBFS(N));
    }

    @Test
    public void solution3(){
        int N = 5000;
        int expected = 5;

        assertEquals(expected, calculate(N));
        assertEquals(expected, noBFS(N));
    }

    private int calculate(int n) {
        ArrayDeque<Integer[]> queue = new ArrayDeque<>();
        int min = 0;
        queue.addLast(new Integer[]{n, 0});


        while(!queue.isEmpty()) {
            Integer[] element = queue.removeFirst();
            int position = element[0];
            int count = element[1];

            if(position == 0) {
                min = count;
                break;
            }

            if(position % 2 == 0 && position / 2 >= 0) queue.addLast(new Integer[]{position / 2 , count} );
            if(position % 2 != 0 && position - 1 >= 0) queue.addLast(new Integer[]{position - 1, count + 1});
        }

        return min;
    }


    private int noBFS(int n) {
        int position = n;
        int count = 0;


        while(position > 0) {

            if(position % 2 == 0 && position / 2 >= 0) {
                position /= 2;
            }
            else if(position % 2 != 0 && position - 1 >= 0) {
                position -= 1;
                count += 1;
            }
        }

        return count;
    }

}
