package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class DivideNumber {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {5,9,7,10}, 5)));
	}
	public static int[] solution(int[] arr, int divisor) {
		int[] answer = {};
		ArrayList<Integer> num = new ArrayList<>(0);
		
		for(int n : arr){
			if(n%divisor==0){
				num.add(n);
			}
		}

		if(num.size()==0){
			answer = new int[] {-1};
		} else {
			answer = num.stream().mapToInt(Integer::intValue).toArray();
			Arrays.sort(answer);
		}
		
		return answer;
	    }
}
