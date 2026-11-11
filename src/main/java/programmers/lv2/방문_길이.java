package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class 방문_길이 {


    @Test
    public void solution1() {
        String dirs = "ULURRDLLU";
        int expected = 7;

        assertEquals(expected, calculate(dirs));
    }

    @Test
    public void solution2() {
        String dirs = "LULLLLLLU";
        int expected = 7;

        assertEquals(expected, calculate(dirs));
    }

    private int calculate(String dirs) {
        int max = 5;
        int min = -5;
        Set<String> position = new HashSet<>();

        int x = 0;
        int y = 0;


        for (Character element : dirs.toCharArray()) {
            switch (element) {
                case 'L':
                    if (x - 1 < min) {
                        break;
                    }
                    position.add(x + ";" + y + ">" + (x - 1) + ";" + y);
                    position.add((x - 1) + ";" + y + ">" + (x) + ";" + y);
                    x -= 1;
                    break;
                case 'R':
                    if (x + 1 > max) {
                        break;
                    }
                    position.add(x + ";" + y + ">" + (x + 1) + ";" + y);
                    position.add((x + 1) + ";" + y + ">" + (x) + ";" + y);
                    x += 1;
                    break;
                case 'U':
                    if (y + 1 > max) {
                        break;
                    }
                    position.add(x + ";" + y + ">" + x + ";" + (y + 1));
                    position.add(x + ";" + (y+1) + ">" + x + ";" + (y));
                    y += 1;
                    break;
                case 'D':
                    if (y - 1 < min) {
                        break;
                    }
                    position.add(x + ";" + y + ">" + x + ";" + (y - 1));
                    position.add(x + ";" + (y - 1) + ">" + x + ";" + (y));
                    y -= 1;
                    break;
            }

        }

        return position.size() / 2;
    }

}
