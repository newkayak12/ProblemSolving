package programmers;

import java.util.Arrays;

public class Budget {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,3,2,5,4}, 9));	
	}
	public static int solution(int[] d, int budget) {
		int answer = 0;
		int bud = budget;
		int[] p = d;
		Arrays.sort(p);
		for(int price : p){
			if(bud>0 && bud>=price){
				answer++;
				bud-=price;

			}
		}
		return answer;
	    }
}
