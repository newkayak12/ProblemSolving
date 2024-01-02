package programmers.lv1;

import java.util.Arrays;

public class CaesarCipher {
	public static void main(String[] args) {
		System.out.println(solution("AB", 1));
		System.out.println(solution("z", 1));
		System.out.println(solution("a B z", 4));
		System.out.println(solution("AaZz", 25));
		System.out.println(solution("ZzYy", 25));
	}	
	public static String solution(String s, int n){
		char[] arr = s.toCharArray();
		for(int i =0; i<arr.length; i++){
			for(int j =1; j<=n; j++){
				if(arr[i]=='z' || arr[i]=='Z'){
					if(arr[i]=='z'){
						arr[i] = 'a';
					} else if(arr[i]=='Z') {
						arr[i] = 'A';
					}
				} else if(arr[i]==' '){

				} else {
					arr[i] = (char)(arr[i]+1);
				}
			}
		}
		return String.valueOf(arr);
	}
}
