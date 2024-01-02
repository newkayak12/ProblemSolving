package programmers.lv1;

import java.util.Arrays;

public class SumAllAbsentNumber {
	public static void main(String[] args) {
		System.out.println(solution(new int[] {1,2,3,4,6,7,8,0}));
		System.out.println("\n");
		System.out.println(solution(new int[] {5,8,4,0,6,7,9}));
	}
	
	public static int solution(int[] numbers) {
		int answer = 0;
		Arrays.sort(numbers);
		for(int i = 0; i<numbers.length; i++){
			answer+=numbers[i];
		}
		return 45-answer;
	    }
}
