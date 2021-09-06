package programmers;

import java.util.Arrays;

public class Gcd {
	public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(solution2(8, 28)));
		
	}

	public static int[] solution(int x, int y) {
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
	       
	       if(answer[0]!=1){
		   answer[1]= big;
       
	       } else {
		   answer[1] = small* big;
	       }
		  
       
	       return answer;
	   }

	   public static int[] solution2(int x, int y) {
		int[] answer = new int[2];
		if(x<y){
			int temp = x;
			x = y;
			y = temp;
		} 
		if(x%y==0){
			answer[0] = y;
			answer[1] = x;
			return answer;
		} else {

			
			
			for(int i =1; i<=x; i++){
				if(x%i==0&&y%i==0){
					System.out.println(i);
					answer[0] = i;
					answer[1] = x*y/i;
				}
			}
			
		}
	       return answer;
	   }
}
