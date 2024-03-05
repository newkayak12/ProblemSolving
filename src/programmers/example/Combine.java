package programmers.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Combine {
    public static void main(String[] args) {
        System.out.println(combine(Arrays.asList(1,4,6,7,9), 3));
    }

    static List<String> combine( List<Integer> list, int count ) {
        List<String> result = new ArrayList<>();
        if( count == 1 ) return list.stream().map(String::valueOf).collect(Collectors.toList());
        for (AtomicInteger i = new AtomicInteger(0); i.get() < list.size(); i.getAndIncrement() ) {
            List<String> combinations = combine(
                    new ArrayList<>(list.subList(i.get() + 1, list.size())),
                    count - 1
            ).stream()
             .map(v -> list.get(i.get())+v)
             .collect(Collectors.toList());

            result.addAll(combinations);
        }

        return result;
    }

}
