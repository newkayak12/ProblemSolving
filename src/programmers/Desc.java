package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class Desc {
	public static void main(String[] args) {
		System.out.println(solution(118372));
	}
	
	public static long solution(long n ){
		long answer = 0;
		String str = String.valueOf(n);
		String[] arr = str.split("");
		Arrays.sort(arr,new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return Integer.parseInt(o2)-Integer.parseInt(o1);
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for(String a : arr){
			sb.append(a);
		}
		answer = Long.parseLong(sb.toString());
		return answer;
	}
}
