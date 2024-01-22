package programmers.lv1.pcceLv1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class Dressing {
    //https://school.programmers.co.kr/learn/courses/30/lessons/250137
    /**
     * <pre>
     * 어떤 게임에는 붕대 감기라는 기술이 있습니다.
     *
     * 붕대 감기는 t초 동안 붕대를 감으면서 1초마다 x만큼의 체력을 회복합니다.
     * t초 연속으로 붕대를 감는 데 성공한다면 y만큼의 체력을 추가로 회복합니다.
     *
     * 게임 캐릭터에는 최대 체력이 존재해 현재 체력이 최대 체력보다 커지는 것은 불가능합니다.
     *
     * 기술을 쓰는 도중 몬스터에게 공격을 당하면 기술이 취소되고,
     * 공격을 당하는 순간에는 체력을 회복할 수 없습니다.
     *
     * 몬스터에게 공격당해 기술이 취소당하거나
     * 기술이 끝나면 그 즉시 붕대 감기를 다시 사용하며,
     * 연속 성공 시간이 0으로 초기화됩니다.
     * 몬스터의 공격을 받으면 정해진 피해량만큼 현재 체력이 줄어듭니다.
     *
     * 이때, 현재 체력이 0 이하가 되면 캐릭터가 죽으며 더 이상 체력을 회복할 수 없습니다.
     *
     * 당신은 붕대감기 기술의 정보,
     * 캐릭터가 가진 최대 체력과 몬스터의 공격 패턴이 주어질 때
     * 캐릭터가 끝까지 생존할 수 있는지 궁금합니다.
     *
     * 붕대 감기 기술의 시전 시간, 1초당 회복량,
     * 추가 회복량을 담은 1차원 정수 배열 bandage와
     * 최대 체력을 의미하는 정수 health,
     * 몬스터의 공격 시간과 피해량을 담은 2차원 정수 배열 attacks가
     * 매개변수로 주어집니다.
     *
     * 모든 공격이 끝난 직후 남은 체력을 return 하도록 solution 함수를 완성해 주세요.
     * 만약 몬스터의 공격을 받고 캐릭터의 체력이 0 이하가 되어 죽는다면 -1을 return 해주세요.
     * </pre>
     */

    @Nested
    class TestCases {
        @Test
        public void case1 () {
            int[] bandage = {5,1,5};
            int health = 30;
            int[][] attacks = {{2,10}, {9,15}, {10,5}, {11,5}};
            int result = 5;

            Assertions.assertEquals(result, solution(bandage, health, attacks));
        }

        @Test
        public void case2 () {
            int[] bandage = {3, 2, 7};
            int health = 20;
            int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};
            int result = -1;

            Assertions.assertEquals(result, solution(bandage, health, attacks));
        }

        @Test
        public void case3 () {
            int[] bandage = {4, 2, 7};
            int health = 20;
            int[][] attacks = {{1,15}, {5,16}, {8,6}};
            int result = -1;

            Assertions.assertEquals(result, solution(bandage, health, attacks));
        }

        @Test
        public void case4 () {
            int[] bandage = {1, 1, 1};
            int health = 5;
            int[][] attacks = {{1,2}, {3,2}};
            int result = 3;

            Assertions.assertEquals(result, solution(bandage, health, attacks));
        }

        @Test
        public void case5 () {
            int[] bandage = {1, 1, 1};
            int health = 5;
            int[][] attacks = {{1,2}, {3,2}, {5,1}};
            int result = 4;

            Assertions.assertEquals(result, solution(bandage, health, attacks));
        }

        @Test
        public void case6 () {
            int[] bandage = {10,10,100};
            int health = 10;
            int[][] attacks = {{1, 15}, {3,1}};
            int result = -1;

            Assertions.assertEquals(result, solution(bandage, health, attacks));
        }
    }

    public int solution ( int[] bandage, int health, int[][] attacks ){
        int maxHealth = health;
        int turn = attacks[attacks.length - 1][0];
        int bandageIdx = 0;
        int attackIdx = 0;
        int continuous = 0;
        int healed = 0;
        int heal = bandage[bandageIdx];


        if ( health <= 0 ) return -1;
        else return health;
    }


    public int solution2 ( int[] bandage, int health, int[][] attacks ) {
        int turn = attacks[attacks.length - 1][0];

        Stack<int[]> attacking = new Stack<>();
        Stack<Integer> dressing = new Stack<>();

        for ( int i = bandage.length - 1 ; i >= 0; i-- ) dressing.push(bandage[i]);
        for ( int i = attacks.length - 1 ; i >= 0; i--) attacking.push(attacks[i]);

        int healthNow = health;
        int heal = dressing.pop();
        int origin = heal;
        int continous = 0;




        cycle: for ( int time = 0; time <= turn; time ++ ) {
            int damage = 0;
            boolean isAttack = false;

            if ( healthNow <= 0) break cycle;
            if ( time != 0 ) {
                if (attacking.peek()[0] == time) {
                    isAttack = true;
                    damage = attacking.pop()[1];
                }

                if ( isAttack ) {
                    healthNow -= damage;
                    continous = 0;
                } else {
                    if ( heal <= 0 && !dressing.isEmpty() ) {
                        heal = dressing.pop();
                        origin = heal;
                    }
                    if ( healthNow < health ) {
                        healthNow += 1;
                        heal -= 1;
                    }

                    continous += 1;

                    if (continous == origin) {
                        continous = 0;
                        healthNow += origin;
                    }
                    if ( healthNow > health) healthNow = health;
                }
            }

            System.out.println(time +" | "+ healthNow +" | "+ continous +" | "+ isAttack);
        }


        if ( healthNow <= 0) return -1;
        else return healthNow;
    }


}
