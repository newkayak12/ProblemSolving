package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class ToArray {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(12345)));
		System.out.println(solution(12345) instanceof int[]);
	}
	public static int[] solution(long n) {
		int[] answer = {};
		String str = String.valueOf(n);
		String[] strArr = str.split("");
		Arrays.sort(strArr,new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return -1;
			}
			
		});

		answer = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();



		return answer;
	    }
}
