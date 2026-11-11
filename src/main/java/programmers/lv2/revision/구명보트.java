package programmers.lv2.revision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class 구명보트 {

    /**
     * <pre>
     * 무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다.
     * 구명보트는 작아서 한 번에 <b>최대 2명씩</b> 밖에 탈 수 없고, 무게 제한도 있습니다.
     * 예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg 이라면
     * 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg 이므로
     * 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.
     *
     * 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
     * 사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때,
     * 모든 사람을 구출하기 위해 필요한 <b>구명보트 개수의 최솟값</b>을 return 하도록 solution 함수를 작성해주세요.
     * </pre>
     */

    @Test
    void solution1() {
        int[] people = {70, 50, 80, 50};
        int limit = 100;

        int returnValue = 3;

        assertEquals(returnValue, calculate(people, limit));
    }

    @Test
    void solution2() {
        int[] people = {70, 80, 50};
        int limit = 100;

        int returnValue = 3;

        assertEquals(returnValue, calculate(people, limit));
    }


    int calculate(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int forwardIndex = people.length - 1;
        int backwardIndex = 0;


        for(; forwardIndex >= backwardIndex; forwardIndex-- ) {
            int forwardWeight = people[forwardIndex];
            int backwardWeight = people[backwardIndex];


            if(forwardWeight + backwardWeight <= limit) {
                backwardIndex ++;
            }

            answer++;
        }

        return answer;
    }

}
