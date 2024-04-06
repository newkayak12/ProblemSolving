package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class VisitPathLength {
    //https://school.programmers.co.kr/learn/courses/30/lessons/49994v
    /**
     * <pre>
     *
     * 게임 캐릭터를 4가지 명령어를 통해 움직이려 합니다. 명령어는 다음과 같습니다.
     * - U: 위쪽으로 한 칸 가기
     * - D: 아래쪽으로 한 칸 가기
     * - R: 오른쪽으로 한 칸 가기
     * - L: 왼쪽으로 한 칸 가기
     * 캐릭터는 좌표평면의 (0, 0) 위치에서 시작합니다.
     * 좌표평면의 경계는
     * 왼쪽 위(-5, 5),
     * 왼쪽 아래(-5, -5),
     * 오른쪽 위(5, 5),
     * 오른쪽 아래(5, -5)로 이루어져 있습니다.
     *
     * 예를 들어, "ULURRDLLU"로 명령했다면
     * <img src="../img/shot.png">
     * 이때, 우리는 게임 캐릭터가 지나간 길 중 캐릭터가 처음 걸어본 길의 길이를 구하려고 합니다.
     * 예를 들어 위의 예시에서 게임 캐릭터가 움직인 길이는 9이지만,
     * 캐릭터가 처음 걸어본 길의 길이는 7이 됩니다. (8, 9번 명령어에서 움직인 길은 2, 3번 명령어에서 이미 거쳐 간 길입니다)
     * 단, 좌표평면의 경계를 넘어가는 명령어는 무시합니다.
     * 예를 들어, "LULLLLLLU"로 명령했다면
     * </pre>
     *
     */

    @Nested
    class TestCases {

        @Test
        public void case1 () {
            String dirs = "ULURRDLLU";
            int answer = 7;

            Assertions.assertEquals(answer, solution(dirs));
        }

        @Test
        public void case2 () {
            String dirs = "LULLLLLLU";
            int answer = 7;

            Assertions.assertEquals(answer, solution(dirs));
        }

    }

    public int solution(String dirs) {
        int x = 0;
        int y = 0;
        Set<String> path = new LinkedHashSet<>();

        for ( String dir : dirs.split("") ) {
            switch ( dir ) {
                case "U": {
                    if( y + 1 > 5) break;
                    path.add(this.setting(x, y, x, y + 1));
                    y += 1;
                    break;
                }
                case "D": {
                    if( y - 1 < -5) break;
                    path.add(this.setting(x, y, x, y - 1));
                    y -= 1;
                    break;
                }
                case "R": {
                    if( x + 1 > 5) break;
                    path.add(this.setting(x, y, x + 1, y));
                    x += 1;
                    break;
                }
                case "L": {
                    if( x - 1 < -5) break;
                    path.add(this.setting(x, y, x - 1, y));
                    x -= 1;
                    break;
                }
            }
        }

        System.out.println(path);
        return path.size();
    }

    private String setting( int x, int y, int px, int py ) {
        String[] arr = new String[] {
                String.format("%d,%d", x, y),
                String.format("%d,%d",  px, py)
        };

        return Arrays.stream(arr).sorted().reduce("", (p, n) -> p+n);
    }
}
