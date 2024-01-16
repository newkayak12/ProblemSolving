package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Drag {
    //https://school.programmers.co.kr/learn/courses/30/lessons/161990

    /**
     *  <pre>
     * 코딩테스트를 준비하는 머쓱이는 프로그래머스에서 문제를 풀고
     * 나중에 다시 코드를 보면서 공부하려고 작성한 코드를 컴퓨터 바탕화면에
     * 아무 위치에나 저장해 둡니다.
     *
     * 저장한 코드가 많아지면서 머쓱이는 본인의 컴퓨터 바탕화면이 너무 지저분하다고 생각했습니다.
     * 프로그래머스에서 작성했던 코드는 그 문제에 가서 다시 볼 수 있기 때문에
     * 저장해 둔 파일들을 전부 삭제하기로 했습니다.
     *
     * 컴퓨터 바탕화면은 각 칸이 정사각형인 격자판입니다.
     * 이때 컴퓨터 바탕화면의 상태를 나타낸 문자열 배열 wallpaper가 주어집니다.
     *
     * 파일들은 바탕화면의 격자칸에 위치하고
     * 바탕화면의 격자점들은 바탕화면의 가장 왼쪽 위를 (0, 0)으로 시작해
     * (세로 좌표, 가로 좌표)로 표현합니다.
     *
     * 빈칸은 ".", 파일이 있는 칸은 "#"의 값을 가집니다.
     * 드래그를 하면 파일들을 선택할 수 있고, 선택된 파일들을 삭제할 수 있습니다.
     *
     * 머쓱이는 최소한의 이동거리를 갖는 한 번의 드래그로 모든 파일을 선택해서
     * 한 번에 지우려고 하며 드래그로 파일들을 선택하는 방법은 다음과 같습니다.
     *
     * 드래그는 바탕화면의 격자점 S(lux, luy)를 마우스 왼쪽 버튼으로 클릭한 상태로
     * 격자점 E(rdx, rdy)로 이동한 뒤 마우스 왼쪽 버튼을 떼는 행동입니다.
     *
     * 이때, "점 S에서 점 E로 드래그한다"고 표현하고
     * 점 S와 점 E를 각각 드래그의 시작점,
     * 끝점이라고 표현합니다.
     *
     * 점 S(lux, luy)에서 점 E(rdx, rdy)로 드래그를 할 때,
     * "드래그 한 거리"는 |rdx - lux| + |rdy - luy|로 정의합니다.
     *
     * 점 S에서 점 E로 드래그를 하면 바탕화면에서 두 격자점을 각각 왼쪽 위,
     * 오른쪽 아래로 하는 직사각형 내부에 있는 모든 파일이 선택됩니다.
     *
     *   예를 들어 wallpaper = [".#...", "..#..", "...#."]인 바탕화면을
     *   그림으로 나타내면 다음과 같습니다.
     *
     *
     * (0, 0)에서 (3, 5)로 드래그해도 모든 파일을 선택할 수 있지만
     * 이때 드래그 한 거리는 (3 - 0) + (5 - 0) = 8이고 이전의 방법보다
     * 거리가 늘어납니다.
     *
     * 머쓱이의 컴퓨터 바탕화면의 상태를 나타내는 문자열 배열 wallpaper가 매개변수로 주어질 때
     * 바탕화면의 파일들을 한 번에 삭제하기 위해 최소한의 이동거리를 갖는
     * 드래그의 시작점과 끝점을 담은 정수 배열을 return하는 solution 함수를 작성해 주세요.
     *
     * 드래그의
     *
     * 시작점이 (lux, luy),
     * 끝점이 (rdx, rdy)라면
     *
     * 정수 배열 [lux, luy, rdx, rdy]를 return하면 됩니다.
     *  </pre>
     */

    @Nested
    class TestCases {

        @Test
        public void case1 () {
            String[] wallpaper = {".#...", "..#..", "...#."};
            int[] result = {0, 1, 3, 4};

            Assertions.assertArrayEquals(result, solution(wallpaper));
        }
        @Test
        public void case2 () {
            String[] wallpaper = {"..........", ".....#....", "......##..", "...##.....", "....#....."};
            int[] result = {1,3,5,8};
            Assertions.assertArrayEquals(result, solution(wallpaper));
        }
        @Test
        public void case3 () {
            String[] wallpaper = {".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."};
            int[] result = {0, 0, 7, 9};
            Assertions.assertArrayEquals(result, solution(wallpaper));
        }
        @Test
        public void case4 () {
            String[] wallpaper = {"..", "#."};
            int[] result = {1, 0, 2, 1};
            Assertions.assertArrayEquals(result, solution(wallpaper));
        }
    }

    public int[] solution(String[] wallpaper) {
        List<List<String>> splitWallpaper = Arrays.stream(wallpaper)
                                                  .map(elem -> Arrays.stream(elem.split("")).collect(Collectors.toList()))
                                                  .collect(Collectors.toList());
        int startX = wallpaper[0].length();
        int startY = wallpaper.length;
        int endX = 0;
        int endY = 0;

        for ( int yIndex = 0; yIndex < splitWallpaper.size(); yIndex ++ ) {
            int xIndexStart = splitWallpaper.get(yIndex).indexOf("#");
            int xIndexEnd = splitWallpaper.get(yIndex).lastIndexOf("#");

            if (xIndexStart != -1 ) startX = Math.min(xIndexStart, startX);
            endX = Math.max(xIndexEnd, endX);


            if( splitWallpaper.get(yIndex).contains("#") ) {
                startY = Math.min(startY, yIndex);
                endY = Math.max(endY, yIndex);
            }
//            if(xIndex == startX) startY = yIndex;
//            if(xIndex == endX) endY = yIndex;
        }


//        System.out.println(">>> (" + startY +", "+ startX+") : ("+ (endY + 1)+", "+(endX + 1)+")");
        return new int[] {startY, startX,  endY + 1, endX + 1};
    }
}
