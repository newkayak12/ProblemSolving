package programmers;

import java.util.Arrays;

public class Gcd2 {
    public static void main(String[] args) {
        
        System.out.println(Arrays.toString(solutions(2, 5)));
        System.out.println(5%2);
    }



    public static int[] solutions (int x, int y){
        int[] answer = new int[2];
        int big = 0;
        int small = 0;
        if(x>y){
            big = x;
            small = y;
        } else if(x<y){
            big = y;
            small = x;
        } else {
            answer[0] = x;
            answer[1] = y;
            return answer;
        }

        if(big%small == 0 ){
            answer[0] = small;
        } else {
            answer[0] = 1;
        }
        
        System.out.println(big);
        System.out.println(small);
        System.out.println(answer[0]);
        System.out.println(answer[1]);

        if(answer[0]!=1){
            answer[1]= big;

        } else {

            answer[1] = small* big;

            System.out.println("ceh"+answer[1]);
        }
           


        return answer;
    }


}