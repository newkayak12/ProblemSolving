package programmers.lv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class HeapOfCards {
    // https://school.programmers.co.kr/learn/courses/30/lessons/159994
    /**
     *
     *
     * <pre>
     *     코니는 영어 단어가 적힌 카드 뭉치 두 개를 선물로 받았습니다.
     *     코니는 다음과 같은 규칙으로 카드에 적힌 단어들을 사용해
     *     원하는 순서의 단어 배열을 만들 수 있는지 알고 싶습니다.
     *
     *        -  원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용합니다.
     *        -  한 번 사용한 카드는 다시 사용할 수 없습니다.
     *        -  카드를 사용하지 않고 다음 카드로 넘어갈 수 없습니다.
     *        -  기존에 주어진 카드 뭉치의 단어 순서는 바꿀 수 없습니다.
     *
     *     예를 들어
     *     첫 번째 카드 뭉치에 순서대로 ["i", "drink", "water"],
     *     두 번째 카드 뭉치에 순서대로 ["want", "to"]가 적혀있을 때
     *     ["i", "want", "to", "drink", "water"] 순서의 단어
     *     배열을 만들려고 한다면
     *
     *     첫 번째 카드 뭉치에서 "i"를 사용한 후
     *     두 번째 카드 뭉치에서 "want"와 "to"를 사용하고
     *
     *     첫 번째 카드뭉치에 "drink"와 "water"를 차례대로 사용하면
     *     원하는 순서의 단어 배열을 만들 수 있습니다.
     *     문자열로 이루어진 배열 cards1, cards2와 원하는 단어 배열 goal이 매개변수로 주어질 때, cards1과 cards2에 적힌 단어들로 goal를 만들 있다면 "Yes"를, 만들 수 없다면 "No"를 return하는 solution 함수를 완성해주세요.
     *
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            String[] cards1 = {"i", "drink", "water"};
            String[] cards2 = {"want", "to"};
            String[] goal = {"i", "want", "to", "drink", "water"};

            String expect = "Yes";

            Assertions.assertEquals(expect, solution(cards1, cards2, goal));
        }

        @Test
        public void case2 () {
            String[] cards1 = {"i", "water", "drink"};
            String[] cards2 = {"want", "to"};
            String[] goal = {"i", "want", "to", "drink", "water"};

            String expect = "No";
            Assertions.assertEquals(expect, solution(cards1, cards2, goal));
        }
    }

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        Stack<String> cardPile1 = new Stack<>();
        Stack<String> cardPile2 = new Stack<>();
        Stack<String> goals = new Stack<>();

        for( String card : cards1) cardPile1.add(card);
        for( String card : cards2) cardPile2.add(card);
        for( String word : goal) goals.add(word);



        while( !goals.isEmpty() ) {
            String goalWord = goals.pop();

            if( goalWord.equals(cardPile1.peek()) ) {
                cardPile1.pop();
                continue;
            }
            if(goalWord.equals(cardPile2.peek())) {
                cardPile2.pop();
                continue;
            }


            answer = "No";
            break;
        }

        return answer;
    }

    class Success {
        public static String solution(String[] cards1, String[] cards2, String[] goal) {
            Stack<String> cardPile1 = toStack(cards1);
            Stack<String> cardPile2 = toStack(cards2);

            Boolean result = true;

            for ( String goalPiece : goal ) {
                String card1 = cardPile1.isEmpty() ? "" : cardPile1.peek();
                String card2 = cardPile2.isEmpty() ? "" : cardPile2.peek();

                if ( goalPiece.equals(card1) ) {
                    cardPile1.pop();
                    continue;
                }
                if ( goalPiece.equals(card2) ) {
                    cardPile2.pop();
                    continue;
                }
                result &= false;
            }

            return result ? "Yes" : "No";
        }

        private static Stack<String> toStack(String[] cardPile) {
            Stack< String > pile = new Stack<>();
            Arrays.sort(cardPile, (a, b) -> -1);

            for ( String card : cardPile ) {
                pile.push(card);
            }

            return pile;
        }
    }
}
