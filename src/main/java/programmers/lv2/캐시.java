package programmers.lv2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class 캐시 {

    @Test
    public void solution1() {

        int cacheSize = 3;
        String[] cities = new String[]{
            "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"
        };
        int expected = 50;

        assertEquals(expected, calculate(cacheSize, cities));
    }

    @Test
    public void solution2() {

        int cacheSize = 3;
        String[] cities = new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        int expected = 21;

        assertEquals(expected, calculate(cacheSize, cities));
    }

    @Test
    public void solution3() {

        int cacheSize = 2;
        String[] cities = new String[]{
            "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris",
            "Jeju", "NewYork", "Rome"
        };
        int expected = 60;

        assertEquals(expected, calculate(cacheSize, cities));
    }

    @Test
    public void solution4() {

        int cacheSize = 5;
        String[] cities = new String[]{
            "Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris",
            "Jeju", "NewYork", "Rome"
        };
        int expected = 52;

        assertEquals(expected, calculate(cacheSize, cities));
    }

    @Test
    public void solution5() {

        int cacheSize = 2;
        String[] cities = new String[]{"Jeju", "Pangyo", "NewYork", "newyork"};
        int expected = 16;

        assertEquals(expected, calculate(cacheSize, cities));
    }

    @Test
    public void solution6() {
        int cacheSize = 0;
        String[] cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int expected = 25;

        assertEquals(expected, calculate(cacheSize, cities));

    }

    private int calculate(int cacheSize, String[] cities) {
        //LRU -> Map ttl 저장
        //cacheMiss + 5
        //cacheHit + 1
        //실행 시간 반환

        int time = 0;
        List<String> cache = new ArrayList<>();

        for (int i = 0; i < cities.length; i++) {

            if (cache.contains(cities[i].toLowerCase())) {
                time += 1;
                cache.remove(cache.indexOf(cities[i].toLowerCase()));
            }
            else time += 5;

            if(cache.size() >= cacheSize && !cache.isEmpty()) cache.remove(0);
            if(cacheSize > cache.size()) cache.add(cities[i].toLowerCase());
        }

        return time;
    }
}


