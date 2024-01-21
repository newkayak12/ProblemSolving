package programmers.lv1.pcceLv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Forensics {

    /**
     * <pre>
     * AI 엔지니어인 현식이는 데이터를 분석하는 작업을 진행하고 있습니다.
     * 데이터는 [
     *          "코드 번호(code)",
     *          "제조일(date)",
     *          "최대 수량(maximum)",
     *          "현재 수량(remain)"
     *        ]
     * 으로 구성되어 있으며
     *
     * 현식이는 이 데이터들 중 조건을 만족하는 데이터만 뽑아서 정렬하려 합니다.
     *
     *
     * 예를 들어 다음과 같이 데이터가 주어진다면
     *
     * data = [
     *          [1, 20300104, 100, 80],
     *          [2, 20300804, 847, 37],
     *          [3, 20300401, 10, 8]
     *        ]
     *
     * <table>
     *     <tr>
     *         <th>code</th>
     *         <th>date</th>
     *         <th>maximum</th>
     *         <th>remain</th>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>20300104</td>
     *         <td>100</td>
     *         <td>80</td>
     *     </tr>
     *     <tr>
     *          <td>2</td>
     *          <td>20300804</td>
     *          <td>847</td>
     *          <td>37</td>
     *      </tr>
     *      <tr>
     *          <td>3</td>
     *          <td>20300401</td>
     *          <td>10</td>
     *          <td>8</td>
     *      </tr>
     * </table>
     *
     * 주어진 데이터 중 "제조일이 20300501 이전인 물건들을
     * 현재 수량이 적은 순서"로 정렬해야 한다면
     * 조건에 맞게 가공된 데이터는 다음과 같습니다.
     *
     * data = [
     *          [3,20300401,10,8],
     *          [1,20300104,100,80]
     *        ]
     *
     * 정렬한 데이터들이 담긴 이차원 정수 리스트 data와
     * 어떤 정보를 기준으로 데이터를 뽑아낼지를 의미하는 문자열 ext,
     * 뽑아낼 정보의 기준값을 나타내는 정수 val_ext,
     * 정보를 정렬할 기준이 되는 문자열 sort_by가 주어집니다.
     *
     * data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후,
     * sort_by에 해당하는 값을 기준으로 오름차순으로 정렬하여
     * return 하도록 solution 함수를 완성해 주세요.
     * 단, 조건을 만족하는 데이터는 항상 한 개 이상 존재합니다.
     * </pre>
     */

    @Nested
    class TestCase {
        @Test
        public void case1 () {
            int[][] data = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}};
            String ext = "date";
            int val_ext = 20300501;
            String sort_by = "remain";

            int[][] result = {{3,20300401,10,8}, {1,20300104,100,80}};

            Assertions.assertArrayEquals(result, solution(data, ext, val_ext, sort_by));
        }

        @Test
        public void case2 () {
            int[][] data = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}};
            String ext = "date";
            int val_ext = 20300810;
            String sort_by = "remain";

            int[][] result = {{3,20300401,10,8}, {2, 20300804, 847, 37}, {1,20300104,100,80}};

            Assertions.assertArrayEquals(result, solution(data, ext, val_ext, sort_by));
        }
    }

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        int[][] lstArr = Arrays.stream(data)
                .filter(ele -> {
                    int comp = 0;
                    switch (ext) {
                        case "code":
                            comp = ele[0];
                            break;
                        case "date":
                            comp = ele[1];
                            break;
                        case "maximum":
                            comp = ele[2];
                            break;
                        case "remain":
                            comp = ele[3];
                            break;
                    }
                    return comp < val_ext;
                })
                .sorted((e1, e2) -> {
                    int comp1 = 0;
                    int comp2 = 0;
                    switch (sort_by) {
                        case "code":
                            comp1 = e1[0];
                            comp2 = e2[0];
                            break;
                        case "date":
                            comp1 = e1[1];
                            comp2 = e2[1];
                            break;
                        case "maximum":
                            comp1 = e1[2];
                            comp2 = e2[2];
                            break;
                        case "remain":
                            comp1 = e1[3];
                            comp2 = e2[3];
                            break;
                    }

                    return comp1 - comp2;
                })
                .toArray(int[][]::new);



        return lstArr;
    }

    private void print (int[][] data) {
        System.out.println("[");
        for( int [] dat : data) {
            System.out.println("\t[");
                for ( int pi : dat) {
                    System.out.println("\t\t" + pi);
                }
            System.out.println("\t],");
        }
        System.out.println("]");
    }
}
