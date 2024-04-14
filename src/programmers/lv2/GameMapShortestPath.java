package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GameMapShortestPath {
    //https://school.programmers.co.kr/learn/courses/30/lessons/1844

    /**
     * <pre>
     * ROR 게임은 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임입니다.
     * 따라서, 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리합니다.
     * 지금부터 당신은 한 팀의 팀원이 되어 게임을 진행하려고 합니다.
     * 다음은 5 x 5 크기의 맵에,
     * 당신의 캐릭터가 (행: 1, 열: 1) 위치에 있고,
     * 상대 팀 진영은 (행: 5, 열: 5) 위치에 있는 경우의 예시입니다.
     *
     *
     * </pre>
     * <img src="./img/스크린샷 2024-02-10 14.53.35.png"/>
     *
     * <pre>
     * 위 그림에서 검은색 부분은 벽으로 막혀있어 갈 수 없는 길이며, 흰색 부분은 갈 수 있는 길입니다.
     * 캐릭터가 움직일 때는 동, 서, 남, 북 방향으로 한 칸씩 이동하며, 게임 맵을 벗어난 길은 갈 수 없습니다.
     * 아래 예시는 캐릭터가 상대 팀 진영으로 가는 두 가지 방법을 나타내고 있습니다.
     * 첫 번째 방법은 11개의 칸을 지나서 상대 팀 진영에 도착했습니다.
     * 두 번째 방법은 15개의 칸을 지나서 상대팀 진영에 도착했습니다.
     *
     *
     * 만약, 상대 팀이 자신의 팀 진영 주위에 벽을 세워두었다면 상대 팀 진영에 도착하지 못할 수도 있습니다.
     *
     * 게임 맵의 상태 maps가 매개변수로 주어질 때,
     * 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성해주세요.
     * 단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.
     *
     * 제한사항
     *  maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로,
     *  n과 m은 각각 1 이상 100 이하의 자연수입니다.
     *
     *  n과 m은 서로 같을 수도,
     *  다를 수도 있지만,
     *  n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
     *
     *  maps는 0과 1로만 이루어져 있으며,
     *  0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
     *
     *  처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며,
     *  상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.
     * </pre>
     */


    //Dijkstra?
    //Dynamic?
    @Nested
    class TestCases {

        @Test
        public void case1Queue() {
            int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
            int answer = 11;

            Assertions.assertEquals(answer, solutionBFS(map));

        }

        @Test
        public void case1Stack() {
            int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
            int answer = 11;

            Assertions.assertEquals(answer, solutionDFS(map));
        }

        @Test
        public void case2Queue() {
            int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
            int answer = -1;

            Assertions.assertEquals(answer, solutionBFS(map));
        }

        @Test
        public void case2Stack() {
            int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}};
            int answer = -1;

            Assertions.assertEquals(answer, solutionDFS(map));
        }

        @Test
        public void case3() {
            int[][] map = {{1, 0}};
            int answer = -1;

            Assertions.assertEquals(answer, solutionBFS(map));
        }
        @Test
        public void case4() { //19번 반례

            int[][] map = {{1}, {0}};
            int answer = -1;

            Assertions.assertEquals(answer, solutionBFS(map));
        }
    }


    public int solutionBFS(int[][] maps) {
        if (!checkIsConnectedBFS(maps)) return -1;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] checkMap = new boolean[maps.length][maps[0].length];

        queue.add(new int[]{0, 0, 1});
        checkMap[0][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];
            int score = now[2];


            if (y == maps.length - 1 && x == maps[0].length - 1) return score;
            if (y > 0 && maps[y - 1][x] != 0 && (!(checkMap[y - 1][x]))) {
                queue.add(new int[]{y - 1, x, score + 1});
                checkMap[y - 1][x] = true;
            }
            if (x > 0 && maps[y][x - 1] != 0 && (!(checkMap[y][x - 1]))) {
                queue.add(new int[]{y, x - 1, score + 1});
                checkMap[y][x - 1] = true;
            }
            if (y < maps.length - 1 && maps[y + 1][x] != 0 && (!(checkMap[y + 1][x]))) {
                queue.add(new int[]{y + 1, x, score + 1});
                checkMap[y + 1][x] = true;
            }
            if (x < maps[0].length - 1 && maps[y][x + 1] != 0 && (!(checkMap[y][x + 1]))) {
                queue.add(new int[]{y, x + 1, score + 1});
                checkMap[y][x + 1] = true;
            }

            print(checkMap);
        }


        return -1;
    }

    private Boolean checkIsConnectedBFS(int[][] map) {
        int yIdx = map.length - 1;
        int xIdx = map[0].length - 1;


        if(yIdx < 1 || xIdx < 1) return Boolean.TRUE;
        if (map[yIdx][xIdx - 1] == 0 && map[yIdx - 1][xIdx] == 0) return Boolean.FALSE;
        else return Boolean.TRUE;
    }

    /**
     * 몇몇 케이스에 실패 떨어짐
     * -> DFS 실패
     */

    public static int solutionDFS(int[][] maps) {
        int endY = maps.length - 1;
        int endX = maps[endY].length - 1;
        if (!checkIsConnectedDFS(maps)) return -1;

        Stack<int[]> nextStep = new Stack();
        boolean[][] checkedMap = new boolean[maps.length][maps[0].length];
        nextStep.add(new int[]{0, 0, 1});
        checkedMap[0][0] = true;

        int result = -1;

        while (!nextStep.isEmpty()) {
            int[] step = nextStep.pop();
            int y = step[0];
            int x = step[1];
            int count = step[2];

            if (y == endY && x == endX) {
                result = step[2];
                continue;
            }


            if (y > 0 && maps[y - 1][x] != 0 && (!(checkedMap[y - 1][x]))) {
                nextStep.push(new int[]{y - 1, x, count + 1});
                checkedMap[y - 1][x] = true;
            }

            if (x > 0 && maps[y][x - 1] != 0 && (!(checkedMap[y][x - 1]))) {
                nextStep.push(new int[]{y, x - 1, count + 1});
                checkedMap[y][x - 1] = true;
            }


            if (y < maps.length - 1 && maps[y + 1][x] != 0 && (!(checkedMap[y + 1][x]))) {
                nextStep.push(new int[]{y + 1, x, count + 1});
                checkedMap[y + 1][x] = true;
            }
            if (x < maps[0].length - 1 && maps[y][x + 1] != 0 && (!(checkedMap[y][x + 1]))) {
                nextStep.push(new int[]{y, x + 1, count + 1});
                checkedMap[y][x + 1] = true;
            }

        }
        return result;
    }

    private static Boolean checkIsConnectedDFS(int[][] map) {
        int yIdx = map.length - 1;
        int xIdx = map[0].length - 1;


        if(yIdx < 1 || xIdx < 1) return Boolean.TRUE;
        if (map[yIdx][xIdx - 1] == 0 && map[yIdx - 1][xIdx] == 0) return Boolean.FALSE;
        else return Boolean.TRUE;
    }




    private static void print(boolean[][] map) {
        System.out.println(" ========== ");
        for (boolean[] row : map) {
            for ( boolean b : row ) {
                System.out.print(String.format("%s ", b ? "T" : "F"));
            }
            System.out.println();
        }
        System.out.println(" ========== ");
    }

    class Failure1 {
        public int solutionStack(int[][] maps) {
            int endY = maps.length - 1;
            int endX = maps[endY].length - 1;
            if (!checkIsConnectedStack(maps, endX, endY)) return -1;

            Stack<int[]> nextStep = new Stack();
            Boolean[][] checkedMap = new Boolean[maps.length][maps[0].length];
            nextStep.add(new int[]{0, 0, 1});
            checkedMap[0][0] = true;

            int result = -1;

            while (!nextStep.isEmpty()) {
                int[] step = nextStep.pop();
                int y = step[0];
                int x = step[1];
                int count = step[2];

                if (y == endY && x == endX) {
                    result = step[2];
                    continue;
                }


                if (y > 0 && maps[y - 1][x] != 0 && (Objects.isNull(checkedMap[y - 1][x]))) {
                    nextStep.push(new int[]{y - 1, x, count + 1});
                    checkedMap[y - 1][x] = true;
                }

                if (x > 0 && maps[y][x - 1] != 0 && (Objects.isNull(checkedMap[y][x - 1]))) {
                    nextStep.push(new int[]{y, x - 1, count + 1});
                    checkedMap[y][x - 1] = true;
                }


                if (y < maps.length - 1 && maps[y + 1][x] != 0 && (Objects.isNull(checkedMap[y + 1][x]))) {
                    nextStep.push(new int[]{y + 1, x, count + 1});
                    checkedMap[y + 1][x] = true;
                }
                if (x < maps[0].length - 1 && maps[y][x + 1] != 0 && (Objects.isNull(checkedMap[y][x + 1]))) {
                    nextStep.push(new int[]{y, x + 1, count + 1});
                    checkedMap[y][x + 1] = true;
                }

            }
            return result;
        }

        private  Boolean checkIsConnectedStack(int[][] map, int x, int y) {
            if (map[y][x - 1] == 0 && map[y - 1][x] == 0) return Boolean.FALSE;
            else return Boolean.TRUE;
        }

    }
}
