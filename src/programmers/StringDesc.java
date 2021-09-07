package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class StringDesc {
	public static void main(String[] args) {
		System.out.println(solution("Zbcdefg"));
	}

	public static String solution(String s) {
		String[] arr = s.split("");
		Arrays.sort(arr, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				
				return o2.charAt(0)-o1.charAt(0);
			}
			
		});
		StringBuilder sb = new StringBuilder();
		for(String p : arr){
			sb.append(p);
		}
		
		return sb.toString();
	    }
}
