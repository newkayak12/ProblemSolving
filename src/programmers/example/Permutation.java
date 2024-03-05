package programmers.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Permutation {
    public static void main(String[] args) {
        System.out.println(permutations(Arrays.asList(1, 2, 3, 4), 3));
    }
    static List<List<Integer>> permutations( List<Integer> list, int count ) {
        List<List<Integer>> result = new ArrayList<>();
        if( count == 1 ) {
            return  list.stream()
                    .map(elem -> new ArrayList<>(List.of(elem)))
                    .collect(Collectors.toList());
        }

        for(AtomicInteger i = new AtomicInteger(0); i.get() < list.size(); i.getAndIncrement()) {
            List<Integer> subList = new ArrayList<>();
            subList.addAll(list.subList(0, i.get()));
            subList.addAll(list.subList(i.get() + 1, list.size()));
            List<List<Integer>> permutations = permutations(subList, count - 1);

            permutations.stream().forEach(l -> {
                l.add(list.get(i.get()));
                result.add(l);
            });
        }

        return result;
    }
}
