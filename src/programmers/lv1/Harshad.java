package programmers.lv1;

public class Harshad{
    public static void main(String[] args) {
        System.out.println(solution(12));
    }

    public static boolean solution(int x){
        boolean answer = false;
        if(x<10){
            answer = true;
        } else {
            String str = String.valueOf(x);
            char[] temp = str.toCharArray();
            int number = 0;
            for(char piece : temp){
                number += Integer.parseInt(String.valueOf(piece));
            }
            System.out.println(number);

            if(x%number==0){
                answer = true;
            }
        }
        
        return answer;
    }


}