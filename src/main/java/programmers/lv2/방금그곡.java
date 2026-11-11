package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class 방금그곡 {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/17683?language=java
     */

    @Test
    void solution1() {
        String m = "ABCDEFG";
        String[] musicinfos = new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String expected = "HELLO";

        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution2() {
        String m = "CC#BCC#BCC#BCC#B";
        String[] musicinfos = new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String expected = "FOO";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution3() {
        String m = "ABC";
        String[] musicinfos = new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String expected = "WORLD";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution4() {
        String m = "CC#BCC#B";
        String[] musicinfos = new String[]{"03:00,03:08,FOO,CC#B"};
        String expected = "FOO";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution5() {
        String m = "ABC";
        String[] musicinfos = new String[]{"12:00,12:04,BAR,ABCDE"};
        String expected = "BAR";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution6() {
        String m = "ABC";
        String[] musicinfos = new String[]{"12:00,12:07,BAR,ABC"};
        String expected = "BAR";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution7() {
        String m = "A";
        String[] musicinfos = new String[]{"00:00,00:01,ONE,A#"};
        String expected = "(None)";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution8() {
        String m = "CC#B";
        String[] musicinfos = new String[]{"00:00,00:03,FOO,CC#B"};
        String expected = "FOO";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution9() {
        String m = "C#C";
        String[] musicinfos = new String[]{"00:00,00:02,FOO,CC#"};
        String expected = "(None)";
        assertEquals(expected, calculate(m, musicinfos));
    }

    @Test
    void solution10() {
        String m = "ABC";
        String[] musicinfos = new String[]{"00:00,00:02,FOO,ABCD"};
        String expected = "(None)";
        assertEquals(expected, calculate(m, musicinfos));
    }

    /**
     * 시뮬레이션
     * <pre>
     * 1. musicinfos에 있는 길이에 맞춰서 악보를 늘린다.
     * 2. 시간, 제목, 늘린 악보로 반환한다.
     * 3. m, musicinfos의 값을 코드 집합인 dictionary의 index로 치환한다.
     * 4. 노래를 찾는다.
     * 4-1. 늘린 악보 중에서 일치하는 구간이 있는 음악을 필터링한다.
     * 4-2. 재생 시간이 긴 순으로 정렬한다.
     * 5. 노래 제목을 뱉는다.
     * </pre>
     */
    private Map<String, String> getDictionary(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("C#", "ㄴ");
        map.put("D#", "ㄹ");
        map.put("F#", "ㅅ");
        map.put("G#", "ㅈ");
        map.put("A#", "ㅋ");
//        map.put("C", "ㄱ");
//        map.put("D", "ㄷ");
//        map.put("E", "ㅁ");
//        map.put("F", "ㅂ");
//        map.put("G", "ㅇ");
//        map.put("A", "ㅊ");
//        map.put("B", "ㅌ");
        return map;
    }

    private String convert(String target) {
        return target.replace("C#", "c")
            .replace("D#", "d")
            .replace("F#", "f")
            .replace("G#", "g")
            .replace("A#", "a");
    }

    private String[] manipulateMusicInfo(String musicInfo){
        String[] info = musicInfo.split(",");
        String[] start = info[0].split(":");
        String[] end = info[1].split(":");

        Integer startHour = Integer.parseInt(start[0]) * 60;
        Integer startMinute = Integer.parseInt(start[1]);

        Integer endHour = Integer.parseInt(end[0]) * 60;
        Integer endMinute = Integer.parseInt(end[1]);

        int duration = (endHour + endMinute) - (startHour + startMinute);
        String title = info[2];
        String convertedOriginal = convert(info[3]);


        StringBuilder scale = new StringBuilder();
        int idx = 0;
        for(int i = 0; i < duration; i++){
            scale.append(convertedOriginal.charAt(idx));
            idx = (idx + 1) % convertedOriginal.length();
        }

        return new String[]{String.valueOf(duration), title, scale.toString()};
    }

    private String calculate(String m, String[] musicinfos) {
        final List<String[]> manipulatedMusicInfos = Arrays.stream(musicinfos)
            .map(it -> manipulateMusicInfo(it))
            .collect(Collectors.toList());

        final String reference = convert(m);


        List<String[]>  filtered = manipulatedMusicInfos.stream()
        .filter(it -> it[2].contains(reference))
        .sorted((o1, o2) -> Integer.parseInt(o2[0]) - Integer.parseInt(o1[0]))
        .collect(Collectors.toList());



        if(filtered.isEmpty()){
            return "(None)";
        }
        return filtered.getFirst()[1];
    }


    @Test
    void solution1Dot(){
        String m = "C#BC";
        String[] musicinfos = new String[]{"00:00,00:05,Loop,C#BC#A"};
        String expected = "(None)";
        assertEquals(expected, calculate2(m, musicinfos));
    }

    @Test
    void solution2Dot(){
        String m = "ABC#";
        String[] musicinfos = new String[]{"00:00,00:03,Song,ABC#ABC#"};
        String expected = "Song";
        assertEquals(expected, calculate2(m, musicinfos));
    }



    private String calculate2(String m, String[] musicinfos) {
        // 샤프 음을 단일 문자로 변환
        String reference = convertSharp(m);

        String answer = "(None)";
        int maxDuration = 0;

        for (String musicinfo : musicinfos) {
            String[] info = musicinfo.split(",");
            int start = toMinutes(info[0]);
            int end = toMinutes(info[1]);
            int duration = end - start;
            if(duration <= 0) continue;
            String title = info[2];
            String sheet = convertSharp(info[3]);

            // duration만큼 재생된 악보 생성
            StringBuilder played = new StringBuilder();
            for (int i = 0; i < duration; i++) {
                played.append(sheet.charAt(i % sheet.length()));
            }

            if (played.toString().contains(reference)) {

                if (duration > maxDuration) {
                    maxDuration = duration;
                    answer = title;
                }
            }
        }

        return answer;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private String convertSharp(String s) {
        return s.replace("C#", "c")
            .replace("D#", "d")
            .replace("F#", "f")
            .replace("G#", "g")
            .replace("A#", "a");
    }
}
