package programmers.lv1.kakaoLv1.privacy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Privacy {
    //https://school.programmers.co.kr/learn/courses/30/lessons/150370
    /**
     * A라는 약관의 유효기간이 12 달이고, 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면 해당 개인정보는 2022년 1월 4일까지 보관 가능하며 2022년 1월 5일부터 파기해야 할 개인정보입니다.
     * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
     *
     * 모든 달은 28일까지 있다고 가정합니다. *** localDate 쓰기에 애매한 부분?? 그냥 앞뒤 구분만 하면 되려나
     *
     * 약관 종류	유효기간
     *    A	     6 달
     *    B	     12 달
     *    C	     3 달
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
