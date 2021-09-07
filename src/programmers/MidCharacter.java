package programmers;

public class MidCharacter {
	public static void main(String[] args) {
		System.out.println(solution("abcde"));
		System.out.println(solution("qwer"));
	}
	public static String solution(String s) {
		String answer = "";
		int end = s.length();
		if(end%2==0){
			end = end/2;
			String a = String.valueOf(s.charAt(end));
			String b = String.valueOf(s.charAt(end-1));
			answer = b.concat(a);
		    
		} else if(end%2!=0){
			end = end/2;
		    answer = String.valueOf(s.charAt(end));
		}
		return answer;
	    }
}
