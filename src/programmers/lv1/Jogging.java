package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Jogging {

    /**
     * <pre>
     * 지나다니는 길을 'O', 장애물을 'X'로 나타낸 직사각형 격자 모양의 공원에서
     * 로봇 강아지가 산책을 하려합니다.
     *
     * 산책은 로봇 강아지에 미리 입력된 명령에 따라 진행하며,
     * 명령은 다음과 같은 형식으로 주어집니다.
     *
     *     - ["방향 거리", "방향 거리" … ]
     *
     * 예를 들어 "E 5"는 로봇 강아지가 현재 위치에서 동쪽으로 5칸 이동했다는 의미입니다.
     * 로봇 강아지는 명령을 수행하기 전에 다음 두 가지를 먼저 확인합니다.
     *
     *     - 주어진 방향으로 이동할 때 공원을 벗어나는지 확인합니다.
     *     - 주어진 방향으로 이동 중 장애물을 만나는지 확인합니다.
     *
     * 위 두 가지중 어느 하나라도 해당된다면,
     * 로봇 강아지는 해당 명령을 무시하고 다음 명령을 수행합니다.
     *
     * 공원의 가로 길이가 W, 세로 길이가 H라고 할 때,
     *
     * 공원의 좌측 상단의 좌표는 (0, 0),
     * 우측 하단의 좌표는 (H - 1, W - 1) 입니다.
     *
     *  <table>
     *      <tr>
     *          <td>(0, 0)</td>
     *          <td> ... </td>
     *          <td></td>
     *      </tr>
     *      <tr>
     *           <td></td>
     *           <td></td>
     *           <td></td>
     *       </tr>
     *       <tr>
     *           <td></td>
     *           <td></td>
     *           <td>(H -1, W - 1)</td>
     *       </tr>
     *  </table>
     *
     *
     * 공원을 나타내는 문자열 배열 park,
     * 로봇 강아지가 수행할 명령이 담긴 문자열 배열 routes가 매개변수로 주어질 때,
     * 로봇 강아지가 모든 명령을 수행 후 놓인 위치를 [세로 방향 좌표, 가로 방향 좌표] 순으로
     * 배열에 담아 return 하도록 solution 함수를 완성해주세요.
     *
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String[] park = {"SOO","OOO","OOO"};
            String[] routes = {"E 2","S 2","W 1"};
            int[] result = {2, 1};

            Assertions.assertArrayEquals(result, solution(park,routes));
        }


        @Test
        public void case2 () {
            String[] park = {"SOO","OXX","OOO"};
            String[] routes = {"E 2","S 2","W 1"};
            int[] result = {0, 1};

            Assertions.assertArrayEquals(result, solution(park,routes));
        }

        @Test
        public void case3 () {
            String[] park = {"OSO","OOO","OXO","OOO"};
            String[] routes = {"E 2","S 3","W 1"};
            int[] result = {0, 0};

            Assertions.assertArrayEquals(result, solution(park,routes));
        }
    }


    public int[] solution( String[] park, String[] routes ) {
        int startX = 0;
        int startY = 0;

        String[][] map = Arrays.stream(park)
                               .map(elemX -> elemX.split(""))
                               .toArray(String[][]::new);

        print(map);

        findY: for (; startY < park.length; startY ++ ) {
            findX: for (; startX < park[0].length(); startX ++ ) {
                if("S".equals(map[startY][startX])) break findY;
            }
        }



        System.out.println();
        System.out.println(startY + ", "+ startX);
        return new int[] {1,0};
    }


    public void print (String[][] map) {
        for (String[] x : map) {
            System.out.println(Arrays.stream(x).collect(Collectors.joining("|")));
        }
    }











    @Deprecated
    public int[] solutionBak( String[] park, String[] routes ) {
       String startPoint = "0,0";
       List<String> obstacles = new ArrayList();

       int xMin = 0;
       int yMin = 0;
       int xMax = park[0].length() - 1;
       int yMax = park.length - 1;


       for ( int y = 0; y < park.length; y ++  ) {
           String[] xLine = park[y].split("");
           for ( int x = 0; x < xLine.length; x ++ ) {
               if( xLine[x].equals("X")) obstacles.add(getPosition(y, x));
               if( xLine[x].equals("S")) startPoint = getPosition(y, x);
           }
       }


        for (String path : routes) {
            System.out.println(startPoint);
            System.out.println(path);

            String[] pathSplit = path.split(" ");
            Boolean isHorizontal = "E".equals(pathSplit[0]) || "W".equals(pathSplit[0]) ? true : false;
            Integer distance = "E".equals(pathSplit[0]) || "S".equals(pathSplit[0]) ? Integer.parseInt(pathSplit[1]) : -1 *Integer.parseInt(pathSplit[1]);

            String[] splitPosition = startPoint.split(",");
            Integer x = Integer.parseInt(splitPosition[1]);
            Integer y = Integer.parseInt(splitPosition[0]);


            if (
                   (isHorizontal && (x + distance  < xMin ||  x + distance  >  xMax)) ||
                   (!isHorizontal && (y + distance < yMin || y + distance  >  yMax ))
            ) {
                continue;
            }



            if ( isHorizontal ) {
                int max = Math.max(x,(x + distance));
                int min = Math.min(x,(x + distance));
                Pattern patternX = Pattern.compile(String.format("%d,"+"[\\d]{%d,%d}", y, min, max), Pattern.DOTALL);
                Optional<String> obstacle = obstacles.stream().filter(elem -> patternX.matcher(elem).find()).findAny();
                if ( obstacle.isPresent() ) continue;
                startPoint = getPosition( y, x + distance);
            } else {
                int max = Math.max(y, (y + distance));
                int min = Math.min(y, (y + distance));
                Pattern patternY = Pattern.compile(String.format("[\\d]{%d,%d},%d", min, max, x), Pattern.DOTALL);
                Optional<String> obstacle = obstacles.stream().filter(elem -> patternY.matcher(elem).find()).findAny();
                if ( obstacle.isPresent() ) continue;
                startPoint = getPosition( y + distance, x);
            }


//            System.out.println(String.format("-----------%s------------", path));
//            System.out.println(startPoint);
//            System.out.println("---------------------------\n");
        }

        return Arrays.stream(startPoint.split(",")).mapToInt(Integer::parseInt).toArray();
    }
    private String getPosition( Integer y, Integer x ) {
        return String.format("%d,%d", y, x);
    }



}
