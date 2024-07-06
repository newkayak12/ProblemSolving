package programmers.example;

import org.junit.jupiter.api.Test;

import java.util.*;
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




    @Test
    public void test1() {
        String num = "1234";

        System.out.println(permutation(num));
    }

    public static class Tuple <T1, T2>{
        T1 t1;
        T2 t2;

        public Tuple(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public static  <T1, T2> Tuple of(T1 t1, T2 t2) {
            return new Tuple<>(t1, t2);
        }
    }

    private List<String> permutation( String n ) {
        String[] strings = n.split("");
        Set<String> set = new HashSet<>();

        for( int i = 0; i < strings.length; i ++ ) {
            Stack<Tuple<String, boolean[]>> charSet = new Stack<>();
            boolean[] visit = new boolean[strings.length];
            String charac = strings[i];
            set.add(charac);
            visit[i] = true;

            charSet.add(Tuple.of(charac, Arrays.copyOf(visit, visit.length)));

            while( !charSet.isEmpty() ){
                Tuple<String, boolean[]> pop = charSet.pop();



                for(int j = 0; j < strings.length; j ++ ) {
                    if(!pop.t2[j]) {

                        boolean[] cloneMap = Arrays.copyOf(pop.t2, pop.t2.length);
                        cloneMap[j] = true;
                        charSet.add(Tuple.of(pop.t1+strings[j], cloneMap));
                        set.add(pop.t1+strings[j]);
                    }
                }
            }
        }

        List<String> result = set.stream().collect(Collectors.toList());
        Collections.sort(result);
        return result;
    }
}
