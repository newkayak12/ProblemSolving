package programmers.hyunsoo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class HouseTasker {
    /**
     * <pre>
     * 어떤 회사에서 담당 업무에 따라 사원 몇 명을 재택근무로 전환하려 합니다. 업무는 재택 가능한 업무,
     * 출근해야 할 업무 두 가지 종류가 있습니다. 어떤 사원의 업무가 재택 가능한 업무로만 이루어져 있다면,
     * 그 사원은 재택근무 대상자입니다.
     * 회사에는 1번부터 num.employees 번까지 각각 다른 사원 번호를 가진 num_emp Loyees 명의 사원들이 있습니다. 회사의 사원들은 num. teans 개의 팀으로 나뉘어 있으며,
     * 사원마다 소속된 팀 번 호가 있습니다.
     * 팀마다 최소 한 명은 출근해야 합니다. 만약 어떤 담에서 팀원 모두가 재택근무 대상 자일 때, 사원 번호가 가장 빠른 사람이 출근하기로 했습니다. 이에 해당하지 않는 재택근무 대상자들 은 재택근무를 하게 됩니다. 당신은 재택근무를 하게 될 사원의 번호를 구하려 합니다.
     * 다음과 같은 업무와 사원들의 정보가 있을 때의 예시를 들어보겠습니다.
     *
     * 재택 가능한 업무: development, marketing, homtask
     * 출근해야 할 업무: recruitnent education officetask
     *
     *  사번 소속팀 번호  담당 업무
     *   1     1       development hometask
     *   2     1       recruttment marketing
     *   3     2       hometask
     *   4     2       development marketing hometask
     *   5     3       marketing
     *   6     3       officetask
     *   7     3       development
     * • 재택 가능한 업무만 하는 사원은 (1,3,4,5.7) 5명입니다.
     * • 13번 팀은 출근하는 탈원이 있지만 2번 팀은 모두 재택근무 대상자입니다. 그러므로, 2번 팀에 서 사원 번호가 가장 빠른 3번 사원은 출근해야 합니다.
     * • 재택근무를 하게 될 사원은 (1,4,5,7) 4명입니다.
     * 팀의 개수를 나타내는 정수 num_teams, 재택 가능한 업무들을 나타내는 문자열 배열 remote_tasks,
     * 출근해야 할 업무들을 나타내는 문자열 배열 office_tasks , 사원들의 정보 를
     * 1번 사원부터 순서대로 나타내는 문자열 배열 employees 가 매개변수로 주어집니다.
     * 재택근무를 하게 될 사원들의 번호를 정수 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해 주 세요. 최소 한 명 이상 재택근무를 하게 될 사원이 존재하는 경우만 주어집니다.
     *
     *
     *
     * </pre>
     */


    @Nested
    class TestCases {
        @Test
        public void case1() {
            int teams = 3;
            String[] remoteTasks = new String[]{"development", "marketing", "hometask"};
            String[] officeTasks = new String[]{"recruitment", "education", "officetask"};
            String[] employees = new String[]{"1 development hometask", "1 recruitment marketing", "2 hometask", "2 development marketing hometask", "3 marketing", "3 officetask", "3 development"};
            int[] result = new int[]{1, 4, 5, 7};

            Assertions.assertArrayEquals(result, solution(teams, remoteTasks, officeTasks, employees));
        }


        @Test
        public void case2() {
            int teams = 2;
            String[] remoteTasks = new String[]{"design"};
            String[] officeTasks = new String[]{"builiding", "supervise"};
            String[] employees = new String[]{"2 design", "1 supervise building design", "1 design", "2 design"};
            int[] result = new int[]{3, 4};

            Assertions.assertArrayEquals(result, solution(teams, remoteTasks, officeTasks, employees));
        }
    }




    int[] solution(int num_teams, String[] remote_tasks, String[] office_tasks, String[] employees) {
        Map<String, List<Integer>> homeListUp = new HashMap();


        Map<String, Integer> officeListUp = new HashMap<>();
        String remote = Arrays.stream(remote_tasks).collect(Collectors.joining(" "));
        String office = Arrays.stream(office_tasks).collect(Collectors.joining(" "));
        for (int i = 0; i < employees.length; i ++ ) {
            String[] emp = employees[i].split(" ");
            boolean isHome = true;
            for( int j = 1; j < emp.length;  j ++ ) {
                if(office.contains(emp[j])) isHome = false;
            }

            if( isHome ) {
                homeListUp.putIfAbsent(emp[0], new ArrayList<>());
                List<Integer> list = homeListUp.get(emp[0]);
                list.add(i + 1);
                homeListUp.put(emp[0], list);
            } else {
                officeListUp.put(emp[0], i + 1);
            }

        }

        homeListUp.keySet().forEach(key -> {
            if( Objects.isNull( officeListUp.getOrDefault(key, null) )) {
                List<Integer> list = homeListUp.get(key);
                list.remove(0);
                homeListUp.put(key, list);
            }
        });

        return  homeListUp.values().stream().flatMap(i -> i.stream()).mapToInt(i -> i).toArray();
    }


}




