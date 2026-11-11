package programmers.lv3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 정수_삼각형 {

    /**
     * TOP에서 row + 1, row + 1, col + 1로 값 업데이트 기존 값과 계산 한 값에 대해서 max 비교하고 최대가 되는 값으로 업데이트 원본 값 유지를 위해서 별도 array로 진행
     */
    public int solution(int[][] triangle) {
        int[][] copyOfTriangle = Arrays.stream(triangle)
            .map(t -> Arrays.stream(t).toArray())
            .toArray(int[][]::new);

        for (int row = 0; row < triangle.length - 1; row++) {
            for (int col = 0; col < triangle[row].length; col++) {
                int now = copyOfTriangle[row][col];

                int leftChild = triangle[row + 1][col];
                int rightChild = triangle[row + 1][col + 1];

                if (copyOfTriangle[row + 1][col] < now + leftChild) {
                    copyOfTriangle[row + 1][col] = now + leftChild;
                }
                if (copyOfTriangle[row + 1][col + 1] < now + rightChild) {
                    copyOfTriangle[row + 1][col + 1] = now + rightChild;
                }

            }
        }

        return Arrays.stream(copyOfTriangle[copyOfTriangle.length - 1]).max().getAsInt();
    }


    public int solution2(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {

            triangle[i][0] += triangle[i - 1][0]; // i열의 0
            triangle[i][i] += triangle[i - 1][i - 1]; // i열의 i

            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j], triangle[i - 1][j - 1]);
            }
        }

        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }

    public int solution3(int[][] triangle) {

        for (int i = triangle.length - 1; i > 0; i--) {
            for (int j = 1; j < triangle[i].length; j++) {
                triangle[i - 1][j - 1] += Math.max(triangle[i][j - 1], triangle[i][j]);
            }
        }
        return triangle[0][0];
    }


    @Test
    void test1() {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int result = 30;

        assertEquals(
            result,
            solution(Arrays.stream(triangle).map(it -> Arrays.stream(it).toArray()).toArray(int[][]::new))
        );
        assertEquals(
            result,
            solution2(Arrays.stream(triangle).map(it -> Arrays.stream(it).toArray()).toArray(int[][]::new))
        ); //top-down
        assertEquals(
            result,
            solution3(Arrays.stream(triangle).map(it -> Arrays.stream(it).toArray()).toArray(int[][]::new))
        ); //bottom-up
    }

}
