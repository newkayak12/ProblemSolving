package programmers.lv1.kakaoLv1.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Report {
    // https://school.programmers.co.kr/learn/courses/30/lessons/92334
    /**
     * 신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다. 무지가 개발하려는 시스템은 다음과 같습니다.
     * 각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다
     *
     * 신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
     * 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
     *
     *
     * k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
     * 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
     *
     * 다음은 전체 유저 목록이 ["muzi", "frodo", "apeach", "neo"]이고, k = 2(즉, 2번 이상 신고당하면 이용 정지)인 경우의 예시입니다.
     */


    @Nested
    class ReportCases {
        @Test
        public void case1 () {
            String[] id_list = new String[] {"muzi", "frodo", "apeach", "neo"};
            String[] report = new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
            int count = 2;

            int[] expect = new int[] {2,1,1,0};
            Assertions.assertArrayEquals(expect, solution(id_list, report, count));
        }

        @Test
        public void case2 () {
            String[] id_list = new String[] {"con", "ryan"};
            String[] report = new String[]{"ryan con", "ryan con", "ryan con", "ryan con"};
            int count = 3;

            int[] expect = new int[] {0,0};
            Assertions.assertArrayEquals(expect, solution(id_list, report, count));
        }
    }

    public int[] solution( String [] id_list, String[] report, int k ) {
        String[] distinctReport = Arrays.stream(report).distinct().toArray(String[]::new);
        Map<String, Person> map = new LinkedHashMap<>();


        for ( String id : id_list ){
            map.put(id, new Person(id));
        }
        for ( String target : distinctReport ) {
            StringTokenizer tokenizer = new StringTokenizer(target, " ");
            String reporter = tokenizer.nextToken();
            String reportee = tokenizer.nextToken();

            map.get(reporter).reported.add(reportee);
            map.get(reportee).whoReported.add(reporter);
            map.get(reportee).updateReportedCount();
        }


        Set<String> personSet = map.values()
                .stream()
                .filter(person -> person.whoReportedCount >= k)
                .map(person -> person.id)
                .collect(Collectors.toSet());



        int[] counts = map.values().stream()
                .map(person -> {
                    Set<String> intersection = new HashSet<>(person.reported);
                    intersection.retainAll(personSet);

                    return intersection.size();
                })
                .mapToInt( i -> i)
                .toArray();

        return counts;
    }

    static class Person {
        String id;
        int whoReportedCount = 0;
        Set<String> reported =  new HashSet<>();
        Set<String> whoReported = new HashSet<>();

        public Person(String id) {
            this.id = id;
        }

        public void updateReportedCount () {
            whoReportedCount = whoReported.size();
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id +
//                     '\'' +
//                    ", reportedCount=" + whoReportedCount +
//                    ", reported=" + reported +
//                    ", whoReported=" + whoReported +
                    '}';
        }
    }


}
