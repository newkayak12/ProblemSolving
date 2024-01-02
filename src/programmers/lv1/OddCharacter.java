package programmers.lv1;

import java.util.Arrays;

public class OddCharacter {
	public static void main(String[] args) {
		System.out.println((solution("try hello world")));
	}
	public static String solution(String s) {
		String answer = "";
		String str2 = s.replaceAll(" ", "#");
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for(int i = 0; i<str2.length(); i++){
			while(true){
				System.out.println(j);
				if(String.valueOf(str2.charAt(i)).equals("#")){
					sb.append(String.valueOf(str2.charAt(i)).replace("#", " "));
					j=0;
					break;
				} else if(!String.valueOf(str2.charAt(i)).equals("#")) {
					if(j%2==0){
						sb.append(String.valueOf(str2.charAt(i)).toUpperCase());
						j+=1;
						break;
					} else if(j%2!=0){
						sb.append(String.valueOf(str2.charAt(i)).toLowerCase());
						j+=1;
						break;
					}
				}

				
			}

		}

		return sb.toString();
	    }
}
