package programmers.lv1.kakaoLv1.privacy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Privacy {
    //https://school.programmers.co.kr/learn/courses/30/lessons/150370
    /**
     * <pre>
     *고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다. 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다. 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다. 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 합니다.
     * 예를 들어, A라는 약관의 유효기간이 12 달이고, 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면 해당 개인정보는 2022년 1월 4일까지 보관 가능하며 2022년 1월 5일부터 파기해야 할 개인정보입니다.
     * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
     * 모든 달은 28일까지 있다고 가정합니다.
     * 다음은 오늘 날짜가 2022.05.19일 때의 예시입니다.
     *
     * 약관 종류	유효기간
     *   A  	6 달
     *   B  	12 달
     *   C  	3 달
     *
     * 번호	개인정보 수집 일자	약관 종류
     *   1  	2021.05.02	  A
     *   2  	2021.07.01	  B
     *   3  	2022.02.19	  C
     *   4  	2022.02.20	  C
     * 첫 번째 개인정보는 A약관에 의해 2021년 11월 1일까지 보관 가능하며, 유효기간이 지났으므로 파기해야 할 개인정보입니다.
     * 두 번째 개인정보는 B약관에 의해 2022년 6월 28일까지 보관 가능하며, 유효기간이 지나지 않았으므로 아직 보관 가능합니다.
     * 세 번째 개인정보는 C약관에 의해 2022년 5월 18일까지 보관 가능하며, 유효기간이 지났으므로 파기해야 할 개인정보입니다.
     * 네 번째 개인정보는 C약관에 의해 2022년 5월 19일까지 보관 가능하며, 유효기간이 지나지 않았으므로 아직 보관 가능합니다.
     * 따라서 파기해야 할 개인정보 번호는 [1, 3]입니다.
     * 오늘 날짜를 의미하는 문자열 today, 약관의 유효기간을 담은 1차원 문자열 배열 terms와 수집된 개인정보의 정보를 담은 1차원 문자열 배열 privacies가 매개변수로 주어집니다. 이때 파기해야 할 개인정보의 번호를 오름차순으로 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해 주세요.
     * </pre>
     *
     *
     */

    static class StopWatch{
        private static Long startTime = 0L;
        private static Long endTime = 0L;

        public static void start() {
            startTime = 0L;
            endTime = 0L;
            startTime = System.currentTimeMillis();
        }

        public static void end () {
            endTime = System.currentTimeMillis();

            System.out.println("TIME :: " + (long) (endTime - startTime / 1000));
        }


    }
    @Nested
    class TestCase {
        @Test
        public void case1 () {
            String today = "2022.05.19";
            String[] terms = {"A 6", "B 12", "C 3"};
            String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
            int[] expect = {1, 3};

            Assertions.assertArrayEquals(expect, solution(today, terms, privacies));
        }

        @Test
        public void case2 () {
            String today = "2020.01.01";
            String[] terms = {"Z 3", "D 5"};
            String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
            int[] expect = {1, 4, 5};

            Assertions.assertArrayEquals(expect, solution(today, terms, privacies));
        }
    }
    public int[] solution (String today, String[] terms, String[] privacies) {
        String[] todaySplit = today.split("\\.");

        Integer todaySum = (Integer.parseInt(todaySplit[0])*12 *28)+
                            (Integer.parseInt(todaySplit[1])*28)+
                            Integer.parseInt(todaySplit[2]);

        List<Integer> indexes = new ArrayList<>();

        Map<String, Integer> termMap = new HashMap<>();
        for( String term : terms ) {
            String[] split = term.split(" ");
            termMap.put(split[0], Integer.parseInt(split[1]));
        }

        for( int i = 0; i < privacies.length; i ++  ) {
            String privacy = privacies[i];
            String[] privacySplit = privacy.split(" ");

            String[] privacyCalendar = privacySplit[0].split("\\.");

            int year = Integer.parseInt(privacyCalendar[0]) * 28 * 12;
            int mon  = Integer.parseInt(privacyCalendar[1]) * 28;
            int day  = Integer.parseInt(privacyCalendar[2]);
            int next = termMap.get(privacySplit[1]) * 28;

            int sum = year+mon+day+next;

            if( todaySum >= sum ) {
                indexes.add(i + 1);
            }
        }



        return indexes.stream().mapToInt(i -> i).toArray();
    }

    class Success {

        public int[] solution1 (String today, String[] terms, String[] privacies) {
            List<Integer> answer = new ArrayList<>();
            CustomDate now = new CustomDate(today);
            Map<String, Integer> termsMap = Arrays.stream(terms)
                    .map((elem) -> elem.split(" "))
                    .collect(Collectors.toMap( elem -> elem[0], elem -> Integer.parseInt(elem[1])));

            for ( int i = 0; i < privacies.length; i ++ ) {
                String[] split = privacies[i].split(" ");
                String k = split[0];
                Integer v = termsMap.get(split[1]);

                CustomDate date = new CustomDate(k);
                date.addMonth(v);

                if(now.isAfter(date)) {
                    answer.add(i + 1);
                }
            }


            return answer.stream().mapToInt(i -> i).toArray();
        }
        class CustomDate {
            private int year;
            private int month;
            private int date;


            public CustomDate(int year, int month, int date) {
                this.year = year;
                this.month = month;
                this.date = date;
            }
            public CustomDate(String target) {
                StringTokenizer tokenizer = new StringTokenizer(target,".");
                year = Integer.parseInt(tokenizer.nextToken());
                month = Integer.parseInt(tokenizer.nextToken());
                date = Integer.parseInt(tokenizer.nextToken());
            }
            public void setYear (int year) {
                this.year = year;
            }
            public void setMonth (int month){
                this.month = month;
                this.calculate();
            }
            public void setDate(int date) {
                this.date = date;
                this.calculate();
            }

            public void addYear (int year) {
                this.year += year;
            }
            public void addMonth (int month){
                this.month += month;
                this.date -= 1;
                this.calculate();
            }
            public void addDate(int date) {
                this.date +=  date;
                this.calculate();
            }


            private void calculate () {
                if( this.date - 28 > 0 ) {
                    int diff = this.date / 28;
                    this.addMonth(diff);
                    this.date -= diff * 28;
                }
                if( this.month - 12 > 0 ) {
                    int diff = this.month / 12;
                    this.addYear(diff);
                    this.month -= diff * 12;
                }
                if( this.date == 0 ) {
                    this.month -= 1;
                    this.date = 28;
                }
                if( this.month == 0 ) {
                    this.year -= 1;
                    this.month = 12;
                }
            }
            public int getYear() {
                return year;
            }
            public int getMonth() {
                return month;
            }
            public int getDate() {
                return date;
            }

            public Boolean isAfter (CustomDate reference) {
                int thisYear = this.year;
                int thisMonth = this.month;
                int thisDate = this.date;

                int refYear = reference.getYear();
                int refMonth = reference.getMonth();
                int refDate = reference.getDate();

                if ( thisYear - refYear  < 0 ) return Boolean.FALSE;
                if ( thisYear - refYear > 0 ) return Boolean.TRUE;

                if ( thisMonth - refMonth < 0 ) return Boolean.FALSE;
                if ( thisMonth - refMonth > 0 ) return Boolean.TRUE;

                if ( thisDate - refDate < 0 ) return Boolean.FALSE;
                if ( thisDate - refDate > 0 ) return Boolean.TRUE;

                return Boolean.FALSE;
            }


            @Override
            public String toString() {
                return year + "-" + month + "-" + date;
            }
        }

        public int[] solution2 (String today, String[] terms, String[] privacies) {
            List<Integer> answer = new ArrayList<>();
            int now = getDate(today);
            Map<String, Integer> termsMap = Arrays.stream(terms)
                    .map((elem) -> elem.split(" "))
                    .collect(Collectors.toMap( elem -> elem[0], elem -> Integer.parseInt(elem[1])));

            StringTokenizer tokenizer;
            for ( int i = 0; i < privacies.length; i ++ ) {
                tokenizer = new StringTokenizer(privacies[i], " ");
                int diffDate = getDate(tokenizer.nextToken());
                int addDay = termsMap.get(tokenizer.nextToken()) * 28;

                if ( now < (diffDate + addDay) ) answer.add(i + 1);
            }



            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
        private int getDate( String date ) {
        StringTokenizer tokenizer = new StringTokenizer(date, ".");
        int year = Integer.parseInt(tokenizer.nextToken());
        int month = Integer.parseInt(tokenizer.nextToken());
        int day = Integer.parseInt(tokenizer.nextToken());
        return year * 12 * 28 + month * 28 + day;
    }

    }

}
