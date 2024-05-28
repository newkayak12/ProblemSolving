package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogging {

    /**
     * TODO
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
        public void case1() {
            String[] park = {"SOO", "OOO", "OOO"};
            String[] routes = {"E 2", "S 2", "W 1"};
            int[] result = {2, 1};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }


        @Test
        public void case2() {
            String[] park = {"SOO", "OXX", "OOO"};
            String[] routes = {"E 2", "S 2", "W 1"};
            int[] result = {0, 1};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

        @Test
        public void case3() {
            String[] park = {"OSO", "OOO", "OXO", "OOO"};
            String[] routes = {"E 2", "S 3", "W 1"};
            int[] result = {0, 0};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

        @Test
        public void case4() {
            String[] park = {"OSO", "OOO", "O0O", "OOO"};
            String[] routes = {"E 2", "W 2", "S 3", "N 2"};
            int[] result = {1, 1};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

        @Test
        public void case5() {
            String[] park = {"XSX", "XXX", "XXX", "XXX"};
            String[] routes = {"E 1", "W 1", "S 1", "N 1"};
            int[] result = {0, 1};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

        @Test
        public void case6() {
            String[] park = {"XSX", "XXX", "XXX", "XXX"};
            String[] routes = {"E 2", "W 2", "S 2", "N 2"};
            int[] result = {0, 1};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

        @Test
        public void case7() {
            String[] park = {"XSX", "XOX", "XXX", "XOX"};
            String[] routes = {"E 2", "W 2", "S 3", "S 1"};
            int[] result = {1, 1};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

        @Test
        public void case8() {
            String[] park = {"XSX", "XOX", "XOO", "OOX"};
            /**
             * XOX
             * XOX
             * XOO
             * OSX
             */
            String[] routes = {
                    "E 2", "W 2", //ignore
                    "S 2", "W 1", //S Ok W ignore
                    "E 1", "W 1",  // OK but SAME
                    "S 1", "E 1", // S OK E ignore
                    "W 1"};
            int[] result = {3, 0};

            Assertions.assertArrayEquals(result, solution(park, routes));
        }

    }

    public int[] solution(String[] park, String[] routes) {
        int[] coordinate = {0, 0};


        String[][] parkMap = new String[park.length][park[0].length()];






        Map<Character, Integer> moveMap = Map.of(
                'E', 1,
                'W', -1,
                'S', 1,
                'N', -1
        );

        for(int y = 0; y < park.length; y ++ ) {
            String[] row = park[y].split("");
            for ( int x = 0; x < row.length; x ++ ) {
                parkMap[y][x] = row[x];

                if( row[x].equalsIgnoreCase("s") ) {
                    coordinate[0] = y;
                    coordinate[1] = x;
                }
            }
        }


        for (String route : routes) {


            if( this.canIWalk( parkMap , coordinate, route)) {
                Integer walk = Integer.parseInt(String.valueOf(route.charAt(2)));
                Character direction = route.charAt(0);
                switch (direction) {
                    case 'E', 'W':
                        coordinate[1] += (walk * moveMap.get(direction));
                        break;
                    case 'S', 'N':
                        coordinate[0] += (walk * moveMap.get(direction));;
                        break;
                }
            }
        }



        return coordinate;
    }

    private boolean canIWalk (String[][] parkMap, int[] coordinate, String route) {

        String obstacles = "X";
        Character direction = route.charAt(0);
        Integer walk = Integer.parseInt(String.valueOf(route.charAt(2)));
        int x = coordinate[1];
        int y = coordinate[0];

        boolean result = Boolean.TRUE;

        System.out.println(Arrays.toString(coordinate));
        for(String[] map: parkMap) {
            System.out.println(Arrays.toString(map));
        }


        switch (direction) {
            case 'E':{
                walk *= 1;
                if( parkMap[0].length <=  (walk + x ) ) {
                    result = Boolean.FALSE;
                    break;
                }


                for (int i = x; i <= walk + x; i ++ ) {
                    if( parkMap[y][i].equalsIgnoreCase(obstacles) ) {
                        result = Boolean.FALSE;
                        break;
                    }
                }
                break;
            }
            case 'S': {
                walk *= 1;


                if( parkMap.length  <=  (walk + y ) ) {
                    result = Boolean.FALSE;
                    break;
                }
                for (int i = y; i <= walk + y; i ++ ) {
                    if( parkMap[i][x].equalsIgnoreCase(obstacles) ) {
                        result = Boolean.FALSE;
                        break;
                    }
                }
                break;
            }
            case 'W': {
                walk *= -1;

                if( (walk + x)  < 0) {
                    result = Boolean.FALSE;
                    break;
                }

                for (int i = x; i >= walk + x; i -- ) {

                    if( parkMap[y][i].equalsIgnoreCase(obstacles) ) {
                        result = Boolean.FALSE;
                        break;
                    }
                }
                break;
            }
            case 'N': {
                walk *= -1;
                if( (walk + y) < 0) {
                    result = Boolean.FALSE;
                    break;
                }
                for (int i = y; i >= walk + y; i -- ) {
                    if( parkMap[i][x].equalsIgnoreCase(obstacles) ) {
                        result = Boolean.FALSE;
                        break;
                    }
                }
                break;
            }
        }


        System.out.println("RESULT :::" + result+"\n");
        return result;
    }




  class Success {

    /**
     * 정답으로 제출하면 런타임에러만 죄다 남
     * switchCase쪽인거 같고 ArrayIndexOutOfBoundsException으로 추측됨
     */

    public int[] solution(String[] park, String[] routes) {
        int startX = 0;
        int startY = 0;

        String[][] map = Arrays.stream(park).map(elemX -> elemX.split("")).toArray(String[][]::new);


        findY:
        for (; startY < park.length; startY++) {
            findX:
            for (; startX < park[0].length(); startX++) {
                if ("S".equals(map[startY][startX])) break findY;
            }
        }


        route:
        for (String route : routes) {


            char direction = route.charAt(0);
            Integer distance = Character.getNumericValue(route.charAt(2));


            if ('N' == direction || 'W' == direction) distance *= -1;

            if (('E' == direction || 'W' == direction) && (startX + distance < 0 || (startX + distance) > (map[0].length - 1)))
                continue;
            if (('N' == direction || 'S' == direction) && (startY + distance < 0 || (startY + distance) > (map.length - 1)))
                continue;

            Boolean isBomb = Boolean.FALSE;
            switch (direction) {
                case 'E':
                    for (int i = startX; i <= startX + distance; i++) {
                        if ("X".equals(map[startY][i])) isBomb = Boolean.TRUE;
                    }
                    break;
                case 'W':
                    for (int i = startX; i >= startX + distance; i--) {
                        if ("X".equals(map[startY][i])) isBomb = Boolean.TRUE;
                    }
                    break;
                case 'S':
                    for (int i = startY; i <= startY + distance; i++) {
                        if ("X".equals(map[i][startX])) isBomb = Boolean.TRUE;
                    }
                    break;
                case 'N':
                    for (int i = startY; i >= startY + distance; i--) {
                        if ("X".equals(map[i][startX])) isBomb = Boolean.TRUE;
                    }
                    break;
            }

            if (isBomb) continue;
            else if (('N' == direction || 'S' == direction)) startY += distance;
            else startX += distance;

            System.out.println(route);
            System.out.println(startY + "," + startX);
            System.out.println();
        }

        return new int[]{startY, startX};
    }
  }

    /**
     * 다른 사람 풀인데 무슨 차이인지 모르겠음
     */
    class Solution {
        public int[] solution(String[] park, String[] routes) {

            // 이차원 배열을 새로 만들면서, 로봇의 시작 위치를 찾는다.
            int robotRow = -1;
            int robotColumn = -1;

            int rowNum = park.length;
            int columnNum = park[0].length();

            char[][] parkArray = new char[rowNum][columnNum];

            for (int i = 0; i < rowNum; i++) { //50
                String rowData = park[i];
                for (int j = 0; j < columnNum; j++) { //50

                    char locationData = rowData.charAt(j);
                    parkArray[i][j] = locationData;

                    if (locationData == 'S') {
                        robotRow = i;
                        robotColumn = j;
                    }
                }
            }


            // 좌표 값 저장
            Map<Character, List<Integer>> dirMove = new HashMap<Character, List<Integer>>();
            dirMove.put('E', List.of(0, 1));
            dirMove.put('W', List.of(0, -1));
            dirMove.put('N', List.of(-1, 0));
            dirMove.put('S', List.of(1, 0));


            // routes 만큼 반복한다.
            for (String route : routes) {

                char direction = route.charAt(0);
                Integer move = route.charAt(2) - '0';


                // 이동할 위치 계산
                // 현재 위치를 먼저 저장한다. (현재 명령이 불가능하다면 롤백을 해주기 위함)
                int tmpRow = robotRow;
                int tmpColumn = robotColumn;

                Boolean possible = true;

                // 해당 방향으로 몇칸을 이동해야해서, 한칸씩 이동하며 가능성을 확인한다.
                for (int i = 0; i < move; i++) {

                    Integer moveRow = robotRow + dirMove.get(direction).get(0);
                    Integer moveColumn = robotColumn + dirMove.get(direction).get(1);

                    // 공원 바깥이면 실패
                    if (0 > moveRow || moveRow >= rowNum || 0 > moveColumn || moveColumn >= columnNum) {
                        possible = false;
                        break;
                    }

                    // 장애물을 만나면 실패
                    if (parkArray[moveRow][moveColumn] == 'X') {
                        possible = false;
                        break;
                    }

                    //실제 이동
                    parkArray[moveRow][moveColumn] = 'S';
                    parkArray[robotRow][robotColumn] = 'O';

                    robotRow = moveRow;
                    robotColumn = moveColumn;
                }

                //불가능한 경우가 존재하면 롤백
                if (possible == false) {
                    robotRow = tmpRow;
                    robotColumn = tmpColumn;
                }
            }

            // 마지막에 로봇의 위치를 배열에 저장해서 return하면 끝
            int[] answer = new int[2];
            answer[0] = robotRow;
            answer[1] = robotColumn;
            return answer;
        }

    }
}
