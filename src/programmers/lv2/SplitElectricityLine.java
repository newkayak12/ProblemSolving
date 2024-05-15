package programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SplitElectricityLine {
    //https://school.programmers.co.kr/learn/courses/30/lessons/86971



    /**
     * <pre>
     *  문제 설명
     *
     * n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
     * 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
     * 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
     *
     * 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다.
     * 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때,
     * 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록
     * solution 함수를 완성해주세요.
     *
     * 제한사항
     *  - n은 2 이상 100 이하인 자연수입니다.
     *  - wires는 길이가 n-1인 정수형 2차원 배열입니다.
     *      - wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
     *      - 1 ≤ v1 < v2 ≤ n 입니다.
     *      - 전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
     * </pre>
     */

    @Nested
    public class TestCases {
        @Test
        public void case1() {
            int n = 9;
            int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
            int result = 3;

            Assertions.assertEquals(result, solution(n, wires));
        }

        @Test
        public void case2() {
            int n = 4;
            int[][] wires = {{1, 2}, {2, 3}, {3, 4}};
            int result = 0;

            Assertions.assertEquals(result, solution(n, wires));
        }

        @Test
        public void case3() {
            int n = 7;
            int[][] wires = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};
            int result = 1;

            Assertions.assertEquals(result, solution(n, wires));
        }
    }

    private Map<Integer, List<Integer>> prepare(int[][] wires) {
        Map<Integer, List<Integer>> map = new HashMap<>();


        for( int i = 0; i < wires.length; i ++ ) {
            int start = wires[i][0];
            int end = wires[i][1];

            map.putIfAbsent(start, new LinkedList<Integer>());
            map.putIfAbsent(end, new LinkedList<Integer>());
            map.get(start).add(end);
            map.get(end).add(start);
        }

        return map;
    }

    private int traverse(Map<Integer, List<Integer>> map, boolean[] visited) {
        Queue<Integer> queue = new LinkedList();
        queue.add(1);

        int count = 0;
        while (!queue.isEmpty()) {
            Integer elem = queue.poll();
            if( visited[ elem - 1 ] ) continue;
            count += 1;
            visited[ elem - 1 ] = true;
            queue.addAll(map.get(elem));
        }

        return count;
    }
    public int solution(int n, int[][] wires) {
        Map<Integer, List<Integer>> map = prepare(wires);

        int answer = Integer.MAX_VALUE;

        for ( int i = 0; i < wires.length; i ++ ) {
            int start = wires[i][0];
            int end = wires[i][1];
            map.get(start).remove(Integer.valueOf(end));
            map.get(end).remove(Integer.valueOf(start));

            int count = traverse(map, new boolean[map.size()]);
            answer = Math.min(answer, Math.abs(count - (n - count)));

            map.get(start).add(end);
            map.get(end).add(start);
        }



        return answer;
    }




    class success {
        public int solution(int n, int[][] wires) {
            List<Integer>[] graph = new LinkedList[ n + 1 ];
            int min = Integer.MAX_VALUE;
            for ( int i = 1; i <= n; i++ ) graph[i] = new LinkedList<>();
            for ( int i = 0; i < wires.length; i++ ) {
                int start = wires[i][0];
                int end = wires[i][1];
                graph[start].add(end);
                graph[end].add(start);
            }

            for ( int i = 0; i < wires.length; i ++ ) {
                int start = wires[i][0];
                int end = wires[i][1];

                boolean[] visited = new boolean[n + 1];
                graph[start].remove(Integer.valueOf(end));
                graph[end].remove(Integer.valueOf(start));

                int count = dfs(1, visited, graph);
                int diff = Math.abs(count - (n - count));
                min = Math.min(diff, min);

                graph[start].add(end);
                graph[end].add(start);

            }

            System.out.println(Arrays.toString(graph));

            return min;
        }

        int dfs(int start, boolean[] visited, List<Integer>[] graph) {
            visited[start] = true;
            int cnt = 1;

            for (int next : graph[start]) {
                if (!visited[next]) {
                    cnt += dfs(next, visited, graph);
                }
            }

            return cnt;
        }

    }

    class Failure1 {

        //3,5,8,11,12 실패
        public int solution(int n, int[][] wires) {
            int answer = n;

/**
 * 노드 수 = n
 * 간선의 수 = n - 1
 *
 * 1. 0부터 시작해서 연결된 선을 하나씩 끊는다.
 * 2. 연결 상태를 점검한다.
 * 3. 연결된 노드의 수를 구한다.
 * 4. ABS(A - B)로 최소 차이를 구한다.
 * 5. 반복
 *
 */
            for( int i = 0; i < wires.length; i ++ ) {
                int count = traverse(wires, i);
                int partA = n - count;
                int partB = count;

                System.out.println(partA + " - " + partB +" = "+Math.abs(partA - partB));

                answer = Math.min(answer, Math.abs(partA - partB));
                System.out.println(answer);
            }

            return answer;
        }

        private int traverse( int[][] wires, int skip) {
            boolean[] check = new boolean[wires.length];
            Queue<Integer> queue = new LinkedList<>();

            check[0] = true;
            check[skip] = true;

            if( skip != 0 ) {
                queue.add(wires[0][1]);
            }
            else {
                check[skip + 1] = true;
                queue.add(wires[skip + 1][1]);
            }
            int  count = 0;

            while( !queue.isEmpty() ) {
                int end = queue.poll();

                for( int i = 0; i < wires.length; i ++ ) {

                    if( check[i] ) continue;




                    if( wires[i][0] == end ) {
                        queue.add(wires[i][1]);
                        count += 1;
                        check[i] = true;
                    }
                    else if( wires[i][1] == end ) {
                        queue.add(wires[i][0]);
                        count += 1;
                        check[i] = true;
                    }

                }
            }

            return count;
        }
    }

    void print(int[][] wires) {
        for (int[] wire : wires) {
            System.out.print(Arrays.toString(wire) + ", ");
        }
        System.out.println();
    }

}
